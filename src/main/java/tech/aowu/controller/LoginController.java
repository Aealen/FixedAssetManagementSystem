package tech.aowu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.aowu.entity.ResponseResult;
import tech.aowu.entity.dto.UmUser;

import tech.aowu.service.LoginService;

/**
 * @Description: TODO
 * @ClassName: LoginController
 * @Author: Aealen
 * @Date: 2023/1/31 20:32
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody UmUser user){

        return loginService.login(user);
    }

    @RequestMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }




}
