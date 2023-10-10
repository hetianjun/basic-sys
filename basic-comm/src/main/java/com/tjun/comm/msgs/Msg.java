package com.tjun.comm.msgs;

import cn.hutool.core.date.DateUtil;

import java.io.Serializable;


/**
 * 接口响应对应
 */

@lombok.Data
public class Msg<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	
//	@ApiModelProperty(value = "返回状态[0:正常,其他:异常]", name="code",example="0",allowableValues="")
	private Integer code;	//正常
	
//	@ApiModelProperty(value = "返回状态描述消息", name="msg",example="操作成功")
	private String msg;	
	
//	@ApiModelProperty(value = "返回结果对象", name="data",example="")
	private T data;	
	
	
//	@ApiModelProperty(value = "返回时间", name="time")
	private String time = DateUtil.now();

	public static <T> Msg<T> ok(){
		return new Msg<T>(MsgCodeEnums.SUCCESS.getCode(),"操作成功",null);
	}
	
	public static <T> Msg<T> ok(String msg){
		return new Msg<T>(MsgCodeEnums.SUCCESS.getCode(),msg,null);
	}
	public static <T> Msg<T> ok(T data){
		return new Msg<T>(MsgCodeEnums.SUCCESS.getCode(),"操作成功",data);
	}
	public static <T> Msg<T> ok(String msg,T data){
		return new Msg<T>(MsgCodeEnums.SUCCESS.getCode(),msg,data);
	}
	
	
	public static <T> Msg<T> err(MsgCodeEnums msgEnum){
		return new Msg<T>(msgEnum.getCode(),msgEnum.getMessage(),null);
	}
	
	public static <T> Msg<T> err(MsgCodeEnums msgEnum,T data){
		return new Msg<T>(msgEnum.getCode(),msgEnum.getMessage(),data);
	}
	
	public static <T> Msg<T> err(String msg){
		return new Msg<T>(MsgCodeEnums.ERROR.getCode(),msg,null);
	}
	
	public static <T> Msg<T> err(MyException ex){
		return new Msg<T>(ex.getCode(),ex.getMsg(),null);
	}
	
	public static <T> Msg<T> err(String msg,T data){
		return new Msg<T>(MsgCodeEnums.ERROR.getCode(),msg,data);
	}
	
	public Msg(Integer code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.time = String.valueOf(System.currentTimeMillis());
	}
	
	
	
	
}
