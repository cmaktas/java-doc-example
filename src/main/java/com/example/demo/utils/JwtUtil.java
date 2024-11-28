package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import static com.example.demo.domain.Constants.SECRET_KEY;
import static com.example.demo.domain.Constants.VALID_TOKENS;

/**
 * Utility class for generating and validating JWT tokens.
 * <p>
 * This class provides methods to generate JWT tokens, validate their authenticity,
 * and extract claims from tokens. It uses HMAC-SHA256 algorithm for signing tokens
 * and a shared secret key defined in the application's constants.
 * </p>
 * <p>
 * Tokens are also tracked in an in-memory list to manage their validity state.
 * </p>
 */
@Component
public class JwtUtil {

    /**
     * The cryptographic key used for signing JWT tokens.
     */
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     * Generates a JWT token for the given username.
     * <p>
     * The token includes the username as the subject, a role claim with "ROLE_USER",
     * an issued timestamp, and an expiration time of 30 minutes from generation.
     * </p>
     * The generated token is also added to the in-memory {@code VALID_TOKENS} list.
     *
     * @param username the username to be included in the token's subject claim.
     * @return the generated JWT token as a {@code String}.
     */
    public String generateToken(String username) {
        String token = Jwts.builder()
                .setSubject(username)
                .claim("roles", "ROLE_USER") // Add user roles to token claims
                .setIssuedAt(new Date()) // Set the issued timestamp
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // Set expiration to 30 minutes
                .signWith(key, SignatureAlgorithm.HS256) // Sign token with HMAC-SHA256
                .compact();

        VALID_TOKENS.add(token); // Track token in the in-memory list
        return token;
    }

    /**
     * Validates a JWT token.
     * <p>
     * This method checks whether the token is included in the {@code VALID_TOKENS} list
     * and verifies its signature and expiration. If the token is valid, it returns {@code true};
     * otherwise, it returns {@code false}.
     * </p>
     *
     * @param token the JWT token to validate.
     * @return {@code true} if the token is valid; {@code false} otherwise.
     */
    public boolean isTokenValid(String token) {
        try {
            if (!VALID_TOKENS.contains(token)) {
                return false; // Token is not in the valid tokens list
            }
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token); // Verify token
            return true;
        } catch (Exception e) {
            return false; // Any exception indicates invalid token
        }
    }

    /**
     * Extracts all claims from a JWT token.
     * <p>
     * This method parses the token and retrieves all claims included in its payload.
     * Claims can contain information such as the subject (username), roles, issued timestamp, and expiration time.
     * </p>
     *
     * @param token the JWT token from which to extract claims.
     * @return a {@code Claims} object containing the claims from the token.
     */
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) // Use the signing key to parse the token
                .build()
                .parseClaimsJws(token)
                .getBody(); // Extract claims from token body
    }
}
