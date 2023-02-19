package tech.aowu.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tech.aowu.entity.vo.ResponseResult;
import tech.aowu.utils.WebUtils;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: TODO
 * @ClassName: AuthenticationEntryPointImpl
 * @Author: Aealen
 * @Date: 2023/2/1 17:02
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //处理异常
        ResponseResult result=new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"用户认证失败请重新登陆");
        String jsonString = JSON.toJSONString(result);

        WebUtils.renderString(httpServletResponse,jsonString);


    }
}
