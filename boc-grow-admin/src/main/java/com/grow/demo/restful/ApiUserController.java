package com.grow.demo.restful;

import com.grow.demo.common.ConResult;
import com.grow.demo.model.User;
import com.grow.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuxw
 * @since 1.0
 */
@Api(tags = "user 接口API")
@RestController
@RequestMapping("/api/user")
public class ApiUserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value="注册用户", notes="注册用户")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ConResult register(){
        return ConResult.success();
    }

    @ApiOperation(value="用户登录", notes="用户登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ConResult login(){

        return ConResult.success();
    }


    @ApiOperation(value="修改用户", notes="修改用户")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ConResult updateUser(){
        return ConResult.success();
    }

    @ApiOperation(value="修改用户头像", notes="修改用户头像")
    @RequestMapping(value = "/updateAvatar",method = RequestMethod.POST)
    public ConResult updateAvatar(){
        return ConResult.success();
    }


    @ApiOperation(value="修改用户密码", notes="根据用户id修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userId", value = "用户ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "password", value = "旧密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "newPassword", value = "新密码", required = true, dataType = "String")
    })
    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
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

        User user = userService.getUserById(userId);
        if(user!=null){
            userService.updatePassword(userId,newPassword);
        }

        return "密码修改成功!";
    }

}
