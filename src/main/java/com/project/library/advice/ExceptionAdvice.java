package com.project.library.advice;

import com.project.library.exception.UserAlreadyException;
import com.project.library.response.CommonResult;
import com.project.library.service.ResponseService;
import io.swagger.annotations.ResponseHeader;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    private final MessageSource messageSource;

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

    private String getMessage(String code){
        return getMessage(code, null);
    }

    private String getMessage(String code, Object[] args){
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
