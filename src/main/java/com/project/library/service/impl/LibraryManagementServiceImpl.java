package com.project.library.service.impl;

import com.project.library.dto.BookAddDto;
import com.project.library.dto.BookUpdateDto;
import com.project.library.dto.FindBookDto;
import com.project.library.model.Book;
import com.project.library.repository.BookRepository;
import com.project.library.service.LibraryManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Book> findBookAll() {
        List<Book> books = bookRepository.findAll();
        if(books.isEmpty()) throw new IllegalArgumentException("도서 목록에 도서가 없습니다.");
        return books;
    }

    @Override
    public Map<Long, Book> findBook(FindBookDto findBookDto) {
        List<Book> findBook = bookRepository.findBookByTitle(findBookDto.getTitle());
        if(findBook.isEmpty()) throw new IllegalArgumentException(findBookDto.getTitle() + " 도서를 찾을 수 없습니다.");
        Map<Long,Book> map = new HashMap<>();
        map.put(findBook.get(0).getIdx(),findBook.get(0));
        return map;
    }

    @Transactional
    @Override
    public void update(Long idx, BookUpdateDto bookUpdateDto) {
        Book book = bookRepository.findById(idx)
                .orElseThrow(()-> new IllegalArgumentException("책이 존재하지 않습니다."));

        book.updateBook(bookUpdateDto.getTitle(),bookUpdateDto.getAuthor());
    }

    @Override
    public void delete(Long idx) {
        if (bookRepository.findById(idx).isEmpty()) throw new IllegalArgumentException("해당 번호의 도서를 찾을 수 없습니다.");
        bookRepository.deleteById(idx);
    }
}
