package tech.aowu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.aowu.entity.ResponseResult;
import tech.aowu.entity.vo.QueryByPageParams;
import tech.aowu.service.UserService;
import tech.aowu.utils.JwtUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    @PostMapping("/user/queryUserByPage")
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


    @ApiOperation(value = "根据Token查询用户",notes = "<span style='color:red;'>详细描述：</span>&nbsp;根据Token查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Token", dataType = "String", defaultValue = "" ,paramType = "body"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 103, message = "用户不存在"),
            @ApiResponse(code = 150, message = "数据库操作异常")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:worker','system:user:reporter','system:user:custodian')")
    @GetMapping("/user/getUserViewByToken")
    public ResponseResult getUserViewByToken(HttpServletRequest request){

        return userService.getUserViewByToken(request.getHeader("Authorization"));
    }

    @ApiOperation(value = "根据ID查询用户",notes = "<span style='color:red;'>详细描述：</span>&nbsp;根据ID查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "1", dataType = "Long", defaultValue = "" ,paramType = "body"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 103, message = "用户不存在"),
            @ApiResponse(code = 150, message = "数据库操作异常")
    })
    @PreAuthorize("hasAuthority('system:user:admin')")
    @GetMapping("/user/getUserViewById/{uid}")
    public ResponseResult getUserViewById(@PathVariable Long uid){

        return userService.getUserViewById(uid);
    }

    @ApiOperation(value = "重置用户密码",notes = "<span style='color:red;'>详细描述：</span>&nbsp;重置用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "1", dataType = "Long", defaultValue = "" ,paramType = "body"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 103, message = "用户不存在"),
            @ApiResponse(code = 150, message = "数据库操作异常")
    })
    @PreAuthorize("hasAuthority('system:user:admin')")
    @GetMapping("/user/resetPassword/{uid}")
    public ResponseResult resetUserPassword(@PathVariable Long uid){

        return userService.resetUserPassword(uid);
    }

    @ApiOperation(value = "删除用户",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "1", dataType = "Long", defaultValue = "" ,paramType = "body"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 103, message = "用户不存在"),
            @ApiResponse(code = 150, message = "数据库操作异常")
    })
    @PreAuthorize("hasAuthority('system:user:admin')")
    @GetMapping("/user/delUser/{uid}")
    public ResponseResult delUser(@PathVariable Long uid){

        return userService.changeUserStatus(uid,1);
    }

    @ApiOperation(value = "删除用户",notes = "<span style='color:red;'>详细描述：</span>&nbsp;删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "1", dataType = "Long", defaultValue = "" ,paramType = "body"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 103, message = "用户不存在"),
            @ApiResponse(code = 150, message = "数据库操作异常")
    })
    @PreAuthorize("hasAuthority('system:user:admin')")
    @GetMapping("/user/getAllCustodian")
    public ResponseResult getAllCustodian(){

        return userService.getAllCustodian();
    }







}
