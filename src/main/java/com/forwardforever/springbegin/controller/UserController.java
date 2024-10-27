package com.forwardforever.springbegin.controller;

import com.forwardforever.springbegin.domain.entity.User;
import com.forwardforever.springbegin.domain.dto.Result;
import com.forwardforever.springbegin.domain.dto.UserLoginForm;
import com.forwardforever.springbegin.domain.dto.UserRegisterForm;
import com.forwardforever.springbegin.service.UserService;
import com.forwardforever.springbegin.utils.HttpCodeEnum;
import com.forwardforever.springbegin.utils.JWTUtils;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JWTUtils jwtUtils;

    public UserController(UserService userService, JWTUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public Result<String> userRegister(@RequestBody @Valid UserRegisterForm userRegisterForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getErrorCount());
            FieldError error = bindingResult.getFieldError();
            return new Result<>(
                    HttpCodeEnum.BAD_REQUEST.getCode(),
                    Objects.requireNonNull(error).getField() + "：" + error.getDefaultMessage(),
                    null
            );
        }
        Boolean f = userService.userRegister(userRegisterForm);
        return f ? Result.success("注册成功") : Result.failure("注册失败，用户已存在");
    }

    @PostMapping("/login")
    public Result<Map<String, Object>> userLogin(@RequestBody @Valid UserLoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError error = bindingResult.getFieldError();
            return new Result<>(
                    HttpCodeEnum.BAD_REQUEST.getCode(),
                    Objects.requireNonNull(error).getField() + "：" + error.getDefaultMessage(),
                    null
            );
        }
        User user = userService.checkLogin(loginForm);
        if (user == null) {
            // 用户不存在
            return Result.failure("用户不存在");
        }
        else if (!user.getUserPwd().equals(loginForm.getUserPwd())) {
            // 密码错误
            return Result.failure("密码错误");
        }
        // 登录成功
        Map<String, Object> encodeData = new HashMap<>();
        encodeData.put("userName", user.getUserName());
        encodeData.put("userId", user.getUserId());
        String token = jwtUtils.createToken(encodeData);

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return Result.success(map);
    }
}

