package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.factory.SqlMapClientFactory;
import kr.or.ddit.vo.MemberVO;
/**
 * @Class Name : IMemberDaoImpl.java
 * @Description : iBatis 연결 및 내용 전송
 * @Modification Information
 * @author 정희수
 * @since  2018.02.21.
 * @version 1.0
 * @see IMemberDao
 * <pre>
 * << 개정이력(Modification Information) >>
 *    수정일       수정자          수정내용
 *    -------      -------     -------------------
 *    2018.02.21.  정희수      최초작성
 * Copyright (c) 2018 by DDIT  All right reserved
 * </pre>
 */
public class IMemberDaoImpl implements IMemberDao {

	private static IMemberDao dao = new IMemberDaoImpl();
	private SqlMapClient client;
	/**
	 * 생성자 및 SqlMapCilent의 인스턴스화
	 */
	private IMemberDaoImpl(){
		client = SqlMapClientFactory.getSqlMapClient();
	}
	/**
	 * 싱글톤
	 * @return IMemberDao
	 */
	public static IMemberDao getInstance(){
		return (dao == null ) ? dao = new IMemberDaoImpl() : dao;
	}
	


	/**
	 * 회원에 대한 정보 출력
	 * @param params
	 * @return MemberVO
	 * @throws java.sql.SQLException
	 */
	@Override
	public MemberVO getMemberInfo(Map<String, String> params)
			throws SQLException {
		return (MemberVO) client.queryForObject("member.memberInfo", params);
	}

	/**
	 * 가입된 회원들 리스트 출력
	 * @return List<MemberVO>
	 * @throws java.sql.SQLException
	 */
	@Override
	public List<MemberVO> getMemberList() throws SQLException {
		return client.queryForList("member.memberList");
	}

	/**
	 * 회원가입
	 * @param memberInfo
	 * @return String
	 * @throws java.sql.SQLException
	 */
	@Override
	public String insertMemberInfo(MemberVO memberInfo) throws SQLException {
		return (String) client.insert("member.insertMemberInfo", memberInfo);
	}

	/**
	 * 회원탈퇴
	 * @param params
	 * @throws java.sql.SQLException
	 */
	@Override
	public void deleteMemberInfo(Map<String, String> params)
			throws SQLException {
		client.update("member.deleteMemberInfo",params);
	}
	
	/**
	 * 회원정보 수정 
	 * @param memberInfo
	 * @throws java.sql.SQLException
	 */
	@Override
	public void updateMemberInfo(MemberVO memberInfo) throws SQLException {
		client.update("member.updateMemberInfo", memberInfo);
		
	}

}
