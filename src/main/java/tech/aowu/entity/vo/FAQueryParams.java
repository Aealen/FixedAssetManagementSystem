package tech.aowu.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: FA根据类型和部门查询的参数
 * @ClassName: FAQueryParams
 * @Author: Aealen
 * @Date: 2023/2/26 21:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FAQueryParams {
    private Long tid;
    private Long did;
}
