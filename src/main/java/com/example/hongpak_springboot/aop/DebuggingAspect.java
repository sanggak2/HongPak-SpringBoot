package com.example.hongpak_springboot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect // AOP클래스 선언
@Component  // IOC에 객체 등록
@Slf4j
public class DebuggingAspect {
    // 주입대상 지정 : api에 있는 모든 컨트롤러 메소드
    @Pointcut("execution(* com.example.hongpak_springboot.api.*.*(..))")
    private void cut() {}

    // 실행 시점 설정 : cut이 수행되기 전에 수행되는 함수
    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint) { // cut()의 대상메서드
        // 입력값 가져오기
        Object[] args = joinPoint.getArgs();

        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        // 메소드명
        String funcName = joinPoint.getSignature()
                .getName();

        // 입력값 로깅
        // CommentService#create() 입력값 => 4
        // CommentService#create() 입력값 => CommentDto(id=null . . .)
        for(Object arg : args) {
            log.info("{}#{}의 입력값 => {}", className, funcName, arg);
        }
    }

    // @AfterReturning : cut이 가리키는 대상이 성공적으로 실행된 후에 실행
    @AfterReturning(value = "cut()", returning = "result")
    public void loggingReturn(JoinPoint joinPoint   // cut의 대상메서드
                            , Object result) {      // 리턴값
        // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        // 메소드명
        String funcName = joinPoint.getSignature()
                .getName();

        // 반환값 로깅
        // CommentService#create() 반환값 => CommentDto(id=null . . .)
        log.info("{}#{}의 반환값 => {}", className, funcName, result);
    }
}
