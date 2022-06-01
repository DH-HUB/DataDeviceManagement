package com.example.DataDeviceManagement.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtUtils {

    @Value("${secret}")
    private String secret;

    public Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserDetailsDemo userDetailsDemo) {

        //UserDetailsDemo userDetailsDemo = (UserDetailsDemo) userDetails;

        Map<String, Object> data = new HashMap<>();// on va lui dire de stocker une Map donc une donnée
        data.put("id", userDetailsDemo.getUtilisateur().getId());
        data.put("email", userDetailsDemo.getUtilisateur().getEmail());

        String listeDroit = userDetailsDemo
                .getAuthorities()
                .stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.joining(","));

        data.put("droits", listeDroit);
        Calendar dateAujourdhui = Calendar.getInstance();
        long dateAujourdhuiEnMilliseconde = dateAujourdhui.getTimeInMillis();

        data.put("numeroToken", userDetailsDemo.getUtilisateur().getNumeroToken());
        Date dateExpiration = new Date(dateAujourdhuiEnMilliseconde + 10000/*(4*660*1000*/);

        return Jwts.builder()
                .setClaims(data)
               // .setExpiration(DateExpiration) // Veuillez decommanter la ligne pour activer l'expiration
                .setSubject(userDetailsDemo.getUsername())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    public boolean tokenValide(String token, UserDetailsDemo userDetails) {

        Claims claims = getTokenBody(token);

        boolean utilisateurValide = claims.getSubject().equals(userDetails.getUsername());
        boolean numeroTokenValide = claims
                .get("numeroToken")
                .equals(userDetails.getUtilisateur().getNumeroToken());


        return utilisateurValide && numeroTokenValide;
    }
}
