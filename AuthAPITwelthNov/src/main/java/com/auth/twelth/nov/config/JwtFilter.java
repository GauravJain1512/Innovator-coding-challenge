package com.auth.twelth.nov.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private CustomUserDeatilsService customUserDeatilsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String header = request.getHeader("Authorization");
		if(StringUtils.hasText(header) && header.startsWith("Bearer ")) {
			String token = header.substring(7);
			try {
				String username = jwtTokenHelper.extractUsername(token);
				if(StringUtils.hasText(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = customUserDeatilsService.loadUserByUsername(username);
					if(jwtTokenHelper.validateToken(token, userDetails)) {
						if(isUserRoleAuthorized(token,userDetails)) {
							UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
							usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
							SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
						}else {
							logger.error("User does not have specified role");
						}
					}else {
						logger.error("Invalid token");
					}
				}else {
					logger.error("Username is invalid");
				}
			}catch (IllegalArgumentException ex) {
				logger.error(ex);
			}catch (ExpiredJwtException ex) {
				logger.error(ex);
			}catch (SignatureException ex) {
				logger.error(ex);
			}catch (MalformedJwtException ex) {
				logger.error(ex);
			}catch (UnsupportedJwtException ex) {
				logger.error(ex);
			}
		}
		filterChain.doFilter(request, response);
		
	}

	@SuppressWarnings("unchecked")
	private boolean isUserRoleAuthorized(String token, UserDetails userDetails) {
		
		List<String> roles = jwtTokenHelper.extractClaim(token, claims -> claims.get("roles", List.class));
		
		String userRole = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse(null);
		
		return roles.contains(userRole);
	}

}
