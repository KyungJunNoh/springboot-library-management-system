package com.project.library.service;

import com.project.library.dto.MemberDto;
import com.project.library.dto.SigninDto;

import java.util.Map;

public interface MemberService {
    void signUp(MemberDto memberDto);
    Map<String,String> signin(SigninDto signinDto);
    void logout();
}
