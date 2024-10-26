package com.forwardforever.springbegin.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName(value ="news_headline")
@Data
@NoArgsConstructor
public class Headline implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer hid;

    private String title;

    private String article;

    private Integer type;

    private Integer publisher;

    private Integer pageViews;

    private Date createTime;

    private Date updateTime;

    @Version
    private Integer version;

    @TableLogic
    private Integer isDeleted;

    public Headline(String title, String article, Integer type, Integer publisher){
        this.title = title;
        this.article = article;
        this.type = type;
        this.publisher = publisher;
    }
    public Headline(Integer hid, String title, String article, Integer type){
        this.hid = hid;
        this.title = title;
        this.article = article;
        this.type = type;
    }
    public Headline(String title, String article, Integer type){
        this.title = title;
        this.article = article;
        this.type = type;
    }

    @Serial
    private static final long serialVersionUID = 1L;
}