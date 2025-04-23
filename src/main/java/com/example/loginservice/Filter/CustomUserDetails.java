package com.example.loginservice.Filter;

import com.example.loginservice.Entity.Member;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
    private final Member member;
}
