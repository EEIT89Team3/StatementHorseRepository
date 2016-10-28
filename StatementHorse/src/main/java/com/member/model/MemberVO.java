package com.member.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.message.model.MsgVO;
import com.tracklisting.model.TrackListingVO;

/*
 * 註1: classpath必須有hibernate-jpa-2.0-api-1.0.0.Final.jar 
 * 註2: Annotation可以添加在屬性上，也可以添加在getXxx()方法之上
 */

@Entity // 要加上@Entity才能成為JPA的一個Entity類別
@Table(name = "MEMBER") // 代表這個class是對應到資料庫的實體table，目前對應的table是Member
public class MemberVO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String memberId;//會員編號
	private String memberEmail;//會員E-mail(帳號)
	private String memberPassword;//會員密碼
	private Set<MsgVO> msgVOs; //連結通知明細VO
	private Set<TrackListingVO> trackListingVOs;//連結追蹤清單VO

	public MemberVO() { // 必需有一個不傳參數建構子(JavaBean基本知識)
	}

	@Id // @Id代表這個屬性是這個Entity的唯一識別屬性，並且對映到Table的主鍵
	@Column(name = "member_id") // @Coluㄆmn指這個屬性是對應到資料庫Table的哪一個欄位
	//【非必要，但當欄位名稱與屬性名稱不同時則一定要用】
	// @SequenceGenerator(name = "xxx", allocationSize = 1) //
	// 1.先用@SequenceGenerator建立一個generator
	// @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "xxx") //
	// 2.再用@GeneratedValue的generator屬性指定要用哪個generator
	//【strategy的GenerationType,
	// 有四種值:
	// AUTO,
	// IDENTITY,
	// SEQUENCE,
	// TABLE】
	public String getMemberId() {
		return memberId;

	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Column(name = "member_email")
	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	@Column(name = "member_password")
	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID")
	public Set<MsgVO> getMsgVOs() {
		return msgVOs;
	}

	public void setMsgVOs(Set<MsgVO> msgVOs) {
		this.msgVOs = msgVOs;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID")
	public Set<TrackListingVO> getTrackListingVOs() {
		return trackListingVOs;
	}

	public void setTrackListingVOs(Set<TrackListingVO> trackListingVOs) {
		this.trackListingVOs = trackListingVOs;
	}

}
