package com.project.library.service.impl;

import com.project.library.dto.MemberDto;
import com.project.library.dto.SigninDto;
import com.project.library.model.Member;
import com.project.library.repository.MemberRepository;
import com.project.library.security.jwt.JwtTokenProvider;
import com.project.library.service.MemberService;
import com.project.library.util.CurrentUserUtil;
import com.project.library.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.project.library.security.jwt.JwtTokenProvider.TOKEN_VALIDATION_SECOND;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisUtil redisUtil;
    private final CurrentUserUtil currentUserUtil;

    @Override
    public void signUp(MemberDto memberDto) {
        if(!memberRepository.existsByIdAndCallNum(memberDto.getId(),memberDto.getCallNum())){
            memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword())); // PasswordEncoder 를 활용하여 비밀번호 인코딩
            memberRepository.save(memberDto.toEntity());
        } else {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }

    @Override
    public Map<String,String> signin(SigninDto signinDto) {
        Member findMember = memberRepository.findById(signinDto.getId());
        if(!passwordEncoder.matches(signinDto.getPassword(), findMember.getPassword())) throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        String token = jwtTokenProvider.createToken(signinDto.getId());
        String refreshToken = jwtTokenProvider.createRefreshToken();

        redisUtil.deleteData(signinDto.getId());
        redisUtil.setDataExpire(signinDto.getId(),refreshToken,TOKEN_VALIDATION_SECOND * 24 * 180);

        Map<String,String> response = new HashMap<>();
        response.put("AccessToken", token);
        response.put("RefreshToken", refreshToken);

        return response;
    }

    @Override
    public void logout() {
        redisUtil.deleteData(currentUserUtil.getCurrentUser().getId());
    }
}
