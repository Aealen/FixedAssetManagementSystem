package tech.aowu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.aowu.entity.vo.ResponseResult;
import tech.aowu.entity.po.UmUser;
import tech.aowu.entity.vo.UserView;
import tech.aowu.mapper.UserMapper;
import tech.aowu.service.LoginService;
import tech.aowu.service.MailService;
import tech.aowu.service.UserService;
import tech.aowu.utils.MailClient;
import tech.aowu.utils.RedisCache;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.List;
import java.util.Random;

@SpringBootTest
class FixedAssetManagementSystemApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;


    @Resource
    MailClient mailClient;

    @Resource
    MailService mailService;

    @Resource
    LoginService loginService;

    @Resource
    RedisCache redisCache;

    @Test
    public void testSendMail(){
//        String to="fangmingxuan@aowu.tech";
//        String subject="验证码";
//        String content="<h1>您的验证码为:</h1> <h3>8876</h3>";
//        mailService.sendHtmlMail(to,subject,content);
        ResponseResult responseResult = loginService.sendResetPasswordMail("fangmingxuan@aowu.tech");

        System.out.println(responseResult.getData());
        System.out.println(responseResult.getMsg());

        System.out.println((String) redisCache.getCacheObject("code:1"));
//        ResponseResult responseResult1 = loginService.checkCode("66159", "fangmingxuan@aowu.tech");
//        System.out.println((String) redisCache.getCacheObject("code:1"));


    }

    @Test
    public void testUserMapper(){
        List<UmUser> umUsers = userMapper.selectList(null);
        System.out.println(umUsers);
    }


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void testBCrypt(){
        //$2a$10$OZsnNlr8DORdXOyDeCrLbuxYm87f9U7MIg4QlV8jyu4TAkXxDVXb2
        System.out.println(passwordEncoder.encode("122122"));
    }

    @Resource
    private UserService userService;
    @Test
    public void changePwdTest(){
        ResponseResult responseResult = userService.changePwd(3L, "122123");
        System.out.println(responseResult.getMsg());
    }
    @Test
    public void changeEmailTest(){
        ResponseResult responseResult = userService.changeEmail(3L, "12aaaa2112@qq.com");
        System.out.println(responseResult.getMsg());
    }
    @Test
    public void changePhoneTest(){
//        ResponseResult responseResult = userService.changePhone(3L, "11122221111");
//        System.out.println(responseResult.getMsg());
        UmUser umUser = new UmUser();
        umUser.setUid(3L);
        umUser.setPhoneNum("99988887777");
        int result = userMapper.updateById(umUser);
        System.out.println(result);
    }

    @Test
    public void updateUserInfoByUid(){
        UserView userView = new UserView();
        userView.setId(3L);
        userView.setPhoneNum("19900090007");
        userView.setUsername("TestWorker");
        userView.setPassword("TestPassword");
        userView.setNickname("ImAWorker");
        userView.setEmail("workerTest@ap.tech");
        userView.setDeptId(3L);
        userView.setRoleId(3L);

        ResponseResult responseResult = userService.updateByUid(userView);
        System.out.println(responseResult.getMsg());



    }



}
