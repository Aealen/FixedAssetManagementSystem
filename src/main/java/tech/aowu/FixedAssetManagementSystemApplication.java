package tech.aowu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("tech.aowu.mapper")
@CrossOrigin(originPatterns = "*",allowCredentials = "true")
@EnableSwagger2

public class FixedAssetManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FixedAssetManagementSystemApplication.class, args);
    }

}
