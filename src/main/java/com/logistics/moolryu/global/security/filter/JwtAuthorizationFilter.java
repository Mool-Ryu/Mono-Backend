package com.logistics.moolryu.global.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.logistics.moolryu.global.security.user.UserDetailsImpl;
import com.logistics.moolryu.global.security.user.UserDetailsServiceImpl;
import com.logistics.moolryu.global.security.util.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthorizationFilter  extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final UserDetailsServiceImpl userDetailsService;

	public JwtAuthorizationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException
	{
		String tokenValue = jwtUtil.getTokenFromRequest(request);

		if (StringUtils.hasText(tokenValue)) {
			tokenValue = jwtUtil.substringToken(tokenValue);

			if (!jwtUtil.validateToken(tokenValue)) {
				log.error("JWT 토큰 에러");
				filterChain.doFilter(request, response);
				return;
			}

			Claims claims = jwtUtil.getUserInfoFromToken(tokenValue);

			try {
				setAuthentication(claims.getSubject());
			} catch (Exception e) {
				log.error(e.getMessage());
				filterChain.doFilter(request, response);
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	private void setAuthentication(String username) {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		Authentication authentication = createAuthentication(username);
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);
	}

	private Authentication createAuthentication(String username) {
		UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(username);
		return new UsernamePasswordAuthenticationToken(userDetails.getUser(), null, userDetails.getAuthorities());
	}
}
