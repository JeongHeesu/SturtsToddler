package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.MemberVO;
/**
 * @Interface Name : IMemberService.java
 * @Description : IMemberServiceImpl 연결 위한 Interface 
 * @Modification Information
 * @author 정희수
 * @since  2018.02.21.
 * @version 1.0
 * <pre>
 * << 개정이력(Modification Information) >>
 *    수정일       수정자          수정내용
 *    -------      -------     -------------------
 *    2018.02.21.  정희수      최초작성
 * Copyright (c) 2018 by DDIT  All right reserved
 * </pre>
 */
public interface IMemberService {
	/**
	 * 회원에 대한 정보 출력
	 * @param Map
	 * @throws java.sql.SQLException
	 */
	public MemberVO getMemberInfo(Map<String, String> params);
	/**
	 * 가입된 회원들 리스트 출력
	 */
	public List<MemberVO> getMemberList();
	/**
	 * 회원가입
	 * @param MemberVO 
	 * @throws java.sql.SQLException
	 */
	public String insertMemberInfo(MemberVO memberInfo);
	/**
	 * 회원탈퇴
	 * @param Map
	 * @throws java.sql.SQLException
	 */
	public void deleteMemberInfo(Map<String, String> params);
	/**
	 * 회원정보 수정 
	 * @param MemberVO
	 * @throws java.sql.SQLException
	 */
	public void updateMemberInfo(MemberVO memberInfo);
	
	
	
}
