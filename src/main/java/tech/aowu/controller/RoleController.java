package tech.aowu.controller;

import io.swagger.annotations.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.aowu.entity.vo.ResponseResult;
import tech.aowu.entity.po.UmUserRole;
import tech.aowu.service.RoleService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description: TODO
 * @ClassName: RoleController
 * @Author: Aealen
 * @Date: 2023/2/18 15:15
 */
@Api(tags = "RoleController", description = "角色相关接口")
@RestController
@RequestMapping("/role")
public class RoleController {



    @Resource
    RoleService roleService;

    @ApiOperation(value = "获取所有角色",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取所有角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "Token", dataType = "String", defaultValue = "" ,paramType = "header"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 152, message = "参数非法"),
            @ApiResponse(code = 150, message = "数据库操作异常!请尽快联系系统管理员!"),
    })
    @PreAuthorize("hasAuthority('system:user:admin')")
    @GetMapping("/getAllRoles")
    public ResponseResult getAllRoles(HttpServletRequest request){
        return roleService.getAllRoles();
    }

    @ApiOperation(value = "更改用户角色",notes = "<span style='color:red;'>详细描述：</span>&nbsp;更改用户角色,权限信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户id", dataType = "String", defaultValue = "" ,paramType = "body"),
            @ApiImplicitParam(name = "rid", value = "角色id", dataType = "String", defaultValue = "" ,paramType = "body"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 152, message = "参数非法"),
            @ApiResponse(code = 150, message = "数据库操作异常!请尽快联系系统管理员!"),
    })
    @PreAuthorize("hasAuthority('system:user:admin')")
    @PostMapping("/setUserRole")
    public ResponseResult setUserRole(@RequestBody UmUserRole umUserRole){
        return roleService.setUserRole(umUserRole.getUid(),umUserRole.getRid());
    }





}
