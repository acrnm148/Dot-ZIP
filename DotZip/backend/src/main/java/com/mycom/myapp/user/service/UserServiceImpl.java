package com.mycom.myapp.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.user.dao.UserDao;
import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResultDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	private final int SUCCESS = 1;
	private final int FAIL = -1;
	
	@Override
	public UserResultDto userLogin(UserDto dto) {
		UserDto userDto = userDao.userLogin(dto.getUserEmail());
		UserResultDto userResultDto = new UserResultDto();
		// userDtosms 테이블에서 조회한 데이터가 포함
		// dto는 client가 전송한 데이터가 포함
		if (userDto != null && userDto.getUserPassword().equals(dto.getUserPassword())) {
			userResultDto.setDto(userDto);
			userResultDto.setResult(SUCCESS);
		}else {
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}
	
	@Override
	public UserResultDto userRegister(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		if (userDao.userRegister(userDto) == SUCCESS) {
			userResultDto.setResult(SUCCESS);
		} else {
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}
	@Override
	public UserDto userInfo(String userEmail) throws Exception {
		return userDao.userInfo(userEmail);
	}

	@Override
	public UserResultDto userUpdate(UserDto userDto, MultipartHttpServletRequest request) {
		UserResultDto userResultDto = new UserResultDto();
		try {
			userDao.userChange(userDto);
			
			userResultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		
		return userResultDto;
	}

	@Override
	public UserResultDto userDelete(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		try {
			userDao.userUnregist(userDto);
			
			userResultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		
		return userResultDto;
	}

	@Override
	public int userDupCheck(UserDto userDto) {
		return userDao.userDupCheck(userDto);
	}
	
	@Override
	public void saveRefreshToken(String userEmail, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userEmail", userEmail);
		map.put("token", refreshToken);
		userDao.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userEmail) throws Exception {
		return userDao.getRefreshToken(userEmail);
	}

	@Override
	public void deleRefreshToken(String userEmail) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userEmail", userEmail);
		map.put("token", null);
		userDao.deleteRefreshToken(map);
	}

	@Override
	public List<UserDto> getUsers(int userSeq) {
		return userDao.getUsers(userSeq);
	}


}