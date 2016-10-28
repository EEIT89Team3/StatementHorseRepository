package com.member.model;

import org.hibernate.Session;

import com.utils.HibernateUtil;

public class MemberService implements MemberServiceInterface {
	private MemberDAOInterface memberDAOInterface;

	public MemberService() {
		memberDAOInterface = new MemberHibernateDAO();
	}
	//比對現有會員帳號
	@Override
	public MemberVO findMember(String memberEmail) {
		MemberVO memberVO = memberDAOInterface.findByMemberEmail(memberEmail);
		return memberVO;
	}
	//新增會員帳號
	@Override
	public void insertMember(MemberVO insmember) {
		memberDAOInterface.insert(insmember);
	}
}
