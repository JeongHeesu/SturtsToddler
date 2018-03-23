package kr.or.ddit.vo;

import java.util.List;


/**
 * @Class Name : FreeboardVO.java
 * @Description :  Freeboard 테이블 컬럼
 * @Modification Information
 * @author 정희수
 * @since  2018.03.09.
 * @version 1.0
 * <pre>
 * << 개정이력(Modification Information) >>
 *    수정일       수정자          수정내용
 *    -------      -------     -------------------
 *    2018.03.09.  정희수      최초작성
 *    2018.03.14.  정희수      첨부파일 추가
 * Copyright (c) 2018 by DDIT  All right reserved
 * </pre>
 */

public class FreeboardVO {
	
	private String rnum;
	private String bo_no;
	private String bo_title;
	private String bo_writer;
	private String bo_nickname;
	private String bo_pwd;
	private String bo_mail;
	private String bo_ip;
	private String bo_content;
	private String bo_hit;
	private String bo_reg_date;
	private String bo_status;
	private String bo_group;
	private String bo_seq;
	private String bo_depth;
	
	private List<FileItemVO> fileItemList;
	
	public String getRnum() {
		return rnum;
	}
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	public String getBo_no() {
		return bo_no;
	}
	public void setBo_no(String bo_no) {
		this.bo_no = bo_no;
	}
	public String getBo_title() {
		return bo_title;
	}
	public void setBo_title(String bo_title) {
		this.bo_title = bo_title;
	}
	public String getBo_writer() {
		return bo_writer;
	}
	public void setBo_writer(String bo_writer) {
		this.bo_writer = bo_writer;
	}
	public String getBo_nickname() {
		return bo_nickname;
	}
	public void setBo_nickname(String bo_nickname) {
		this.bo_nickname = bo_nickname;
	}
	public String getBo_pwd() {
		return bo_pwd;
	}
	public void setBo_pwd(String bo_pwd) {
		this.bo_pwd = bo_pwd;
	}
	public String getBo_mail() {
		return bo_mail;
	}
	public void setBo_mail(String bo_mail) {
		this.bo_mail = bo_mail;
	}
	public String getBo_ip() {
		return bo_ip;
	}
	public void setBo_ip(String bo_ip) {
		this.bo_ip = bo_ip;
	}
	public String getBo_content() {
		return bo_content;
	}
	public void setBo_content(String bo_content) {
		this.bo_content = bo_content;
	}
	public String getBo_hit() {
		return bo_hit;
	}
	public void setBo_hit(String bo_hit) {
		this.bo_hit = bo_hit;
	}
	public String getBo_reg_date() {
		return bo_reg_date;
	}
	public void setBo_reg_date(String bo_reg_date) {
		this.bo_reg_date = bo_reg_date;
	}
	public String getBo_status() {
		return bo_status;
	}
	public void setBo_status(String bo_status) {
		this.bo_status = bo_status;
	}
	public String getBo_group() {
		return bo_group;
	}
	public void setBo_group(String bo_group) {
		this.bo_group = bo_group;
	}
	public String getBo_seq() {
		return bo_seq;
	}
	public void setBo_seq(String bo_seq) {
		this.bo_seq = bo_seq;
	}
	public String getBo_depth() {
		return bo_depth;
	}
	public List<FileItemVO> getFileItemList() {
		return fileItemList;
	}
	public void setFileItemList(List<FileItemVO> fileItemList) {
		this.fileItemList = fileItemList;
	}
	public void setBo_depth(String bo_depth) {
		this.bo_depth = bo_depth;
	}

	
}
