package com.mycom.myapp.user.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResultDto;

public interface UserService {
	UserResultDto userLogin(UserDto dto);
	UserResultDto userRegister(UserDto userDto); //post
	List<UserDto> getUsers(int userSeq);
	public UserDto userInfo(String userEmail) throws Exception;
	UserResultDto userUpdate(UserDto userDto, MultipartHttpServletRequest request); //put
	UserResultDto userDelete(UserDto userDto); //delete
	int userDupCheck(UserDto userDto);
	public void saveRefreshToken(String userEmail, String refreshToken) throws Exception;
	public Object getRefreshToken(String userEmail) throws Exception;
	public void deleRefreshToken(String userEmail) throws Exception;
}