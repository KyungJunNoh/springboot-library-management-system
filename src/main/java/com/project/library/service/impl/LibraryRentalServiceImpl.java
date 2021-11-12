package com.project.library.service.impl;

import com.project.library.dto.RentalBookDto;
import com.project.library.model.Book;
import com.project.library.model.Member;
import com.project.library.repository.BookRepository;
import com.project.library.repository.RentalRepository;
import com.project.library.service.LibraryRentalService;
import com.project.library.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LibraryRentalServiceImpl implements LibraryRentalService {

    private final BookRepository bookRepository;
    private final RentalRepository rentalRepository;
    private final CurrentUserUtil currentUserUtil;

    @Override
    public void rental(RentalBookDto rentalBookDto) {
        Member currentUser = currentUserUtil.getCurrentUser();

        Book getBook = bookRepository.findById(rentalBookDto.getBookIdx())
                .orElseThrow(() -> new IllegalArgumentException("책을 찾을 수 없습니다."));

        rentalRepository.save(rentalBookDto.toEntity(currentUser,getBook));
    }
}
