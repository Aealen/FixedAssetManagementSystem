package tech.aowu.service.impl;

import org.springframework.stereotype.Service;
import tech.aowu.entity.po.Order;
import tech.aowu.entity.vo.OrderView;
import tech.aowu.entity.vo.QueryByPageParams;
import tech.aowu.entity.vo.ResponseResult;
import tech.aowu.entity.vo.UserView;
import tech.aowu.mapper.OrderMapper;
import tech.aowu.service.OrderService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Description: TODO
 * @ClassName: OrderServiceImpl
 * @Author: Aealen
 * @Date: 2023/2/25 13:54
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Resource
    private OrderMapper orderMapper;



    /**
     * 获取用户总数
     * @return
     */
    @Override
    public ResponseResult getCount() {

        int userCount = orderMapper.getCount();
        if (userCount!=0){
            return new ResponseResult(200,userCount);
        }
        return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
    }

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public ResponseResult queryByPage(QueryByPageParams params) {
        if (params.getKeyword()==null||params.getKeyword().isEmpty()){
            params.setKeyword("");
        }
        int currIndex=(params.getPage()-1)*params.getPerPage();
        List<OrderView> orderByPage = orderMapper.getOrderByPage(params.getKeyword(),currIndex, params.getPerPage());
        if (Objects.isNull(orderByPage.get(0))){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
        }
        return new ResponseResult(200,"查询成功",orderByPage);

    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public ResponseResult getOrderByID(Long id) {
        OrderView orderView=orderMapper.getOrderByID(id);
        if (Objects.isNull(orderView)){
            return new ResponseResult(160,"无此订单信息");
        }
        return new ResponseResult(200, "success",orderView);
    }


    /**
     * 根据ID删除订单
     * @param id
     * @return
     */
    @Override
    public ResponseResult delOrder(Long id) {
        //判断存在

        ResponseResult orderByID = getOrderByID(id);
        if (orderByID.getCode()!=200){
            //不存在
            return new ResponseResult(160,"无此订单信息");
        }


        int i = orderMapper.delOrder(id);
        if (i<1){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
        }

        return new ResponseResult(200, "success");
    }
    /**
     * 根据ID修改订单状态
     * @param id
     * @return
     */
    @Override
    public ResponseResult updateOrderStatus(Long id, Long status) {
        //判断存在

        ResponseResult orderByID = getOrderByID(id);
        if (orderByID.getCode()!=200){
            //不存在
            return new ResponseResult(160,"无此订单信息");
        }

        int i = orderMapper.updateOrderStatus(id,status);
        if (i<1){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
        }

        return new ResponseResult(200, "success");
    }


}
