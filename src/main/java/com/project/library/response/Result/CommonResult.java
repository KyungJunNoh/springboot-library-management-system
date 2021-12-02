package com.project.library.response.Result;

import lombok.*;

@Builder @Getter
@Setter @AllArgsConstructor @NoArgsConstructor
public class CommonResult {

    private boolean success;
    private int code;
    private String message;
}
