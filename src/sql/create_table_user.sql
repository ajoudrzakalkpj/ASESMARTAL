
DROP TABLE TBL_USER_INFO;
CREATE TABLE TBL_USER_INFO (
	USER_NUMSEQ INT(20) UNSIGNED NOT NULL AUTO_INCREMENT comment 'sequence number: primary key',
	USER_ID VARCHAR(20) NOT NULL comment 'user id: 사용자 아이디',
	USER_PASSWORD VARCHAR(512) NOT NULL comment 'user password: 사용자 비밀번호',
	USER_NAME VARCHAR(14) NOT NULL comment 'user name: 사용자 이름',
	USER_PHONENUMBER VARCHAR(11) NOT NULL comment 'phonenumber: 사용자 휴대전화번호',
	USER_PRIVILEGE INT(1) NOT NULL comment '사용자 권한 확인 1: 일반 사용자 2: 서버 관리자',
	USER_SERVERADMIN VARCHAR(20) NOT NULL comment '사용자가 속한 시스템 관리자 위치 확인',
	USER_BIRTHDATE DATE NOT NULL,
	USER_REGDATE  DATETIME 	NOT NULL,
	USER_CONFIRMED INT(1) NOT NULL comment '사용자 확정 상태 값이 1이면 확정 / 값이 0 이면 미확정',
	USER_CONFIRMEDTIME DATETIME comment '사용자 확장 날짜 확정일',
	USER_LASTSSID VARCHAR(30) comment '사용자가 최근 접속한 SSID 확인 : 세션 유지를 위해서....',	
	PRIMARY KEY (`USER_NUMSEQ`),
	INDEX `USER_ID` (`USER_ID`),
	INDEX `USER_SERVERADMIN` (`USER_SERVERADMIN`)
);