DROP TABLE TBL_SA_INFO;
CREATE TABLE TBL_SA_INFO (
	SA_NUMSEQ INT(20) UNSIGNED NOT NULL AUTO_INCREMENT comment 'sequence number: primary key',
	SA_TYPE VARCHAR(20) NOT NULL comment '센서 종류 1 TVstatus, 2 TV channel, 3 WM status 4 LED status',
	SA_RASPBERRYSN VARCHAR(30) NOT NULL comment 'raspberry serial number: 라즈베리 시리얼번호',
	SA_NAME VARCHAR(1) comment '사용자가 알기쉽게 넣은 이름',
	SA_UPDATEVALUE VARCHAR(16) comment '실제 값이 저장되는 튜플',
	SA_RESERVESTATUS VARCHAR(20) comment '라즈베리파이의 센서가 예약되어 있는 상태 정보 ',
	SA_LASTUPDATETIME DATETIME NOT NULL comment 'raspberry 센서값이 최종 업데이트되는 시간',	
	PRIMARY KEY (`SA_NUMSEQ`)
);


	INDEX `FK_tbl_raspberry_info_tbl_sa_info` (`SA_RASPBERRYSN`),
	CONSTRAINT `FK_tbl_raspberry_info_tbl_sa_info` FOREIGN KEY (`SA_RASPBERRYSN`) REFERENCES `tbl_raspberry_info` (`RASPBERRY_NUMSN`) ON UPDATE CASCADE ON DELETE CASCADE,
	
	
	
INSERT INTO TBL_SA_INFO (
	SA_TYPE,
	SA_RASPBERRYSN,
	SA_RESERVESTATUS,
	SA_LASTUPDATETIME			
) VALUES (
	'3',
	'545252342342',
	'0',
	now()
);