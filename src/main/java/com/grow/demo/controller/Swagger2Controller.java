package com.grow.demo.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Swagger2 demo
 * @author liuxw
 * @date 2020/6/30
 * @since 1.0
 */

@RestController
@RequestMapping("/swagger")
@Api(tags = "Swagger2  使用说明类")
public class Swagger2Controller {


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ApiOperation(value = "get 请求api ",notes = "不需要参数")
    public String test(){
        return "get success";
    }


    @RequestMapping(value = "/test1",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation(value = "post,get 请求demo",notes = "不需要参数")
    public String test1(){
        return "test1";
    }

    @RequestMapping(value = "/test2",method = {RequestMethod.GET})
    @ApiOperation(value = "传值demo",notes = "userNumber 需要值")
    @ApiImplicitParam(paramType="query", name = "userNumber", value = "用户编号", required = true, dataType = "Integer")
    public String test2(@RequestParam Integer userNumber){
        return "test2" + userNumber;
    }

    @RequestMapping("/updatePassword")
    @ApiOperation(value="修改用户密码", notes="根据用户id修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userId", value = "用户ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "password", value = "旧密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "newPassword", value = "新密码", required = true, dataType = "String")
    })
    public String updatePassword(@RequestParam(value="userId") Integer userId, @RequestParam(value="password") String password,
                                 @RequestParam(value="newPassword") String newPassword){
        if(userId <= 0 || userId > 2){
            return "未知的用户";
        }
        if(password == "" || newPassword == ""){
            return "密码不能为空";
        }
        if(password.equals(newPassword)){
            return "新旧密码不能相同";
        }
        return "密码修改成功!";
    }


    @RequestMapping(value = "/test3",method = {RequestMethod.GET})
    @ApiOperation(value = "传值demo",notes = "多个参数，多种的查询参数类型")
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    public String test3(){

        return "hello! Mr.";
    }

    /**
     *
     * @param name
     * @return
     */
    @GetMapping("/test4/{name}")
    @ApiOperation("打招呼")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
    })
    public String test4(
            @ApiParam("名字") @PathVariable(required = false) String name
    ) {
        return "hello! Mr." + name;
    }

}
