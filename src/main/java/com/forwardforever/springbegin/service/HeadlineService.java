package com.forwardforever.springbegin.service;

import com.forwardforever.springbegin.domain.entity.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.forwardforever.springbegin.domain.dto.ArticleFindForm;
import com.forwardforever.springbegin.domain.dto.NewArticleForm;
import com.forwardforever.springbegin.domain.vo.ArticleVO;

import java.util.Map;


public interface HeadlineService extends IService<Headline> {
    Map<String, Object> findArticleWithPastime(ArticleFindForm articleFindForm, String token);
    ArticleVO findArticleDetailById(Integer hid);
    Boolean addNewArticle(NewArticleForm newArticleForm, String token);
    Boolean updateArticle(Integer hid, NewArticleForm newArticleForm, String token);
    Boolean deleteArticle(Integer hid, String token);
}
