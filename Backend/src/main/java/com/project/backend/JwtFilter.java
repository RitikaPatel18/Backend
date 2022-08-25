//package com.project.backend;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import io.jsonwebtoken.ExpiredJwtException;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//   @Autowired
//   private JwtUserDetailsService userDetailsService;
//   @Autowired
//   private TokenManager tokenManager;
//   @Override
//   protected void doFilterInternal(HttpServletRequest request,
//      HttpServletResponse response, FilterChain filterChain)
//      throws ServletException, IOException {
//      
//      String tokenHeader = request.getHeader("Authorization");
//      String username = null;
//      String token = null;
//      if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
//         token = tokenHeader.substring(7);
//         try {
//            username = tokenManager.getIdFromToken(token);
//         } catch (IllegalArgumentException e) {
//            System.out.println("Unable to get JWT Token");
//         } catch (ExpiredJwtException e) {
//            System.out.println("JWT Token has expired");
//         }
//      } else {
//         System.out.println("Bearer String not found in token");
//      }
//      
//      filterChain.doFilter(request, response);
//   }
//}