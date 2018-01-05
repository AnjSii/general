/*
Navicat MySQL Data Transfer

Source Server         : MyDataBase
Source Server Version : 50605
Source Host           : localhost:3306
Source Database       : general

Target Server Type    : MYSQL
Target Server Version : 50605
File Encoding         : 65001

Date: 2018-01-05 10:27:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` tinyint(1) NOT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `trueName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `userRole` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `lastLoginDate` datetime DEFAULT NULL,
  `loginDate` datetime DEFAULT NULL,
  `lastLoginIp` varchar(255) DEFAULT NULL,
  `loginIp` varchar(255) DEFAULT NULL,
  `loginCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
