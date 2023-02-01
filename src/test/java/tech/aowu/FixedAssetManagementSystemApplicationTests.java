package tech.aowu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tech.aowu.entity.dto.UmUser;
import tech.aowu.mapper.UserMapper;

import java.util.List;

@SpringBootTest
class FixedAssetManagementSystemApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper(){
        List<UmUser> umUsers = userMapper.selectList(null);
        System.out.println(umUsers);
    }


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Test
    public void testBCrypt(){
        //$2a$10$OZsnNlr8DORdXOyDeCrLbuxYm87f9U7MIg4QlV8jyu4TAkXxDVXb2
        System.out.println(passwordEncoder.encode("122122"));
    }



}
