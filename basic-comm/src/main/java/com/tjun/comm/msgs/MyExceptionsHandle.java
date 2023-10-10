package com.tjun.comm.msgs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@ControllerAdvice
@RestControllerAdvice
public class MyExceptionsHandle {

	/**
	 * * 统一处理自定义业务异常
	 */
	@ExceptionHandler(MyException.class)
	public Msg<String> bizException(MyException ex) {
		log.error("统一处理自定义业务异常="+ex.getLocalizedMessage(), ex);
		return Msg.err(ex);
	}

	/**
	 * * 统一处理非自定义异常外的所有异常
	 */
	@ExceptionHandler(Exception.class)
	public Msg<Object> handleException(Exception e) {
		log.error(e.getMessage(), e);
		return Msg.err(e.getMessage());
	}

	/**
	 * * 兼容Validation校验框架：忽略参数异常处理器
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Msg<String> parameterMissingExceptionHandler(MissingServletRequestParameterException e) {
		log.error(e.getMessage(), e);
		return Msg.err("请求参数 " + e.getParameterName() + " 不能为空");
	}

	/**
	 * * 兼容Validation校验框架：缺少请求体异常处理器
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Msg<Object> parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
		log.error(e.getMessage(), e);
		return Msg.err("参数体不能为空");
	}

	/**
	 * * 兼容Validation校验框架：参数效验异常处理器
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Msg<Object> parameterExceptionHandler(MethodArgumentNotValidException e) {
		log.error(e.getMessage(), e);
		// 获取异常信息
		BindingResult exceptions = e.getBindingResult();
		// 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
		if (exceptions.hasErrors()) {
			List<ObjectError> errs = exceptions.getAllErrors();
			if (!errs.isEmpty()) {
				// 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
				FieldError fieldError = (FieldError) errs.get(0);
				return Msg.err(fieldError.getDefaultMessage());
			}
		}
		return Msg.err("请求参数校验异常");
	}
}
