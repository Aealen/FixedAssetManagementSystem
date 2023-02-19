package tech.aowu.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.aowu.entity.LoginUser;
import tech.aowu.entity.vo.ResponseResult;
import tech.aowu.entity.po.UmUser;
import tech.aowu.entity.vo.UserView;
import tech.aowu.mapper.RoleMapper;
import tech.aowu.mapper.UserMapper;
import tech.aowu.service.LoginService;
import tech.aowu.service.MailService;
import tech.aowu.utils.JwtUtil;
import tech.aowu.utils.RedisCache;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @Description: TODO
 * @ClassName: LoginServiceImpl
 * @Author: Aealen
 * @Date: 2023/1/31 20:36
 */


@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisCache redisCache;

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private MailService mailService;



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
            return new ResponseResult(101,"用户名或密码错误!");

//            throw new RuntimeException("用户名或密码错误");
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

    /**
     * 发送重置密码的验证码
     * @param to  目标邮箱
     * @return
     */
    @Override
    public ResponseResult sendResetPasswordMail(String to) {

        //根据邮箱获取用户
        QueryWrapper<UmUser> umUserQueryWrapper = new QueryWrapper<>();
        umUserQueryWrapper.eq("email",to);
        umUserQueryWrapper.eq("del_flag",0);
        UmUser umUser = userMapper.selectOne(umUserQueryWrapper);
        if (Objects.isNull(umUser)){
            return new ResponseResult(191,"无此邮箱匹配的用户");
        }

        //产生五位随机数字
        String code = String.valueOf(new Random().nextInt(9999, 99999));

        if (mailService.sendResetPasswordCode(to, code)){
            //code存入redis
            redisCache.setCacheObject("code:" +umUser.getUid(),code);
            //设置失效时间 5min
            redisCache.expire("code:" +umUser.getUid(),1000*60*5);

            return new ResponseResult(200, "success");
        }else {
            return new ResponseResult(190,"邮件发送失败");
        }
    }


    /**
     * 检查验证码是否正确
     * @param code  用户传入的验证码
     * @return
     */
    @Override
    public ResponseResult checkCode(String code, String to){

        //根据邮箱获取用户
        QueryWrapper<UmUser> umUserQueryWrapper = new QueryWrapper<>();
        umUserQueryWrapper.eq("email",to);
        umUserQueryWrapper.eq("del_flag",0);
        UmUser umUser = userMapper.selectOne(umUserQueryWrapper);
        if (Objects.isNull(umUser)){
            return new ResponseResult(191,"无此邮箱匹配的用户");
        }

        String codeInRedis = redisCache.getCacheObject("code:" + umUser.getUid());

        if (code.equals(codeInRedis)){
            //删除redis中存的验证码
            redisCache.deleteObject("code:" + umUser.getUid());
            return new ResponseResult(200, "true");
        }else {
            return new ResponseResult(107,"验证码错误");
        }

    }

}

