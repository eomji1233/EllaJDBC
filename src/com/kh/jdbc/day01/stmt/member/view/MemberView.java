package com.kh.jdbc.day01.stmt.member.view;

import java.util.*;
import com.kh.jdbc.day01.stmt.member.controller.MemberController;
import com.kh.jdbc.day01.stmt.member.model.vo.Member;

public class MemberView {

	MemberController mController;

	public MemberView() {
		mController = new MemberController();
	}

	public void startProgram() {
		int choice = 0;
		끝 :
		while(true) {
			choice = this.printMainMenu();
			switch(choice) {
				case 0 : break 끝;
				case 1 : 
					Member member = this.inputMember();
					mController.insertMember(member);
					break;
				case 2 : 
					List<Member> mList = mController.listMember();
					this.displayMemberList(mList);
					break;
				case 3 : 
					String memberId = this.inputMemberId();
					member = mController.printOneMember(memberId);
					this.displayOne(member);
					break;
				case 4 :
					
					break;
				case 5 :
					
					break;
				default : this.displayMessage("메뉴를 다시 선택해주세요");
			}
		}
		// mController.insertMember();
//		mController.listMember();
//		List<Member> mList = mController.listMember();
//		this.displayMemberList(mList);
	}
	
	private String inputMemberId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 아이디 입력: ");
		String memberId = sc.next();
		return memberId;
	}


	private Member inputMember() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 회원 정보 입력 =====");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("성별 : ");
		String gender = sc.next();
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine(); // 두개를 같이 쓰면 문제 발생, 띄어쓰기나 엔터 제거하기 위해서 쓰는거!, next()다음에 써줘야해
		String address = sc.nextLine(); // 띄어쓰기나 개행 모두 입력받는거
		System.out.print("취미 : ");
		String hobby = sc.nextLine();
		Member member = new Member();
		member.setMemberId(memberId);
		member.setAddress(address);
		member.setAge(age);
		member.setEmail(email);
		member.setGender(gender);
		member.setHobby(hobby);
		member.setPhone(phone);
		member.setMemberPw(memberPw);
		member.setMemberName(memberName);
		return member;
	}
	
	private void displayMessage(String msg) {
		System.out.println(msg);
	}

	private int printMainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 회원 관리 프로그램 =====");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원 전체 조회");
		System.out.println("3. 회원 검색(아이디로 조회)");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int value = sc.nextInt();
		return value;
	}

	private void displayOne(Member member) {
		System.out.println(" ===== 회원 정보 출력(아이디로 검색) =====");
		System.out.printf("이름 : %s, 나이 : %d" 
				+ ", 아이디 : %s, 성별 : %s, 이메일 : %s"
				+ ", 전화번호 : %s, 주소 : %s, 취미 : %s"
				+ ", 가입날짜 : %s\n"
				, member.getMemberName()
				, member.getAge()
				, member.getMemberId()
				, member.getGender()
				, member.getEmail()
				, member.getPhone()
				, member.getAddress()
				, member.getHobby()
				, member.getRegDate());
	}
	
	public void displayMemberList(List<Member> mList) {
		System.out.println("===== 회원 정보 전체 출력 =====");
		for(Member member : mList) {
			System.out.printf("이름 : %s, 나이 : %d" 
					+ ", 아이디 : %s, 성별 : %s, 이메일 : %s"
					+ ", 전화번호 : %s, 주소 : %s, 취미 : %s"
					+ ", 가입날짜 : %s\n"
					, member.getMemberName()
					, member.getAge()
					, member.getMemberId()
					, member.getGender()
					, member.getEmail()
					, member.getPhone()
					, member.getAddress()
					, member.getHobby()
					, member.getRegDate());
		}
 	}


}
