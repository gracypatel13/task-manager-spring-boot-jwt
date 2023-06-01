package com.taskmanager.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(userName, claims);
	}
	private String createToken(String userName, Map<String, Object> claims) {
		// TODO Auto-generated method stub
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
				.signWith(getSignKey(),SignatureAlgorithm.HS256).compact()
				;
	}

	private Key getSignKey() {
		// TODO Auto-generated method stub
		byte[] keyBytes=Decoders.BASE64.decode("655468576D5A7134743777217A25432A462D4A404E635266556A586E32723575");
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				;
	}

	public <T> T extractClaims(String token,Function<Claims, T> claimsResolver) {
		Claims claims=extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public String extractUserName(String token) {
		return extractClaims(token,Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaims(token,Claims::getExpiration);
	}
	
	private Boolean isTokenExpired(String Token) {
		return extractExpiration(Token).before(new Date());
	}
	
	public Boolean validateToken(String token,UserDetails userDetails) {
		String username=extractUserName(token);
		System.out.println(userDetails.getUsername());
	
		 return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	

	
	

}
