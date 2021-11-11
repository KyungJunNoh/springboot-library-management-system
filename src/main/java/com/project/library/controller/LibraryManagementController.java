package com.project.library.controller;

import com.project.library.Response.CommonResult;
import com.project.library.Response.ResponseService;
import com.project.library.dto.BookAddDto;
import com.project.library.dto.BookUpdateDto;
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
    private final ResponseService responseService;

    @ApiOperation("도서 목록에 책 추가")
    @PostMapping("/add")
    public CommonResult bookAdd(@RequestBody BookAddDto bookAddDto){
        libraryManagementService.bookAdd(bookAddDto);
        return responseService.commonResult();
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

    @ApiOperation("책 정보 수정")
    @PutMapping("/update/{idx}")
    public CommonResult update(@PathVariable("idx") Long idx, @RequestBody BookUpdateDto bookUpdateDto){
        libraryManagementService.update(idx,bookUpdateDto);
        return responseService.commonResult();
    }

    @ApiOperation("책 삭제")
    @DeleteMapping("/delete/{idx}")
    public CommonResult delete(@PathVariable("idx") Long idx){
        libraryManagementService.delete(idx);
        return responseService.commonResult();
    }
}
