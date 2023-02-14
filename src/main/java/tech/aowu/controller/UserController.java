package tech.aowu.controller;

import io.swagger.annotations.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.aowu.entity.ResponseResult;
import tech.aowu.entity.vo.QueryByPageParams;
import tech.aowu.service.UserService;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @ClassName: UserController
 * @Author: Aealen
 * @Date: 2023/2/14 14:42
 */
@Api(tags = "UserController", description = "用户模块接口")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "分页查询用户",notes = "<span style='color:red;'>详细描述：</span>&nbsp;分页查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", dataType = "String", defaultValue = "" ,paramType = "body"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "String", defaultValue = "",paramType = "body"),
            @ApiImplicitParam(name = "perPage", value = "每页数量", dataType = "String", defaultValue = "",paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "注册成功"),
            @ApiResponse(code = 102, message = "用户已存在"),
            @ApiResponse(code = 150, message = "数据库操作异常")

    })
    @PreAuthorize("hasAuthority('system:user:admin')")
    @GetMapping("/user/queryUserByPage")
    public ResponseResult queryUserByPage(@RequestBody QueryByPageParams params){
        return userService.queryUserByPage(params);
    }


    @ApiOperation(value = "获取用户数量",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取用户数量")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 150, message = "数据库操作异常!请尽快联系系统管理员!")

    })
    @PreAuthorize("hasAuthority('system:user:admin')")
    @GetMapping("/user/getUserCount")
    public ResponseResult getUserCount(){
        return userService.getUserCount();
    }
}
