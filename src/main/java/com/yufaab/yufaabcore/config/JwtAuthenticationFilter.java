package com.yufaab.yufaabcore.config;

import com.yufaab.yufaabcore.service.UserInfoService;
import com.yufaab.yufaabcore.util.JwtHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  @Autowired
  private JwtHelper jwtHelper;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private UserInfoService userInfoService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {

    String requestHeader = request.getHeader("Authorization");
    String userId = null;
    String token = null;
    if (requestHeader != null && requestHeader.startsWith("JWT")) {
      token = requestHeader.substring(4);
      try {
        userId = this.jwtHelper.extractUserId(token);
      } catch (IllegalArgumentException e) {
        log.info("Illegal Argument while fetching the username !! with error: {}", e.getMessage());
      } catch (ExpiredJwtException e) {
        log.info("Given jwt token is expired !!");
      } catch (MalformedJwtException e) {
        log.info("Some changed has done in token !! Invalid Token with error: {}", e.getMessage());
      } catch (Exception e) {
        log.info("Some error occurred with message : {}", e.getMessage());
      }
    } else {
      log.info("Header present is : {}", requestHeader);
    }

    if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userInfoService.loadUserByUsername(userId);
      if (jwtHelper.validateToken(token, userDetails)) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      } else {
        log.info("Validation fails !!");
      }
    }
    filterChain.doFilter(request, response);
  }
}