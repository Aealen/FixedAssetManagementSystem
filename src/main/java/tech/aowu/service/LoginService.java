package tech.aowu.service;


import tech.aowu.entity.ResponseResult;
import tech.aowu.entity.UmUser;
import tech.aowu.entity.vo.UserView;

/**
 * @Description: TODO
 * @ClassName: LoginService
 * @Author: Aealen
 * @Date: 2023/1/31 20:35
 */
public interface LoginService {

    ResponseResult login(UmUser user);
    ResponseResult logout();
    ResponseResult regist(UserView user);

}
