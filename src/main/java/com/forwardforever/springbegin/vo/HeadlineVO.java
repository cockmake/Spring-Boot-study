package com.forwardforever.springbegin.vo;

import lombok.Data;

@Data
public class HeadlineVO {
    private Integer hid;
    private String title;
    private String article;
    private String author;
    private Integer pastime;
    private Integer pageViews;
    private Boolean isOwner;
}
