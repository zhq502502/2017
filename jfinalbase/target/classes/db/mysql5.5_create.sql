DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 存储过程
DELIMITER $$
USE `ckshop`$$
DROP PROCEDURE IF EXISTS `sp_add3`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_add3`(a INT, b INT,OUT c INT)
BEGIN 
SET c=a+ b;
END$$
DELIMITER ;


-- ----------------------------
-- Table structure for `tbtoken`
-- ----------------------------
DROP TABLE IF EXISTS `tbtoken`;
CREATE TABLE `tbtoken` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `updatetime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `test1`;
CREATE TABLE `test1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `test` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
