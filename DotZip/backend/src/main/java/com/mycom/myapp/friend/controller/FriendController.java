package com.mycom.myapp.friend.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.friend.dto.FriendDto;
import com.mycom.myapp.friend.dto.FriendResultDto;
import com.mycom.myapp.friend.service.FriendService;
import com.mycom.myapp.user.dto.UserDto;

@CrossOrigin(
		origins = "http://localhost:5500",
		allowCredentials = "true",
		allowedHeaders = "*",
		methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, 
				RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS})
@RestController
public class FriendController {
	@Autowired
	FriendService friendService;
	

	private static final int SUCCESS = 1;
	private static final int INCORRECT_INFO = 2;
	
	@PostMapping(value="/friend")
	public ResponseEntity<FriendResultDto> friendFollow(@RequestBody FriendDto friendDto, HttpSession session) {
		
//		UserDto userDto = (UserDto) session.getAttribute("userDto");
//		if(userDto != null) friendDto.setFromId(userDto.getUserSeq());
		System.out.println(friendDto);
		friendDto.setFromId(friendDto.getUserSeq());
		
		FriendResultDto friendResultDto = friendService.friendFollow(friendDto);
		
		if( friendResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	}
	
	@PostMapping(value="/friend/delete")
	public ResponseEntity<FriendResultDto> friendUnfollow(@RequestBody FriendDto friendDto, HttpSession session) {
//		FriendDto friendDto = new FriendDto();
//		UserDto userDto = (UserDto) session.getAttribute("userDto");
//		if(userDto != null) friendDto.setFromId(userDto.getUserSeq());
		friendDto.setFromId(friendDto.getUserSeq());
		
		FriendResultDto friendResultDto = friendService.friendUnFollow(friendDto);
		
		if( friendResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	}
	
	@GetMapping("/friend/count/{userSeq}")
	public ResponseEntity<FriendResultDto> friendCount(@PathVariable int userSeq,HttpSession session){
//		UserDto userDto = (UserDto) session.getAttribute("userDto");
//		if(userDto != null) friendDto.setFromId(userDto.getUserId());
		
		FriendResultDto friendResultDto = friendService.friendCount(userSeq);
		
		if( friendResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	}
	

	@GetMapping("/friend/follower/{userSeq}")
	public ResponseEntity<FriendResultDto> friendFollower(@PathVariable int userSeq,HttpSession session) {
//		UserDto userDto = (UserDto) session.getAttribute("userDto");
//		if(userDto != null) friendDto.setFromId(userDto.getUserId());
		
		FriendResultDto friendResultDto = friendService.friendFollower(userSeq);
		
		if( friendResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	}
	
	@GetMapping("/friend/following/{userSeq}")
	public ResponseEntity<FriendResultDto> friendFollowing(@PathVariable int userSeq,HttpSession session) {
//		UserDto userDto = (UserDto) session.getAttribute("userDto");
//		if(userDto != null) friendDto.setFromId(userDto.getUserId());
		
		FriendResultDto friendResultDto = friendService.friendFollowing(userSeq);
		
		if( friendResultDto.getResult() == SUCCESS ) {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.OK);
	    }else {
	        return new ResponseEntity<FriendResultDto>(friendResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
	}
	
}
