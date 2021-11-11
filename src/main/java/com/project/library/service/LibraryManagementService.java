package com.project.library.service;

import com.project.library.dto.BookAddDto;
import com.project.library.dto.BookDeleteDto;
import com.project.library.dto.FindBookDto;
import com.project.library.model.Book;

import java.util.List;
import java.util.Map;

public interface LibraryManagementService {
    void bookAdd(BookAddDto bookAddDto);
    Map<Long,Book> findBookAll();
    Map<Long,Book> findBook(FindBookDto findBookDto);
    void delete(BookDeleteDto bookDeleteDto);
}
