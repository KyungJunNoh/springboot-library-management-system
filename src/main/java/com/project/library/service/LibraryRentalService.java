package com.project.library.service;

import com.project.library.dto.RentalBookDto;
import com.project.library.dto.ReturnBookDto;

public interface LibraryRentalService {
    void rental(RentalBookDto rentalBookDto);
    void returnBook(ReturnBookDto returnBookDto);
}
