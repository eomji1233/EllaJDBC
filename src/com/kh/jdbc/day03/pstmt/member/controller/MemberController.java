package com.kh.jdbc.day03.pstmt.member.controller;

import com.kh.jdbc.day03.pstmt.member.model.dao.MemberDAO;
import com.kh.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberController {
	MemberDAO mDao;
	
	public MemberController () {
		mDao = new MemberDAO();
	}
	
	public int registerMember(Member mOne) {
		int result = mDao.insertMember(mOne);
		return result;
	}

	public Member checkLogin(Member member) {
		Member result = mDao.selectOne(member);
		return result;
	}

	public int deleteMember(String memberId) {
		int result = mDao.deleteMember(memberId);
		return result;
	}

	public Member checkMember(String memberId) {
		Member result = mDao.selectOne(memberId); // 매개변수 달라서 오버로딩 되니까 이름 같아도 ㄱㅊ
		return result;
	}

	public int updateMember(Member member) {
		int result = mDao.updateMember(member);
		return result;
	}

}
