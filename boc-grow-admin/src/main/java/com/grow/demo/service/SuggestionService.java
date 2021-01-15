package com.grow.demo.service;

import com.grow.demo.common.PageModel;
import com.grow.demo.dao.ISuggestionDao;
import com.grow.demo.model.Suggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuxw
 * @since 1.0
 */
@Service
public class SuggestionService extends AbstractBaseService<Suggestion> {

    @Autowired
    private ISuggestionDao iSuggestionDao;

    /**
     * 获取分页数据
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageModel getPage(Integer pageNum,Integer pageSize){

        int startPage = (pageNum - 1) * pageSize;

        List list = iSuggestionDao.getList(startPage,pageSize);

        int total = iSuggestionDao.getListCount();

        return new PageModel(list,total,startPage,pageSize);
    }

}
