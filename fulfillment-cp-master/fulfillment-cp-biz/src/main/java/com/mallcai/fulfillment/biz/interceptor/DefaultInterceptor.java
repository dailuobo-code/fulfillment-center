package com.mallcai.fulfillment.biz.interceptor;


import com.mallcai.fulfillment.biz.interceptor.handle.HandleRequest;
import com.mallcai.fulfillment.biz.interceptor.handle.HandleResponse;
import com.mallcai.fulfillment.biz.interceptor.handle.Handler;
import com.mallcai.fulfillment.biz.interceptor.handle.HandlerHelper;
import com.mallcai.manager.common.util.ContextHolder;
import com.mallcai.manager.common.util.LazyLogs;
import com.mallcai.manager.common.util.TraceIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;


@Slf4j
@Aspect
@Component
public class DefaultInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(DefaultInterceptor.class);

  private static final String MDC_TRACE_ID = "INNER_TRACE_ID";

  private static final String SEPARATE = ", ";

  /**
   * 异常处理器
   */
  @Autowired
  private Handler exceptionHandler;


    @Pointcut("execution(* com.mallcai.fulfillment.biz.dubbo.impl..*.*(..))")//两个..代表所有子目录，最后括号里的两个..代表所有参数
    public void managerExPoint() {
    }

    @Around("managerExPoint()")
    public Object intercept(ProceedingJoinPoint pjp) throws Throwable {
        HandleRequest request = this.buildHandleRequest(pjp);
        // ProfileTracer profileTracer = new ProfileTracer();
        Object obj = null;
        try {
          //log tradeId
          MDC.put(MDC_TRACE_ID, TraceIDGenerator.generate());

          //buffer request
          String beforeExecute = bufferRequestBeforeExecute(pjp);
          request.setOriginalRequestParam(beforeExecute);
          //执行目标方法
          obj = pjp.proceed();
          //buffer response
          request.setResult(obj);
          String afterExecute = bufferResponseAfterExecute(request);

          LazyLogs.info(logger, getPrintContent(beforeExecute, afterExecute));
        } catch (Exception e) {
          request.setException(e);
          HandleResponse response = this.exceptionHandler.handle(request);
          obj = response.getResult();
        } finally {
          ContextHolder.clean();
          MDC.remove(MDC_TRACE_ID);
        }
        return obj;
  }

  private HandleRequest buildHandleRequest(ProceedingJoinPoint pjp) {
    long startTime = (new Date()).getTime();
    HandleRequest request = new HandleRequest();
    request.setStartTime(Long.valueOf(startTime));
    request.setProceedingJoinPoint(pjp);
    return request;
  }


  public void setExceptionHandler(Handler exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
  }

  private String bufferRequestBeforeExecute(ProceedingJoinPoint pjp) {
    StringBuffer bufferResult = new StringBuffer();
    String requestParam = HandlerHelper.getRequest(pjp);
    bufferResult.append("request: [").append(requestParam).append("]");

    return bufferResult.toString();
  }


  private String bufferResponseAfterExecute(HandleRequest handleRequest) {
    StringBuffer bufferResult = new StringBuffer();

    String response = HandlerHelper.getResponse(handleRequest);
    bufferResult.append(", response: [").append(response).append("]");

    long runTime = System.currentTimeMillis() - handleRequest.getStartTime();
    bufferResult.append(", runtime: [").append(runTime).append("ms]");


    return bufferResult.toString();
  }


  private String getPrintContent(String beforeExecute, String afterExecute) {
    StringBuffer bufferResult = new StringBuffer();
    String printContent = bufferResult.append(beforeExecute).append(afterExecute).toString();
    if (printContent.startsWith(SEPARATE)) {
      printContent = printContent.substring(2);
    }
    return printContent;
  }
}

