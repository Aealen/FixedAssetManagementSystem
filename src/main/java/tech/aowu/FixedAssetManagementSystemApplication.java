package tech.aowu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@MapperScan("tech.aowu.mapper")
@CrossOrigin
public class FixedAssetManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FixedAssetManagementSystemApplication.class, args);
    }

}
