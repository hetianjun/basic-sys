package com.tjun.comm.msgs;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MyException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private Integer code;

    private String msg;
    
    private String mydetails;
    
    public MyException(MsgCodeEnums code,String msg) {
    	super(msg);
    	this.code = code.getCode();
    	this.msg = msg;
    	this.mydetails = msg;
    }


    public MyException(String msg) {
    	super(msg);
        this.code = MsgCodeEnums.ERROR.getCode();
        this.msg = msg;
        this.mydetails = msg;
    }
    
    public MyException(String msg,String details) {
    	super(msg);
    	this.code = MsgCodeEnums.ERROR.getCode();
    	this.msg = msg;
    	this.mydetails = details;
    }
    
    /**
     * 断言
     * @param bool 为真抛异常
     * @param msg 错误信息
     */
    public static void turesErr(boolean bool,String msg) throws MyException {
//    	Assert.assertTrue(msg,  ! bool);
    	if(bool) {
    		throw new MyException(msg);
    	}
    }

}
