package com.project.library.service;

import com.project.library.response.CommonResult;
import com.project.library.response.ListResult;
import com.project.library.response.SingleResult;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    @Getter
    public enum CommonResponse{
        SUCCESS(1, "성공하였습니다."),
        FAIL(-1, "실패하였습니다.");

        int code;
        String massage;

        CommonResponse(int code, String massage){
            this.code = code;
            this.massage = massage;
        }
    }

    public <T> SingleResult<T> getSingleResult(T data){
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    public <T> ListResult<T> getListResult(List<T> list){
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }

    public CommonResult getFailResult(int code, String message) {
        CommonResult result = new CommonResult();

        return result.builder()
                .success(false)
                .code(code)
                .message(message)
                .build();
    }

    public CommonResult getSuccessResult(){
        CommonResult commonResult = new CommonResult();
        setSuccessResult(commonResult);
        return commonResult;
    }

    private void setSuccessResult(CommonResult result){
        result.setSuccess(true);
        result.setCode(result.getCode());
        result.setMessage(result.getMessage());
    }

}
