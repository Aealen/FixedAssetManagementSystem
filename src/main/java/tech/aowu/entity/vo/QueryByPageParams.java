package tech.aowu.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 用于接收前端分页查询的参数
 * @ClassName: QueryByPageParams
 * @Author: Aealen
 * @Date: 2023/2/14 10:47
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryByPageParams {

    private String keyword;
    private Integer page;
    private Integer perPage;
    private Long rid;
    private Long uid;

}
