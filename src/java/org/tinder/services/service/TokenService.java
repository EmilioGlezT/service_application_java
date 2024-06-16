/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.tinder.services.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import java.util.Date;

/**
 *
 * @author emilio
 */
public class TokenService {
    //private static final String SECRET_KEY = "TuClaveSecreta";
    // private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final String SECRET_KEY = "xv5io4PRLWsoaMIhoRctKd4EfOMvPk0YEm9pWz+Lad4mwsGnprcv6R/O1mYlUcQWYCApthWVIZBzcFLlXjYEXKUZJgoUOyTBOivtjmdSUFlgbzzcO2lWDTWxVYsvs3xgOB1yzO9TzBuuGKyNmZnme0dbWhIpdEqnws/e90x6NtGq5KpRGlWNc7DPH8tvuteeXvFggmhw72B2wQpPyPcR6T9M76Fzt0acxcfQfc6irzUcB79KfsdG/FdipuUxKVlpviZa0eIkwtETj//rjhHBzK2/ewi7e+l9rVjrmHriwskXBJt+y+KU035Ze2Gx87+nUhn+NAZZcvQdyKI1K8Z5KGBo3xkvH/u+1e7B91e26p8="; //    US3yctg8RaOYPu5g0XzV7SomVepwUuVj

    public String generateToken(String email) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 12 * 60 * 60 * 1000); // Expira en 12 horas

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
     public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException | SignatureException e) {
            // Token inválido
            return false;
        }
    }
    
}
