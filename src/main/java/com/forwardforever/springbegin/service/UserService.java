package com.forwardforever.springbegin.service;

import com.forwardforever.springbegin.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.forwardforever.springbegin.dto.UserLoginForm;
import com.forwardforever.springbegin.dto.UserRegisterForm;

public interface UserService extends IService<User> {
    User checkLogin(UserLoginForm loginForm);
    Boolean userRegister(UserRegisterForm userRegisterForm);
}