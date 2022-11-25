package com.mycom.myapp.region.dto;

public class DongCodeDto {
	private String code;
	private String name;
	private String cityCode;
	private String cityName;
	private String gugunCode;
	private String gugunName;
	@Override
	public String toString() {
		return "DongCodeDto [code=" + code + ", name=" + name + ", cityCode=" + cityCode + ", cityName=" + cityName
				+ ", gugunCode=" + gugunCode + ", gugunName=" + gugunName + "]";
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
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getGugunCode() {
		return gugunCode;
	}
	public void setGugunCode(String gugunCode) {
		this.gugunCode = gugunCode;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
}
