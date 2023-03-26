package tech.aowu.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Description: 记录用户操作
 * @ClassName: UserOpreateLogAspect
 * @Author: Aealen
 * @Date: 2023/3/26 15:51
 */
@Component
@Aspect
public class UserOpreateLogAspect {

    //切入点
    @Pointcut("@annotation(tech.aowu.aspects.UserOpreateLogAnnotation)")
    public void userAspect(){}


    @AfterThrowing("userAspect()")
    public void whenThorwException(JoinPoint joinPoint){
        System.out.println(joinPoint.getArgs());
    }

    @After("userAspect()")
    public void doAfter(JoinPoint joinPoint){
        System.out.println(joinPoint.getArgs());
    }




}
