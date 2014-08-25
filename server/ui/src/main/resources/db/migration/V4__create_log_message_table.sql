CREATE TABLE `LOG_MESSAGE` (
  `ID` BIGINT(20) AUTO_INCREMENT,
  `APPLICATION_ID` BIGINT(20) NOT NULL,
  `TIMESTAMP` TIMESTAMP NOT NULL,
  `MESSAGE` TEXT NOT NULL,
  `LOGGER_NAME` VARCHAR(512) NOT NULL,
  `LEVEL` VARCHAR(32) NOT NULL,
  `FILENAME` VARCHAR(256) NULL DEFAULT NULL,
  `CLASS` VARCHAR(256) NULL DEFAULT NULL,
  `METHOD` VARCHAR(256) NULL DEFAULT NULL,
  `LINE` SMALLINT(6) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
);
