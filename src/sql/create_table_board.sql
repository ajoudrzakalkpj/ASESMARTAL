CREATE TABLE `tbl_board_info` (
	`BOARD_NUMSEQ` INT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'board sequence',
	`BOARD_SUBJECT` VARCHAR(250) NOT NULL COMMENT 'board subject: 제목',
	`BOARD_WRITER` VARCHAR(20) NOT NULL COMMENT 'board writer: 작성자: 외래키 user table의 name',
	`BOARD_CONTENTS` TEXT NULL COMMENT 'board contents: 내용',
	`BOARD_OPENPOLICY` VARCHAR(2) NOT NULL COMMENT 'board type: 일반 = 1, 공지 = 0, default = 1',
	`BOARD_UPDATETIME` DATETIME NOT NULL COMMENT 'board register: 작성일 now()',
	`BOARD_FINISHEDTIME` DATETIME NOT NULL COMMENT '이 보드가 종료되는 시간',
	`BOARD_SERVERADMIN` VARCHAR(20) NOT NULL COMMENT 'board writer: 작성자: 외래키 user table의 name',
	PRIMARY KEY (`BOARD_NUMSEQ`),
	INDEX `FK_tbl_board_info_tbl_user_info` (`BOARD_SERVERADMIN`),
	CONSTRAINT `FK_tbl_board_info_tbl_user_info` FOREIGN KEY (`BOARD_SERVERADMIN`) REFERENCES `tbl_user_info` (`USER_SERVERADMIN`) ON UPDATE CASCADE ON DELETE CASCADE
);

