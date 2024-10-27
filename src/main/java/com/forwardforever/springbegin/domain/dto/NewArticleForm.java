package com.forwardforever.springbegin.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NewArticleForm {
    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "文章内容不能为空")
    private String article;

    @NotNull(message = "类型不能为空")
    private Integer type;
}
