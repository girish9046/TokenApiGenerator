package com.example.token.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.token.jwt.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTUtility implements Serializable {

    private static final long serialVersionUID = 234234523523L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 ;

    @Value("${jwt.secret}")
    private String secretKey;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


    //for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }


    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    //generate token for user
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        System.out.println("..........generateToken........."+user.getUserName());
        return doGenerateToken(claims, user.getUserName());
    }


    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    private String doGenerateToken(Map<String, Object> claims, String subject) {
    	String ret=null;
    	try {
    		System.out.println("..........secretKey........."+secretKey);
    		String jwtToken="";
    	    jwtToken = Jwts.builder().setClaims(claims).setSubject(subject)
    	    		.setIssuedAt(new Date(System.currentTimeMillis()))
    	    		.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
    	    		.signWith(SignatureAlgorithm.HS256, secretKey).compact();
    	    Map<String, String> jwtTokenGen = new HashMap<>();
    	    jwtTokenGen.put("token", jwtToken);
    	    //jwtTokenGen.put("message", message);
    	    return jwtToken;
//    		ret = Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                    .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//                    .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
        return ret;
    }


    //validate token
    public boolean isValidateToken(String token, User user) {
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getUserName()) && !isTokenExpired(token));
    }
}
