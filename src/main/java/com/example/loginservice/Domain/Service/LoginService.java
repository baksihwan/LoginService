package com.example.loginservice.Domain.Service;

import com.example.loginservice.Domain.Dto.LoginResponseDto;
import com.example.loginservice.Domain.Repository.LoginRepository;
import com.example.loginservice.Entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginResponseDto signUp(String username, String password, String nickname){
    Member member = new Member(username, password, nickname);
    Member saveMember = loginRepository.save(member);

    return new LoginResponseDto(saveMember.getUsername(), saveMember.getPassword(), saveMember.getNickname());
    }
}
