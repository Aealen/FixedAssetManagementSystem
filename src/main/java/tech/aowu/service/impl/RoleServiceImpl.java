package tech.aowu.service.impl;

import org.springframework.stereotype.Service;
import tech.aowu.entity.ResponseResult;
import tech.aowu.entity.UmRole;
import tech.aowu.entity.vo.UserView;
import tech.aowu.mapper.RoleMapper;
import tech.aowu.mapper.UserMapper;
import tech.aowu.service.RoleService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Description: TODO
 * @ClassName: RoleServiceImpl
 * @Author: Aealen
 * @Date: 2023/2/18 15:19
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleMapper roleMapper;
    @Resource
    UserMapper userMapper;

    @Override
    public ResponseResult getAllRoles() {
        List<UmRole> allRoles = roleMapper.getAllRoles();
        if (Objects.isNull(allRoles)){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
        }
        return new ResponseResult(200,allRoles);
    }

    @Override
    public ResponseResult setUserRole(Long uid,Long rid) {
        //判断传入参数合法
        List<UserView> userViewById = userMapper.getUserViewById(uid);
        UmRole roleById = roleMapper.getRoleById(rid);
        if (Objects.isNull(roleById)||Objects.isNull(userViewById.get(0))){
            //参数非法
            return new ResponseResult(152 ,"参数非法!");
        }
        //设置用户角色

        int updateUserRole = roleMapper.updateUserRole(uid, rid);
        if (updateUserRole==0){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
        }

        return new ResponseResult(200,"success");
    }
}
