package kr.or.ddit.vo;
/**
 * @Class Name : IDPictureVO.java
 * @Description : IDPictureVO 컬럼
 * @Modification Information
 * @author 정희수
 * @since  2018.03.14.
 * @version 1.0
 * <pre>
 * << 개정이력(Modification Information) >>
 *    수정일       수정자          수정내용
 *    -------      -------     -------------------
 *    2018.03.14.  정희수      최초작성
 * Copyright (c) 2018 by DDIT  All right reserved
 * </pre>
 */
public class IDPictureVO {

	private String file_seq;
	private String file_mem_id;
	private String file_name;
	private String file_save_name;
	private String file_content_type;
	private String file_size;
	private String file_status;
	private String file_reg_date;
	public String getFile_seq() {
		return file_seq;
	}
	public void setFile_seq(String file_seq) {
		this.file_seq = file_seq;
	}
	public String getFile_mem_id() {
		return file_mem_id;
	}
	public void setFile_mem_id(String file_mem_id) {
		this.file_mem_id = file_mem_id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_save_name() {
		return file_save_name;
	}
	public void setFile_save_name(String file_save_name) {
		this.file_save_name = file_save_name;
	}
	public String getFile_content_type() {
		return file_content_type;
	}
	public void setFile_content_type(String file_content_type) {
		this.file_content_type = file_content_type;
	}
	public String getFile_size() {
		return file_size;
	}
	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}
	public String getFile_status() {
		return file_status;
	}
	public void setFile_status(String file_status) {
		this.file_status = file_status;
	}
	public String getFile_reg_date() {
		return file_reg_date;
	}
	public void setFile_reg_date(String file_reg_date) {
		this.file_reg_date = file_reg_date;
	}
	
	
	
}
