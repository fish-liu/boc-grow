package com.grow.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户相关controller
 * 改controller下的方法需要权限（需要登录）
 * @author liuxw
 * @since 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 用户首页
     */
    @RequestMapping(value = {"","/"})
    public String home(){
        return "user/home";
    }


    @RequestMapping(value = {"/manage","/manage/{path}"},method = RequestMethod.GET)
    public ModelAndView manage(@PathVariable(value = "path",required = false)String path){

        String content;
        String page;
        if(path == null || path.equals("")){
            page = "user/tags";
            content = "tags";
        }else {
            content = path;
            page = "user/"+path;
        }

        ModelAndView modelAndView =  new ModelAndView("user/manage");
        // th:replace="${page}::${content}
        modelAndView.addObject("page", page);
        modelAndView.addObject("content", content);

        return modelAndView;
    }

    @RequestMapping(value = {"/member","/member/{path}"},method = RequestMethod.GET)
    public ModelAndView member(@PathVariable(value = "path",required = false)String path){

        String content;
        String page;
        if(path == null || path.equals("")){
            page = "user/detail";
            content = "detail";
        }else {
            content = path;
            page = "user/"+path;
        }

        ModelAndView modelAndView =  new ModelAndView("user/member");
        // th:replace="${page}::${content}
        modelAndView.addObject("page", page);
        modelAndView.addObject("content", content);

        return modelAndView;
    }


    /**
     * 添加信息页
     * @return
     */
    @RequestMapping("/article")
    public String article(){
        return "user/add-article";
    }

    /**
     * 分类管理页
     * @return
     */
    @RequestMapping("/category")
    public String category(){
        return "user/category";
    }

    /**
     * 标签管理页
     * @return
     */
    @RequestMapping("/tags")
    public String tags(){


        return "user/tags";
    }


}
