package tech.aowu.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.aowu.entity.po.UmUser;

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

    public UmUser setUmUser(){
        UmUser umUser=new UmUser();
        umUser.setUid(this.getId());
        umUser.setUsername(this.username);
        umUser.setPassword(this.password);
        umUser.setNickname(this.nickname);
        umUser.setDepartment(this.deptId);
        umUser.setPhoneNum(this.phoneNum);
        umUser.setEmail(this.email);
        return umUser;
    }
    public UmUser setUmUserWithoutPassword(){
        UmUser umUser=new UmUser();
        umUser.setUid(this.getId());
        umUser.setUsername(this.username);
        umUser.setNickname(this.nickname);
        umUser.setDepartment(this.deptId);
        umUser.setPhoneNum(this.phoneNum);
        umUser.setEmail(this.email);
        return umUser;
    }

    private Long id;
    private Float balance;
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
