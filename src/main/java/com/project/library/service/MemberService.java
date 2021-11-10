package com.project.library.service;

import com.project.library.dto.MemberDto;
import com.project.library.dto.SigninDto;

public interface MemberService {
    void signUp(MemberDto memberDto);
    void signin(SigninDto signinDto);
}
