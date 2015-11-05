CREATE TABLE `tbl_attach_file` (
	`ATTACH_SEQ` INT(11) NOT NULL AUTO_INCREMENT,
	`ATTACH_SELL_SEQ` INT(11) NOT NULL,
	`ATTACH_FILE_NAME` VARCHAR(255) NOT NULL,
	`ATTACH_TEMP_NAME` VARCHAR(255) NOT NULL,
	`ATTACH_CONTENT_TYPE` VARCHAR(255) NOT NULL,
	`ATTACH_FILE_URL` VARCHAR(255) NOT NULL,
	`ATTACH_REG_DATE` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`ATTACH_SEQ`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
ROW_FORMAT=DEFAULT
AUTO_INCREMENT=7