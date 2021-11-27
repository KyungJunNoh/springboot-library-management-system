package com.project.library.util;

import com.project.library.model.Member;
import com.project.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUserUtil {

    private final MemberRepository memberRepository;

    public static String getCurrentUserId(){
        String id = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            id = ((UserDetails) principal).getUsername();
        } else{
            id = principal.toString();
        }
        return id;
    }

    public Member getCurrentUser() {
        String id = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            id = ((UserDetails) principal).getUsername();
        } else{
            id = principal.toString();
        }
        return memberRepository.findById(id).get();
    }
}
