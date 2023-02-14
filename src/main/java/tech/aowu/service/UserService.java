package tech.aowu.service;

import tech.aowu.entity.ResponseResult;
import tech.aowu.entity.vo.QueryByPageParams;

/**
 * @Description: TODO
 * @ClassName: UserService
 * @Author: Aealen
 * @Date: 2023/2/14 14:41
 */
public interface UserService {
    ResponseResult queryUserByPage(QueryByPageParams params);

    ResponseResult getUserCount();
}
