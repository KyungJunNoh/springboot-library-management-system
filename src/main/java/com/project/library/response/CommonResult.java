package com.project.library.response;

import lombok.*;

@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE) @AllArgsConstructor
public class CommonResult {

    private boolean success;
    private int code;
    private String massage;

    public void updateMassage(String massage){
        this.massage = massage;
    }

    public CommonResult(CommonResult commonResult){
        this.success = commonResult.success;
        this.code = commonResult.code;
        this.massage = commonResult.massage;
    }
}
