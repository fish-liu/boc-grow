package com.grow.demo.service;

import com.grow.demo.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liuxw
 * @since 1.0
 */
public abstract class AbstractBaseService<T> {


    @Autowired
    BaseDao<T> baseDao;


    /**
     * 保存记录
     * @param t
     * @return
     */
    public int save(T t){
        return baseDao.save(t);
    }

    /**
     * 更新记录
     * @param t
     * @return
     */
    public int update(T t){
        return baseDao.update(t);
    }

    /**
     * 根据id更新状态
     * @param id
     * @param status
     * @return
     */
    public int updateStatusById(Integer id,int status){
        return baseDao.updateStatusById(id,status);
    }

    /**
     * 根据id 删除记录
     * @param id
     * @return
     */
    public int deleteById(Integer id){
        return baseDao.deleteById(id);
    }


}
