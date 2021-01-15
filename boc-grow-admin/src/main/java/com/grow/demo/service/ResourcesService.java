package com.grow.demo.service;

import com.grow.demo.dao.IResourcesDao;
import com.grow.demo.model.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuxw
 * @since 1.0
 */
@Service
public class ResourcesService extends AbstractBaseService<Resources> {

    @Autowired
    private IResourcesDao iResourcesDao;

}
