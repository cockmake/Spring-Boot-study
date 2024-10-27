package com.forwardforever.springbegin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.forwardforever.springbegin.domain.entity.Type;
import com.forwardforever.springbegin.domain.dto.Result;
import com.forwardforever.springbegin.service.TypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {
    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/")
    public Result<List<Type>> getAllTypes() {
        LambdaQueryWrapper<Type> queryWrapper = new LambdaQueryWrapper<>();
        // 只返回特定的字段
        queryWrapper.select(Type::getTid, Type::getTName);
        List<Type> typeList = typeService.list(queryWrapper);
        // 配置 default-property-inclusion: non_null 过滤null
        return Result.success(typeList);
    }
}
