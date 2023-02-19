package tech.aowu.controller;

import io.swagger.annotations.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.aowu.entity.FaFixedasset;
import tech.aowu.entity.FaType;
import tech.aowu.entity.ResponseResult;
import tech.aowu.entity.vo.QueryByPageParams;
import tech.aowu.service.FAService;

import javax.annotation.Resource;

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


    @ApiOperation(value = "分页查询固定资产",notes = "<span style='color:red;'>详细描述：</span>&nbsp;分页查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", dataType = "String", defaultValue = "" ,paramType = "body"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "String", defaultValue = "",paramType = "body"),
            @ApiImplicitParam(name = "perPage", value = "每页数量", dataType = "String", defaultValue = "",paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 150, message = "数据库操作异常")

    })
    @PreAuthorize("hasAuthority('system:user:admin')")
    @PostMapping("/queryFaByPage")
    public ResponseResult queryFaByPage(@RequestBody QueryByPageParams params){
        return faService.queryFaByPage(params);
    }

    @ApiOperation(value = "分页查询固定资产",notes = "<span style='color:red;'>详细描述：</span>&nbsp;分页查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", dataType = "String", defaultValue = "" ,paramType = "body"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "String", defaultValue = "",paramType = "body"),
            @ApiImplicitParam(name = "perPage", value = "每页数量", dataType = "String", defaultValue = "",paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 150, message = "数据库操作异常")

    })
    @PreAuthorize("hasAuthority('system:user:admin')")
    @GetMapping("/getFaCount")
    public ResponseResult getFaCount(){
        return faService.getFaCount();
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
    public ResponseResult addFa(@RequestBody FaFixedasset faFixedasset){
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
    public ResponseResult delFa(@PathVariable Long fid){
        return faService.changeFaStatus(fid,1);
    }


    @ApiOperation(value = "获取所有资产类型",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取所有资产类型")
    @ApiResponses({
            @ApiResponse(code = 150, message = "数据库操作异常")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:custodian')")
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
    public ResponseResult addType( String typename){
        return faService.addType(typename);
    }


    @ApiOperation(value = "更新类型信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;更新类型信息")
    @ApiResponses({
            @ApiResponse(code = 150, message = "数据库操作异常"),
            @ApiResponse(code = 171, message = "类型不存在")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin')")
    @PostMapping("/updateTypeInfo")
    public ResponseResult updateTypeInfo(@RequestBody FaType faType){
        return faService.updateTypeInfo(faType);
    }






}
