package tech.aowu.service;

import tech.aowu.entity.vo.ResponseResult;
import tech.aowu.entity.vo.QueryByPageParams;
import tech.aowu.entity.vo.UserView;

/**
 * @Description: TODO
 * @ClassName: UserService
 * @Author: Aealen
 * @Date: 2023/2/14 14:41
 */
public interface UserService {
    ResponseResult queryUserByPage(QueryByPageParams params);

    ResponseResult getUserCount();

    ResponseResult updateByUid(UserView userView);

    ResponseResult changePwd(Long uid,String password);

    ResponseResult changeEmail(Long uid,String email);

    ResponseResult changePhone(Long uid,String Phone);

    ResponseResult changeUserStatus(Long uid,Integer status);

    ResponseResult getUserViewByToken(String token);

    ResponseResult getUserViewById(Long uid);

    ResponseResult resetUserPassword(Long uid);

    ResponseResult getAllCustodian();

}
