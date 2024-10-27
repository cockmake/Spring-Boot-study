package com.forwardforever.springbegin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.forwardforever.springbegin.domain.entity.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forwardforever.springbegin.domain.dto.ArticleFindForm;
import com.forwardforever.springbegin.domain.vo.ArticleVO;
import com.forwardforever.springbegin.domain.vo.HeadlineVO;
import org.apache.ibatis.annotations.Param;


public interface HeadlineMapper extends BaseMapper<Headline> {
    IPage<HeadlineVO> findArticleWithPastime(IPage<HeadlineVO> page, @Param("articleFindForm") ArticleFindForm articleFindForm, @Param("userId") Integer userId);
    ArticleVO findArticleDetailById(@Param("hid") Integer hid);
}



