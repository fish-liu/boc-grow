package com.grow.demo.controller;

import com.grow.demo.model.Tags;
import com.grow.demo.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuxw
 * @date 2020/7/6
 * @since 1.0
 */
@Api(tags = "标签（tag）接口API")
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation(value = "保存标签",notes = "不需要参数")
    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public int save(){
        Tags tag = Tags.builder().tagName("tag1").uid(1).build();
        /*tag.setCateId(1);
        tag.setTagName("tag1");
        tag.setUid(1);*/

        tagService.save(tag);
        return 1;
    }


}
