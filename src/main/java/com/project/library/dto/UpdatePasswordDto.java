package com.project.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordDto {
    private String oldPassword;
    private String newPassword;
}
