package com.project.library.controller;

import com.project.library.dto.BookAddDto;
import com.project.library.dto.BookDeleteDto;
import com.project.library.dto.FindBookDto;
import com.project.library.model.Book;
import com.project.library.service.LibraryManagementService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/book")
public class LibraryManagementController {

    private final LibraryManagementService libraryManagementService;

    @ApiOperation("도서 목록에 책 추가")
    @PostMapping("/add")
    public void bookAdd(@RequestBody BookAddDto bookAddDto){
        libraryManagementService.bookAdd(bookAddDto);
    }

    @ApiOperation("도서 목록에 책 전체 조회")
    @GetMapping("/findall")
    public Map<Long,Book> findBookAll(){
        return libraryManagementService.findBookAll();
    }

    @ApiOperation("도서 목록에 책 단일 조회")
    @PostMapping("/find")
    public Map<Long,Book> findBook(@RequestBody FindBookDto findBookDto){
        return libraryManagementService.findBook(findBookDto);
    }

    @ApiOperation("책 삭제")
    @PostMapping("/delete")
    public void delete(@RequestBody BookDeleteDto bookDeleteDto){
        libraryManagementService.delete(bookDeleteDto);
    }

}
