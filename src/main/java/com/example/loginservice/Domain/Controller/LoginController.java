package com.example.loginservice.Domain.Controller;

import com.example.loginservice.Domain.Dto.LoginResponseDto;
import com.example.loginservice.Domain.Dto.SignUpRequestDto;
import com.example.loginservice.Domain.Service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("0.0.0.0:8080")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/signup")
    public ResponseEntity<LoginResponseDto> signup(@RequestBody SignUpRequestDto requestDto){
        LoginResponseDto loginResponseDto = loginService.signUp(requestDto.getUsername(),
                                                                requestDto.getPassword(),
                                                                requestDto.getNickname());
        return ResponseEntity.ok(loginResponseDto);
    }
}
