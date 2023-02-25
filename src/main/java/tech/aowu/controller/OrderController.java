package tech.aowu.controller;

import io.swagger.annotations.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
