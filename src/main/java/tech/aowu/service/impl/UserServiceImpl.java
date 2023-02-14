package tech.aowu.service.impl;

import org.springframework.stereotype.Service;
import tech.aowu.entity.ResponseResult;
import tech.aowu.entity.vo.QueryByPageParams;
import tech.aowu.mapper.UserMapper;
import tech.aowu.service.UserService;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @ClassName: UserServiceImpl
 * @Author: Aealen
 * @Date: 2023/2/14 14:41
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public ResponseResult getUserCount() {

        int userCount = userMapper.getUserCount();
        if (userCount!=0){
            return new ResponseResult(200,userCount);
        }
        return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
    }

    @Override
    public ResponseResult queryUserByPage(QueryByPageParams params) {



        return new ResponseResult(200,"ss");
    }
}
