package com.project.library.service.impl;

import com.project.library.dto.BookAddDto;
import com.project.library.repository.BookRepository;
import com.project.library.service.LibraryManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LibraryManagementServiceImpl implements LibraryManagementService {

    private final BookRepository bookRepository;

    @Override
    public void bookAdd(BookAddDto bookAddDto) {
        bookRepository.save(bookAddDto.toEntity());
    }
}
