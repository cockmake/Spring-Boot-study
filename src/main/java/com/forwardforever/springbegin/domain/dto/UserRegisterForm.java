package com.forwardforever.springbegin.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRegisterForm {
    @NotBlank
    @Length(min = 8, max = 20, message = "用户名长度应在8-20之间")
    private String userName;

    @NotBlank
    private String userPwd;

    @NotBlank
    @Length(min = 2, max = 16, message = "昵称长度应在2-16之间")
    private String nickName;
}
