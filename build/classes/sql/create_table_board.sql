--- 20151008 Byungwook update
--- create test complete 20151014 변경내용; 게시판 table + comment table!!
--- 20151027 봉재 password 관련 내용 추가 / Comment 내용중 세미콜론 내용을 콜론으로 변경

CREATE TABLE `tbl_board_info` (
	`BOARD_NUMSEQ` INT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'board sequence',
	`BOARD_SUBJECT` VARCHAR(250) NOT NULL COMMENT 'board subject: 제목',
	`BOARD_WRITER` VARCHAR(15) NOT NULL COMMENT 'board writer: 작성자: 외래키 user table의 name',
	`BOARD_TYPE` VARCHAR(15) NOT NULL DEFAULT '1' COMMENT 'board type: 일반 = 1, 공지 = 0, default = 1',
	`BOARD_PASSWORD` VARCHAR(20) NOT NULL COMMENT 'BoardPassword: 게시판 패스워드',
	`BOARD_CONTENTS` TEXT NULL COMMENT 'board contents: 내용',
	`BOARD_HITS` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT 'board hits: 조회수: default = 0',
	`BOARD_RECOMMENDATION` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT 'board recommendation: 추천수: default = 0',
	`BOARD_REGDATE` DATETIME NOT NULL COMMENT 'board register: 작성일 now()',
	`BOARD_MODDATE` DATETIME NULL DEFAULT NULL COMMENT 'board modify: 수정일: 수정시 추가',
	`BOARD_STEP` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '0일 경우 게시판, 아닐 경우 댓글임을 의미',
	`BOARD_UPPER` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT 'BOARD_NUMSEQ를 참조: 0일 경우 게시판, 아닌 경우 상위 게시판을 참조하거나 상위 댓글을 참조',
	`BOARD_ROOTUPPER` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '최상위 BOARD_NUMSEQ를 참조: 0일 경우 게시판, 아닌 경우 최상위 게시판을 참조',
	PRIMARY KEY (`BOARD_NUMSEQ`),
	INDEX `FK_tbl_board_info_tbl_user_info` (`BOARD_WRITER`),
	INDEX `FK_tbl_board_info_tbl_board_option` (`BOARD_TYPE`),
	CONSTRAINT `FK_tbl_board_info_tbl_board_option` FOREIGN KEY (`BOARD_TYPE`) REFERENCES `tbl_board_option` (`BOARD_TYPE`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `FK_tbl_board_info_tbl_user_info` FOREIGN KEY (`BOARD_WRITER`) REFERENCES `tbl_user_info` (`USER_ID`) ON UPDATE CASCADE ON DELETE CASCADE
);

