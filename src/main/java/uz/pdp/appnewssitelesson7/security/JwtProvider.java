package uz.pdp.appnewssitelesson7.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssitelesson7.entity.Role;
import uz.pdp.appnewssitelesson7.entity.enums.Permission;

import java.util.Date;
import java.util.Set;


@Component
public class JwtProvider {

    private static final long expireTime = 60 * 60 * 24000;
    private static final String secretKey = "secretKey";

    public String getUsernameFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }
    public String generateToken(String username, Role role) {
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .claim("roles", role.getName())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }


}
