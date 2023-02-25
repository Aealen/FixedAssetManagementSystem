package tech.aowu.controller;

import io.swagger.annotations.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.aowu.entity.vo.QueryByPageParams;
import tech.aowu.entity.vo.ResponseResult;
import tech.aowu.service.OrderService;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @ClassName: OrderController
 * @Author: Aealen
 * @Date: 2023/2/25 13:54
 */
@Api(tags = "OrderController", description = "订单相关")
@RequestMapping("/order")
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @ApiOperation(value = "分页查询订单信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;分页查询订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", dataType = "String", defaultValue = "" ,paramType = "body"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "String", defaultValue = "",paramType = "body"),
            @ApiImplicitParam(name = "perPage", value = "每页数量", dataType = "String", defaultValue = "",paramType = "body")
    })
    @PreAuthorize("hasAuthority('system:user:admin')")
    @PostMapping("/queryByPage")
    public ResponseResult queryByPage(@RequestBody QueryByPageParams params){
        return orderService.queryByPage(params);
    }

    @ApiOperation(value = "分页查询订单信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;分页查询订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", dataType = "String", defaultValue = "" ,paramType = "body"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "String", defaultValue = "",paramType = "body"),
            @ApiImplicitParam(name = "perPage", value = "每页数量", dataType = "String", defaultValue = "",paramType = "body")
    })
    @PreAuthorize("hasAuthority('system:user:admin')")
    @GetMapping("/getCount")
    public ResponseResult getCount(){
        return orderService.getCount();
    }
    @ApiOperation(value = "根据ID获取订单",notes = "<span style='color:red;'>详细描述：</span>&nbsp;根据ID获取订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "String", defaultValue = "" ,paramType = "path")
    })
    @PreAuthorize("hasAuthority('system:user:admin')")
    @GetMapping("/getOrderByID/{id}")
    public ResponseResult getOrderByID(@PathVariable Long id){
        return orderService.getOrderByID(id);
    }


    @ApiOperation(value = "根据ID删除订单",notes = "<span style='color:red;'>详细描述：</span>&nbsp;根据ID删除订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "String", defaultValue = "" ,paramType = "path")
    })
    @PreAuthorize("hasAuthority('system:user:admin')")
    @GetMapping("/del/{id}")
    public ResponseResult delOrder(@PathVariable Long id){
        return orderService.delOrder(id);
    }

    @ApiOperation(value = "根据ID修改订单状态",notes = "<span style='color:red;'>详细描述：</span>&nbsp;根据ID修改订单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "String", defaultValue = "" ,paramType = "path"),
            @ApiImplicitParam(name = "status", value = "status", dataType = "String", defaultValue = "" ,paramType = "path")
    })
    @PreAuthorize("hasAuthority('system:user:admin')")
    @GetMapping("/updateOrderStatus/{id}/{status}")
    public ResponseResult updateOrderStatus(@PathVariable Long id,@PathVariable Long status){
        return orderService.updateOrderStatus(id,status);
    }


}