package com.project.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.library.dto.MemberDto;
import com.project.library.model.Member;
import com.project.library.response.ResponseService;
import com.project.library.response.Result.CommonResult;
import com.project.library.security.jwt.JwtTokenProvider;
import com.project.library.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean MemberService memberService; // MemberController 에서 사용되고 있는 memberService 를 @MockBean 을 통해 주입받음 ( 가짜 빈 주입 )
    @MockBean ResponseService responseService;
    @MockBean JwtTokenProvider jwtTokenProvider;

    final MemberDto memberDto = MemberDto.builder()
            .id("ngj")
            .password("1234")
            .username("노경준")
            .callNum("01012341234")
            .build();

    @Test
    void signupTest() throws Exception{
        String content = objectMapper.writeValueAsString(memberDto);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/member/signup")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
