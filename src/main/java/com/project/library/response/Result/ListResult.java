package com.project.library.response.Result;

import com.project.library.response.Result.CommonResult;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class ListResult<T> extends CommonResult {
    private List<T> list;
}
