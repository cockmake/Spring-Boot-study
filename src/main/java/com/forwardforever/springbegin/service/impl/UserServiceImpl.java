package com.forwardforever.springbegin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forwardforever.springbegin.domain.entity.User;
import com.forwardforever.springbegin.domain.dto.UserLoginForm;
import com.forwardforever.springbegin.domain.dto.UserRegisterForm;
import com.forwardforever.springbegin.mapper.UserMapper;
import com.forwardforever.springbegin.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    @Transactional(readOnly = true)
    public User checkLogin(UserLoginForm loginForm) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(User::getUserName, loginForm.getUserName());
        return this.getOne(queryWrapper);
    }

    @Override
    public Boolean userRegister(UserRegisterForm userRegisterForm) {
        // 查询是否已经存在了用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userRegisterForm.getUserName());

        User user = this.getOne(queryWrapper);
        if (user != null) {
            // 用户已经存在了
            return false;
        }
        // 用户不存在
        User userToRegister = new User();
        userToRegister.setUserName(userRegisterForm.getUserName());
        userToRegister.setUserPwd(userRegisterForm.getUserPwd());
        userToRegister.setNickName(userRegisterForm.getNickName());
        System.out.println(userToRegister);
        this.save(userToRegister);
        return true;
    }
}




