package com.grow.demo.restful;

import com.grow.demo.common.ConResult;
import com.grow.demo.common.enums.TagGroupEnum;
import com.grow.demo.common.enums.TagsTypeEnum;
import com.grow.demo.model.Tags;
import com.grow.demo.model.vo.TagsVo;
import com.grow.demo.service.TagService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuxw
 * @since 1.0
 */
@Api(tags = "tag 接口API")
@RestController
@RequestMapping("/api/tag")
public class ApiTagController {

    @Autowired
    private TagService tagService;

    /**
     * 获取热门tags
     * @return
     */
    @ApiOperation(value = "热门tags",notes = "获取热门tags")
    @RequestMapping(value = "/hot",method = RequestMethod.GET)
    public ConResult<List<TagsVo>> getHotTagsList(){

        return ConResult.success(tagService.getHotTagsList());
    }

    /**
     * 获取公共的tags
     * @return
     */
    /*@ApiOperation(value = "公共tags",notes = "获取公共的tags")
    @RequestMapping(value = "/public",method = RequestMethod.GET)
    public ConResult<List<TagsVo>> getPublicTagsList(){

        return ConResult.success(tagService.getPublicTagsList());
    }*/

    /**
     * 获取私有的tags
     * @param uid
     * @return
     */
    @ApiOperation(value = "私有tags",notes = "根据用户id获取该用户的tags列表")
    @RequestMapping(value = "/personal/{uid}",method = RequestMethod.GET)
    public ConResult<List<TagsVo>> getPrivateTagsList(@ApiParam(name = "uid",value = "用户id") @PathVariable("uid")Integer uid){

        return ConResult.success(tagService.getPrivateTagsList(uid));
    }


    @ApiOperation(value = "添加tag",notes = "用户id，tag名称不能为空")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "uid", value = "用户id", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "tagName", value = "tag名称", required = true, dataType = "String")
    })
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ConResult<Integer> addTag(@RequestParam(value="uid") Integer uid,
                            @RequestParam(value="tagName") String tagName
                            ){
        if(uid == null || uid == 0){
            return ConResult.fail("用户id不正确");
        }

        if(StringUtils.isEmpty(tagName)){
            return ConResult.fail("tag名称为空");
        }

        Tags tag = Tags.builder()
                .uid(uid)
                .groupId(TagGroupEnum.DEFAULT.getCode())
                .type(TagsTypeEnum.PRIVATE.getCode())
                .tagName(tagName)
                .build();

        if(tagService.save(tag) > 0){
            return ConResult.success("添加成功",tag.getId());
        }else {
            return ConResult.fail("添加失败");
        }

    }

    @ApiOperation(value = "修改tag",notes = "根据tagId修改tag名称")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "用户id", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "tagName", value = "tag名称", required = true, dataType = "String")
    })
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ConResult updateTag(
            @RequestParam(value="id") Integer id,
            @RequestParam(value="tagName") String tagName
            ){

        Tags tag = Tags.builder().id(id).tagName(tagName).build();
        if(tagService.update(tag)>0){
            return ConResult.success("修改成功",null);
        }else {
            return ConResult.fail("修改失败");
        }
    }

    /**
     * 根据id删除tag
     * @param id
     * @return
     */
    @ApiOperation(value = "删除tag",notes = "根据tagId删除tag")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public ConResult deleteTag(@PathVariable("id") Integer id){

        if(tagService.deleteById(id) > 0){
            return ConResult.success("删除成功",null);
        }else {
            return ConResult.fail("删除失败");
        }
    }

}
