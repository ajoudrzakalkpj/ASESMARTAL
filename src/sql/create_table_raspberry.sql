DROP TABLE TBL_RASPBERRY_INFO;
CREATE TABLE TBL_RASPBERRY_INFO (
	RASPBERRY_NUMSEQ INT(20) UNSIGNED NOT NULL AUTO_INCREMENT comment 'sequence number: primary key',
	RASPBERRY_ID VARCHAR(20) comment 'raspberry id: 라즈베리 아이디',
	RASPBERRY_NUMSN VARCHAR(30) NOT NULL comment 'raspberry serial number: 라즈베리 시리얼번호',
	RASPBERRY_STATUS VARCHAR(14) comment 'raspberry status : 상태',
	RASPBERRY_IPADDR VARCHAR(11) comment 'raspberry ipaddress : IP주소',
	RASPBERRY_SERVERADMIN VARCHAR(20) comment 'raspberry serveradmin : 라즈베리가 속하는 서버 admin 주소',
	RASPBERRY_LASTUPDATETIME DATETIME comment 'raspberry 최종 업데이트 시간',
	RASPBERRY_SSID VARCHAR(30) comment '라즈베리의 접속한 SSID ',	
	PRIMARY KEY (`RASPBERRY_NUMSEQ`)

);