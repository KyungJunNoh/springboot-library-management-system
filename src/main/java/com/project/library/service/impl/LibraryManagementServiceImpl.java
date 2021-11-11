package com.project.library.service.impl;

import com.project.library.dto.BookAddDto;
import com.project.library.model.Book;
import com.project.library.repository.BookRepository;
import com.project.library.service.LibraryManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class LibraryManagementServiceImpl implements LibraryManagementService {

    private final BookRepository bookRepository;

    @Override
    public void bookAdd(BookAddDto bookAddDto) {
        bookRepository.save(bookAddDto.toEntity());
    }

    @Override
    public Map<Long,Book> findAll() {
        List<Book> books = bookRepository.findAll();
        if(books.isEmpty()) throw new IllegalArgumentException("도서 목록에 도서가 없습니다.");
        Map<Long,Book> map = new HashMap<>();
        for(Book book : books){
            map.put(book.getIdx(),book);
        }
        return map;
    }
}
