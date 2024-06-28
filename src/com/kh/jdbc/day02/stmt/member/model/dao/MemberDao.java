package com.kh.jdbc.day02.stmt.member.model.dao;

import java.sql.*;
import java.util.*;

import com.kh.jdbc.day02.stmt.member.model.vo.Member;

public class MemberDao {
	// JDBC 코딩절차
	// JDBC를 통해 DB의 데이터를 가져옴
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USERNAME = "ELLAJDBC";
	private final String PASSWORD = "ELLAJDBC";

	public void insertMember(Member member) {
		/*
		 * 1. 드라이버 등록 2. 연결 생성 3. Statement 생성 4. SQL문 전송 5. 결과 받기 6. 자원해제
		 */
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.createStatement(); // 워크시트 열기
			// 쿼리문 작성, ; 오타 조심!, '(홑따옴표) 오타 조심!
			String query = "INSERT INTO MEMBER_TBL VALUES('" + member.getMemberId() + "','" + member.getMemberPw()
					+ "','" + member.getMemberName() + "','" + member.getGender() + "','" + member.getAge() + "','"
					+ member.getEmail() + "','" + member.getPhone() + "','" + member.getAddress() + "','"
					+ member.getHobby() + "',DEFAULT)";
			// DML의 경우 성공한 행의 갯수가 리턴, 메소드는 executeUpdate() 사용
			int result = stmt.executeUpdate(query);
			// 다 쓴 자원은 해제 해줌
//		stmt.close();
//		conn.close();
			if (result > 0) { // 지금은 자동커밋!
				// commit
				System.out.println("성공!");
			} else {
				// rollback
				System.out.println("실패");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				// 예외가 발생하든 안하든 무조건 실행
				// 자원반납을 통해 오류 발생 방지
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int updateMember(Member modifyInfo) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String query = "UPDATE MEMBER_TBL SET MEMBER_PW = '"+modifyInfo.getMemberPw()
											+"', EMAIL = '"+modifyInfo.getEmail()
											+"', PHONE = '"+modifyInfo.getPhone()
											+"', ADDRESS = '"+modifyInfo.getAddress()
											+"', HOBBY = '"+modifyInfo.getHobby()
											+"'  WHERE MEMBER_ID = '"+modifyInfo.getMemberId()+"'";
			result = stmt.executeUpdate(query);
			if (result > 0) {
				conn.commit(); // 커밋, 영구저장
			} else {
				conn.rollback(); // 롤백, 최근 커밋시점으로 이동(원복)
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteMember(String memberId) {
		// finally에서 close() 하니까
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			// 1. 드라이버 등록
			// checked Exception이니까 try ~ catch
			Class.forName(DRIVER_NAME);
			// 2. 연결 생성
			// checked Exception이니까 catch절 추가
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// 밑에서 커밋/롤백 할거니까 자동커밋 해제, setAutoCommit(false)
			conn.setAutoCommit(false);
			// 3. Statement 생성
			stmt = conn.createStatement();
			String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = '" + memberId + "'"; 
			// 4. 쿼리문 전송 및 5. 결과 받기
			// DML의 결과값은 성공한 행의 갯수니까 int result
			// 쿼리 실행 메소드는 DML이니까 executeUpdate(query);
			result = stmt.executeUpdate(query);
			// 쿼리 성공하면 커밋, 실패하면 롤백해야되니까 if(result > 0)
			if(result > 0) {
				// 커밋
				conn.commit();
			} else {
				// 롤백
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// try 안에서 쓴 변수는 return 안되니까 try밖에서 int result = 0;
		// 호출한 곳에서 써야되니까 return member, MemberController:66
		return result;
	}

	public List<Member> selectList() {
		/*
		 * 1. 드라이버 등록 2. 연결 생성 3. Statement 생성 4. SQL문 전송 5. 결과 받기 6. 자원해제
		 */
		// MemberController:23
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		// DB에서 가져온 값 넘겨줘야 하니까
		List<Member> mList = null;
		try {
			Class.forName(DRIVER_NAME); // 드라이버 등록
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); // 연결생성
			stmt = conn.createStatement(); // 워크시트 열기
			String query = "SELECT * FROM MEMBER_TBL"; // 쿼리문 작성
			// 실행 CTRL + ENTER
			rset = stmt.executeQuery(query); // SELECT는 executeQuery(query)
			// 후처리, 여러 개니까 while, 전부 가져올 때 까지 돔
			// null이면 안되니까 new ArrayList<>()
			mList = new ArrayList<Member>(); // ************
			while (rset.next()) {
				// rset은 바로 못쓰니까 Member
				Member member = this.rsetToMember(rset);
				// member에 다 담고 List에 담아야 되니까
				mList.add(member);

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 자원해제해야되니까 close
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 호출한 곳에서 써야되니까 return, MemberController:23
		return mList;
	}

	public Member selectOne(String memberId) {
		Connection conn = null;
		Statement stmt = null;
		// select니까 ResultSet
		ResultSet rset = null;
		Member member = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.createStatement();
			// ***********************************************************************
			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '" + memberId + "'"; 
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				member = rsetToMember(rset); // this 생략 가능!
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// try 안에서 쓴 변수는 return 안되니까 try밖에서 Member member = null;
		}
		return member;
	}

	// return이 있으니까 void대신 Member
	public Member rsetToMember(ResultSet rset) throws SQLException {
		Member member = new Member();
		// 비어있으면 안되니까 setter
		// resultset에서 값을 가져와야하니까 rset.getStirng("컬럼명")
		member.setMemberId(rset.getString("MEMBER_ID"));
		member.setMemberPw(rset.getString("MEMBER_PW"));
		member.setMemberName(rset.getString("MEMBER_NAME"));
		member.setGender(rset.getString("GENDER"));
		member.setAge(rset.getInt("AGE"));
		member.setEmail(rset.getString("EMAIL"));
		member.setPhone(rset.getString("PHONE"));
		member.setAddress(rset.getString("ADDRESS"));
		member.setHobby(rset.getString("HOBBY"));
		member.setRegDate(rset.getDate("REG_DATE"));
		// member에 다 담았고 호출한 곳에서 써야하니까 return member;
		return member;
	}
}
