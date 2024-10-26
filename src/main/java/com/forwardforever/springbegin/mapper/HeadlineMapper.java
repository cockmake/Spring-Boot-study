package com.forwardforever.springbegin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.forwardforever.springbegin.domain.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forwardforever.springbegin.dto.ArticleFindForm;
import com.forwardforever.springbegin.vo.ArticleVO;
import com.forwardforever.springbegin.vo.HeadlineVO;
import org.apache.ibatis.annotations.Param;


public interface HeadlineMapper extends BaseMapper<Headline> {
    IPage<HeadlineVO> findArticleWithPastime(IPage<HeadlineVO> page, @Param("articleFindForm") ArticleFindForm articleFindForm, @Param("userId") Integer userId);
    ArticleVO findArticleDetailById(@Param("hid") Integer hid);
}



