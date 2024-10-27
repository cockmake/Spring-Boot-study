package com.forwardforever.springbegin.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


@TableName(value = "news_user")
@Data
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String userPwd;

    private String nickName;

    @Version
    private Integer version;

    @TableLogic
    private Integer isDeleted;

    private Date createDate;

    private Date updateDate;

    @Serial
    private static final long serialVersionUID = 1L;
}