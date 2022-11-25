package com.mycom.myapp.admin.service;

import java.sql.SQLException;
import java.util.List;

import com.mycom.myapp.admin.dto.EventUserDto;

public interface EventUserService {
	int insert(int eventSeq, int userSeq) throws SQLException; //등록 (참여하기 버튼 클릭 시 유저insert)
    List<EventUserDto> select() throws SQLException; //목록 (이벤트 참여 유저의 목록 보여줌 select)
    EventUserDto detail(int eventUserSeq) throws SQLException; //상세 (이벤트 참여 유저 상세 정보 select)
    int delete(int eventUserSeq) throws SQLException; //삭제 (이벤트 참여 유저 삭제 delete)
    int modify(int eventUserSeq) throws SQLException; //수정 (이벤트 참여 유저 수정 modify)
}
