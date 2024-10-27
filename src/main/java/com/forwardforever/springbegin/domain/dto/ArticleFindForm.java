package com.forwardforever.springbegin.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ArticleFindForm {
    private String keywords;

    private Integer tid;

    @NotNull(message = "页数量不允许为空")
    private Integer pageSize;
    @NotNull(message = "当前页不允许为空")
    private Integer currentPage;
}
