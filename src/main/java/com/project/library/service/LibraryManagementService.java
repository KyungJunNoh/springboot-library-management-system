package com.project.library.service;

import com.project.library.dto.BookAddDto;
import com.project.library.dto.BookUpdateDto;
import com.project.library.dto.FindBookDto;
import com.project.library.model.Book;

import java.util.List;
import java.util.Map;

public interface LibraryManagementService {
    void bookAdd(BookAddDto bookAddDto);
    List<Book> findBookAll();
    List<Book> findBook(FindBookDto findBookDto);
    void update(Long idx, BookUpdateDto bookUpdateDto);
    void delete(Long idx);
}
