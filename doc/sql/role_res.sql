/*
Navicat MySQL Data Transfer

Source Server         : MyDataBase
Source Server Version : 50605
Source Host           : localhost:3306
Source Database       : general

Target Server Type    : MYSQL
Target Server Version : 50605
File Encoding         : 65001

Date: 2018-01-05 10:27:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role_res
-- ----------------------------
DROP TABLE IF EXISTS `role_res`;
CREATE TABLE `role_res` (
  `role_id` bigint(20) NOT NULL,
  `res_id` bigint(20) NOT NULL,
  KEY `fk222` (`res_id`),
  KEY `faweq` (`role_id`),
  CONSTRAINT `fk222` FOREIGN KEY (`res_id`) REFERENCES `res` (`id`),
  CONSTRAINT `faweq` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_res
-- ----------------------------
