package com.project.library.controller;

import com.project.library.dto.RentalBookDto;
import com.project.library.service.LibraryRentalService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class LibraryRentalController {

    private final LibraryRentalService libraryRentalService;

    @PostMapping("/rental")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header"),
    })
    @ApiOperation(value = "도서 대출")
    public void rental(@RequestBody RentalBookDto rentalBookDto){
        libraryRentalService.rental(rentalBookDto);
    }

}
