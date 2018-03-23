package kr.or.ddit.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

/**
 * @Class Name : CryptoGenerator.java
 * @Description : key 암호화
 * @Modification Information
 * @author 정희수
 * @since  2018.03.12.
 * @version 1.0
 * @see IFreeboardDao
 * <pre>
 * << 개정이력(Modification Information) >>
 *    수정일       수정자          수정내용
 *    -------      -------     -------------------
 *    2018.03.12.  정희수      최초작성
 * Copyright (c) 2018 by DDIT  All right reserved
 * </pre>
 */


public class CryptoGenerator {
	//암호화(공개키:PublicKey), 복호화(비밀키:PrivateKey) 키를 작성(공개키와 비밀키 쌍 수 MAP으로 반환
	// * 키는 재사용 불가
	public static Map<String, String> getGeneratorKey(HttpSession session){
		KeyPairGenerator generator = null;
		KeyPair keyPair = null;
		KeyFactory keyFactory = null;
		
		PublicKey publicKey = null;
		PrivateKey privateKey = null;
		
		Map<String, String> publicKeyMap = new HashMap<String, String>();
		try {
			//공개키, 비밀키 작성하는 자원에 적용할 암화화 알고리즘을 설정
			generator = KeyPairGenerator.getInstance("RSA");
			//공개치 작성시의 키의 사이즈
			generator.initialize(2048);
			//공개키 비밀키 작성
			keyPair = generator.generateKeyPair();
			//공개키 취득
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
			
			session.setAttribute("privateKey", privateKey);
			
			keyFactory = KeyFactory.getInstance("RSA");
			
			//클라이언트에 제공될 공개키를 설정된 사이즈와 암호화 알고리즘을 기초로 Double타입의 부호+가수부+지수부로 분리
			// -1456.1238374328748342 => 1 00010100 10101011111101111111001
			// 1456.1238374328748342  => 0 
			//														소수점 위치 	  값
			// float(32bit 단정도 비트 처리) : 부호부트(양수 0, 음수 1) + 가수부(8bit) + 지수부(23bit)
			// double(64bit 배정도 비트 처리) : 부호부트(양수 0, 음수 1) + 가수부(11bit) + 지수부(52bit)
			RSAPublicKeySpec publicKeySpec = (RSAPublicKeySpec)keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
			
			//공개키(지수)
			String publicKeyModulus = publicKeySpec.getModulus().toString(16);
			//공개키(가수)
			String publicKeyExponent = publicKeySpec.getPublicExponent().toString(16);
			
			publicKeyMap.put("publicKeyModulus", publicKeyModulus);
			publicKeyMap.put("publicKeyExponent", publicKeyExponent);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return publicKeyMap;
	}//method End
	
	public static String decryptoRSA(HttpSession session, String secureValue){
		
		PrivateKey privateKey = (PrivateKey) session.getAttribute("privateKey");
		String returnValue = "";
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] targetBytes = hextoByteArray(secureValue);
			byte[] beforeString = cipher.doFinal(targetBytes);
			
			returnValue = new String(beforeString,"UTF-8");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	private static byte[] hextoByteArray(String secureValue){
		if(secureValue == null || secureValue.length()%2 != 0){
			return new byte[]{};
		}
		byte[] bytes = new byte[secureValue.length()/2];
		for(int i=0; i<secureValue.length(); i+=2){
			byte value =  (byte) Integer.parseInt(secureValue.substring(i, i+2),16);
			bytes[(int) Math.floor(i/2)] = value;
		}
		return bytes;
	}
}	
