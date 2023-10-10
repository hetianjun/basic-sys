package com.tjun.comm.msgs;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MsgCodeEnums {
	SUCCESS(0,"操作成功"),
	ERROR(500,"自定义异常内容"),
	
    PARAM_ERROR(401,"参数异常"),
    SERVICE_ERROR(400,"服务异常"),
    ;
	
    private final Integer code;
    private final String message;

}
