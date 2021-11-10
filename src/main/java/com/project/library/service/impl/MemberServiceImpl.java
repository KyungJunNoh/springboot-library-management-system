package com.project.library.service.impl;

import com.project.library.dto.MemberDto;
import com.project.library.repository.MemberRepository;
import com.project.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(MemberDto memberDto) {
        if(!memberRepository.existsByIdAndCallNum(memberDto.getId(),memberDto.getCallNum())){
            memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword())); // PasswordEncoder 를 활용하여 비밀번호 인코딩
            memberRepository.save(memberDto.toEntity());
        } else {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }

}
