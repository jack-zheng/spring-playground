package t5.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnoAspect {
    @Around("execution(* t5.service.MyService.*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("before join point process...");
        Object ret = joinPoint.proceed();
        System.out.println("after join point process...");
        return ret;
    }
}
