package com.project.library.service;

import com.project.library.dto.FindRentalBookDto;
import com.project.library.dto.RentalBookDto;
import com.project.library.dto.ReturnBookDto;
import com.project.library.model.Rental;

import java.util.Map;

public interface LibraryRentalService {
    void rental(RentalBookDto rentalBookDto);
    void returnBook(ReturnBookDto returnBookDto);
    Map<Long, Rental> findAllRentalBook();
    Map<Long, Rental> findRentalBook(Long idx);

}
