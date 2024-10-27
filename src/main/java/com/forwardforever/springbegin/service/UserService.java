package com.forwardforever.springbegin.service;

import com.forwardforever.springbegin.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.forwardforever.springbegin.domain.dto.UserLoginForm;
import com.forwardforever.springbegin.domain.dto.UserRegisterForm;

public interface UserService extends IService<User> {
    User checkLogin(UserLoginForm loginForm);
    Boolean userRegister(UserRegisterForm userRegisterForm);
}