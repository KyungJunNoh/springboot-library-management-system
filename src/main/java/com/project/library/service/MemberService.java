package com.project.library.service;

import com.project.library.dto.MemberDto;
import com.project.library.dto.SigninDto;
import com.project.library.dto.UpdatePasswordDto;

import java.util.Map;

public interface MemberService {
    void signUp(MemberDto memberDto);
    Map<String,String> signin(SigninDto signinDto);
    void update(UpdatePasswordDto updatePasswordDto);
    void logout();
}
