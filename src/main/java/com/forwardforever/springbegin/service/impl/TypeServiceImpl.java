package com.forwardforever.springbegin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forwardforever.springbegin.domain.entity.Type;
import com.forwardforever.springbegin.service.TypeService;
import com.forwardforever.springbegin.mapper.TypeMapper;
import org.springframework.stereotype.Service;


@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

}




