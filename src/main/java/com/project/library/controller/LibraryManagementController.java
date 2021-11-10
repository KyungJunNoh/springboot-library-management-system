package com.project.library.controller;

import com.project.library.dto.BookAddDto;
import com.project.library.service.LibraryManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/book")
public class LibraryManagementController {

    private final LibraryManagementService libraryManagementService;

    @PostMapping("/add")
    public void bookAdd(@RequestBody BookAddDto bookAddDto){
        libraryManagementService.bookAdd(bookAddDto);
    }
}
