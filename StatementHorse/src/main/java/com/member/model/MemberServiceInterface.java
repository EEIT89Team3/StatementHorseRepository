package com.member.model;

public interface MemberServiceInterface {
		//
		public MemberVO findMember(String memberEmail);
		
		public void insertMember (MemberVO insmember);
}
