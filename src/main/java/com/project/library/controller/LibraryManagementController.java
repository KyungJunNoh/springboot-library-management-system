package com.project.library.controller;

import com.project.library.dto.BookAddDto;
import com.project.library.model.Book;
import com.project.library.service.LibraryManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/book")
public class LibraryManagementController {

    private final LibraryManagementService libraryManagementService;

    @PostMapping("/add")
    public void bookAdd(@RequestBody BookAddDto bookAddDto){
        libraryManagementService.bookAdd(bookAddDto);
    }

    @GetMapping("/findall")
    public Map<Long, Book> findBookAll(){
        return libraryManagementService.findAll();
    }
}
