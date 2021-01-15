package com.grow.demo.dao;

import com.grow.demo.model.Article;
import com.grow.demo.model.ArticleTags;
import com.grow.demo.model.vo.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuxw
 * @since 1.0
 */
@Mapper
@Repository
public interface IArticleDao extends BaseDao<Article>{

    /**
     * 获取文章列表
     * @param uid
     * @return
     */
    List<ArticleVo> getArticleList(@Param("uid") Integer uid);


    /**
     * 保存文章与标签之间的关系
     * @param articleTags
     * @return
     */
    int saveArticleTag(ArticleTags articleTags);


}
