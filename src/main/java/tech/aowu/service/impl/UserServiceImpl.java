package tech.aowu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import tech.aowu.entity.ResponseResult;
import tech.aowu.entity.UmUser;
import tech.aowu.entity.UmUserRole;
import tech.aowu.entity.vo.QueryByPageParams;
import tech.aowu.entity.vo.UserView;
import tech.aowu.mapper.RoleMapper;
import tech.aowu.mapper.UserMapper;
import tech.aowu.service.UserService;
import tech.aowu.utils.JwtUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Description: TODO
 * @ClassName: UserServiceImpl
 * @Author: Aealen
 * @Date: 2023/2/14 14:41
 */
@Service
@EnableTransactionManagement
public class UserServiceImpl implements UserService {



    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 获取用户总数
     * @return
     */
    @Override
    public ResponseResult getUserCount() {

        int userCount = userMapper.getUserCount();
        if (userCount!=0){
            return new ResponseResult(200,userCount);
        }
        return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
    }

    /**
     * 分页查询用户
     * @param params
     * @return
     */
    @Override
    public ResponseResult queryUserByPage(QueryByPageParams params) {

        if (params.getKeyword()==null||params.getKeyword().isEmpty()){
            params.setKeyword("");
        }
        int currIndex=(params.getPage()-1)*params.getPerPage();
        List<UserView> userByPage = userMapper.getUserByPage(params.getKeyword(),currIndex, params.getPerPage());

        return new ResponseResult(200,"查询成功",userByPage);
    }

    /**
     * 根据Token获取用户信息
     * @param token
     * @return 用户信息
     */
    @Override
    public ResponseResult getUserViewByToken(String token) {

        //解析Token
        Long uid=null;
        try {
            String tokenString = JwtUtil.parseJWT(token).getSubject();

            JSONObject jsonObject = JSON.parseObject(tokenString);


            uid= Long.parseLong(String.valueOf(jsonObject.get("uid")));


        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Objects.isNull(uid)){
            return new ResponseResult(106,"Token非法");
        }
        List<UserView> userViewById = userMapper.getUserViewById(uid);
        if (Objects.isNull(userViewById.get(0))){
            return new ResponseResult(103,"用户不存在");
        }

        return new ResponseResult(200,"查询成功", userViewById.get(0));
    }

    /**
     * 根据Uid获取用户信息
     * @param uid
     * @return 用户信息
     */
    @Override
    public ResponseResult getUserViewById(Long uid) {

        List<UserView> userViewById = userMapper.getUserViewById(uid);

        if (Objects.isNull(userViewById.get(0))){
            return new ResponseResult(103,"用户不存在");
        }

        return new ResponseResult(200,"查询成功", userViewById.get(0));
    }

    /**
     * 重置用户密码为 password
     * @param uid
     * @return
     */
    @Override
    public ResponseResult resetUserPassword(Long uid) {
        //重置密码 为password
        String newPassword = passwordEncoder.encode("password");
        UmUser umUser = new UmUser();
        umUser.setUid(uid);
        umUser.setPassword(newPassword);
        int update = userMapper.updateById(umUser);
        if (update==0)
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
        return new ResponseResult(200,"密码重置成功");
    }

    /**
     * 修改用户信息
     * @param userView
     * @return
     */
    @Override
    @Transactional  //由于有多个操作同时进行可能会有单一操作失败 所以加回滚
    public ResponseResult updateByUid(UserView userView) {

        //修改role
        int changeRole = roleMapper.updateById(new UmUserRole(userView.getId(), userView.getRoleId()));
        if (changeRole==0){
            System.out.println("角色修改失败");
            throw new RuntimeException("角色修改失败");
        }
        //UmUser 赋值
        UmUser umUser = userView.setUmUserWithoutPassword();
        System.out.println(umUser);
        int updateUser = userMapper.updateById(umUser);
        if (updateUser==0){
            System.out.println("用户修改失败");

            throw new RuntimeException("用户修改失败");
        }
        if (updateUser==0||changeRole==0){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
        }
        return new ResponseResult(200,"用户信息更新成功");
    }

    /**
     * 修改用户密码
     * @param uid
     * @param password
     * @return
     */
    @Override
    public ResponseResult changePwd(Long uid, String password) {
        String newPassword = passwordEncoder.encode(password);

        UmUser umUser = new UmUser();
        umUser.setUid(uid);
        umUser.setPassword(newPassword);
        int res = userMapper.updateById(umUser);

//        int i = userMapper.changeuserPwd(uid, newPassword);
        if (res==0){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
        }
        return new ResponseResult(200,"密码修改成功!");
    }

    /**
     * 修改用户邮箱
     * @param uid
     * @param email
     * @return
     */
    @Override
    public ResponseResult changeEmail(Long uid, String email) {

        UmUser umUser = new UmUser();
        umUser.setUid(uid);
        umUser.setEmail(email);
        int res = userMapper.updateById(umUser);

//        int i = userMapper.changeUserEmail(uid, email);
        if (res==0){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
        }
        return new ResponseResult(200,"邮箱修改成功!");
    }
    /**
     * 修改用户邮箱
     * @param uid
     * @param phone
     * @return
     */
    @Override
    public ResponseResult changePhone(Long uid, String phone) {

        UmUser umUser = new UmUser();
        umUser.setUid(uid);
        umUser.setEmail(phone);
        int res = userMapper.updateById(umUser);


//        int i = userMapper.changeUserPhone(uid, phone);
        if (res==0){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
        }
        return new ResponseResult(200,"号码修改成功!");
    }


    /**
     * 修改用户状态
     * @param uid
     * @param status 0正常 1 删除 2未认证
     * @return
     */
    @Override
    public ResponseResult changeUserStatus(Long uid,Integer status) {

        UmUser umUser = new UmUser();
        umUser.setUid(uid);
        umUser.setDelFlag(status); //0正常 1 删除 2未认证

        int res = userMapper.updateById(umUser);

        if (res==0){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");
        }
        return new ResponseResult(200,"用户状态修改成功!");
    }
}
