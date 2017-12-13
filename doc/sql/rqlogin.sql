/*
Navicat MySQL Data Transfer

Source Server         : MyDataBase
Source Server Version : 50605
Source Host           : localhost:3306
Source Database       : general

Target Server Type    : MYSQL
Target Server Version : 50605
File Encoding         : 65001

Date: 2017-12-13 16:37:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rqlogin
-- ----------------------------
DROP TABLE IF EXISTS `rqlogin`;
CREATE TABLE `rqlogin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `sid` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `scanTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;
