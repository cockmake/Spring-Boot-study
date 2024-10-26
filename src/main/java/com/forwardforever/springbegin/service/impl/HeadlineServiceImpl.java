package com.forwardforever.springbegin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forwardforever.springbegin.domain.Headline;
import com.forwardforever.springbegin.dto.ArticleFindForm;
import com.forwardforever.springbegin.dto.NewArticleForm;
import com.forwardforever.springbegin.mapper.HeadlineMapper;
import com.forwardforever.springbegin.service.HeadlineService;
import com.forwardforever.springbegin.utils.JWTUtils;
import com.forwardforever.springbegin.vo.ArticleVO;
import com.forwardforever.springbegin.vo.HeadlineVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Service
@Transactional
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
        implements HeadlineService {


    private final HeadlineMapper headlineMapper;

    private final JWTUtils jwtUtils;

    public HeadlineServiceImpl(HeadlineMapper headlineMapper, JWTUtils jwtUtils) {
        this.headlineMapper = headlineMapper;
        this.jwtUtils = jwtUtils;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> findArticleWithPastime(ArticleFindForm articleFindForm, String token) {
        Integer userId = jwtUtils.getUserId(token);
        IPage<HeadlineVO> page = new Page<>(articleFindForm.getCurrentPage(), articleFindForm.getPageSize());
        headlineMapper.findArticleWithPastime(page, articleFindForm, userId);
        Map<String, Object> map = new HashMap<>();
        map.put("totalNum", page.getTotal());
        map.put("totalPage", page.getPages());
        map.put("currentPage", page.getCurrent());
        map.put("pageSize", page.getSize());
        map.put("pageData", page.getRecords());
        return map;
    }

    @Override
    public ArticleVO findArticleDetailById(Integer hid) {
        // 查询文章
        ArticleVO articleDetail = headlineMapper.findArticleDetailById(hid);
        if (articleDetail != null){
            // 阅读量 + 1
            Headline headline = new Headline();
            headline.setHid(hid);
            headline.setVersion(articleDetail.getVersion());
            headline.setPageViews(articleDetail.getPageViews() + 1);
            headlineMapper.updateById(headline);
            // 实体 + 1
            articleDetail.setPageViews(articleDetail.getPageViews() + 1);
        }
        return articleDetail;
    }

    @Override
    public Boolean addNewArticle(NewArticleForm newArticleForm, String token) {
        Integer userId = jwtUtils.getUserId(token);
        Headline headline = new Headline(
                newArticleForm.getTitle(),
                newArticleForm.getArticle(),
                newArticleForm.getType(),
                userId
        );
        return this.save(headline);
    }

    @Override
    public Boolean updateArticle(Integer hid, NewArticleForm newArticleForm, String token) {
        Integer userId = jwtUtils.getUserId(token);
        LambdaUpdateWrapper<Headline> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .eq(Headline::getHid, hid)
                .eq(Headline::getPublisher, userId);

        Headline headline = new Headline(
                newArticleForm.getTitle(),
                newArticleForm.getArticle(),
                newArticleForm.getType()
        );
        boolean f = this.update(headline, updateWrapper);
        System.out.println(f);
        return f;
    }

    @Override
    public Boolean deleteArticle(Integer hid, String token) {
        Integer userId = jwtUtils.getUserId(token);
        LambdaQueryWrapper<Headline> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Headline::getHid, hid)
                .eq(Headline::getPublisher, userId);
        return this.remove(queryWrapper);
    }
}

