package com.example.loginservice.Domain.Controller;

import com.example.loginservice.Domain.Dto.LoginResponseDto;
import com.example.loginservice.Domain.Dto.SignUpRequestDto;
import com.example.loginservice.Domain.Repository.MemberRepository;
import com.example.loginservice.Domain.Service.MemberService;
import com.example.loginservice.Entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("0.0.0.0:8080")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/signup")
    public ResponseEntity<LoginResponseDto> signup(@RequestBody SignUpRequestDto requestDto) {
        LoginResponseDto loginResponseDto = memberService.signUp(requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.getNickname());
        return ResponseEntity.ok(loginResponseDto);
    }

    @GetMapping("/login")
    public ResponseEntity<Member> login(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Member member = memberRepository.findByUsername(username);
        return ResponseEntity.ok(member);
    }
}