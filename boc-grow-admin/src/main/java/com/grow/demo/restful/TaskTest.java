package com.grow.demo.restful;

import com.grow.demo.config.SpringContextHolder;
import com.grow.demo.service.CategoryService;

/**
 * @author liuxw
 * @date 2020/7/15
 * @since 1.0
 */
public class TaskTest {


    public void test(){

        CategoryService service = SpringContextHolder.getBean("categoryService");

        System.out.println("------test-------");
        service.getCategoryList(1);


    }


}
