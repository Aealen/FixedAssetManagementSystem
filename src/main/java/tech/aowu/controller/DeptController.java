package tech.aowu.controller;

import io.swagger.annotations.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.aowu.entity.ResponseResult;
import tech.aowu.entity.vo.UserDept;
import tech.aowu.service.DeptService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description: TODO
 * @ClassName: DeptController
 * @Author: Aealen
 * @Date: 2023/2/18 16:39
 */
@Api(tags = "DeptController", description = "部门相关接口")
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Resource
    DeptService deptService;

    @ApiOperation(value = "获取所有部门信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取所有部门信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Token", dataType = "String", defaultValue = "" ,paramType = "header"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 152, message = "参数非法"),
            @ApiResponse(code = 150, message = "数据库操作异常!请尽快联系系统管理员!"),
    })
    @GetMapping("/getAllDepts")
    public ResponseResult getAllDepts(HttpServletRequest request){
        return deptService.getAllDepts();
    }
    @ApiOperation(value = "设置用户的部门",notes = "<span style='color:red;'>详细描述：</span>&nbsp;设置用户的部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户id", dataType = "String", defaultValue = "" ,paramType = "body"),
            @ApiImplicitParam(name = "did", value = "部门id", dataType = "String", defaultValue = "" ,paramType = "body"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 152, message = "参数非法"),
            @ApiResponse(code = 150, message = "数据库操作异常!请尽快联系系统管理员!"),
    })
    @PreAuthorize("hasAuthority('system:user:admin')")
    @PostMapping("/setUserDept")
    public ResponseResult setUserDept(@RequestBody UserDept userDept){
        return deptService.setUserDept(userDept.getUid(),userDept.getDid());
    }




}
