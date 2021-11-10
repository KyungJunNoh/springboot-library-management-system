package com.project.library.dto;

import com.project.library.model.Book;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookAddDto {

    private String title;
    private String author;

    public Book toEntity(){
        return Book.builder()
                .title(title)
                .author(author)
                .date(LocalDate.now()) // 현재 시간 저장
                .build();
    }
}
