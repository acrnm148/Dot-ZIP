package com.mycom.myapp.friend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.friend.dto.FriendDto;

@Mapper
public interface FriendDao {
	public int friendFollow(FriendDto friendDto); 
	public int friendUnFollow(FriendDto friendDto); 
	
	public List<FriendDto> friendFollower(int toId); // 팔로워 : 나를 친구로 등록한 사람들 조회
	public int friendFollowerCount(int toId); // 팔로워 : 나를 친구로 등록한 사람들 수
	
	public List<FriendDto> friendFollowing(int fromId); // 팔로잉 : 내가 친구로 등록한 사람들 조회
	public int friendFollowingCount(int fromId); // 팔로잉 : 내가 친구로 등록한 사람들 수
}
