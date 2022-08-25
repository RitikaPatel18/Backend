package com.project.backend;

import java.io.Serializable; 
import java.util.Date; 
import java.util.HashMap; 
import java.util.Map; 
import org.springframework.beans.factory.annotation.Value;  
import org.springframework.stereotype.Component; 
import io.jsonwebtoken.Claims; 
import io.jsonwebtoken.Jwts; 
import io.jsonwebtoken.SignatureAlgorithm; 


@Component 
public class authUtils implements Serializable {
	private static final long serialVersionUID = 7008375124389347049L;
	public static final long TOKEN_VALIDITY = 10*60*60; @Value("{secret}") 
	
	   private String jwtSecret; 
	
	   public String generateJwtToken(User user) { 
	      Map<String, Object> claims = new HashMap<>(); 
	      return Jwts.builder().setClaims(claims).setSubject(String.valueOf(user.getId()))
	         .setIssuedAt(new Date(System.currentTimeMillis())) 
	         .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000)) 
	         .signWith(SignatureAlgorithm.HS512, jwtSecret).compact(); 
	   } 
	   public Boolean validateJwtToken(String token) { 
	      String id1 = getIdFromToken(token); 
	      int id=Integer.parseInt(id1);
	      Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	      Boolean isTokenExpired = claims.getExpiration().before(new Date()); 
//	      return ((id==user.getId()) && !isTokenExpired); 
	      return ( !isTokenExpired); 
	   } 
	   public String getIdFromToken(String token) {
	      final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody(); 
	      return claims.getSubject(); 
	   } 
	
}
