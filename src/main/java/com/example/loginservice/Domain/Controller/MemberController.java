package com.example.loginservice.Domain.Controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.loginservice.Domain.Dto.LoginResponseDto;
import com.example.loginservice.Domain.Dto.SignUpRequestDto;
import com.example.loginservice.Domain.Repository.MemberRepository;
import com.example.loginservice.Domain.Service.MemberService;
import com.example.loginservice.Entity.Member;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("0.0.0.0:8080")
@Tag(name = "회원 API", description = "회원 관련 API입니다.")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/signup")
    @SecurityRequirement(name = "Authorization")
    @Operation(summary = "회원가입", description = "회원가입을 처리합니다.")
    public ResponseEntity<LoginResponseDto> signup(@RequestBody SignUpRequestDto requestDto) {
        LoginResponseDto loginResponseDto = memberService.signUp(requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.getNickname());
        return ResponseEntity.ok(loginResponseDto);
    }

    @GetMapping("/login")
    @SecurityRequirement(name = "Authorization") //해당 API에 인증 적용
    @Operation(summary = "로그인", description = "JWT 인증된 사용자의 정보를 반환합니다.")
    public ResponseEntity<Member> login(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Member member = memberRepository.findByUsername(username);
        return ResponseEntity.ok(member);
    }
}