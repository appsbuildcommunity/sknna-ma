package app.core.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import app.core.config.JwtService;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class RateLimitFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    // per IP — for /login brute force protection
    private final Map<String, Bucket> loginBuckets = new ConcurrentHashMap<>();

    // per user email — for authenticated endpoints
    private final Map<String, Bucket> userBuckets = new ConcurrentHashMap<>();

    // 5 login attempts per minute per IP
    private Bucket newLoginBucket() {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(2, Refill.greedy(2, Duration.ofMinutes(1))))
                .build();
    }

    // 60 requests per minute per authenticated user
    private Bucket newUserBucket() {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(55, Refill.greedy(55, Duration.ofMinutes(1))))
                .build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain)
            throws ServletException, IOException {

        String uri = request.getRequestURI();

        // ── 1. Rate limit /login by IP ────────────────────────────────────
        if (uri.equals("/login")) {
            String ip = request.getRemoteAddr();
            Bucket bucket = loginBuckets.computeIfAbsent(ip, k -> newLoginBucket());
            if (!bucket.tryConsume(1)) {
                sendError(response, "Too many login attempts. Try again in 1 minute.");
                return;
            }
        }

        // ── 2. Rate limit authenticated endpoints by user email ───────────
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtService.isTokenValid(token)) {
                String email = jwtService.extractEmail(token);
                Bucket bucket = userBuckets.computeIfAbsent(email, k -> newUserBucket());
                if (!bucket.tryConsume(1)) {
                    sendError(response, "Too many requests. Slow down.");
                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }

    private void sendError(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }
}