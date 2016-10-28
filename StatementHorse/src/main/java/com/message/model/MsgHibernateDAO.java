package com.message.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.dividend.model.DividendVO;
import com.listingdetails.model.ListingDetailsVO;
import com.member.model.MemberVO;
import com.messagelists.model.MsgListVO;
import com.stocknews.model.StockNewsVO;
import com.tracklisting.model.TrackListingVO;

import hibernate.util.HibernateUtil;

public class MsgHibernateDAO implements MsgDAO_interface {

	private static final String GET_ALL = "from MsgVO ";
	private static final String GET_LISTNO = "from MsgVO where member_id=? and stock_no=?";
	private static final String GET_STOCKNO_BY_MEMBERID = "from MsgVO where member_id=?";

	@Override
	public void insert(MsgVO MsgVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(MsgVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(MsgVO MsgVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(MsgVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public List<MsgVO> findByKey(String MemberId, Integer StockNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<MsgVO> list = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_LISTNO);
			query.setParameter(0, MemberId);
			query.setParameter(1, StockNo);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public List<MsgVO> findStockNo(String MemberId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<MsgVO> list = null;
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_STOCKNO_BY_MEMBERID);
			query.setParameter(0, MemberId);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public List<MsgVO> getAll() {
		List<MsgVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	public static void main(String[] args) {
		MsgHibernateDAO msgdao = new MsgHibernateDAO();
		MsgVO msgvo1 = new MsgVO();
		MsgListVO msgListVO = new MsgListVO();
		MemberVO memberVO = new MemberVO();
		memberVO.setMemberId("Charizard");
		msgvo1.setMemberVO(memberVO);
		// msgListVO.setListNo(1);
		// msgvo1.setMsgListVO(msgListVO);;
		// msgvo1.setMemberVO(memberVO);
		// msgvo1.setStockNo("2330");

		// ----------------新增---------------------------------

		// msgdao.insert(msgvo1);

		// ----------------刪除------------------------------------
		// msgdao.delete(msgvo1);;
		//
		// ----------------查詢一個----------------------------------
		// List<MsgVO> msg =
		// msgdao.findByKey(msgvo1.getMemberId(),msgvo1.getStockNo());
		// System.out.print(msg.get(0).getMemberId()+",");
		// System.out.print(msg.get(0).getStockNo()+",");
		// System.out.print(msg.get(0).getMsgListVO().getListNo());
		// System.out.println();
		// System.out.print(msg.get(1).getMemberId()+",");
		// System.out.print(msg.get(1).getStockNo()+",");
		// System.out.print(msg.get(1).getMsgListVO().getListNo());
		// System.out.println();

		// ---------------查詢一個會員有追蹤哪寫股票-----------------------
		List<MsgVO> list = msgdao.findStockNo(msgvo1.getMemberVO().getMemberId());
		for (MsgVO msg : list) {
			System.out.print(msg.getStockVO().getStockNo() + ",");
			Set<DividendVO> dividens = msg.getStockVO().getDividends();
			for (DividendVO dividen : dividens) {
				System.out.print(dividen.getDividend()+",");
			}
			System.out.println();

			// System.out.print(msg.getMsgListVO().getListName()+",");
			// System.out.print(msg.getMsgListVO().getListNo());

			System.out.println();
		}

		// ----------------查詢多個------------------------------------
		// List<MsgVO> list = msgdao.getAll();
		// for (MsgVO msg : list) {
		//
		// for (TrackListingVO tl : msg.getMemberVO().getTrackListingVOs()) {
		// System.out.print(msg.getMemberVO().getMemberId() + ",");
		// System.out.print(tl.getListingName() + ",");
		// System.out.println(tl.getListingNo());
		// for (ListingDetailsVO ld : tl.getLds()) {
		// System.out.print(ld.getTrackListingVO().getListingNo() + ",");
		// System.out.print(ld.getStockNo()+",");
		// };
		// System.out.println();
		//
		// }
		// System.out.print(msg.getStockNo() + ",");
		// System.out.print(msg.getMsgListVO().getListName() + ",");
		// System.out.print(msg.getMsgListVO().getListNo());
		// System.out.println();

		// }

	}

}
