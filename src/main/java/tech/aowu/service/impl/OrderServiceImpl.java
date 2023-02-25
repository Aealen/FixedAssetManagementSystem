package tech.aowu.service.impl;

import org.springframework.stereotype.Service;
import tech.aowu.entity.vo.QueryByPageParams;
import tech.aowu.entity.vo.ResponseResult;
import tech.aowu.service.OrderService;

/**
 * @Description: TODO
 * @ClassName: OrderServiceImpl
 * @Author: Aealen
 * @Date: 2023/2/25 13:54
 */
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public ResponseResult queryByPage(QueryByPageParams params) {
        return null;
    }
}
