package com.grow.demo.controller;

import com.grow.demo.common.ConResult;
import com.grow.demo.common.PageModel;
import com.grow.demo.common.enums.StatusEnum;
import com.grow.demo.model.Suggestion;
import com.grow.demo.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuxw
 * @since 1.0
 */
@Controller
@RequestMapping("/sgt")
public class SuggestionController {

    private static Integer defaultPageNum = 1;

    private static Integer defaultPageSize = 10;

    @Autowired
    private SuggestionService suggestionService;


    @RequestMapping(value ={"","/"},method = RequestMethod.GET)
    public String index(Model model){

        model.addAttribute(suggestionService.getPage(defaultPageNum,defaultPageSize));
        model.addAttribute("title","hello word");

        List<Map<String,String>> lists = new ArrayList<>();
        for(int i = 0; i< 5 ; i++){
            Map<String,String> map = new HashMap<>();
            map.put("author", "曹雪芹"+i);
            map.put("title", "《红楼梦》"+i);
            map.put("url", "www.baidu.com");
            lists.add(map);
        }

        // 用作对照
        model.addAttribute("refresh", "我不会被刷新");

        model.addAttribute("title", "我的书单");
        model.addAttribute("books", lists);

        PageModel pageModel = new PageModel(lists,53,1,10);

        model.addAttribute("pageModel",pageModel);

        return "suggestion/list";
    }

    /**
     * 分页查询 局部刷新
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/list")
    public String list(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                          Model model){

        model.addAttribute(suggestionService.getPage(pageNum,pageSize));

        List<Map<String,String>> lists = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        map.put("author", "罗贯中");
        map.put("title", "《三国演义》");
        map.put("url", "www.baidu.com");
        lists.add(map);

        model.addAttribute("title", "我的书单");
        model.addAttribute("books", lists);

        return "suggestion/list::list_refresh";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ConResult add(Suggestion suggestion){

        suggestion.setStatus(StatusEnum.NORMAL.getCode());
        suggestionService.save(suggestion);
        return ConResult.success();
    }

}
