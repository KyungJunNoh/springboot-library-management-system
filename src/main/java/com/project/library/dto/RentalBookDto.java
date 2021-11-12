package com.project.library.dto;

import com.project.library.model.Book;
import com.project.library.model.Member;
import com.project.library.model.Rental;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalBookDto {

    private Long bookIdx;

    public Rental toEntity(Member member,Book book){
        return Rental.builder()
                .member(member)
                .book(book)
                .rental_date(LocalDate.now())
                .return_date(LocalDate.of(2021,11,19))
                .build();
    }
}
