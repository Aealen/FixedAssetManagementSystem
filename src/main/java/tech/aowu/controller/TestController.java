package tech.aowu.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @ClassName: TestController
 * @Author: Aealen
 * @Date: 2023/1/31 23:20
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/hello")
//    @PreAuthorize("hasAnyAuthority('system.dept.list')")
//    @PreAuthorize("hasAnyAuthority('admin','test','system.dept.list')")   //可匹配多个权限
//    @PreAuthorize("hasRole('system.dept.list')")
    @PreAuthorize("hasAuthority('system:user:test')")
    public String hello(){
        return "Hello";
    }

}
