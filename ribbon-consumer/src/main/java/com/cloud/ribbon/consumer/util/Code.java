package com.cloud.ribbon.consumer.util;

public enum Code {
	SUCCESS(1,"成功"), ERROR(-1,"失败"),Existence(0,"存在");

	private int code;
	private String message;
	
	private Code(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
}