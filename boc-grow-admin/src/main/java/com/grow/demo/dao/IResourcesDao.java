package com.grow.demo.dao;

import com.grow.demo.model.Resources;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author liuxw
 * @since 1.0
 */
@Mapper
@Repository
public interface IResourcesDao extends BaseDao<Resources>{



}
