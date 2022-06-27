package Zelfscanner.Authentication;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.ws.rs.core.SecurityContext;
import java.security.Key;
import java.security.Principal;
import java.sql.Date;

public class MySecurityContext implements SecurityContext {
    final static public Key key = MacProvider.generateKey();

    public static String generateToken(String user) {
        String token = Jwts.builder()
                .setSubject(user)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        return token;
    }

    public static MyPrincipal validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(key).parseClaimsJws(token);

            return new MyPrincipal(claims.getBody().getSubject());
        } catch (Exception e) {
            return null;
        }
    }

    private final MyPrincipal principal;

    public MySecurityContext(MyPrincipal principal) {
        this.principal = principal;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.principal;
    }

    @Override
    public boolean isUserInRole(String s) {
        if (this.principal.getName().equals("BramIsIngelogd") && s.equals("gebruiker")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return "goeiemorgen";
    }
}
