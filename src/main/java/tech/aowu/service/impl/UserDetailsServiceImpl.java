package tech.aowu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.aowu.entity.LoginUser;
import tech.aowu.entity.UmUser;
import tech.aowu.entity.vo.UserView;
import tech.aowu.mapper.PermissionMapper;
import tech.aowu.mapper.UserMapper;

import java.util.List;
import java.util.Objects;

/**
 * @Author 三更  B站： https://space.bilibili.com/663528522
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(User::getUserName,username);
//        User user = userMapper.selectOne(wrapper);

        QueryWrapper<UmUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        UmUser user = userMapper.selectOne(queryWrapper);

        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }
        //TODO 根据用户查询权限信息 添加到LoginUser中
//        List<String> list=new ArrayList<>(Arrays.asList("test","admin"));

        List<String> permslist = permissionMapper.selectPermsByUserId(user.getUid());


        //封装成UserDetails对象返回
        return new LoginUser(user,permslist);
    }
}
