package com.example.deliveryApp.aop;

import com.example.deliveryApp.order.dto.OrderCreateRequestDto;
import com.example.deliveryApp.order.dto.OrderCreateResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect //이 클래스는 AOP에서 사용할 관점
@Component
@Slf4j
public class LogAspect {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-HH-dd hh:mm:ss");

    // createOrder 실행 시 log 찍기
    @Around("execution(* com.example.deliveryApp.order.service.OrderService.createOrder(..))")
    public Object createOrderLogging(ProceedingJoinPoint joinPoint) throws Throwable {

        String requestTime = LocalDateTime.now().format(formatter);
        OrderCreateRequestDto orderCreateRequestDto = null;

        // OrderCreateRequestDto의 매개변수가 null값이 아닌지 확인 후 orderCreateRequestDto로 변환
        Object[] orderCreateRequestDtoArgs = joinPoint.getArgs();
        if(orderCreateRequestDtoArgs[0] instanceof OrderCreateRequestDto) {
            orderCreateRequestDto = (OrderCreateRequestDto) orderCreateRequestDtoArgs[0];
        }

        //메서드의 실제 실행을 담담(호출하지 않으면 메서드가 실행되지 않고 결과적으로 응답데이터도 제대로 반환되지 않음)
        Object result = joinPoint.proceed();

        //result가 OrderCreateResponseDto 형태인지 확인 후 createResponseDto형태로 변환
        if(result instanceof OrderCreateResponseDto) {
            OrderCreateResponseDto responseDto = (OrderCreateResponseDto) result;

            log.info("requestTime : {}",requestTime);
            log.info("storeId : {}",orderCreateRequestDto.getStoreId());
            log.info("orderId : {}",responseDto.getOrderId());
        }

        return result;
    }


//    // logging 실행 시 log 찍기 (&& args(orderId, storeId) -> orderId, storeId 타입의 파라미터만 가져오기)
//    @Around("execution(* com.example.deliveryApp.order.service.OrderService.logging(..)) && args(orderId, storeId)")
//    public Object orderCancelStatusLogging(ProceedingJoinPoint joinPoint, Long orderId, Long storeId) throws Throwable {
//        String requestTime = LocalDateTime.now().format(formatter);
//
//        //log 찍기
//        log.info("requestTime : {}",requestTime);
//        log.info("storeId : {}",storeId);
//        log.info("orderId : {}",orderId);
//
//
//        Object result = joinPoint.proceed();
//
//        return result;
//    }

}
