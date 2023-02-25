package tech.aowu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import tech.aowu.entity.po.Order;
import tech.aowu.entity.vo.OrderView;

import java.util.List;

/**
 * @Description: TODO
 * @ClassName: OrderMapper
 * @Author: Aealen
 * @Date: 2023/2/25 13:53
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    int getCount();

    List<OrderView> getOrderByPage(String keyword, int currIndex, Integer perPage);

    OrderView getOrderByID(Long id);

    int updateOrderStatus(Long id, Long status);

    int delOrder(Long id);
}
