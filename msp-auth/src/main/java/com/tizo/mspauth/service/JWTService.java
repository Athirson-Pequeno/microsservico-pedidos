package com.tizo.mspauth.service;

import com.tizo.mspauth.entity.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Component
public class JWTService {

    @Value("${api.key}")
    public String SECRET;


    public void validateToken(final String token){
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    public String generateToken(User user){
        Map<String, Object> claims = new HashMap<>();

        List<String> rolesClaims = new ArrayList<>();
        user.getRole().stream().forEach(role -> rolesClaims.add(role.getRoleName()));

        claims.put("roles", rolesClaims);
        return createToken(claims, user.getName());
    }

    private String createToken(Map<String, Object> claims, String username) {

        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(username)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(generaterExpirationDate())
                    .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
        }catch (JwtException exception){
            throw new RuntimeException("Error while generating token " + exception);
        }

    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Date generaterExpirationDate() {

        Date date = Date.from( LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")));
        return date;
    }
}

