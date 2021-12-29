package com.lee.common.exception;

import com.lee.common.result.R;
import com.lee.common.result.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author helloyore
 * @createTime 2021年12月24日 15:17:00
 * @Description
 */
//这个注解就是项目中一旦发生异常，就会跑到这个方法进行执行 相当于项目中的一个异常切面
//捕获我们发生的异常，整个做一个切面，然后切到正常的业务逻辑上来，然后处理完异常后在这个方法直接返回了
//@ControllerAdvice
@Slf4j //打印日志
@RestControllerAdvice // 改成这个注解，所有类都做异常处理  而且需要应用程序扫瞄到他，所以我们需要对这里再做处理
public class UnifiedExceptionHandler {

    //异常处理方法，处理一些具体异常信息，并给前端发送指令

    //    @ResponseBody //也做一个json返回前台 所以加这个注解 但是这个包里所有类都会做异常处理方法，所以拉出来放到头上
    //法1。 不灵活
    @ExceptionHandler(value = Exception.class)
    public R handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.error();
    }
    //法2 针对每个异常单独写，太繁琐
    @ExceptionHandler(value = BadSqlGrammarException.class)
    public R handleException(BadSqlGrammarException e) {
        log.error(e.getMessage(), e);
        return R.setResult(ResponseEnum.BAD_SQL_GRAMMAR_ERROR);
    }
    //法3 自定义异常
    @ExceptionHandler(value = BusinessException.class)
    public R handleException(BusinessException e) {
        log.error(e.getMessage(), e);
        return R.error().message(e.getMessage()).code(e.getCode());
    }

    /**
     * Controller上一层相关异常 前端进入Controller之前的各种异常
     * 批量的异常捕获，处理
     */
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MethodArgumentNotValidException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    //上面的太多了，就写一个父类exception就行
    public R handleServletException(Exception e) {
        log.error(e.getMessage(), e);
        //SERVLET_ERROR(-102, "servlet请求异常"),
        //前端也不用知道太多内容，重要的是我们后段日志打印了解就行
//        return R.setResult(ResponseEnum.SERVLET_ERROR); //这个好像也行，更简洁一些
        return R.error().message(ResponseEnum.SERVLET_ERROR.getMessage()).code(ResponseEnum.SERVLET_ERROR.getCode());
    }

}
