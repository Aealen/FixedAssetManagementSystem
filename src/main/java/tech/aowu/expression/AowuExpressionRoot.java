package tech.aowu.expression;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import tech.aowu.entity.LoginUser;

import java.util.List;

/**
 * @Description: TODO
 * @ClassName: AowuExpressionRoot
 * @Author: Aealen
 * @Date: 2023/2/1 20:39
 */
@Component("AowuEx")
public class AowuExpressionRoot {

   public boolean hasAnyAuthority(String authority){
       //获取当前用户的权限
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       LoginUser loginUser = (LoginUser) authentication.getPrincipal();

       List<String> permissions = loginUser.getPermissions();
       //判断用户权限集合中是否存在 authority

       return permissions.contains(authority);
   }
}
