package com.project.library.controller;

import com.project.library.response.CommonResult;
import com.project.library.response.ResponseService;
import com.project.library.dto.ExtensionBook;
import com.project.library.dto.RentalBookDto;
import com.project.library.dto.ReturnBookDto;
import com.project.library.model.Rental;
import com.project.library.service.LibraryRentalService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/rental")
public class LibraryRentalController {

    private final LibraryRentalService libraryRentalService;
    private final ResponseService responseService;

    @PostMapping("/")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header"),
    })
    @ApiOperation(value = "도서 대출")
    public CommonResult rental(@RequestBody RentalBookDto rentalBookDto) {
        libraryRentalService.rental(rentalBookDto);
        return responseService.commonResult();
    }

    @DeleteMapping("/returnbook")
    @ApiOperation(value = "책 반납")
    public CommonResult returnBook(@RequestBody ReturnBookDto returnBookDto){
        libraryRentalService.returnBook(returnBookDto);
        return responseService.commonResult();
    }

    @PutMapping("/extension")
    @ApiOperation(value = "책 반납일 연장")
    public CommonResult extensionBook(@RequestBody ExtensionBook extensionBook){
        libraryRentalService.extensionBook(extensionBook);
        return responseService.commonResult();
    }

    @GetMapping("/findAllbook")
    @ApiOperation(value = "책 대출 목록 전체 조회")
    public Map<Long, Rental> findAllRentalBook(){
        return libraryRentalService.findAllRentalBook();
    }

    @PostMapping("/findbook/{id}")
    @ApiOperation(value = "책 대출 목록 단일 조회")
    public Map<Long, Rental> findRentalBook(@PathVariable("id")Long idx){
        return libraryRentalService.findRentalBook(idx);
    }

    @PostMapping("/findBook/bookname/{book}")
    @ApiOperation(value = "책 이름으로 대출 정보 조회")
    public Map<Long, Rental> findRentalBookByBookName(@PathVariable("book")String book){
        return libraryRentalService.findRentalBookByBookName(book);
    }
}
