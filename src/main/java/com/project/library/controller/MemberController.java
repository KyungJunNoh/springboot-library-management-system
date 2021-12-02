package com.project.library.controller;

import com.project.library.dto.UpdatePasswordDto;
import com.project.library.response.Result.CommonResult;
import com.project.library.response.ResponseService;
import com.project.library.dto.MemberDto;
import com.project.library.dto.SigninDto;
import com.project.library.response.Result.SingleResult;
import com.project.library.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/member")
public class MemberController {

    private final MemberService memberService;

    private final ResponseService responseService;

    @PostMapping("/signup")
    @ApiOperation(value = "회원가입")
    public CommonResult signup(@RequestBody MemberDto memberDto){
        memberService.signUp(memberDto);
        return responseService.getSuccessResult();
    }

    @PostMapping("/signin")
    @ApiOperation(value = "로그인")
    public SingleResult<Map<String, String>> signin(@RequestBody SigninDto signinDto){
        return responseService.getSingleResult(memberService.signin(signinDto));
    }

    @PutMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header"),
    })
    @ApiOperation(value = "비밀번호 변경")
    public CommonResult update(@RequestBody UpdatePasswordDto updatePassword){
        memberService.update(updatePassword);
        return responseService.getSuccessResult();
    }

    @DeleteMapping("/logout")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header"),
    })
    @ApiOperation(value = "로그아웃")
    public CommonResult logout(){
        memberService.logout();
        return responseService.getSuccessResult();
    }
}
