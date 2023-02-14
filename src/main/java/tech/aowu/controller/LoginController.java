package tech.aowu.controller;

import io.swagger.annotations.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aowu.entity.ResponseResult;
import tech.aowu.entity.UmUser;

import tech.aowu.entity.vo.UserView;
import tech.aowu.service.LoginService;

/**
 * @Description: TODO
 * @ClassName: LoginController
 * @Author: Aealen
 * @Date: 2023/1/31 20:32
 */
@Api(tags = "LoginController", description = "用户认证登陆相关")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    @ApiOperation(value = "用户登陆",notes = "<span style='color:red;'>详细描述：</span>&nbsp;使用用户名和密码进行登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", defaultValue = "" ,paramType = "body"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", defaultValue = "",paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "登陆成功"),
            @ApiResponse(code = 401, message = "用户认证失败请重新登陆"),
            @ApiResponse(code = 150, message = "数据库操作异常")
    })
    @PostMapping("/user/auth/login")
    public ResponseResult login(@RequestBody UmUser user){

        return loginService.login(user);
    }

    @ApiOperation(value = "用户登陆",notes = "<span style='color:red;'>详细描述：</span>&nbsp;使用用户名和密码进行登陆")
    @RequestMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }



    @ApiOperation(value = "用户注册",notes = "<span style='color:red;'>详细描述：</span>&nbsp;用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", defaultValue = "" ,paramType = "body"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", defaultValue = "",paramType = "body"),
            @ApiImplicitParam(name = "nickname", value = "昵称", dataType = "String", defaultValue = "",paramType = "body"),
            @ApiImplicitParam(name = "department", value = "部门", dataType = "Integer", defaultValue = "",paramType = "body"),
            @ApiImplicitParam(name = "phoneNum", value = "电话", dataType = "String", defaultValue = "",paramType = "body"),
            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String", defaultValue = "",paramType = "body"),
            @ApiImplicitParam(name = "rid", value = "角色", dataType = "String", defaultValue = "",paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "注册成功"),
            @ApiResponse(code = 102, message = "用户已存在"),
            @ApiResponse(code = 150, message = "数据库操作异常")

    })
    @PostMapping("/user/auth/regist")
    public ResponseResult regist(@RequestBody UserView user){
        return loginService.regist(user);
    }





}
