package com.grow.demo.controller;

import com.grow.demo.common.enums.CategoryTypeEnum;
import com.grow.demo.common.enums.StatusEnum;
import com.grow.demo.common.enums.TagGroupEnum;
import com.grow.demo.common.enums.TagsTypeEnum;
import com.grow.demo.model.Category;
import com.grow.demo.model.Tags;
import com.grow.demo.restful.TaskTest;
import com.grow.demo.service.CategoryService;
import com.grow.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 网站首页
 * @author liuxw
 * @since 1.0
 */
@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = {"","/"})
    public String index(){
        return "index";
    }

    /**
     * 关于我们
     * @return
     */
    @RequestMapping(value = "/about",method = RequestMethod.GET)
    public String article(){
        return "about";
    }

    @RequestMapping("/category")
    public String category(){
        return "user/category";
    }

    @RequestMapping("/tags")
    public String tags(){
        return "user/tags";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/test1")
    @ResponseBody
    public String test1(){

        TaskTest taskTest = new TaskTest();
        taskTest.test();
        return "test1";
    }


    @RequestMapping("/init")
    @ResponseBody
    public String initSystem(){

        Category category = Category.builder()
                .type(CategoryTypeEnum.DEFAULT.getCode())
                .categoryName("其他")
                .status(StatusEnum.NORMAL.getCode())
                .build();

        categoryService.save(category);



        for(int i =0 ; i < 3; i++){

            Category cate = Category.builder()
                    .type(CategoryTypeEnum.PUBLIC.getCode())
                    .categoryName("分类"+i)
                    .status(StatusEnum.NORMAL.getCode())
                    .build();

            categoryService.save(cate);
        }


        Tags tag = Tags.builder()
                .groupId(TagGroupEnum.INTEREST.getCode())
                .type(TagsTypeEnum.PUBLIC.getCode())
                .tagName("")
                .build();

        tagService.save(tag);

        return "success";

    }





}
