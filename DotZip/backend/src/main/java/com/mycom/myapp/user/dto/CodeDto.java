package com.mycom.myapp.user.dto;

public class CodeDto {
	String code;
	String code_name;
	String use_yn;
	@Override
	public String toString() {
		return "CodeDto [code=" + code + ", code_name=" + code_name + ", use_yn=" + use_yn + "]";
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	
	
	
	
}
