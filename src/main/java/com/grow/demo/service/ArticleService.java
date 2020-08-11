package com.grow.demo.service;

import com.grow.demo.common.enums.StatusEnum;
import com.grow.demo.dao.IArticleDao;
import com.grow.demo.model.Article;
import com.grow.demo.model.ArticleTags;
import com.grow.demo.model.vo.ArticleFormParams;
import com.grow.demo.model.vo.ArticleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuxw
 * @since 1.0
 */
@Service
public class ArticleService extends AbstractBaseService<Article> {

    @Autowired
    private IArticleDao iArticleDao;

    /**
     * 保存文章
     * @param formParams
     * @return
     */
    public int saveArticle(ArticleFormParams formParams){

        Article article = Article.builder()
                .cateId(formParams.getCateId())
                .content(formParams.getContent())
                .imgIds(formParams.getImgIds())
                .location(formParams.getLocation())
                .publishTime(formParams.getPublishTime())
                .uid(formParams.getUid())
                .visibility(formParams.getVisibility())
                .status(StatusEnum.NORMAL.getCode())
                .build();

        iArticleDao.save(article);

        String tagStr = formParams.getTagIds();

        String tags[] = tagStr.split(",");
        for(String tag : tags){

            ArticleTags articleTags = ArticleTags.builder()
                    .artiId(article.getId())
                    .uid(formParams.getUid())
                    .tagId(Integer.valueOf(tag))
                    .build();

            iArticleDao.saveArticleTag(articleTags);
        }

        return article.getId();
    }


    public List<ArticleVo> getArticleList(Integer uid){
        return iArticleDao.getArticleList(uid);
    }

}
