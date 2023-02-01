package tech.aowu.filter;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import tech.aowu.entity.LoginUser;
import tech.aowu.utils.JwtUtil;
import tech.aowu.utils.RedisCache;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Description: TODO
 * @ClassName: JwtAuthenticationTokenFilter
 * @Author: Aealen
 * @Date: 2023/1/31 22:25
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //获取Token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //没有Token  放行  不需解析token  交给后面过滤器
            filterChain.doFilter(request, response);
            return;
        }

        //解析Token
        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Token 非法!!");
        }

        //从redis获取用户信息
        String redisKey = "login:" + userid;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);

        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }
        //存入SecurityContextHolder
        // 用户信息
        //TODO 获取权限信息封装到Authentication

        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        //放行
        filterChain.doFilter(request,response);

    }
}
