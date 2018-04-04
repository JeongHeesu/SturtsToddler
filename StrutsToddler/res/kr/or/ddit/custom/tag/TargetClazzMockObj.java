package kr.or.ddit.custom.tag;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TargetClazzMockObj {
	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public  Integer plus(Integer i, Integer j){
		return i+j;
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public Integer mulitple(String i, Integer j){
		return Integer.parseInt(i) * j;
	}
	
	/**
	 * 
	 * @return
	 */
	public  String today(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return dateFormat.format(new Date());
	}
	
	/**
	 * 중복되는 글자의 개수
	 * @param target
	 * @param value
	 * @return Integer
	 */
	public Integer charCount(String target, String value){
		int idx = 0;
		int cnt = 0;
		idx = target.indexOf(value);
		for(; idx != -1; cnt++){
			target=target.substring(target.indexOf(value)+1,target.length());
			idx = target.indexOf(value);
		}
		return cnt;
	}
	
}
