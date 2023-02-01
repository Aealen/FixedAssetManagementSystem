package tech.aowu.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tech.aowu.entity.ResponseResult;

import tech.aowu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: TODO
 * @ClassName: AccessDeniedHandlerImpl
 * @Author: Aealen
 * @Date: 2023/2/1 17:06
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        //处理异常
        ResponseResult result=new ResponseResult(HttpStatus.FORBIDDEN.value(),"权限不足");
        String jsonString = JSON.toJSONString(result);

        WebUtils.renderString(httpServletResponse,jsonString);

    }
}
