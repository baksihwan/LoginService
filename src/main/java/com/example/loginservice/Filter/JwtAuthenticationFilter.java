package com.example.loginservice.Filter;

import com.example.loginservice.Entity.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public Authentication getAuthentication(String token){
        String username = this.getUsername(token);
        UserDetails userDetails = authService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //토큰 파싱
        String token = jwtTokenService.resolveTokenFromRequest(request.getHeader(TOKEN_HEADER));

        //토큰 문제 유무 체크
        if(StringUtils.hasText(token) && jwtTokenService.validateToken(token)
                && !jwtTokenService.isAccessTokenDenied(token)) {
            //토큰 유효성 검증시 로직
            Authentication auth = jwtTokenService.getAuthentication(token); //Authenticaton 객체
            SecurityContextHolder.getContext().setAuthentication(auth); // SecurityContext에 로드
        } else {
            log.info("토큰 유효성 검증을 실패하였습니다.");
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("회원을 찾을 수 없습니다."));
        return new CustomUserDetails(member);
    }

}
