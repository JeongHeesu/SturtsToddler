/**
 * 입력값 검증
 * 버전 : 1.0
 * << 개정이력(Modification Information) >>
 * 수정일       수정자          수정내용   
 * =======      =======     ===================== 
 * 2018.02.19.   정희수        최초작성   
 * Copyright (c) 2018 by DDIT  All right reserved  
 */

//"a001".validationID()
String.prototype.validationID = function(){
	//"a001" 전달 == this
	return /^[a-z][0-9]{3}$/.test(this);
};
//"asdfasdf".validationPWD()
String.prototype.validationPWD = function(){
	return /^[0-9a-z]{4,10}$/.test(this);
};

//"정희수".validationNM()
String.prototype.validationNM = function(){
	return /^[가-힣]{2,4}$/.test(this);
};
//"111111-1111118.vaildationREGNO()
String.prototype.validationREGNO = function(){
	// 1 1 1 1 1 1 - 1 1 1 1 1 1    8(매직넘버)
	// * * * * * *   * * * * * *   
	// 2+3+4+5+6+7 + 8+9+2+3+4+5 = 값1
	// (11-(값1%11))%10 = 값2
	//if(값2 == 매직넘버) 유효 else 무효
	
	var moto = this.replace('-', '');
	var magicNum = this.substr(13, 1);
	var checkSum = '234567892345';
	var sum = 0;
	for(var i =0; i<12; i++){
		sum+= moto.charAt(i) * checkSum.charAt(i);
	}
	var finalval = (11-(sum%11))%10;
	if(finalval == parseInt(magicNum)){
		return true;
	}else{
		return false;
	}
};

String .prototype.validationZIPCODE = function(){
	return /^\d{3}-\d{3}$/.test(this);
};

String.prototype.validationHOMETEL = function(){
	return /^0\d{1,2}-\d{3,4}-\d{4}$/.test(this);
};

String.prototype.validationEMAIL = function(){
	// \w  [0-9a-z_]
	// . 키보드로 입력가능한 모든 캐릭터셋 중에 한글자.
	// \ 정규식  패턴 의미있는 기호를 일반 기호로 제외
	return /^\w+@[a-z]+(\.[a-z]+){1,2}$/.test(this);
};
//회사번호
String.prototype.validationCOMTEL = function(){
	return /^0\d{1,2}-\d{3,4}-\d{4}$/.test(this);
};
//핸드폰번호
String.prototype.validationHP = function(){
	return /^01(0|1|6|7-9)-\d{3,4}-\d{4}$/.test(this);
};
String.prototype.validationTITLE = function(){
	return /^[0-9a-z가-힣]+$/.test(this);
};
String.prototype.validationNICKNAME = function(){
	return /^[가-힣]{1,4}$/.test(this);
};


