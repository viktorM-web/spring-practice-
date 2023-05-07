package com.victor.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(2)
public class SecondAspect {

    @Around("com.victor.spring.aop.FirstAspect.anyFindByIdServiceMethod() && target(service) && args(id)")
    public Object addLoggingAround(ProceedingJoinPoint joinPoint, Object service, Object id) throws Throwable {
        log.info("Around before invoke findById method in class {}, with id {}", service, id);
        try {
            var result = joinPoint.proceed();
            log.info("Around after returning - invoke findById method in class {}, result {}", service, result);
            return result;
        } catch (Throwable ex) {
            log.info("Around after throwing - invoke findById method in class {}, exception {}: {}", service, ex.getClass(), ex.getMessage());
            throw ex;
        } finally {
            log.info("Around after (finally) - invoke findById method in class {}", service);
        }

    }
}
