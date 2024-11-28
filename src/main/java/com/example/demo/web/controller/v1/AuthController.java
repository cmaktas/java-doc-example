package com.example.demo.web.controller.v1;

import com.example.demo.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Controller for managing JWT authentication.
 */
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Authentication API", description = "API for generating and validating JWT tokens.")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    /**
     * Generate a new JWT token.
     *
     * @return the generated token
     */
    @Operation(summary = "Generate JWT Token", description = "Generates a new JWT token for authentication.")
    @GetMapping("/token")
    public ResponseEntity<String> generateToken() {
        String token = jwtUtil.generateToken("dummy-user"); // Dummy user for demo
        return ResponseEntity.ok(token);
    }
}
