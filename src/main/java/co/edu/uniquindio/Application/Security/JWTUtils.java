package co.edu.uniquindio.Application.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Component
public class JWTUtils {

    public String generateToken(String id, Map<String, String> claims) {
        Instant now = Instant.now();
        return Jwts.builder()
                .claims(claims)
                .subject(id)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(1L, ChronoUnit.HOURS)))
                .signWith(getKey())
                .compact();
    }

    public Jws<Claims> parseJwt(String jwtString) throws ExpiredJwtException,
            UnsupportedJwtException, MalformedJwtException, IllegalArgumentException {
        JwtParser jwtParser = Jwts.parser().verifyWith(getKey()).build();
        return jwtParser.parseSignedClaims(jwtString);
    }

    //TODO: Haga que la clave secreta venga dada desde el archivo application.properties usando @Value("${jwt.secret}") para que no esté hardcodeada en el código. La clave debe tener al menos 32 caracteres.
    private SecretKey getKey(){
        String secretKey = "secretsecretsecretsecretsecretsecretsecretsecret";
        byte[] secretKeyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(secretKeyBytes);
    }
}