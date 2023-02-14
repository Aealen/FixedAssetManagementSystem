package tech.aowu.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 用户视图
 * @ClassName: UserView
 * @Author: Aealen
 * @Date: 2023/2/14 11:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserView {
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String phoneNum;
    private String email;
    private Long deptId;
    private String deptName;
    private Long roleId;
    private String roleName;
    private String regTime;
    private String loginTime;
}
