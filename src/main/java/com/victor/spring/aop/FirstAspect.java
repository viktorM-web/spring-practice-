package com.victor.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Aspect
@Component
public class FirstAspect {

    /*
        @within - check annotation on the class level
     */
    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {

    }

    /*
        within - check class type name
     */
    @Pointcut("within(com.victor.spring.service.*Service)")
    public void isServiceLayer() {
    }

    /*
    this - check AOP proxy class type
    target - check target object class type
     */
    @Pointcut("this(org.springframework.data.repository.Repository)")
//    @Pointcut("target(org.springframework.data.repository.Repository)")
    public void isRepositoryLayer() {
    }

    /*
    @annotation - check annotation on method level
     */
    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping() {
    }

    /*
    args - check method params type
    * - any param type
    .. - 0+ any params type
     */
    @Pointcut("isControllerLayer() && args(org.springframework.ui.Model,..)")
    public void hasModelParam() {
    }

    /*
    @args - check annotation on the param type
    * - any param type
    .. - 0+ any params type
     */
    @Pointcut("isControllerLayer() && @args(com.victor.spring.validation.UserInfo,..)")
    public void hasUserInfoParamAnnotation() {
    }

    /*
    bean - check bean name
     */
    @Pointcut("bean(*Service)")
    public void isServiceLayerBean() {
    }

    /*
    execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?)
     */
    @Pointcut("execution(public * com.victor.spring.service.*Service.findById(*))")
    public void anyFindByIdServiceMethod() {
    }

    @Before(value = "anyFindByIdServiceMethod()" +
                    "&& args(id)" +
                    "&& target(service)" +
                    "&& this(serviceProxy)" +
                    "&& @within(transactional)",
            argNames = "joinPoint,id,service,serviceProxy,transactional")
//    @Before("execution(public * com.victor.spring.service.*Service.findById(*))")
    public void addLogging(JoinPoint joinPoint,
                           Object id,
                           Object service,
                           Object serviceProxy,
                           Transactional transactional) {
        log.info("Before invoke findById method in class {}, with id {}", service, id);
    }

    @AfterReturning(value = "anyFindByIdServiceMethod()" +
                            "&& target(service)",
            returning = "result")
    public void addLoggingAfterReturning(Object result, Object service) {
        log.info("After returning - invoke findById method in class {}, result {}", service, result);
    }

    @AfterThrowing(value = "anyFindByIdServiceMethod()" +
                           "&& target(service)",
            throwing = "ex")
    public void addLoggingAfterThrowing(Throwable ex, Object service) {
        log.info("After throwing - invoke findById method in class {}, exception {}: {}", service, ex.getClass(), ex.getMessage());
    }

    @After("anyFindByIdServiceMethod()" +
           "&& target(service)")
    public void addLoggingAfterFinally(Object service) {
        log.info("After (finally) - invoke findById method in class {}", service);
    }

    @Around("anyFindByIdServiceMethod() && target(service) && args(id)")
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
