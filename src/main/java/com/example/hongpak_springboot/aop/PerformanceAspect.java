package com.example.hongpak_springboot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {
    // 특정 어노테이션을 지정
    @Pointcut("@annotation(com.example.hongpak_springboot.annotation.RunningTime)")
    private void enableRunningTime(){

    }

    // 특정 메소드(모든 메소드) 지정
    @Pointcut("execution(* com.example.hongpak_springboot..*.*(..))")
    private void cut(){

    }

    // @Around : 메서드 실행 전후 부가기능 삽입
    @Around("cut() && enableRunningTime()") // cut()과 enableRunningTime에서 지정한 메소드&&어노테이션을 만족한 대상
    public void logginRunningTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 메소드 수행 전, 측정 시작
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 메소드 수행
        Object returningObj = joinPoint.proceed();

        // 메소드명
        String funcName = joinPoint.getSignature()
                .getName();

        // 메소드 수행 후, 측정 종료 -> 로깅
        stopWatch.stop();
        log.info("{}의 총 수행 시간 => {}", funcName, stopWatch.prettyPrint());
    }
}
