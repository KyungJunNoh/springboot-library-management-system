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

    // 단일 건의 결과
    public <T> SingleResult<T> getSingleResult(T data){
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    // List 건의 결과
    public <T> ListResult<T> getListResult(List<T> list){
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }

    // 성공 Response
    public CommonResult getSuccessResult(){
        return new CommonResult().builder()
                .success(true)
                .code(CommonResponse.SUCCESS.getCode())
                .message(CommonResponse.SUCCESS.getMassage())
                .build();
    }

    // 실패 Response
    public CommonResult getFailResult(int code, String msg) {
        return new CommonResult().builder()
                .success(false)
                .code(code)
                .message(msg)
                .build();
    }

    private void setSuccessResult(CommonResult result){
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMessage(CommonResponse.SUCCESS.getMassage());
    }

}
