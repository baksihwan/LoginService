package com.example.loginservice.Filter;

import com.example.loginservice.Domain.Repository.MemberRepository;
import com.example.loginservice.Entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//Spring Security 인증 처리 과정 중 , 사용자 정보를 불러오는 핵심 로직
public class AuthService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("회원을 찾을 수 없습니다."));
        return new CustomUserDetails(member);
    }
}
