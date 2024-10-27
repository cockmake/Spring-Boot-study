package com.forwardforever.springbegin.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ArticleVO {
    private Integer hid;
    private String title;
    private String article;
    private Integer type;
    private String typeName;
    private String publisher;
    private String author;
    private Integer pastime;
    private Integer pageViews;
    private String createTime;

    @JsonIgnore
    private Integer version;
}
