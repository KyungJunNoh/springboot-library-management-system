package com.project.library.exception;

import com.project.library.exception.book.BookListAtBookNotFoundException;
import com.project.library.exception.book.TheNumberOfBookNotFoundException;
import com.project.library.exception.member.IdOrPasswordIncorrectException;
import com.project.library.exception.member.NotMatchPasswordException;
import com.project.library.exception.member.UserAlreadyException;
import com.project.library.exception.rental.BookLoanHistoryNotFoundException;
import com.project.library.exception.rental.BookNotFoundException;
import com.project.library.response.CommonResult;
import com.project.library.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    private final MessageSource messageSource;

    // *** Member Exception *** //

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest httpServletRequest, Exception e){
        // 예외 처리 메세지를 MessageSource 에서 가져오도록 수정
        return responseService.getFailResult(Integer.valueOf(getMessage("unKnown.code")),getMessage("unKnown.msg"));
    }

    @ExceptionHandler(UserAlreadyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult UserAlreadyException(HttpServletRequest httpServletRequest, Exception e){
        return responseService.getFailResult(Integer.valueOf(getMessage("userAlready.code")),getMessage("userAlready.msg"));
    }

    @ExceptionHandler(IdOrPasswordIncorrectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult IdOrPasswordIncorrectException(HttpServletRequest httpServletRequest, Exception e){
        return responseService.getFailResult(Integer.valueOf(getMessage("idOrPasswordIncorrect.code")),getMessage("idOrPasswordIncorrect.msg"));
    }

    // *** Book Exception *** //

    @ExceptionHandler(BookListAtBookNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult BookListAtBookNotFoundException(HttpServletRequest httpServletRequest, Exception e){
        return responseService.getFailResult(Integer.valueOf(getMessage("bookListAtBookNotFound.code")),getMessage("bookListAtBookNotFound.msg"));
    }

    @ExceptionHandler(TheNumberOfBookNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult TheNumberOfBookNotFoundException(HttpServletRequest httpServletRequest, Exception e){
        return responseService.getFailResult(Integer.valueOf(getMessage("theNumberOfBookNotFound.code")),getMessage("theNumberOfBookNotFound.msg"));
    }

    // *** Rental Exception *** //

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult BookNotFoundException(HttpServletRequest httpServletRequest, Exception e){
        return responseService.getFailResult(Integer.valueOf(getMessage("bookNotFound.code")),getMessage("bookNotFound.msg"));
    }

    @ExceptionHandler(BookLoanHistoryNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult BookLoanHistoryNotFoundException(HttpServletRequest httpServletRequest, Exception e){
        return responseService.getFailResult(Integer.valueOf(getMessage("bookLoanHistoryNotFound.code")),getMessage("bookLoanHistoryNotFound" +
                ".msg"));
    }



    private String getMessage(String code){
        return getMessage(code, null);
    }

    private String getMessage(String code, Object[] args){
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
