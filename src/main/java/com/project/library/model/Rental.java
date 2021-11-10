package com.project.library.model;

import javax.persistence.*;

@Entity
public class Rental {

    @Id @GeneratedValue
    @Column(name = "rental_idx")
    private String idx;

    @ManyToOne
    @JoinColumn(name = "member_idx")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_idx")
    private Book book;

}
