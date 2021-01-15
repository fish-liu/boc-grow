package com.grow.demo.dao;

import com.grow.demo.model.Tags;
import com.grow.demo.model.vo.TagsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liuxw
 * @date 2020/7/6
 * @since 1.0
 */
@Mapper
@Repository
public interface ITagDao extends BaseDao<Tags> {

    /**
     * 获取热门tags
     * @return
     */
    List<TagsVo> getHotTagsList();

    /**
     * 获取公共的tags
     * @return
     */
    List<TagsVo> getPublicTagsList();

    /**
     * 获取私有的tags
     * @param uid
     * @return
     */
    List<TagsVo> getPrivateTagsList(@Param("uid") Integer uid);

}
