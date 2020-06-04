package za.co.bmw.invoice.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    @Value("${aspects.logging.length:500}")
    private int logStringLength;

    @Autowired
    private ObjectMapper objectMapper;

    @Before("execution(* za..*Controller.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        Logger log = LoggerCache.findLogger(joinPoint.getTarget().getClass());
        log.info("Started Controller: " + joinPoint + " with Arguments: " + toJSON(joinPoint.getArgs()));
    }

    @AfterReturning(
            pointcut = "execution(* za..*Controller.*(..))",
            returning= "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        Logger log = LoggerCache.findLogger(joinPoint.getTarget().getClass());
        log.info("Completed Controller: " + joinPoint + " with Result: " + toJSON(result));
    }

    @AfterThrowing(
            pointcut = "execution(* za..*Controller.*(..))",
            throwing= "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        Logger log = LoggerCache.findLogger(joinPoint.getTarget().getClass());
        log.info("Failed Controller: " + joinPoint + " with Arguments: " + toJSON(joinPoint.getArgs()) + ". ERROR: " + error);
    }

    @Before("execution(* za..*Service.*(..))")
    public void logServiceStart(JoinPoint joinPoint) {
        Logger log = LoggerCache.findLogger(joinPoint.getTarget().getClass());
        log.info("Started Service: " + joinPoint + " with Arguments: " + toJSON(joinPoint.getArgs()));
    }

    @AfterReturning(
            pointcut = "execution(* za..*Service.*(..))",
            returning= "result")
    public void logServiceCompletion(JoinPoint joinPoint, Object result) {
        Logger log = LoggerCache.findLogger(joinPoint.getTarget().getClass());
        log.info("Completed Service: " + joinPoint + " with Result: " + toJSON(result));
    }

    @AfterThrowing(
            pointcut = "execution(* za..*Service.*(..))",
            throwing= "error")
    public void logServiceAfterThrowing(JoinPoint joinPoint, Throwable error) {
        Logger log = LoggerCache.findLogger(joinPoint.getTarget().getClass());
        log.info("Failed Controller: " + joinPoint + " with Arguments: " + toJSON(joinPoint.getArgs()) + ". ERROR: " + error);
    }

    String toJSON(Object... any) {
        if (any == null)
            return "null";

        int arrayMax = any.length - 1;
        if (arrayMax == -1)
            return "[]";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (int i = 0; ; i++) {
            try {
                stringBuilder.append(objectMapper.writeValueAsString(any[i]));
            } catch (JsonProcessingException e) {
                //DO nothing
            }
            if (i == arrayMax)
                return StringUtils.substring(stringBuilder.append(']').toString(), 0, logStringLength);
            stringBuilder.append(", ");
        }
    }
}
