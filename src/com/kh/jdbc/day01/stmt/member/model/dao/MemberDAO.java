package com.kh.jdbc.day01.stmt.member.model.dao;

import java.sql.*;
import java.util.*;

import com.kh.jdbc.day01.stmt.member.model.vo.Member;

public class MemberDAO {
	// JDBC를 이용하여
	// Oracle DB에 접속하는 클래스
	// JDBC 코딩이 있어야 함.
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USERNAME = "ELLAJDBC";
	private final String PASSWORD = "ELLAJDBC";
	
	public Member selectOne(String memberId) {
		Member member = null;
			try {
				Class.forName(DRIVER_NAME);
				Connection conn 
					= DriverManager.getConnection(URL, USERNAME, PASSWORD);
				Statement stmt = conn.createStatement();
				String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '" + memberId + "'";  // 시험
				ResultSet rset = stmt.executeQuery(query);
				if(rset.next()) {
					// restToMember
					member = new Member();
					member.setMemberId(rset.getString("MEMBER_ID"));
					member.setMemberName(rset.getString("MEMBER_NAME"));
					member.setMemberPw(rset.getString("MEMBER_PW"));
					member.setAge(rset.getInt("AGE")); // getInt로 가져와야함
					member.setGender(rset.getString("GENDER"));
					member.setEmail(rset.getString("EMAIL"));
					member.setPhone(rset.getString("PHONE"));
					member.setAddress(rset.getString("ADDRESS"));
					member.setHobby(rset.getString("HOBBY"));
					member.setRegDate(rset.getDate("REG_DATE"));
				}
				rset.close();
				stmt.close();
				conn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return member;
	}
	
	public List<Member> selectList() {
		/*
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성
		 * 3. Statement 생성
		 * 4. 쿼리문 전송
		 * 5. 결과값 받기
		 * 6. 자원해제
		 */
		// 1. 왜 mList에 member가 들어가나요? -- mList로 회원정보를 받아오자나
		// 2. rset은 왜 mList에 못들어가나요? -- rset은 ResultSet, mList는 <Member>
		// 3. rset을 member로 어떻게 바꾸나요? -- 안에 다 집어넣어야지!
		// 3.1 Member 클래스에는 필드와 게터/세터 필요
		// 3.2 ResultSet의 getString(), getInt(), getDate() 필요
		List<Member> mList = new ArrayList<Member>();
		try {
			// 1.
			Class.forName(DRIVER_NAME);
			// 2.
			Connection conn
				= DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 3.
			Statement stmt = conn.createStatement();
			// 4. / 5.
			String query = "SELECT * FROM MEMBER_TBL";
			ResultSet rset = stmt.executeQuery(query);
			// 후처리
			while(rset.next()) { // SELECT의 결과값인 rset의 다음 내용이 없을 떄 까지
				// rsetToMember - rset을 member로 바꾸는거
				Member member = new Member();
//				String memberId = rset.getString("MEMBER_ID");
//				member.setMemberId(memberId); -> 너무귀찮아!
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setMemberPw(rset.getString("MEMBER_PW"));
				member.setAge(rset.getInt("AGE")); // getInt로 가져와야함
				member.setGender(rset.getString("GENDER"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setHobby(rset.getString("HOBBY"));
				member.setRegDate(rset.getDate("REG_DATE"));
				mList.add(member);
				System.out.println("이름 : " + rset.getString("MEMBER_NAME")); // 컬럼명 그대로 오타없이 작성
				System.out.println("아이디 : " + rset.getString("MEMBER_ID")); // 컬럼명 그대로 오타없이 작성
				System.out.println("이메일 : " + rset.getString("EMAIL")); // 컬럼명 그대로 오타없이 작성
				System.out.println("나이 : " + rset.getInt("AGE"));			   // 컬럼명 그대로 오타없이 작성
				System.out.println("등록일 : " + rset.getDate("REG_DATE"));	   // 컬럼명 그대로 오타없이 작성

//				객체 <Member>의 새로운 변수 members에 쿼리문 select로 받아온 
//				rset의 아이디, 비번, 나이를 입력하고 sList에 입력하는 법 
//				게터세터 메소드 이용!
//				List<Member> sList = new ArrayList<Member>();
//				Member members = new Member();
//				members.setMemberId(rset.getString("MEMBER_ID"));
//				members.setMemberPw(rset.getString("MEMBSR_PW"));
//				members.setAge(rset.getInt("AGE"));
//				mList.add(members);
//				return sList;
				
			}
			// 6.
			rset.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
	
	public void insertMember(Member member) {
		/*
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성
		 * 3. Statement 생성
		 * 4. 쿼리문 전송
		 * 5. 결과값 받기
		 * 6. 자원해제
		 */
		try {
			// 1.
			Class.forName(DRIVER_NAME);
			// 2.
			Connection conn 
					= DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 3.
			Statement stmt = conn.createStatement();
			// 4. / 5.
			String query = "INSERT INTO MEMBER_TBL VALUES('"+member.getMemberId()+"', '"
															+member.getMemberPw()+"', '"
															+member.getMemberName()+"', '"
															+member.getGender()+"', '"
															+member.getAge()+"', '"
															+member.getEmail()+"', '"
															+member.getPhone()+"', '"
															+member.getAddress()+"', '"
															+member.getHobby()+"', DEFAULT)";
			// ResultSet rset = stmt.executeQuery(query); // SELECT 할 때만 ResultSet!! ResultSet은 Select의 결과
			int result = stmt.executeUpdate(query); // 시험 : DML(INSERT, UPDATE, DELETE)의 경우 정수로 받기!
			if(result > 0) {
				// 성공 메시지 출력
				System.out.println("데이터 등록 성공!");
				// commit;
			} else {
				// 실패 메시지 출력
				System.out.println("데이터 등록 실패!");
				// rollback;
			}
			conn.close();
			stmt.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
