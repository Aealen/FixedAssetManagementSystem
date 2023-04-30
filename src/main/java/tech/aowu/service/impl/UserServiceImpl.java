package tech.aowu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import tech.aowu.entity.vo.*;
import tech.aowu.entity.po.UmUser;
import tech.aowu.entity.po.UmUserRole;
import tech.aowu.mapper.RoleMapper;
import tech.aowu.mapper.UserMapper;
import tech.aowu.service.UserService;
import tech.aowu.utils.JwtUtil;
import tech.aowu.utils.RedisCache;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Random;

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

    @Resource
    private RedisCache redisCache;


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
     * 获取在线用户数量
     * @return
     */
    public ResponseResult getOnlineUserCount(){
        int count = redisCache.getCount("login:*");
        return new ResponseResult(200,count);
    }

    /**
     * 获取搜索结果数量
     * @param params
     * @return
     */
    @Override
    public ResponseResult getSearchCount(QueryByPageParams params) {
        ResponseResult responseResult = queryUserByPage(params);
        List data = (List) responseResult.getData();
        if (Objects.isNull(data)){
            return new ResponseResult(200,"查询成功",0);
        }
        responseResult.setData(data.size());
        return responseResult;
    }

    /**
     * 获取根据部门和角色搜索结果数量
     * @param params
     * @return
     */
    @Override
    public ResponseResult getByDeptRoleCount(UserByDeptAndRoleParams params) {

        int count=userMapper.getByDeptRoleCount(params.getDeptId(),params.getRoleId());
        if(Objects.isNull(count)) return new ResponseResult(150,"查询异常");
        return new ResponseResult(200,"查询成功",count);
    }

    /**
     * 根据部门角色查询用户信息
     * @param params
     * @return
     */
    @Override
    public ResponseResult getByDeptRole(UserByDeptAndRoleParams params) {
        int currIndex=(params.getPage()-1)*params.getPerPage();
        List<UserView> userByPage = userMapper.getByDeptRole(params.getDeptId(),params.getRoleId(),currIndex, params.getPerPage());
        return new ResponseResult(200,"查询成功",userByPage);
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

        if (userByPage.size()==0){
            return new ResponseResult(153 ,"无搜索结果！");
        }
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

        //判断邮箱是否重复
        QueryWrapper<UmUser> umUserQueryWrapper = new QueryWrapper<>();
        umUserQueryWrapper.eq("email",userView.getEmail());
        UmUser userByEmail = userMapper.selectOne(umUserQueryWrapper);
        if (Objects.nonNull(userByEmail)){
            //非空  此邮箱已被使用
            //新旧邮箱若一致 且用户id不一致则报错
            if (userByEmail.getEmail()==userView.getEmail()&&userByEmail.getUid()!=userView.getId())
                return new ResponseResult(195,"邮箱已被使用!");
        }

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

        //判断邮箱是否重复
        QueryWrapper<UmUser> umUserQueryWrapper = new QueryWrapper<>();
        umUserQueryWrapper.eq("email",email);
        UmUser userByEmail = userMapper.selectOne(umUserQueryWrapper);
        if (Objects.nonNull(userByEmail)){
            //非空  此邮箱已被使用
            return new ResponseResult(195,"邮箱已被使用!");
        }


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

    /**
     * 获得所有负责人信息
     * @return
     */
    @Override
    public ResponseResult getAllCustodian() {
        List<CustodianView> custodianViews= userMapper.getAllCustodian();
        if (Objects.isNull(custodianViews)){
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");

        }
        return new ResponseResult(200,"success!",custodianViews);
    }




}
