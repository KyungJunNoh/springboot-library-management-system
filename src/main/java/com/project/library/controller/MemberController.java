package com.project.library.controller;

import com.project.library.dto.MemberDto;
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

    @PostMapping("/signup")
    @ApiOperation(value = "회원가입")
    public void signup(@RequestBody MemberDto memberDto){
        memberService.signUp(memberDto);
    }

}
