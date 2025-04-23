package com.example.loginservice.Domain.Service;

import com.example.loginservice.Domain.Dto.LoginResponseDto;
import com.example.loginservice.Domain.Repository.MemberRepository;
import com.example.loginservice.Entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public LoginResponseDto signUp(String username, String password, String nickname){
    Member member = new Member(username, password, nickname);
    Member saveMember = memberRepository.save(member);

    return new LoginResponseDto(saveMember.getUsername(), saveMember.getPassword(), saveMember.getNickname());
    }
}
