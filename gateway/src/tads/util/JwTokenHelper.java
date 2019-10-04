package tads.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;
import javax.validation.constraints.NotNull;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwTokenHelper {

    private static JwTokenHelper jwTokenHelper = null;
    private static final long EXPIRATION_LIMIT = 30;
    private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private JwTokenHelper() {

    }

    public static JwTokenHelper getInstance() {
        if (jwTokenHelper == null)
            jwTokenHelper = new JwTokenHelper();
        return jwTokenHelper;
    }

    public String generateToken(String username, String password) {
        return Jwts
                .builder()
                .setSubject(username)
                .setSubject(password)
                .setExpiration(getExpirationDate())
                .signWith(key)
                .compact();
    }

    public void claimKey(String token) throws ExpiredJwtException, MalformedJwtException {
        Jwts
                .parser()
                .setSigningKey(key)
                .parseClaimsJws(token);
    }

    @NotNull
    private Date getExpirationDate() {
        long currentTimeMillis = System.currentTimeMillis();
        long expMilliSeconds = TimeUnit.MINUTES.toMillis(EXPIRATION_LIMIT);
        return new Date(currentTimeMillis + expMilliSeconds);
    }
}

