package tech.aowu.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 根据部门和角色查询用户的参数
 * @ClassName: UserByDeptAndRoleParams
 * @Author: Aealen
 * @Date: 2023/4/30 12:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserByDeptAndRoleParams {
    private Long DeptId;
    private Long RoleId;
    private Integer page;
    private Integer perPage;
}
