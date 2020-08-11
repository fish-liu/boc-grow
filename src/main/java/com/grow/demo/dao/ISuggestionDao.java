package com.grow.demo.dao;

import com.grow.demo.model.Suggestion;
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
public interface ISuggestionDao extends BaseDao<Suggestion>{

    /**
     * 获取list值
     * @param startPage
     * @param pageSize
     * @return
     */
    List<Suggestion> getList(@Param("startPage") Integer startPage, @Param("pageSize") Integer pageSize);

    /**
     * 获取list count
     * @return
     */
    int getListCount();

}
