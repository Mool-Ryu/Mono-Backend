package com.logistics.moolryu.global.security.util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.logistics.moolryu.global.security.user.UserRoleEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtil {

	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String AUTHORIZATION_KEY = "auth";
	public static final String BEARER_PREFIX = "Bearer ";
	private final long TOKEN_TIME = 12 * 60 * 60 * 1000L; // 12시간

	@Value("${jwt.secret.key}")
	private String secretKey;
	private Key key;

	@PostConstruct
	public void init() {
		byte[] bytes = Base64.getDecoder().decode(secretKey);
		key = Keys.hmacShaKeyFor(bytes);
	}

	public String createToken(String username) {
		Date date = new Date();

		return BEARER_PREFIX +
			Jwts.builder()
				.subject(username)
				.claim(AUTHORIZATION_KEY, UserRoleEnum.USER)
				.expiration(new Date(date.getTime() + TOKEN_TIME))
				.issuedAt(date)
				.signWith(key)
				.compact();
	}

	public String createAdminToken(String username, UserRoleEnum role) {
		Date date = new Date();

		return BEARER_PREFIX +
			Jwts.builder()
				.subject(username)
				.claim(AUTHORIZATION_KEY, role)
				.expiration(new Date(date.getTime() + TOKEN_TIME))
				.issuedAt(date)
				.signWith(key)
				.compact();
	}

	public void addJwtToCookie(String token, HttpServletResponse response) {
		token = URLEncoder.encode(token, StandardCharsets.UTF_8).replaceAll("\\+", "%20");

		Cookie cookie = new Cookie(AUTHORIZATION_HEADER, token);
		cookie.setPath("/");

		response.addCookie(cookie);
		response.setHeader("ACCESS_TOKEN", token);
	}

	public String substringToken(String tokenValue) {
		if (StringUtils.hasText(tokenValue) && tokenValue.startsWith(BEARER_PREFIX)) {
			return tokenValue.substring(7);
		}
		log.error("Not Found Token");
		throw new NullPointerException("Not Found Token");
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().verifyWith((SecretKey)key).build().parseSignedClaims(token);
		} catch (SecurityException | MalformedJwtException e) {
			log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
		} catch (ExpiredJwtException e) {
			log.error("Expired JWT token, 만료된 JWT token 입니다.");
		} catch (UnsupportedJwtException e) {
			log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
		} catch (IllegalArgumentException e) {
			log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
		}
		return false;
	}

	public Claims getUserInfoFromToken(String token) {
		return Jwts.parser().verifyWith((SecretKey)key).build().parseSignedClaims(token).getPayload();
	}

	public String getTokenFromRequest(HttpServletRequest request) {
		return request.getHeader("Authorization");
	}
}
