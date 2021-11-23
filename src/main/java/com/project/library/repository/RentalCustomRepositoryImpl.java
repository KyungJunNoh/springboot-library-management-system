package com.project.library.repository;

import com.project.library.model.Rental;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.project.library.model.QRental.rental;

@AllArgsConstructor
@Repository
public class RentalCustomRepositoryImpl implements RentalCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Rental> findRentalBookByBookName(String book) {
        return jpaQueryFactory.select(rental)
                .from(rental)
                .where(rental.book.title.eq(book))
                .fetch();
    }
}
