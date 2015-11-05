--- 10.8 초기 예제값 입력용 Database 주입 샘플

INSERT INTO TBL_USER_INFO (
	
	USER_ID, 
	USER_PASSWORD, 
	USER_NAME,
	USER_EMAIL1,
	USER_EMAIL2,
	USER_PHONENUMBER,
	USER_BIRTHYEAR,
	USER_BIRTHMONTH,
	USER_BIRTHDATE,
	USER_BIRTHTYPE,
	USER_SEX,	
	USER_REGDATE
	
) VALUES (
	
	'sysadmin',
	'sysamdin',
	'bongja',
	'bongja',
	'ajou.ac.kr',
	1111111111,
	1999,
	11,
	11,
	'양력',
	'남자',
	now()
	
	
);