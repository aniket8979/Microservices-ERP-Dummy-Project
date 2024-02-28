package com.school.LoginService.Service;


import com.school.LoginService.Repo.AdminRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;


@Service
public class JwtService {


    @Autowired
    private AdminRepo superAdminRepo;


    private static final String secret = "0ru239ry28fh2bf82f382382098302jf9nc20290fueoijvoe409nw439h384gun39ng398h39jg394j3mc3j9fj3";


    public void validateToken(final  String token){
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody().getSubject();
    }




    public String generateToken(String franchiseId, String emailId, String roleType, String uniqueId){
        Claims claims = Jwts.claims().setSubject(franchiseId);
        claims.put("uniqueId", uniqueId);
        claims.put("email",emailId);
        claims.put("roleType",roleType);
        return createToken(claims);
    }



    private String createToken(Map<String, Object> claims){
        Instant issued = Instant.now();
        Instant expiry = issued.plusSeconds(43200);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(issued))
                .setExpiration(Date.from(expiry))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
