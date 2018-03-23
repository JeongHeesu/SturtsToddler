package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.IMemberDaoImpl;
import kr.or.ddit.vo.MemberVO;
/**
 * @Class Name : IMemberServiceImpl.java
 * @Description : IMemberDao 연결
 * @Modification Information
 * @author 정희수
 * @since  2018.02.21.
 * @see IMemberService
 * @version 1.0
 * <pre>
 * << 개정이력(Modification Information) >>
 *    수정일       수정자          수정내용
 *    -------      -------     -------------------
 *    2018.02.21.  정희수      최초작성
 * Copyright (c) 2018 by DDIT  All right reserved
 * </pre>
 */
public class IMemberServiceImpl implements IMemberService {
	private static IMemberService service = new IMemberServiceImpl();
	private IMemberDao dao;
	
	/**
	 * 생성자
	 */
	private IMemberServiceImpl(){
		dao = IMemberDaoImpl.getInstance();
	}
	
	/**
	 * 싱글톤
	 * @return IMemberService
	 */
	public static IMemberService getInstance(){
//		if(service == null){
//			service = new IMemberServiceImpl();
//		}
		return (service == null ) ? service = new IMemberServiceImpl() : service;
	}
	
	/**
	 * 회원에 대한 정보 출력
	 * @param params
	 * @return MemberVO
	 * @throws java.sql.SQLException
	 */
	@Override
	public MemberVO getMemberInfo(Map<String, String> params) {
		MemberVO memberInfo = null;
		try {
			memberInfo = dao.getMemberInfo(params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberInfo;
	}

	/**
	 * 가입된 회원들 리스트 출력
	 * @return List<MemberVO>
	 * @throws java.sql.SQLException
	 */
	@Override
	public List<MemberVO> getMemberList() {
		List<MemberVO> memberList = null;
		try {
			memberList = dao.getMemberList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberList;
	}

	/**
	 * 회원가입
	 * @param memberInfo
	 * @return String
	 * @throws java.sql.SQLException
	 */
	@Override
	public String insertMemberInfo(MemberVO memberInfo) {
		String returnValue = null;
		try {
			returnValue = dao.insertMemberInfo(memberInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	/**
	 * 회원탈퇴
	 * @param params
	 * @throws java.sql.SQLException
	 */
	@Override
	public void deleteMemberInfo(Map<String, String> params) {
		try {
			dao.deleteMemberInfo(params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 회원정보 수정 
	 * @param memberInfo
	 * @throws java.sql.SQLException
	 */
	@Override
	public void updateMemberInfo(MemberVO memberInfo) {
		try {
			dao.updateMemberInfo(memberInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
