package com.mycom.myapp.friend.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class FriendDto {
	private int id;
	private int fromId;
	private int toId;
	
	private int userSeq;
	private String userName;
	private String userEmail;
	private LocalDateTime regDt;
	private String userProfileImageUrl;
	private int code;
	public void setId(int id) {
		this.id = id;
	}
	public void setFromId(int fromId) {
		this.fromId = fromId;
	}
	public void setToId(int toId) {
		this.toId = toId;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public void setRegDt(LocalDateTime regDt) {
		this.regDt = regDt;
	}
	public void setUserProfileImageUrl(String userProfileImageUrl) {
		if (userProfileImageUrl == null || "null".equals(userProfileImageUrl) || "".equals(userProfileImageUrl)) {
			this.userProfileImageUrl = "/assets/img/profiles/noProfile.jpg";
		} else {
			this.userProfileImageUrl = userProfileImageUrl;
		}
	}
	public void setCode(int code) {
		this.code = code;
	}
	
}