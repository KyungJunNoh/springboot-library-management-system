package com.project.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_idx")
    private Long idx;

    @Column(name = "member_id", nullable = false, unique = true)
    private String id;

    @Column(name = "member_password", nullable = false)
    private String password;

    @Column(name = "member_username", nullable = false)
    private String username;

    @Column(name = "member_callNum", nullable = false, unique = true)
    private String callNum;
}
