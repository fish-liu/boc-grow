package com.grow.demo.restful;

import com.grow.demo.common.ConResult;
import com.grow.demo.common.enums.StatusEnum;
import com.grow.demo.model.vo.ArticleFormParams;
import com.grow.demo.model.vo.ArticleVo;
import com.grow.demo.service.ArticleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuxw
 * @since 1.0
 */
@Api(tags = "article 接口API")
@RestController
@RequestMapping("/api/article")
public class ApiArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "添加article",notes = "添加article")
    @ApiImplicitParam(name = "article", value = "article表单提交", required = true, dataType = "ArticleFormParams")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ConResult addArticle(ArticleFormParams article){

        //对数据的验证

        try {
            int result = articleService.saveArticle(article);
            return ConResult.success("添加成功",result);
        }catch (Exception e){
            return ConResult.fail("添加失败");
        }
    }

    @ApiOperation(value = "删除article",notes = "根据Id删除Article")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public ConResult deleteArticle(@ApiParam(name = "id",value = "文章id") @PathVariable("id")Integer id){

        if(articleService.updateStatusById(id,StatusEnum.DISABLE.getCode()) > 0){
            return ConResult.success("操作成功",null);
        }else {
            return ConResult.fail("操作失败");
        }
    }

    @ApiOperation(value = "获取articleList",notes = "根据用户id获取该用户的article列表")
    @RequestMapping(value = "/list/{uid}",method = RequestMethod.GET)
    public ConResult<List<ArticleVo>> getArticleList(@ApiParam(name = "uid",value = "用户id") @PathVariable("uid")Integer uid){

        return ConResult.success(articleService.getArticleList(uid));

    }

}
