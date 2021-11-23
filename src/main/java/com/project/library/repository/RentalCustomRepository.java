package com.project.library.repository;

import com.project.library.model.Rental;

import java.util.List;

public interface RentalCustomRepository {
    List<Rental> findRentalBookByBookName(String book);
}
