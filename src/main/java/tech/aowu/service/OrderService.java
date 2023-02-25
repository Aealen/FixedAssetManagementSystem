package tech.aowu.service;

import tech.aowu.entity.vo.QueryByPageParams;
import tech.aowu.entity.vo.ResponseResult;

/**
 * @Description: TODO
 * @ClassName: OrderService
 * @Author: Aealen
 * @Date: 2023/2/25 13:53
 */
public interface OrderService {


    ResponseResult getCount();

    ResponseResult queryByPage(QueryByPageParams params);

    ResponseResult getOrderByID(Long id);

    ResponseResult delOrder(Long id);

    ResponseResult updateOrderStatus(Long id, Long status);
}