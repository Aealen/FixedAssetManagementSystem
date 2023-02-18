package tech.aowu.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.aowu.entity.LoginUser;
import tech.aowu.entity.ResponseResult;
import tech.aowu.entity.UmUser;
import tech.aowu.entity.vo.UserView;
import tech.aowu.mapper.RoleMapper;
import tech.aowu.mapper.UserMapper;
import tech.aowu.service.LoginService;
import tech.aowu.utils.JwtUtil;
import tech.aowu.utils.RedisCache;


import java.util.HashMap;
import java.util.Objects;

/**
 * @Description: TODO
 * @ClassName: LoginServiceImpl
 * @Author: Aealen
 * @Date: 2023/1/31 20:36
 */


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 用户登陆
     * @Description: 用户登陆模块  登陆成功之后生产JWT
     * @param user
     * @return
     */
    @Override
    public ResponseResult login(UmUser user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }
        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getUid().toString();
        HashMap<String, String> jwtMap = new HashMap<>();
        jwtMap.put("uid",loginUser.getUser().getUid().toString());
        jwtMap.put("username",loginUser.getUser().getUsername());
        jwtMap.put("rid", String.valueOf(roleMapper.getRoleByUid(loginUser.getUser().getUid())));

        String jwt = JwtUtil.createJWT(JSON.toJSONString(jwtMap));
        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);

        //user表更新登陆时间
        int updateLoginTimeByUid = userMapper.updateLoginTimeByUid(loginUser.getUser().getUid());
        if (updateLoginTimeByUid==0)
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");

        //把token响应给前端
        HashMap<String,String> map = new HashMap<>();
        map.put("token",jwt);
//        map.put("authenticate",authenticate.getAuthorities().toString());
        return new ResponseResult(200,"登陆成功",map);
    }

    /**
     * 退出登录 --->  把redis中的用户信息删掉
     * @return ResponseResult
     */
    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getUid();
        //删除redis中的值
        redisCache.deleteObject("login:"+userid);
        return new ResponseResult(105,"用户登出");
    }

    /**
     * 用户注册
     * @param userView
     * @return  ResponseResult
     */
    @Override
    public ResponseResult regist(UserView userView) {
        //判断用户名是否存在
        //按用户名查用户
//        QueryWrapper<UmUser> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("username",userView.getUsername());
//        UmUser queryUser = userMapper.selectOne(queryWrapper);

        UmUser queryUser = userMapper.getUmUserByUsername(userView.getUsername());
        if(Objects.nonNull(queryUser)){
            return new ResponseResult(102,"用户已存在");
        }

        UmUser umUser = userView.setUmUser();

        //密码加密
        String encodedPassword = passwordEncoder.encode(umUser.getPassword());
        umUser.setPassword(encodedPassword);
        int regist = userMapper.regist(umUser);
        //角色关系赋值
        int roleWhenRegist = roleMapper.insetWhenRegist(umUser.getUid(), userView.getRoleId());
        if (roleWhenRegist==0||regist==0)
        if (regist==0)
            return new ResponseResult(150,"数据库操作异常!请尽快联系系统管理员!");

        return new ResponseResult(200,"注册成功");

    }
}

