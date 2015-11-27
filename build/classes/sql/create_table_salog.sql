DROP TABLE TBL_SA_INFO;
CREATE TABLE TBL_SALOG_INFO (
	SALOG_NUMSEQ INT(20) UNSIGNED NOT NULL AUTO_INCREMENT comment 'sequence number: primary key',
	SALOG_SATYPE VARCHAR(20) NOT NULL comment '센서 종류 1 TVstatus, 2 TV channel, 3 WM status 4 LED status',
	SALOG_RASPBERRYSN VARCHAR(30) NOT NULL comment 'raspberry serial number: 라즈베리 시리얼번호',
	SALOG_UPDATEVALUE VARCHAR(16) comment '실제 값이 저장되는 튜플',
	SALOG_LASTUPDATETIME DATETIME NOT NULL comment 'raspberry 센서값이 최종 업데이트되는 시간',	
	PRIMARY KEY (`SALOG_NUMSEQ`)
);