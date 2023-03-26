package tech.aowu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import tech.aowu.entity.po.Order;
import tech.aowu.entity.po.UmUser;
import tech.aowu.entity.vo.OrderView;
import tech.aowu.entity.vo.QueryByPageParams;
import tech.aowu.entity.vo.ResponseResult;
import tech.aowu.entity.vo.UserView;
import tech.aowu.mapper.OrderMapper;
import tech.aowu.mapper.UserMapper;
import tech.aowu.service.OrderService;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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

    @Resource
    private UserMapper userMapper;



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



    @Override
    public ResponseResult getCountByRole(Long rid,Long uid) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("del_flag",0);

        Integer integer=null;
        if (rid==2){
            //报修员
            orderQueryWrapper.eq("reporter",uid);
            integer= orderMapper.selectCount(orderQueryWrapper);

        }else if (rid==3){
            //工人
            orderQueryWrapper.eq("worker",uid);
            integer= orderMapper.selectCount(orderQueryWrapper);

        }else if (rid==4){//负责人
            integer = orderMapper.getCountForCustodian(uid);
        }
        return new ResponseResult(200,integer);

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

    @Override
    public ResponseResult getOrderSearchCount(QueryByPageParams params) {
        ResponseResult responseResult = queryByPage(params);
        List data = (List) responseResult.getData();
        if (Objects.isNull(data)){
            return new ResponseResult(200,"查询成功",0);
        }
        responseResult.setData(data.size());
        return responseResult;
    }

    @Override
    public ResponseResult queryByPageForCustodian(QueryByPageParams params) {
        if (params.getKeyword()==null||params.getKeyword().isEmpty()){
            params.setKeyword("");
        }
        int currIndex=(params.getPage()-1)*params.getPerPage();
        //传入的UID获取 所属部门 did
        Long uid = userMapper.selectOne(new QueryWrapper<UmUser>().eq("uid", params.getUid())).getDepartment();

        System.out.println(uid);
        List<OrderView> orderByPage = orderMapper.queryByPageForCustodian(params.getKeyword(),currIndex, params.getPerPage(), uid);

        return new ResponseResult(200,"查询成功",orderByPage);
    }

    @Override
    public ResponseResult getOrderByPageAndRole(QueryByPageParams params) {
        if (params.getKeyword()==null||params.getKeyword().isEmpty()){
            params.setKeyword("");
        }
        int currIndex=(params.getPage()-1)*params.getPerPage();
        List<OrderView> orderByPage=null;

        //开始根据rid和uid分组

        orderByPage= orderMapper.getOrderByPageAndRole(params.getKeyword(),currIndex, params.getPerPage(),params.getRid(),params.getUid());
        if (Objects.isNull(orderByPage.get(0))){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
        }
        return new ResponseResult(200,"查询成功",orderByPage);
    }

    @Override
    public ResponseResult getWorker(Long did) {


        List<UserView> workers= userMapper.getWorker(did);

        return new ResponseResult(200,"success" ,workers);
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
     * 新增订单
     * @param order
     * @return
     */
    @Override
    public ResponseResult addOrder(Order order) {
        //只传进了描述  reporterId 和资产id
        //还需要 status del_flag 和 up_time

        order.setStatus(3); //3 未审核
        order.setDelFlag(0);

        order.setUpTime(new Timestamp(System.currentTimeMillis()));

        int insert = orderMapper.insert(order);
        if (insert<1){
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
