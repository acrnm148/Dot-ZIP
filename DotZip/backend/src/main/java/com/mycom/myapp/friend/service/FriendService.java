package com.mycom.myapp.friend.service;

import com.mycom.myapp.friend.dto.FriendDto;
import com.mycom.myapp.friend.dto.FriendResultDto;

public interface FriendService {
	public FriendResultDto friendFollow(FriendDto friendDto); // 팔로우 : 내가 친구 요청(추가)
	public FriendResultDto friendUnFollow(FriendDto friendDto); // 친구 해제
	
	public FriendResultDto friendCount(int userSeq);
	public FriendResultDto friendFollower(int toId); // 팔로워 : 나를 친구로 등록한 사람들 조회
	public FriendResultDto friendFollowing(int fromId); // 팔로잉 : 내가 친구로 등록한 사람들 조회
}
