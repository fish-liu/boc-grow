package com.grow.demo.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author liuxw
 * @since 1.0
 */
public interface BaseDao<T> {

    /**
     * 保存记录
     * @param t
     * @return
     */
    int save(T t);

    /**
     * 更新记录
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 根据id更新状态
     * @param id
     * @param status
     * @return
     */
    int updateStatusById(@Param("id") Integer id,@Param("status") int status);

    /**
     * 根据id 删除记录
     * @param id
     * @return
     */
    int deleteById(@Param("id") Integer id);

}
