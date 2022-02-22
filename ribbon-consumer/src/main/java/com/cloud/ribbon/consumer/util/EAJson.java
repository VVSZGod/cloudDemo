package com.cloud.ribbon.consumer.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class EAJson extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	public static int SUCCESS = 1;
	public static int ERROR = -1;
	public static int Existence =0;

	//public static int code=1;
	
	public static EAJson success() {
		return new EAJson(Code.SUCCESS);
	}

	public static EAJson existence () {
		return new EAJson(Code.Existence);
	}

	public static EAJson error() {
		return new EAJson(Code.ERROR);
	}

	public static EAJson error(int code, String message) {
		return new EAJson(code, message);
	}

	public EAJson() {
	}

	public EAJson(Code code) {
		this(code.getCode(), code.getMessage());
	}

	public EAJson(int code, String message) {
		this.put("data", new LinkedHashMap());
		this.setCode(code);
		this.setMessage(message);
	}

	/*public static SimpleResult fromError(SimpleError e) {
		return new SimpleResult(e.getCode(), e.getMessage());
	}*/

	public void setCode(int code) {
		this.put("code", code);
	}

	public String getMessage() {
		return (String) this.get("message");
	}

	public void setMessage(String message) {
		this.put("message", message);
	}

	public EAJson putData(String key, Object value) {
		((LinkedHashMap) this.get("data")).put(key, value);
		return this;
	}

	public Object getData(String key) {
		return ((LinkedHashMap) this.get("data")).get(key);
	}

	public EAJson putDataAll(Map<? extends String, ?> map) {
		((LinkedHashMap) this.get("data")).putAll(map);
		return this;
	}
}