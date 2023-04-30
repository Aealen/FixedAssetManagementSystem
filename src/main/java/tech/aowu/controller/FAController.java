package tech.aowu.controller;

import io.swagger.annotations.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.aowu.entity.po.FaFixedasset;
import tech.aowu.entity.po.FaType;
import tech.aowu.entity.vo.FAQueryParams;
import tech.aowu.entity.vo.ResponseResult;
import tech.aowu.entity.vo.QueryByPageParams;
import tech.aowu.service.FAService;
import tech.aowu.service.SysLogService;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description: TODO
 * @ClassName: FAController
 * @Author: Aealen
 * @Date: 2023/2/18 21:46
 */

@Api(tags = "FAController", description = "固定资产相关API")
@RequestMapping("/fa")
@RestController
public class FAController {

    @Resource
    FAService faService;

    @Resource
    SysLogService sysLogService;


    @ApiOperation(value = "分页查询固定资产",notes = "<span style='color:red;'>详细描述：</span>&nbsp;分页查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", dataType = "String", defaultValue = "" ,paramType = "body"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "String", defaultValue = "",paramType = "body"),
            @ApiImplicitParam(name = "perPage", value = "每页数量", dataType = "String", defaultValue = "",paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 150, message = "数据库操作异常")

    })
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:worker','system:user:custodian')")
    @PostMapping("/queryFaByPage")
    public ResponseResult queryFaByPage(@RequestBody QueryByPageParams params){
        return faService.queryFaByPage(params);
    }

    @ApiOperation(value = "修改资产信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;根据ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "String", defaultValue = "" ,paramType = "path")
    })
//    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:custodian','system:user:worker','system:user:reporter')")
    @PermitAll
    @PostMapping("/updatefa")
    public ResponseResult updatefa(@RequestBody FaFixedasset faFixedasset, HttpServletRequest request){
        sysLogService.WriteLog("FAController",request.getHeader("Authorization"),"资产信息修改 ID： "+faFixedasset.getFid());

        return faService.updatefa(faFixedasset);
    }


    @ApiOperation(value = "根据ID查询",notes = "<span style='color:red;'>详细描述：</span>&nbsp;修改资产信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "String", defaultValue = "" ,paramType = "path")
    })
//    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:custodian','system:user:worker','system:user:reporter')")
    @PermitAll
    @PostMapping("/queryFaByID/{id}")
    public ResponseResult queryFaByPageByID(@PathVariable(value = "id") Long params){
        return faService.queryFaByPageByID(params);
    }
    @ApiOperation(value = "根据类型和部门查询资产",notes = "<span style='color:red;'>详细描述：</span>&nbsp;根据类型和部门查询资产")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tid", value = "tID", dataType = "String", defaultValue = "" ,paramType = "body"),
            @ApiImplicitParam(name = "did", value = "dID", dataType = "String", defaultValue = "" ,paramType = "body")
    })
//    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:custodian','system:user:worker','system:user:reporter')")
    @PermitAll
    @PostMapping("/queryFaByTDID")
    public ResponseResult queryFaByTDID(@RequestBody FAQueryParams params){
        return faService.queryFaByTDID(params);
    }





    @ApiOperation(value = "固定资产总数",notes = "<span style='color:red;'>详细描述：</span>&nbsp;固定资产总数")
    @ApiResponses({
            @ApiResponse(code = 150, message = "数据库操作异常")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:reporter','system:user:worker','system:user:custodian')")
    @GetMapping("/getFaCount")
    public ResponseResult getFaCount(){
        return faService.getFaCount();
    }


    @ApiOperation(value = "固定资产总数",notes = "<span style='color:red;'>详细描述：</span>&nbsp;固定资产总数")
    @ApiResponses({
            @ApiResponse(code = 150, message = "数据库操作异常")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:reporter','system:user:worker','system:user:custodian')")
    @PostMapping("/getFASearchCount")
    public ResponseResult getFASearchCount(@RequestBody QueryByPageParams params){
        return faService.getFASearchCount(params);
    }




    @ApiOperation(value = "新增固定资产",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增固定资产")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", dataType = "String", defaultValue = "" ,paramType = "body"),

    })
    @ApiResponses({
            @ApiResponse(code = 150, message = "数据库操作异常")

    })
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:custodian')")
    @PostMapping("/addFa")
    public ResponseResult addFa(@RequestBody FaFixedasset faFixedasset,HttpServletRequest request){
        sysLogService.WriteLog("FAController",request.getHeader("Authorization"),"新增固定资产： "+faFixedasset.getName());
        return faService.addFa(faFixedasset);
    }



    @ApiOperation(value = "删除固定资产",notes = "<span style='color:red;'>详细描述：</span>&nbsp;新增固定资产")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fid", value = "资产id", dataType = "String", defaultValue = "" ,paramType = "path"),

    })
    @ApiResponses({
            @ApiResponse(code = 150, message = "数据库操作异常")

    })
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:custodian')")
    @GetMapping("/delFa/{fid}")
    public ResponseResult delFa(@PathVariable Long fid,HttpServletRequest request){

        sysLogService.WriteLog("FAController",request.getHeader("Authorization"),"删除固定资产 ID："+fid);


        return faService.changeFaStatus(fid,1);
    }


    @ApiOperation(value = "获取所有资产类型",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取所有资产类型")
    @ApiResponses({
            @ApiResponse(code = 150, message = "数据库操作异常")
    })
//    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:custodian')")
    @PermitAll
    @GetMapping("/getAllType")
    public ResponseResult getAllType(){
        return faService.getAllType();
    }


    @ApiOperation(value = "获取所有资产类型(分页)",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取所有资产类型")
    @ApiResponses({
            @ApiResponse(code = 150, message = "数据库操作异常")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin')")
    @PostMapping("/getAllTypeByPage")
    public ResponseResult getAllTypeByPage(@RequestBody QueryByPageParams queryByPageParams){
        return faService.getAllTypeByPage(queryByPageParams);
    }
    @ApiOperation(value = "获取所有类型总数",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取所有类型总数")
    @ApiResponses({
            @ApiResponse(code = 150, message = "数据库操作异常")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin')")
    @GetMapping("/getTypeCount")
    public ResponseResult getTypeCount(){
        return faService.getTypeCount();
    }

    @ApiOperation(value = "获取所有类型总数",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取所有类型总数")
    @ApiResponses({
            @ApiResponse(code = 150, message = "数据库操作异常"),
            @ApiResponse(code = 170, message = "类型已存在")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin')")
    @PostMapping("/addType")
    public ResponseResult addType( String typename,HttpServletRequest request){
        sysLogService.WriteLog("FAController",request.getHeader("Authorization"),"新增类型： "+typename);

        return faService.addType(typename);
    }


    @ApiOperation(value = "更新类型信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;更新类型信息")
    @ApiResponses({
            @ApiResponse(code = 150, message = "数据库操作异常"),
            @ApiResponse(code = 171, message = "类型不存在")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin')")
    @PostMapping("/updateTypeInfo")
    public ResponseResult updateTypeInfo(@RequestBody FaType faType,HttpServletRequest request){
        sysLogService.WriteLog("FAController",request.getHeader("Authorization"),"更新类型信息  ID："+faType.getTid());

        return faService.updateTypeInfo(faType);
    }






}
