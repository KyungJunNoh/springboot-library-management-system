package com.project.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter
@Entity
public class Rental {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 
    @Column(name = "rental_idx")
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "member_idx")
    private Member member;

    @OneToOne
    @JoinColumn(name = "book_idx")
    private Book book;

    @Column(name = "rental_date")
    private LocalDate rental_date;

    @Column(name = "return_date")
    private LocalDate return_date;

    public void update(LocalDate return_date){
        this.return_date = return_date;
    }
}
