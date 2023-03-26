package tech.aowu.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 需要记录的用户操作的注解
 * @ClassName: UserOpreateLogAnnotation
 * @Author: Aealen
 * @Date: 2023/3/26 16:02
 */

//只可在方法上加
@Target({ElementType.METHOD,ElementType.TYPE})
//运行时生效
@Retention(RetentionPolicy.RUNTIME)
public @interface UserOpreateLogAnnotation {

}
