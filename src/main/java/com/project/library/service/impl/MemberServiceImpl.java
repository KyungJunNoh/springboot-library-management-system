package com.project.library.service.impl;

import com.project.library.dto.MemberDto;
import com.project.library.dto.SigninDto;
import com.project.library.dto.UpdatePasswordDto;
import com.project.library.exception.member.IdOrPasswordIncorrectException;
import com.project.library.exception.member.NotMatchPasswordException;
import com.project.library.exception.member.UserAlreadyException;
import com.project.library.model.Member;
import com.project.library.repository.MemberRepository;
import com.project.library.security.jwt.JwtTokenProvider;
import com.project.library.service.MemberService;
import com.project.library.util.CurrentUserUtil;
import com.project.library.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


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
            throw new UserAlreadyException();
        }
    }

    @Override
    public Map<String,String> signin(SigninDto signinDto) {
        memberRepository.findById(signinDto.getId())
                .filter(user -> passwordEncoder.matches(signinDto.getPassword(),user.getPassword()))
                .orElseThrow(() -> new IdOrPasswordIncorrectException());

        return createToken(signinDto);
    }

    @Transactional
    @Override
    public void update(UpdatePasswordDto updatePasswordDto) {
        Member user = currentUserUtil.getCurrentUser();
        if(!passwordEncoder.matches(updatePasswordDto.getOldPassword(), user.getPassword())) throw new NotMatchPasswordException();
        String encodingPassword = passwordEncoder.encode(updatePasswordDto.getNewPassword());
        user.updatePassword(encodingPassword);
    }

    @Override
    public void logout() {
        redisUtil.deleteData(currentUserUtil.getCurrentUser().getId());
    }

    // 토큰 만들기
    private Map<String,String> createToken(SigninDto signinDto){
        String token = jwtTokenProvider.createToken(signinDto.getId());
        String refreshToken = jwtTokenProvider.createRefreshToken();

        redisUtil.deleteData(signinDto.getId());
        redisUtil.setDataExpire(signinDto.getId(),refreshToken,TOKEN_VALIDATION_SECOND * 24 * 180);

        Map<String,String> response = new HashMap<>();
        response.put("AccessToken", "Bearer " + token);
        response.put("RefreshToken", "Bearer " + refreshToken);

        return response;
    }
}
