package com.forwardforever.springbegin.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@TableName(value ="news_type")
@Data
public class Type implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer tid;

    private String tName;

    @Version
    private Integer version;

    @TableLogic
    private Integer isDeleted;

    @Serial
    private static final long serialVersionUID = 1L;
}