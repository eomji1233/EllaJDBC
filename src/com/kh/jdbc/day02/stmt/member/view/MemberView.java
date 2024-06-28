package com.kh.jdbc.day02.stmt.member.view;

import java.util.*;

import com.kh.jdbc.day02.stmt.member.model.vo.Member;
import com.kh.jdbc.day02.stmt.member.controller.MemberController;

public class MemberView {
	
	// View 클래스에서 계속 쓸거니
	// 필드로 두고
	MemberController mController;

	public MemberView() {
		// 생성자에서 초기화 해줌
		mController = new MemberController();
	}
	
	public void startProgram() {
		end :
		while(true) {
			int value = this.printMainMenu();
			switch(value) {
			case 1 : 
				// 1을 눌렀다면 회원의 정보를 입력받아야 함
				Member member = this.inputMember(); // inputMember()를 실행시켜 정보받기
				// ID부터 취미까지 저장된 member 객체를 컨트롤러로 전달
				mController.insertMember(member);
				break;
			case 2 :
				// 2를 눌렀다면 회원의 전체정보를 출력해야함
				// 1. DB에서 데이터 가져오기, 전체 회원 정보니까 여러 개, 여러 개니까 List, 멤버니까 List<Member>
				List<Member> mList = mController.printAllMembers();
				// 2. view의 메소드를 이용해서 출력하기
				this.printAllMembers(mList);
				break;
			case 3 :
				// 3을 눌렀다면 회원의 정보를 입력해야 함(아이디로 검색)
				// 사용자가 검색한 아이디 입력받아야 되니까 inputMember();
				String memberId = this.inputMemberId();
				// 입력받은 아이디로 DB에서 검색해 와야되니까 printOneMember();
				// 컨트롤러로 전달해야 되니까 printOneMember(memberId);
				member = mController.printOneMember(memberId);
				// DB에서 가져온 값을 출력해야되니까 printOneMember
				this.displayOne(member);
				break;
			case 4 :
				// 4를 눌렀다면 회원의 정보를 수정해야 함(아이디로 정보가 존재하는지 확인 후 있으면 수정 없으면 안함)
				// 사용자가 수정할 아이디 입력받아야 되니까 inputMember();
				memberId = inputMemberId();
				// 존재하는 정보만 수정로직을 타야되니까, printOneMember() 호출
				// memberId 전달해야 되니까 printOneMember(memberId);
				// DB에서 가져온 값 저장해야 되니까 member = mController.printOneMember(memberId);
				member = mController.printOneMember(memberId);
				// DB에서 데이터를 가져왔는지 체크해야 되니까 if(member != null)
				// 데이터가 없다면 member는 null일 것임
				if (member != null) {
					 // 수정할 때에는 수정할 정보를 입력해야 되니까 modifyMember(member); member를 전달해야 함
					// 즉 수정할 정보를 가지고 있는 member객체가 필요함
					Member modifyInfo = this.inputModifyInfo();
					// UPDATE할 때는 가장 중요한 것이 WHERE 조건절이니까, WHERE에 들어갈 데이터를 전달해줘야 함
					// modifyMember(member)에서 member에 memberId를 꼭 넣어줘야 하니까, modifyInfo.setMemberId(memberId);
					modifyInfo.setMemberId(memberId);
					int result = mController.modifyMember(modifyInfo);
					if(result > 0) {
						this.displayMessage("수정 성공!");
					} else {
						this.displayMessage("수정 실패!");
					}
				} else {
					this.displayMessage("존재하지 않는 정보입니다.");
				}
				break;
			case 5 :
				// 5를 눌렀다면 회원의 정보를 삭제해야 함(아이디로 삭제)
				// 사용자가 삭제할 아이디 입력받아야 되니까 inputMember();
				memberId = this.inputMemberId();
				// 존재하는 정보만 삭제로직을 타야되니까, printOneMember() 호출
				// memberId 전달해야 되니까 printOneMember(memberId);
				// DB에서 가져온 값 저장해야 되니까 member = mController.printOneMember(memberId);
				member = mController.printOneMember(memberId);
				// DB에서 데이터를 가져왔는지 체크해야 되니까 if(member != null)
				// 데이터가 없다면 member는 null일 것임
				if(member != null) {
					// 입력받은 아이디로 DB에서 삭제해야 되니까 removeMember();
					// 컨트롤러로 전달해야 되니까 removeMember(memberId);
					// DML의 결과는 int니까 int result
					int result = mController.removeMember(memberId);
					if(result > 0) {
						this.displayMessage("삭제 성공!");
					} else {
						this.displayMessage("삭제 실패!");
					}					
				} else {
					this.displayMessage("존재하지 않는 회원입니다.");
				}
				break;
			case 6 :
				
				break;
			case 0 : break end;
			}			
		}
	}
	// MemberView:56, 58
	private void displayMessage(String msg) {
		System.out.println("[서비스 결과] : " + msg);
	}

	// MemberView:45
	private void displayOne(Member member) {
		System.out.println("===== 회원 정보 출력 =====");
			System.out.printf("이름 : %s \t 나이 : %d\t" 
					+ " 아이디 : %s\t 성별 : %s\t 이메일 : %s"
					+ " 전화번호 : %s\t 주소 : %s\t 취미 : %s"
					+ "\t 가입날짜 : %s\n"
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

	private String inputMemberId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 아이디 입력 : ");
		String memberId = sc.next();
		// 호출한 곳에서 써야되니까 return memberId, MemberView:41
		return memberId;
	}

	// MemberView:35
	private void printAllMembers(List<Member> mList) {
		System.out.println("===== 회원 정보 전체 출력 =====");
		for(Member member : mList) {
			System.out.printf("이름 : %s \t 나이 : %d\t" 
					+ " 아이디 : %s\t 성별 : %s\t 이메일 : %s"
					+ " 전화번호 : %s\t 주소 : %s\t 취미 : %s"
					+ "\t 가입날짜 : %s\n"
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
	// MemberView:26
	private Member inputMember() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 회원 정보 등록 =====");
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
		sc.nextLine();
		System.out.print("주소 : ");
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.next();
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		member.setMemberName(memberName);
		member.setGender(gender);
		member.setAge(age);
		member.setEmail(email);
		member.setHobby(hobby);
		member.setAddress(address);
		member.setPhone(phone);
		return member;
	}

	private Member inputModifyInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 회원 정보 수정 =====");
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine();
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.next();
		Member member = new Member();
		member.setMemberPw(memberPw);
		member.setEmail(email);
		member.setHobby(hobby);
		member.setAddress(address);
		member.setPhone(phone);
		return member;
	}

	public int printMainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("===== 회원 관리 프로그램 =====");
		System.out.println("1. 회원가입");
		System.out.println("2. 전체 회원 조회");
		System.out.println("3. 회원 검색");
		System.out.println("4. 회원 정보 수정");
		System.out.println("5. 회원 탈퇴");
		System.out.println("6. 로그인");
		System.out.println("0. 종료");
		System.out.print("메뉴 선택 : ");
		int choice = sc.nextInt();
		return choice; // 다른 메소드에서 쓸 수 있도록 리턴
	}
}
