package com.examplerm.rmdemo.security;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {
    private final static String ACCESS_TOKEN_SECRET ="Oa02BR4NfPwD2oqlpkg1LVt4gnvPTwGdGSJcz2Nzcm8";
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS= 2_592_000L;

    public static String createToken(String name, String email) {

        Long experationTime= ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;

        Date expeationDate= new Date(System.currentTimeMillis()+experationTime);

        Map<String, Object> extra = new HashMap<>();

        extra.put("name", name);

        return Jwts.builder()
            .setSubject(email)
            .setExpiration(expeationDate)
            .addClaims(extra)
            .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
            .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
       try {
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                .build()
                .parseClaimsJwt(token)
                .getBody();

            String email = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException e) {
            return null;
        }
    }
}
