package com.example.loginservice.Filter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
// JWT를 처리하는 핵심 로직을 담당하는 Service 클래스
// 1. JWT 토큰의 생성, 검증, 정보 추출 담당
public class JwtTokenService {

    private final JwtUtil jwtUtil;
    private final UserDetailsService authService;
    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    // 토큰 추출
    public String resolveTokenFromRequest(String header){
        if(header != null && header.startsWith(TOKEN_PREFIX)){
            return header.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateToken(String token){
        return token != null && token.startsWith(TOKEN_PREFIX);
    }

    public boolean isAccessTokenDenied(String token){
        return token != null && token.startsWith(TOKEN_HEADER);
    }

    public Authentication getAuthentication(String token){
        String username = jwtUtil.getUsername(token);
        UserDetails userDetails = authService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }
}
