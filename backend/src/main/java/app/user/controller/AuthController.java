// package ma.seknna.backend.auth.controller;

// import jakarta.validation.Valid;
// import lombok.RequiredArgsConstructor;
// import ma.seknna.backend.auth.dto.SignupRequest;
// import ma.seknna.backend.auth.service.AuthService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/api/")
// @RequiredArgsConstructor
// public class AuthController {

//     private final AuthService authService;

//     @PostMapping("/signup")
//     public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest request) {
//         authService.signup(request);
//         return ResponseEntity.status(201).body("Account created successfully.");
//     }
// }