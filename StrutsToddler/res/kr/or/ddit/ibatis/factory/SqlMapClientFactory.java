package kr.or.ddit.ibatis.factory;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
/**
 * @Class Name : SqlMapClientFactory.java
 * @Description : SqlClient의 싱글톤
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
public class SqlMapClientFactory {
	private static SqlMapClient client;
	/**
	 * static SqlMapClient 싱글톤
	 */
	static{
		Charset charSet = Charset.forName("UTF-8");
		Resources.setCharset(charSet);
		
		try {
			Reader reader = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			client = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 싱글톤
	 * @return SqlMapClient
	 */
	public static SqlMapClient getSqlMapClient(){
		return client;
	}
}
