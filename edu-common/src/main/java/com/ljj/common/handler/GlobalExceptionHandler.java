package com.ljj.common.handler;



import com.ljj.common.exception.SPException;
import com.ljj.common.utils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error();
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了自定义异常");
    }

    @ExceptionHandler(SPException.class)
    @ResponseBody
    public R error(SPException e){
        return R.error().message(e.getMessage()).code(e.getCode()); 
    }
}
