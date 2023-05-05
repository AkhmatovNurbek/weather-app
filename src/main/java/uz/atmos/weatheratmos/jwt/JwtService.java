package uz.atmos.weatheratmos.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service
public class JwtService {
    private static final  String SECRET_KEY = "7134743777217A25432A462D4A614E645267556B58703272357538782F413F44";

    public String extractUserEmail(String token) {
        return extractClaim(token,Claims::getSubject);
    }
    public <T> T extractClaim (String token , Function<Claims, T> claimsSolver){
        final Claims claims = extractClaims(token);
        return claimsSolver.apply(claims);
    }
    public String generateToken (UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }
    public String generateToken(
            Map<String, Object> extractClaims,
            UserDetails userDetails
    ){
        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isValidToken(String token , UserDetails userDetails){
        final String userEmail = extractUserEmail(token);
        return (userEmail.equals(userDetails.getUsername()))&& !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token , Claims::getExpiration);
    }

    private Claims extractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
