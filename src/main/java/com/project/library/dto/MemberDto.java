package com.project.library.dto;

import com.project.library.model.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    private String id;
    private String password;
    private String username;
    private String callNum;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .password(password)
                .username(username)
                .callNum(callNum)
                .build();
    }
}
