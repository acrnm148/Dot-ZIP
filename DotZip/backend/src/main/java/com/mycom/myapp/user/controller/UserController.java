package com.mycom.myapp.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.service.JwtServiceImpl;
import com.mycom.myapp.user.service.UserService;

@RestController
@CrossOrigin(
		// localhost:5500 과 127.0.0.1 구분
		origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class UserController {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	private final String SUCCESS = "success";
	private final String FAIL = "fail";
	private final String DUPLICATED = "duplicated";
	
	
	@PostMapping(value="/users/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserDto dto, HttpSession session){    	
    	System.out.println("login:"+dto);
    	
        Map<String, Object> map = new HashMap<>();
        HttpStatus status = null;
        try {
        	UserResultDto userResultDto = userService.userLogin(dto);
        	System.out.println(userResultDto.getDto());
            
        	if(userResultDto.getDto() != null) {
        		session.setAttribute("userDto", userResultDto.getDto());
        		System.out.println("session : " + session.getAttribute("userDto"));
        		String accessToken = jwtService.createAccessToken("userEmail", userResultDto.getDto().getUserEmail()); // key, data
        		String refreshToken = jwtService.createRefreshToken("userEmail", userResultDto.getDto().getUserEmail());
        		userService.saveRefreshToken(dto.getUserEmail(), refreshToken);
        		logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				map.put("access-token", accessToken);
				map.put("refresh-token", refreshToken);
        		map.put("message", SUCCESS);
        		map.put("user",userResultDto.getDto());
        		status = HttpStatus.ACCEPTED;
        	}else {
        		map.put("message", FAIL);
        		status = HttpStatus.ACCEPTED;
        	}
        }catch(Exception e){
			logger.error("로그인 실패 : {}", e);
        	map.put("message", e.getMessage());
        	status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(map, status);
    }
	
	@GetMapping(value="/users/all/{userSeq}")
	public List<UserDto> getUsers(@PathVariable int userSeq){
		List<UserDto> userList = userService.getUsers(userSeq);
		return userList;
	}
	
	@PostMapping(value="/users/register")
	public ResponseEntity<Map<String, String>> userRegister(@RequestBody UserDto dto,HttpSession session) {
		System.out.println(dto);
		Map<String, String> map = new HashMap<>();
		
		int res = userService.userDupCheck(dto);
		System.out.println(res);
		if (res == 0) {
			UserResultDto userResultDto = userService.userRegister(dto);
			if (userResultDto.getResult() == 1) {
				map.put("result", SUCCESS);
				return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
			} else {
				map.put("result", FAIL);
				return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
			}
		} else {
			map.put("result", DUPLICATED);
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/users/info/{userEmail}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userEmail")String userEmail, HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		System.out.println(request.getHeader("access-token"));
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.userInfo(userEmail);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	

	@GetMapping("/users/logout/{userEmail}")
	public ResponseEntity<?> removeToken(@PathVariable("userEmail") String userEmail) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.deleRefreshToken(userEmail);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@PostMapping("/users/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDto userDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, memberDto : {}", token, userDto);
		if (jwtService.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(userDto.getUserEmail()))) {
				String accessToken = jwtService.createAccessToken("userid", userDto.getUserEmail());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	@GetMapping(value="/users")
	public ResponseEntity<UserResultDto> userDetail(HttpSession session) {
		UserDto userDto = (UserDto)session.getAttribute("userDto");
		System.out.println("getInfo:"+userDto);
		
		//UserResultDto userResultDto = userService.userInfo(userDto);
		//Map<String, String> map = new HashMap<>();
		UserResultDto userResultDto = new UserResultDto();
		
		if (userDto != null) {
			userResultDto.setResult(1);
			userResultDto.setDto(userDto);
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			userResultDto.setResult(-1);
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/users/{userEmail}") //post for imgupload
	public ResponseEntity<UserResultDto> userUpdate(UserDto userDto, MultipartHttpServletRequest request) {

		UserResultDto userResultDto = userService.userUpdate(userDto, request);
		
		if (userResultDto.getResult() == 1) {
			userResultDto.setResult(1);
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			userResultDto.setResult(-1);
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value="/users/{userEmail}") //post for imgupload
	public ResponseEntity<UserResultDto> userDelete(UserDto userDto) {

		UserResultDto userResultDto = userService.userDelete(userDto);
		
		if (userResultDto.getResult() == 1) {
			userResultDto.setResult(1);
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			userResultDto.setResult(-1);
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.NOT_FOUND);
		}
	}
	
}