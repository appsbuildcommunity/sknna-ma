package app.common.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        return "Welcome to the STRIDE project! `" 
                + request.getSession().getId() + "`";
    }
}