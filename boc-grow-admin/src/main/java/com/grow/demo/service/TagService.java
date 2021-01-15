package com.grow.demo.service;

import com.grow.demo.dao.ITagDao;
import com.grow.demo.model.Tags;
import com.grow.demo.model.vo.TagsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuxw
 * @since 1.0
 */
@Service
public class TagService extends AbstractBaseService<Tags> {

    @Autowired
    private ITagDao iTagDao;

    /**
     * 获取热门tags
     * @return
     */
    public List<TagsVo> getHotTagsList(){
        return iTagDao.getHotTagsList();
    }

    /**
     * 获取公共的tags
     * @return
     */
    public List<TagsVo> getPublicTagsList(){
        return iTagDao.getPublicTagsList();
    }

    /**
     * 获取私有的tags
     * @param uid
     * @return
     */
    public List<TagsVo> getPrivateTagsList(Integer uid){
        return iTagDao.getPrivateTagsList(uid);
    }

}
