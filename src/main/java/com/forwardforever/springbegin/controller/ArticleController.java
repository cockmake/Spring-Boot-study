package com.forwardforever.springbegin.controller;

import com.forwardforever.springbegin.domain.dto.ArticleFindForm;
import com.forwardforever.springbegin.domain.dto.NewArticleForm;
import com.forwardforever.springbegin.domain.dto.Result;
import com.forwardforever.springbegin.service.HeadlineService;
import com.forwardforever.springbegin.domain.vo.ArticleVO;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final HeadlineService headlineService;

    public ArticleController(HeadlineService headlineService) {
        this.headlineService = headlineService;
    }

    @PostMapping("/")
    public Result<Map<String, Object>> findArticleWithPastime(
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid ArticleFindForm articleFindForm, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            FieldError error = bindingResult.getFieldError();
            return Result.failure(Objects.requireNonNull(error).getField() + "：" + error.getDefaultMessage());
        }
        Map<String, Object> articles = headlineService.findArticleWithPastime(articleFindForm, token);
        return Result.success(articles);
    }

    @GetMapping("/{hid}")
    public Result<ArticleVO> getArticleDetail(@PathVariable Integer hid) {
        ArticleVO articleDetail = headlineService.findArticleDetailById(hid);
        System.out.println(articleDetail);
        return Result.success(articleDetail);
    }

    @PostMapping("/new")
    public Result<String> addArticle(
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid NewArticleForm newArticleForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError error = bindingResult.getFieldError();
            return Result.failure(Objects.requireNonNull(error).getField() + "：" + error.getDefaultMessage());
        }
        if (headlineService.addNewArticle(newArticleForm, token)) {
            return Result.success("发布成功");
        }
        return Result.failure("发布失败");
    }

    @DeleteMapping("/{hid}")
    public Result<String> deleteArticle(
            @PathVariable Integer hid,
            @RequestHeader("Authorization") String token) {
        if (headlineService.deleteArticle(hid, token)) {
            return Result.success("删除成功");
        }
        return Result.failure("操作失败");
    }

    @PutMapping("/{hid}")
    private Result<String> updateArticle(
            @PathVariable Integer hid,
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid NewArticleForm newArticleForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError error = bindingResult.getFieldError();
            return Result.failure(Objects.requireNonNull(error).getField() + "：" + error.getDefaultMessage());
        }
        if (headlineService.updateArticle(hid, newArticleForm, token)) {
            return Result.success("更新成功");
        }
        return Result.failure("更新失败");
    }

}
