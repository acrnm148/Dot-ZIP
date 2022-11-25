package com.mycom.myapp.region.dto;

public class GugunCodeDto {
	String code;
	String name;
	String sidoCode;
	@Override
	public String toString() {
		return "GugunCodeDto [code=" + code + ", name=" + name + ", sidoCode=" + sidoCode + "]";
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}
}
