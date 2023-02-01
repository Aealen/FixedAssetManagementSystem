package tech.aowu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tech.aowu.entity.LoginUser;
import tech.aowu.entity.ResponseResult;
import tech.aowu.entity.dto.UmUser;
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
        String jwt = JwtUtil.createJWT(userId);
        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
        //把token响应给前端
        HashMap<String,String> map = new HashMap<>();


        map.put("token",jwt);
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

        return new ResponseResult(200,"注销成功");
    }
}

