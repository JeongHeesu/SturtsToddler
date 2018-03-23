package kr.or.ddit.vo;

import java.io.File;
import java.util.List;

public class FileVO {
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	// MutipartRequestWarpper 스트럿츠 파일 업로드 인터셉터 자원을 활용
	// 파일 전송시의 필드 네임을 활용한 명명규칙
	//파일(input type=file name=files)
	private List<File> files;
	// 파일 컨텐츠 타입(필드네임+contentType)
	private List<String> filesContentType;
	//파일의 이름(필드네임+FileName)
	private List<String> filesFileName;
	
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> files) {
		this.files = files;
	}
	public List<String> getFilesContentType() {
		return filesContentType;
	}
	public void setFilesContentType(List<String> filesContentType) {
		this.filesContentType = filesContentType;
	}
	public List<String> getFilesFileName() {
		return filesFileName;
	}
	public void setFilesFileName(List<String> filesFileName) {
		this.filesFileName = filesFileName;
	}
	
}
