package tech.aowu.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: TODO
 * @ClassName: OrderCount
 * @Author: Aealen
 * @Date: 2023/4/13 12:40
 */
@TableName("order_count")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCount {
    private String date;
    private String count;
}
