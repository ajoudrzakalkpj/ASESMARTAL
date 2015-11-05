--- 20151008 Byungwook update
--- create insert test complete

CREATE TABLE TBL_BOARD_OPTION (
	BOARD_TYPE VARCHAR(15) NOT NULL comment 'board type: 공지=0, 일반=1',	
	BOARD_COUNT INT(10) UNSIGNED NOT NULL DEFAULT '0',	
	PRIMARY KEY (`BOARD_TYPE`)
);

insert into TBL_BOARD_OPTION (BOARD_TYPE) values('0');
insert into TBL_BOARD_OPTION (BOARD_TYPE) values('1');
