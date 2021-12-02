package com.project.library.response.Result;

import com.project.library.response.Result.CommonResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResult<T> extends CommonResult {
    private T data;
}
