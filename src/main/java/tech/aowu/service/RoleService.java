package tech.aowu.service;

import tech.aowu.entity.ResponseResult;

/**
 * @Description: TODO
 * @ClassName: RoleService
 * @Author: Aealen
 * @Date: 2023/2/18 15:19
 */
public interface RoleService {


    /**
     * 获取所有角色
     * @return
     */
    ResponseResult getAllRoles();

    /**
     * 更改用户权限
     * @param uid
     * @param rid
     * @return
     */
    ResponseResult setUserRole(Long uid,Long rid);
}
