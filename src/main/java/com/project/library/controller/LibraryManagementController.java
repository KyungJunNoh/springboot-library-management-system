package com.project.library.controller;

import com.project.library.response.Result.CommonResult;
import com.project.library.response.Result.ListResult;
import com.project.library.response.ResponseService;
import com.project.library.dto.BookAddDto;
import com.project.library.dto.BookUpdateDto;
import com.project.library.dto.FindBookDto;
import com.project.library.model.Book;
import com.project.library.response.Result.SingleResult;
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

    @PostMapping("/add")
    @ApiOperation("도서 목록에 책 추가")
    public CommonResult bookAdd(@RequestBody BookAddDto bookAddDto){
        libraryManagementService.bookAdd(bookAddDto);
        return responseService.getSuccessResult();
    }

    @GetMapping("/findall")
    @ApiOperation("도서 목록에 책 전체 조회")
    public ListResult<Book> findBookAll(){
        return responseService.getListResult(libraryManagementService.findBookAll());
    }


    @PostMapping("/find")
    @ApiOperation("도서 목록에서 도서 제목으로 도서 조회")
    public SingleResult findBookByTitle(@RequestBody FindBookDto findBookDto){
        return responseService.getSingleResult(libraryManagementService.findBookByTitle(findBookDto));
    }

    @PutMapping("/update/{idx}")
    @ApiOperation("도서 정보 수정")
    public CommonResult update(@PathVariable("idx") Long idx, @RequestBody BookUpdateDto bookUpdateDto){
        libraryManagementService.update(idx,bookUpdateDto);
        return responseService.getSuccessResult();
    }

    @DeleteMapping("/delete/{idx}")
    @ApiOperation("도서 삭제")
    public CommonResult delete(@PathVariable("idx") Long idx){
        libraryManagementService.delete(idx);
        return responseService.getSuccessResult();
    }
}
