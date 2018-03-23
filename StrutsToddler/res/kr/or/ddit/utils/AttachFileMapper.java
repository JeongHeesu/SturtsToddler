package kr.or.ddit.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.Filter;

import kr.or.ddit.global.GlobalConstant;
import kr.or.ddit.vo.FileItemVO;
import kr.or.ddit.vo.IDPictureVO;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;
/**
 * @Class Name : AttachFileMapper.java
 * @Description : 회원가입 사진등록위한 메퍼
 * @Modification Information
 * @author 정희수
 * @since  2018.03.15.
 * @version 1.0
 * <pre>
 * << 개정이력(Modification Information) >>
 *    수정일       수정자          수정내용
 *    -------      -------     -------------------
 *    2018.03.15.  정희수      최초작성
 * Copyright (c) 2018 by DDIT  All right reserved
 * </pre>
 */
public class AttachFileMapper {

	public static List<FileItemVO> mapping(String bo_no, FileItem[] items) {
		List<FileItemVO> fileItemsList = new ArrayList<FileItemVO>();
		if( items != null) {
			FileItemVO fileItemInfo = new FileItemVO();
			for(FileItem item : items){
				fileItemInfo = new FileItemVO();
				fileItemInfo.setFile_bo_no(bo_no);
				// D:\\a.png
				String fileName = FilenameUtils.getName(item.getName());
				fileItemInfo.setFile_name(fileName);
				
				// a.png -> a와 png 구분
				//a
				String baseName = FilenameUtils.getBaseName(fileName);
				//png
				String extensionName = FilenameUtils.getExtension(fileName);
				
				// UUID(Universally Unique Identifier) -128bit 의 랜덤한 값 반환
				String genID = UUID.randomUUID().toString().replace("-", "");
				
				//a23423423423424.png
				String saveFileName = baseName+genID+"."+extensionName;
				fileItemInfo.setFile_save_name(saveFileName);
				fileItemInfo.setFile_content_type(item.getContentType());
				fileItemInfo.setFile_size(String.valueOf(item.getSize()));
				
				fileItemsList.add(fileItemInfo);
				
				saveFile(saveFileName, item);
				
			}
		}
		return fileItemsList;
	}
	
	
	private static void saveFile(String fileName, FileItem item){
		File targetFile = new File(GlobalConstant.SAVE_PATH, fileName);
		try {
			item.write(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static IDPictureVO idPicMapping(String mem_id,FileItem item) {
		IDPictureVO idpicInfo=null;
		if( item != null) {
			
			idpicInfo = new IDPictureVO();
			
			idpicInfo.setFile_mem_id(mem_id);
			
			String fileName = FilenameUtils.getName(item.getName());
			idpicInfo.setFile_name(fileName);
			
			String baseName = FilenameUtils.getBaseName(fileName);
			
			String extensionName = FilenameUtils.getExtension(fileName);
			
			String genID = UUID.randomUUID().toString().replace("-", "");
			
			String saveFileName = baseName+genID+"."+extensionName;
			idpicInfo.setFile_save_name(saveFileName);
			idpicInfo.setFile_content_type(item.getContentType());
			idpicInfo.setFile_size(String.valueOf(item.getSize()));
				
			saveFile(saveFileName, item);
				
			
		}
		return idpicInfo;
	}
	
	
}
