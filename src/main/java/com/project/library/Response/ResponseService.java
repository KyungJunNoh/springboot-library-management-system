package com.project.library.Response;

import lombok.Getter;
import org.springframework.stereotype.Service;

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

    public CommonResult commonResult(){
        return CommonResult.builder()
                .success(true)
                .code(CommonResponse.SUCCESS.getCode())
                .massage(CommonResponse.SUCCESS.getMassage())
                .build();
    }

}
