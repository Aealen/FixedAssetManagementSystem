package tech.aowu.controller;

import io.swagger.annotations.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.aowu.entity.po.Order;
import tech.aowu.entity.vo.QueryByPageParams;
import tech.aowu.entity.vo.ResponseResult;
import tech.aowu.service.OrderService;
import tech.aowu.service.SysLogService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    @Resource
    private SysLogService sysLogService;

    @ApiOperation(value = "分页查询订单信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;分页查询订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", dataType = "String", defaultValue = "" ,paramType = "body"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "String", defaultValue = "",paramType = "body"),
            @ApiImplicitParam(name = "perPage", value = "每页数量", dataType = "String", defaultValue = "",paramType = "body")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:custodian')")
    @PostMapping("/queryByPage")
    public ResponseResult queryByPage(@RequestBody QueryByPageParams params){
        return orderService.queryByPage(params);
    }
    @ApiOperation(value = "分页查询未审核订单信息",notes = "<span style='color:red;'>详细描述：</span>&nbsp;分页查询未审核订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", dataType = "String", defaultValue = "" ,paramType = "body"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "String", defaultValue = "",paramType = "body"),
            @ApiImplicitParam(name = "perPage", value = "每页数量", dataType = "String", defaultValue = "",paramType = "body")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:custodian')")
    @PostMapping("/queryByPageForCustodian")
    public ResponseResult queryByPageForCustodian(@RequestBody QueryByPageParams params){
        return orderService.queryByPageForCustodian(params);
    }
    @ApiOperation(value = "分页查询订单信息（根据角色和id）",notes = "<span style='color:red;'>详细描述：</span>&nbsp;分页查询订单信息（根据角色和id）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", dataType = "String", defaultValue = "" ,paramType = "body"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "String", defaultValue = "",paramType = "body"),
            @ApiImplicitParam(name = "perPage", value = "每页数量", dataType = "String", defaultValue = "",paramType = "body"),
            @ApiImplicitParam(name = "uid", value = "用户id", dataType = "Long", defaultValue = "",paramType = "body"),
            @ApiImplicitParam(name = "rid", value = "角色id", dataType = "Long", defaultValue = "",paramType = "body")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:reporter','system:user:worker','system:user:custodian')")
    @PostMapping("/getOrderByPageAndRole")
    public ResponseResult getOrderByPageAndRole(@RequestBody QueryByPageParams params){
        return orderService.getOrderByPageAndRole(params);
    }
    @ApiOperation(value = "新增订单",notes = "<span style='color:red;'>详细描述：</span>&nbsp;分页查询订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", dataType = "String", defaultValue = "" ,paramType = "body"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "String", defaultValue = "",paramType = "body"),
            @ApiImplicitParam(name = "perPage", value = "每页数量", dataType = "String", defaultValue = "",paramType = "body")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:reporter')")
    @PostMapping("/addOrder")
    public ResponseResult addOrder(@RequestBody Order order, HttpServletRequest request){
        sysLogService.WriteLog("OrderController",request.getHeader("Authorization"),"新增订单");

        return orderService.addOrder(order);
    }

    @ApiOperation(value = "根据部门查询worker",notes = "<span style='color:red;'>详细描述：</span>&nbsp;根据部门查询worker")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "did", value = "关键字", dataType = "Long", defaultValue = "" ,paramType = "path")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:reporter')")
    @PostMapping("/getWorker/{did}")
    public ResponseResult getWorker(@PathVariable Long did){
        return orderService.getWorker(did);
    }

    @ApiOperation(value = "根据订单状态获取订单数量",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取订单数量")
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:reporter','system:user:worker','system:user:custodian')")
    @GetMapping("/getCountByStatus/{status}")
    public ResponseResult getCountByStatus(@PathVariable int status){
        return orderService.getCountByStatus(status);
    }

    @ApiOperation(value = "获取订单趋势",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取订单趋势")
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:reporter','system:user:worker','system:user:custodian')")
    @GetMapping("/getOrderCountTrend")
    public ResponseResult getOrderCountTrend(){
        return orderService.getOrderCountTrend();
    }


    @ApiOperation(value = "获取订单数量",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取订单数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", dataType = "String", defaultValue = "" ,paramType = "body"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "String", defaultValue = "",paramType = "body"),
            @ApiImplicitParam(name = "perPage", value = "每页数量", dataType = "String", defaultValue = "",paramType = "body")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:custodian')")
    @GetMapping("/getCount")
    public ResponseResult getCount(){
        return orderService.getCount();
    }


    @ApiOperation(value = "获取订单搜索结果数量",notes = "<span style='color:red;'>详细描述：</span>&nbsp;获取订单数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", dataType = "String", defaultValue = "" ,paramType = "body"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "String", defaultValue = "",paramType = "body"),
            @ApiImplicitParam(name = "perPage", value = "每页数量", dataType = "String", defaultValue = "",paramType = "body")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:custodian','system:user:worker')")
    @PostMapping("/getOrderSearchCount")
    public ResponseResult getOrderSearchCount(@RequestBody QueryByPageParams params){
        return orderService.getOrderSearchCount(params);
    }



    @ApiOperation(value = "根据角色获取订单数量",notes = "<span style='color:red;'>详细描述：</span>&nbsp;根据角色获取订单数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rid", value = "关键字", dataType = "Integer", defaultValue = "" ,paramType = "path")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:reporter','system:user:worker','system:user:custodian')")
    @GetMapping("/getCountByRole/{rid}/{uid}")
    public ResponseResult getCountByRole(@PathVariable Long rid,@PathVariable Long uid){
        return orderService.getCountByRole(rid,uid);
    }
    @ApiOperation(value = "根据ID获取订单",notes = "<span style='color:red;'>详细描述：</span>&nbsp;根据ID获取订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "String", defaultValue = "" ,paramType = "path")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:reporter','system:user:worker','system:user:custodian')")
    @GetMapping("/getOrderByID/{id}")
    public ResponseResult getOrderByID(@PathVariable Long id){
        return orderService.getOrderByID(id);
    }


    @ApiOperation(value = "根据ID删除订单",notes = "<span style='color:red;'>详细描述：</span>&nbsp;根据ID删除订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "String", defaultValue = "" ,paramType = "path")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:reporter','system:user:worker','system:user:custodian')")
    @GetMapping("/del/{id}")
    public ResponseResult delOrder(@PathVariable Long id,HttpServletRequest request){
        sysLogService.WriteLog("OrderController",request.getHeader("Authorization"),"删除订单： "+id);

        return orderService.delOrder(id);
    }

    @ApiOperation(value = "根据ID修改订单状态",notes = "<span style='color:red;'>详细描述：</span>&nbsp;根据ID修改订单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "String", defaultValue = "" ,paramType = "path"),
            @ApiImplicitParam(name = "status", value = "status", dataType = "String", defaultValue = "" ,paramType = "path")
    })
    @PreAuthorize("hasAnyAuthority('system:user:admin','system:user:reporter','system:user:worker','system:user:custodian')")
    @GetMapping("/updateOrderStatus/{id}/{status}")
    public ResponseResult updateOrderStatus(@PathVariable Long id,@PathVariable Long status,HttpServletRequest request){
        sysLogService.WriteLog("OrderController",request.getHeader("Authorization"),"修改订单状态  ID："+id+" 新状态"+status);

        return orderService.updateOrderStatus(id,status);
    }


}
