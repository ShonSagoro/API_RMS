package com.examplerm.rmdemo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;

public class TokenUtils {
    private final static String ACCESS_TOKEN_SECRET ="Oa02BR4NfPwD2oqlpkg1LVt4gnvPTwGdGSJcz2Nzcm8";
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS= 2_592_000L;

    public static String createToken(String name, String email) {

        Long experationTime= ACCESS_TOKEN_VALIDITY_SECONDS *1000;

        Date expeationDate= new Date(System.currentTimeMillis()+experationTime);

        Map<String, Object> extra = new HashMap<>();

        extra.put("name", name);

        return Jwts.builder()
            .setSubject(email)
            .setExpiration(expeationDate)
            .addClaims(extra)
    }
}
