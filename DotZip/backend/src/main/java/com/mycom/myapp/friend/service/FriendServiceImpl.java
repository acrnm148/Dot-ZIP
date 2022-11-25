package com.mycom.myapp.friend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myapp.friend.dao.FriendDao;
import com.mycom.myapp.friend.dto.FriendDto;
import com.mycom.myapp.friend.dto.FriendResultDto;

@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	FriendDao friendDao;
	
	private static final int SUCCESS = 1;	
	private static final int INCORRECT_INFO = 2;
	private static final int FAIL = -1;
	
	@Override
	public FriendResultDto friendFollow(FriendDto friendDto) {
		FriendResultDto friendResultDto = new FriendResultDto();
		System.out.println(friendDto);
		try {
			if (friendDao.friendFollow(friendDto) == 1) {
				friendResultDto.setDto(friendDto);
				friendResultDto.setResult(SUCCESS);
			} else {
				friendResultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			friendResultDto.setResult(FAIL);
		}
		return friendResultDto;
	}

	@Override
	public FriendResultDto friendUnFollow(FriendDto friendDto) {
		FriendResultDto friendResultDto = new FriendResultDto();
		
		try {
			if (friendDao.friendUnFollow(friendDto) == 1) {
				friendResultDto.setDto(friendDto);
				friendResultDto.setResult(SUCCESS);
			} else {
				friendResultDto.setResult(FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			friendResultDto.setResult(FAIL);
		}
		return friendResultDto;
	}

	@Override
	public FriendResultDto friendCount(int userSeq) {
		FriendResultDto friendResultDto = new FriendResultDto();
		
		try {
			friendResultDto.setFollowerCount(friendDao.friendFollowerCount(userSeq));
			friendResultDto.setFollowingCount(friendDao.friendFollowingCount(userSeq));
			friendResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			friendResultDto.setResult(FAIL);
		}
		return friendResultDto;
	}

	@Override
	public FriendResultDto friendFollower(int toId) {
		FriendResultDto friendResultDto = new FriendResultDto();
		
		try {
			List<FriendDto> followerList = friendDao.friendFollower(toId);
			
			friendResultDto.setList(followerList);
			friendResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			friendResultDto.setResult(FAIL);
		}
		return friendResultDto;
	}

	@Override
	public FriendResultDto friendFollowing(int fromId) {
		FriendResultDto friendResultDto = new FriendResultDto();
		
		try {
			List<FriendDto> followingList = friendDao.friendFollowing(fromId);
			
			friendResultDto.setList(followingList);
			friendResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			friendResultDto.setResult(FAIL);
		}
		return friendResultDto;
	}

}
