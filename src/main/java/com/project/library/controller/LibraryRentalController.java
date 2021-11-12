package com.project.library.controller;

import com.project.library.Response.CommonResult;
import com.project.library.Response.ResponseService;
import com.project.library.dto.RentalBookDto;
import com.project.library.dto.ReturnBookDto;
import com.project.library.service.LibraryRentalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class LibraryRentalController {

    private final LibraryRentalService libraryRentalService;
    private final ResponseService responseService;

    @PostMapping("/rental")
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
}
