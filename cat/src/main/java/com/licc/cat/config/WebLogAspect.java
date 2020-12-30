package com.licc.cat.config;

import com.licc.cat.util.Result;
import com.licc.cat.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @package: com.example.ldemo
 * @className: ${TYPE_NAME}
 * @description: 调用接口打印性能日志以及接口报错之后记录错误日志
 * @author: 李臣臣
 * @createDate: 2019/4/15 16:55
 * @updateUser: 李臣臣
 * @updateDate: 2019/4/15 16:55
 * @updateRemark: The modified content
 * @version: 1.0
 * <p>copyright: Copyright (c) 2019</p>
 */

@Aspect
@Component
@Slf4j
public class WebLogAspect {

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /***
     *使用@Aspect注解将一个java类定义为切面类
     *使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等。
     *根据需要在切入点不同位置的切入内容
     *使用@Before在切入点开始处切入内容
     *使用@After在切入点结尾处切入内容
     *使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
     *使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
     *使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
     */

    @Pointcut("execution(public * com.rayeye.insurance.dic.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        log.info("开始....");
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            // 记录下请求内容
            log.info("URL : " + request.getRequestURL().toString());
            log.info("HTTP_METHOD : " + request.getMethod());
            log.info("IP : " + request.getRemoteAddr());
            log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("RESPONSE : " + ret);

        log.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()) + " ms");

        //防止内存泄露，对象使用完，删除
        startTime.remove();

    }

    /**
     * 统计方法执行耗时Around环绕通知
     *
     * @param joinPoint
     * @return
     */
    @Around("webLog()")
    public Object timeAround(ProceedingJoinPoint joinPoint) {
        //获取开始执行的时间
        long startTime = System.currentTimeMillis();
        // 定义返回对象、得到方法需要的参数
        Object obj = null;
        try {
            obj = joinPoint.proceed(joinPoint.getArgs());
            // 获取执行结束的时间
            long endTime = System.currentTimeMillis();
            // 打印耗时的信息
            log.info("=====>处理本次请求共耗时：{} ms", endTime - startTime);
        } catch (Throwable e) {
            //异常统一处理
            return handlerException(joinPoint, e);
        }
        return obj;
    }

    /**
     * @Description 处理接口调用异常
     * @Author 李臣臣
     * @Date 2019/12/23 0023 17:03
     * @Param
     * @Return
     * @Exception
     */
    private Result handlerException(ProceedingJoinPoint pjp, Throwable e) {
        log.error("Exception{方法：" + pjp.getSignature() + "， 参数：" + pjp.getArgs() + ",异常：" + e.getMessage() + "}", e);

        if (e instanceof IllegalStateException && "Committed".equals(e.getMessage())) {
            log.warn("本次请求已经结束，可能使用sendRedirect 或请求终止。");
        } else {
            log.error("=====>统计某方法执行耗时环绕通知出错", e);
        }

        //异常处理

        return Result.build().setMessage(e.getMessage()).setCode(ResultCode.HTTP_500);

    }
}