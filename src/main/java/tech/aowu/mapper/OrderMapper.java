package tech.aowu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import tech.aowu.entity.po.Order;

/**
 * @Description: TODO
 * @ClassName: OrderMapper
 * @Author: Aealen
 * @Date: 2023/2/25 13:53
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
