package com.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.model.MemberService;
import com.member.model.MemberServiceInterface;
import com.member.model.MemberVO;

@Controller
@RequestMapping(value = "/account")
public class LoginController {
	private MemberServiceInterface memberService=new MemberService();
	
	//每一個function
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}
	@RequestMapping(value = "/forget", method = RequestMethod.GET)
	public String forget() {
		return "forget";
	}
	//帳號登入為安全性考量使用POST
	@RequestMapping(value = "/loginSubmit", method = RequestMethod.POST)
	public String loginSubmit(String memberEmail,String password) {
		MemberVO memberVO = memberService.findMember(memberEmail); 
		if (memberEmail.equals(memberVO.getMemberPassword())) {
			return "redirect:loginSuccess.jsp";
		} else {
			return "login";
		}

	}
	//帳號註冊
	@ResponseBody //＊for ajax，
	@RequestMapping(value = "/registerSubmit", method = RequestMethod.POST)//passwordCheck=再次輸入密碼欄位
	public String registerSubmit(String memberEmail,String password,String passwordCheck) {
		//＊＊未完成＊＊
		return null;
	}
	//忘記密碼
}
