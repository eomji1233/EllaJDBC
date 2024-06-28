package com.kh.jdbc.day01.stmt.member.controller;

import java.util.*;

import com.kh.jdbc.day01.stmt.member.model.dao.MemberDAO;
import com.kh.jdbc.day01.stmt.member.model.vo.Member;

public class MemberController {
	MemberDAO mDao;
	
	public MemberController() {
		mDao = new MemberDAO();
	}
	
	public void insertMember(Member member) {
		mDao.insertMember(member);
	}
	
	public List<Member> listMember() {
		return mDao.selectList();
	}

	public Member printOneMember(String memberId) {
		System.out.println("===== 검색한 회원의 정보 =====");
		Member member = mDao.selectOne(memberId);
		return member;
	}

}
