package kr.or.ddit.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;

import kr.or.ddit.global.GlobalConstant;
import kr.or.ddit.vo.FileVO;

import com.opensymphony.xwork2.ModelDriven;

//http://localhost/StrutsToddler/file/fileUplaod.do
// 		method=post, encType = multipart/form-data
//		mem_id=a01 , mem_pass=asdfasdf,mem_name=김은데
//		files= a.png, files=b,png
public class FileController implements ModelDriven<FileVO> {
	private FileVO fileInfo;
	private String fileName;
	
	public String fileUpload(){
		List<File> files = fileInfo.getFiles();
		List<String> filesContentType = fileInfo.getFilesContentType();
		List<String> filesFileName = fileInfo.getFilesFileName();
		
		for(int i=0; i<files.size(); i++){
			File  target = files.get(i);
			if(target.length()>0){
				File saveFile = new File(GlobalConstant.SAVE_PATH, filesFileName.get(i));
				fileName = filesFileName.get(i);
				try {
					FileUtils.copyFile(target, saveFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return "success";
	}

	@Override
	public FileVO getModel() {
		fileInfo = new FileVO();
		return fileInfo;
	}

	public String getFileName() {
		return fileName;
	}
	
	//파일 다운로
	private String contentDisposition;
	private long contentLength;
	private InputStream inputStream;
	
	public String fileDownlaod(){
		File downFile = new File(GlobalConstant.SAVE_PATH,this.fileName);
		this.contentDisposition = "attachment;fileName="+this.fileName;
		this.contentLength = downFile.length();
		
		try {
			this.inputStream = new FileInputStream(downFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return "success";
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public long getContentLength() {
		return contentLength;
	}

	public InputStream getInputStream() {
		return inputStream;
	}
	
	
}
