package com.project.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter
@Entity
public class Book {

    @Id @GeneratedValue
    @Column(name = "book_idx")
    private Long idx;

    @Column(name = "book_title", nullable = false)
    private String title;

    @Column(name = "book_author", nullable = false)
    private String author;

    @Column(name = "registration_date", nullable = false)
    private LocalDate date;

    public void updateBook(String title, String author){
        this.title = title;
        this.author = author;
    }
}
