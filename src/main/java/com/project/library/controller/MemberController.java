package com.project.library.controller;

import com.project.library.Response.CommonResult;
import com.project.library.Response.ResponseService;
import com.project.library.dto.MemberDto;
import com.project.library.dto.SigninDto;
import com.project.library.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class MemberController {

    private final MemberService memberService;

    private final ResponseService responseService;

    @PostMapping("/signup")
    @ApiOperation(value = "회원가입")
    public CommonResult signup(@RequestBody MemberDto memberDto){
        memberService.signUp(memberDto);
        return responseService.commonResult();
    }

    @PostMapping("/signin")
    @ApiOperation(value = "로그인")
    public CommonResult signin(@RequestBody SigninDto signinDto){
        memberService.signin(signinDto);
        return responseService.commonResult();
    }

}
