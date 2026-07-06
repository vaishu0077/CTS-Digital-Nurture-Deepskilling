package com.cognizant.springlearn.controller;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@RestController
public class AuthenticationController {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(AuthenticationController.class);
    @GetMapping("/authenticate")
    public Map<String, String> authenticate(
            @RequestHeader("Authorization") String authHeader) {
        LOGGER.info("Start");
        LOGGER.debug("Authorization Header : {}", authHeader);
        String user = getUser(authHeader);
        String token = generateJwt(user);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        LOGGER.info("End");
        return map;
    }
    private String getUser(String authHeader) {
        String encodedCredentials = authHeader.substring("Basic ".length());
        byte[] decodedBytes =
                Base64.getDecoder().decode(encodedCredentials);
        String credentials = new String(decodedBytes);
        LOGGER.debug("Credentials : {}", credentials);
        return credentials.split(":")[0];
    }
    private String generateJwt(String user) {
        Key key = Keys.hmacShaKeyFor(
                "secretkeysecretkeysecretkey12345"
                        .getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(user)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1200000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
