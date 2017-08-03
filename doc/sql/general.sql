/*
Navicat MySQL Data Transfer

Source Server         : MyDataBase
Source Server Version : 50605
Source Host           : localhost:3306
Source Database       : general

Target Server Type    : MYSQL
Target Server Version : 50605
File Encoding         : 65001

Date: 2017-08-03 18:29:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for checkout
-- ----------------------------
DROP TABLE IF EXISTS `checkout`;
CREATE TABLE `checkout` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` date DEFAULT NULL,
  `deleteStatus` bit(1) DEFAULT b'0',
  `checkout_store_id` bigint(20) DEFAULT NULL COMMENT '结算的店铺id',
  `checkoutTime` datetime DEFAULT NULL COMMENT '结算日期（确认结算完成后）',
  `audit_status` int(2) DEFAULT '0' COMMENT '审核状态 0未审核，1已审核，-1审核未通过，2正在审核中',
  `order_count` bigint(20) DEFAULT NULL,
  `auditTime` datetime DEFAULT NULL COMMENT '审核时间',
  `payment_id` bigint(20) DEFAULT NULL COMMENT '支付方式的id',
  `checkout_status` int(2) DEFAULT '0' COMMENT '结账状态 0未结账 1已结账',
  `totalPrice` decimal(12,2) DEFAULT NULL COMMENT '总金额',
  PRIMARY KEY (`id`),
  KEY `FK_checkout_store` (`checkout_store_id`),
  KEY `FK_checkout_payment` (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=349 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of checkout
-- ----------------------------
INSERT INTO `checkout` VALUES ('1', '2017-07-25', '\0', '1', null, '0', '1', null, '16', '0', '83.96');
INSERT INTO `checkout` VALUES ('2', '2017-07-25', '\0', '1', null, '0', '26', null, '23', '0', '60.58');
INSERT INTO `checkout` VALUES ('3', '2017-07-25', '\0', '1', null, '0', '47', null, '30', '0', '17.30');
INSERT INTO `checkout` VALUES ('4', '2017-07-25', '\0', '32768', null, '0', '47', null, '23', '0', '20.49');
INSERT INTO `checkout` VALUES ('5', '2017-07-25', '\0', '32768', null, '0', '3', null, '16', '0', '101.67');
INSERT INTO `checkout` VALUES ('6', '2017-07-25', '\0', '32769', null, '0', '47', null, '23', '0', '16.29');
INSERT INTO `checkout` VALUES ('7', '2017-07-25', '\0', '32769', null, '0', '45', null, '16', '0', '123.39');
INSERT INTO `checkout` VALUES ('8', '2017-07-25', '\0', '32772', null, '0', '18', null, '23', '0', '97.83');
INSERT INTO `checkout` VALUES ('9', '2017-07-25', '\0', '32772', null, '0', '45', null, '30', '0', '1.34');
INSERT INTO `checkout` VALUES ('10', '2017-07-25', '\0', '32774', null, '0', '20', null, '23', '0', '119.93');
INSERT INTO `checkout` VALUES ('11', '2017-07-25', '\0', '32774', null, '0', '33', null, '16', '0', '43.70');
INSERT INTO `checkout` VALUES ('12', '2017-07-25', '\0', '32775', null, '0', '40', null, '23', '0', '110.96');
INSERT INTO `checkout` VALUES ('13', '2017-07-25', '\0', '32775', null, '0', '5', null, '16', '0', '106.94');
INSERT INTO `checkout` VALUES ('14', '2017-07-25', '\0', '32777', null, '0', '50', null, '23', '0', '40.23');
INSERT INTO `checkout` VALUES ('15', '2017-07-25', '\0', '32777', null, '0', '33', null, '30', '0', '38.90');
INSERT INTO `checkout` VALUES ('16', '2017-07-25', '\0', '32777', null, '0', '30', null, '23', '0', '122.04');
INSERT INTO `checkout` VALUES ('17', '2017-07-25', '\0', '32778', null, '0', '9', null, '16', '0', '114.72');
INSERT INTO `checkout` VALUES ('18', '2017-07-25', '\0', '32778', null, '0', '5', null, '23', '0', '88.30');
INSERT INTO `checkout` VALUES ('19', '2017-07-25', '\0', '32779', null, '0', '13', null, '16', '0', '21.21');
INSERT INTO `checkout` VALUES ('20', '2017-07-25', '\0', '32779', null, '0', '2', null, '23', '0', '91.32');
INSERT INTO `checkout` VALUES ('21', '2017-07-25', '\0', '32779', null, '0', '27', null, '30', '0', '59.21');
INSERT INTO `checkout` VALUES ('22', '2017-07-25', '\0', '32785', null, '0', '39', null, '23', '0', '48.11');
INSERT INTO `checkout` VALUES ('23', '2017-07-25', '\0', '32785', null, '0', '32', null, '16', '0', '3.19');
INSERT INTO `checkout` VALUES ('24', '2017-07-25', '\0', '32786', null, '0', '9', null, '23', '0', '110.64');
INSERT INTO `checkout` VALUES ('25', '2017-07-25', '\0', '32786', null, '0', '46', null, '16', '0', '96.39');
INSERT INTO `checkout` VALUES ('26', '2017-07-25', '\0', '32787', null, '0', '10', null, '23', '0', '88.67');
INSERT INTO `checkout` VALUES ('27', '2017-07-25', '\0', '32787', null, '0', '48', null, '30', '0', '70.22');
INSERT INTO `checkout` VALUES ('28', '2017-07-25', '\0', '32788', null, '0', '51', null, '23', '0', '35.19');
INSERT INTO `checkout` VALUES ('29', '2017-07-25', '\0', '32788', null, '0', '21', null, '16', '0', '27.68');
INSERT INTO `checkout` VALUES ('30', '2017-07-25', '\0', '32789', null, '0', '44', null, '23', '0', '77.12');
INSERT INTO `checkout` VALUES ('31', '2017-07-25', '\0', '32789', null, '0', '27', null, '16', '0', '100.18');
INSERT INTO `checkout` VALUES ('32', '2017-07-25', '\0', '32790', null, '0', '22', null, '23', '0', '93.40');
INSERT INTO `checkout` VALUES ('33', '2017-07-26', '\0', '32790', null, '0', '23', null, '30', '0', '2.88');
INSERT INTO `checkout` VALUES ('34', '2017-07-26', '\0', '32791', null, '0', '37', null, '23', '0', '66.17');
INSERT INTO `checkout` VALUES ('35', '2017-07-26', '\0', '32791', null, '0', '26', null, '16', '0', '115.28');
INSERT INTO `checkout` VALUES ('36', '2017-07-26', '\0', '32792', null, '0', '6', null, '23', '0', '109.82');
INSERT INTO `checkout` VALUES ('37', '2017-07-26', '\0', '32792', null, '0', '1', null, '16', '0', '55.99');
INSERT INTO `checkout` VALUES ('38', '2017-07-26', '\0', '32793', null, '0', '9', null, '23', '0', '66.55');
INSERT INTO `checkout` VALUES ('39', '2017-07-26', '\0', '32793', null, '0', '7', null, '30', '0', '13.44');
INSERT INTO `checkout` VALUES ('40', '2017-07-26', '\0', '32794', null, '0', '4', null, '23', '0', '16.47');
INSERT INTO `checkout` VALUES ('41', '2017-07-26', '\0', '32794', null, '0', '19', null, '16', '0', '59.99');
INSERT INTO `checkout` VALUES ('42', '2017-07-26', '\0', '32795', null, '0', '14', null, '23', '0', '121.56');
INSERT INTO `checkout` VALUES ('43', '2017-07-26', '\0', '32795', null, '0', '2', null, '16', '0', '38.66');
INSERT INTO `checkout` VALUES ('44', '2017-07-26', '\0', '32796', null, '0', '19', null, '23', '0', '123.50');
INSERT INTO `checkout` VALUES ('45', '2017-07-26', '\0', '32796', null, '0', '43', null, '30', '0', '21.64');
INSERT INTO `checkout` VALUES ('46', '2017-07-26', '\0', '32797', null, '0', '17', null, '23', '0', '28.71');
INSERT INTO `checkout` VALUES ('47', '2017-07-26', '\0', '32797', null, '0', '4', null, '16', '0', '95.65');
INSERT INTO `checkout` VALUES ('48', '2017-07-26', '\0', '32798', null, '0', '30', null, '23', '0', '73.89');
INSERT INTO `checkout` VALUES ('49', '2017-07-26', '\0', '32798', null, '0', '11', null, '16', '0', '42.18');
INSERT INTO `checkout` VALUES ('50', '2017-07-26', '\0', '32799', null, '0', '0', null, '23', '0', '4.35');
INSERT INTO `checkout` VALUES ('51', '2017-07-26', '\0', '32799', null, '0', '6', null, '30', '0', '63.02');
INSERT INTO `checkout` VALUES ('52', '2017-07-26', '\0', '32799', null, '0', '8', null, '23', '0', '42.28');
INSERT INTO `checkout` VALUES ('53', '2017-07-26', '\0', '32800', null, '0', '8', null, '16', '0', '104.20');
INSERT INTO `checkout` VALUES ('54', '2017-07-26', '\0', '32800', null, '0', '35', null, '23', '0', '114.33');
INSERT INTO `checkout` VALUES ('55', '2017-07-26', '\0', '32801', null, '0', '28', null, '16', '0', '118.34');
INSERT INTO `checkout` VALUES ('56', '2017-07-26', '\0', '32801', null, '0', '7', null, '23', '0', '103.47');
INSERT INTO `checkout` VALUES ('57', '2017-07-26', '\0', '32801', null, '0', '38', null, '30', '0', '30.89');
INSERT INTO `checkout` VALUES ('58', '2017-07-26', '\0', '32802', null, '0', '50', null, '23', '0', '15.68');
INSERT INTO `checkout` VALUES ('59', '2017-07-26', '\0', '32802', null, '0', '35', null, '16', '0', '10.84');
INSERT INTO `checkout` VALUES ('60', '2017-07-26', '\0', '32803', null, '0', '17', null, '23', '0', '55.80');
INSERT INTO `checkout` VALUES ('61', '2017-07-26', '\0', '32803', null, '0', '11', null, '16', '0', '92.60');
INSERT INTO `checkout` VALUES ('62', '2017-07-26', '\0', '32804', null, '0', '3', null, '23', '0', '18.29');
INSERT INTO `checkout` VALUES ('63', '2017-07-26', '\0', '32804', null, '0', '24', null, '30', '0', '120.16');
INSERT INTO `checkout` VALUES ('64', '2017-07-26', '\0', '32806', null, '0', '21', null, '23', '0', '18.34');
INSERT INTO `checkout` VALUES ('65', '2017-07-27', '\0', '32806', null, '0', '24', null, '16', '0', '119.14');
INSERT INTO `checkout` VALUES ('66', '2017-07-27', '\0', '32807', null, '0', '19', null, '23', '0', '121.48');
INSERT INTO `checkout` VALUES ('67', '2017-07-27', '\0', '32807', null, '0', '40', null, '16', '0', '119.98');
INSERT INTO `checkout` VALUES ('68', '2017-07-27', '\0', '32808', null, '0', '25', null, '23', '0', '70.26');
INSERT INTO `checkout` VALUES ('69', '2017-07-27', '\0', '32808', null, '0', '17', null, '30', '0', '123.13');
INSERT INTO `checkout` VALUES ('70', '2017-07-27', '\0', '32832', null, '0', '49', null, '23', '0', '99.12');
INSERT INTO `checkout` VALUES ('71', '2017-07-27', '\0', '32832', null, '0', '6', null, '16', '0', '28.18');
INSERT INTO `checkout` VALUES ('72', '2017-07-27', '\0', '32833', null, '0', '38', null, '23', '0', '3.36');
INSERT INTO `checkout` VALUES ('73', '2017-07-27', '\0', '32833', null, '0', '46', null, '16', '0', '47.17');
INSERT INTO `checkout` VALUES ('74', '2017-07-27', '\0', '32834', null, '0', '11', null, '23', '0', '118.96');
INSERT INTO `checkout` VALUES ('75', '2017-07-27', '\0', '32834', null, '0', '7', null, '30', '0', '104.77');
INSERT INTO `checkout` VALUES ('76', '2017-07-27', '\0', '32835', null, '0', '40', null, '23', '0', '49.95');
INSERT INTO `checkout` VALUES ('77', '2017-07-27', '\0', '32835', null, '0', '32', null, '16', '0', '120.19');
INSERT INTO `checkout` VALUES ('78', '2017-07-27', '\0', '32846', null, '0', '49', null, '23', '0', '103.05');
INSERT INTO `checkout` VALUES ('79', '2017-07-27', '\0', '32846', null, '0', '15', null, '16', '0', '6.28');
INSERT INTO `checkout` VALUES ('80', '2017-07-27', '\0', '32847', null, '0', '15', null, '23', '0', '43.77');
INSERT INTO `checkout` VALUES ('81', '2017-07-27', '\0', '32847', null, '0', '44', null, '30', '0', '28.20');
INSERT INTO `checkout` VALUES ('82', '2017-07-27', '\0', '32855', null, '0', '28', null, '23', '0', '9.84');
INSERT INTO `checkout` VALUES ('83', '2017-07-27', '\0', '32855', null, '0', '37', null, '16', '0', '47.08');
INSERT INTO `checkout` VALUES ('84', '2017-07-27', '\0', '32856', null, '0', '37', null, '23', '0', '59.08');
INSERT INTO `checkout` VALUES ('85', '2017-07-27', '\0', '32856', null, '0', '10', null, '16', '0', '74.78');
INSERT INTO `checkout` VALUES ('86', '2017-07-27', '\0', '32858', null, '0', '20', null, '23', '0', '19.13');
INSERT INTO `checkout` VALUES ('87', '2017-07-27', '\0', '32858', null, '0', '29', null, '30', '0', '50.45');
INSERT INTO `checkout` VALUES ('88', '2017-07-27', '\0', '32858', null, '0', '15', null, '16', '0', '39.35');
INSERT INTO `checkout` VALUES ('89', '2017-03-27', '\0', '1', null, '0', '1', null, '16', '0', '78.00');
INSERT INTO `checkout` VALUES ('90', '2017-03-27', '\0', '32769', null, '0', '6', null, '16', '0', '424.01');
INSERT INTO `checkout` VALUES ('91', '2017-05-20', '\0', '32769', null, '0', '3', null, '23', '0', '327.00');
INSERT INTO `checkout` VALUES ('92', '2017-05-20', '\0', '32769', null, '0', '3', null, '30', '0', '327.00');
INSERT INTO `checkout` VALUES ('93', '2017-05-20', '\0', '1', null, '0', '1', null, '16', '0', '0.01');
INSERT INTO `checkout` VALUES ('94', '2017-04-20', '\0', '1', null, '0', '1', null, '16', '0', '0.01');
INSERT INTO `checkout` VALUES ('95', '2017-04-20', '\0', '1', null, '0', '11', null, '23', '0', '0.19');
INSERT INTO `checkout` VALUES ('96', '2017-04-20', '\0', '1', null, '0', '1', null, '30', '0', '0.01');
INSERT INTO `checkout` VALUES ('97', '2017-04-20', '\0', '32769', null, '0', '1', null, '23', '0', '0.01');
INSERT INTO `checkout` VALUES ('98', '2017-05-21', '\0', '1', null, '0', '23', null, '30', '0', '1231.00');
INSERT INTO `checkout` VALUES ('99', '2017-03-28', '\0', '32769', null, '0', '23', null, '23', '0', '12323.00');
INSERT INTO `checkout` VALUES ('346', null, '\0', '1', '2017-08-03 18:10:30', '0', '1', '2017-08-03 18:10:30', '23', '0', '1.00');
INSERT INTO `checkout` VALUES ('347', null, '\0', '1', '2017-08-03 18:10:41', '0', '1', '2017-08-03 18:10:41', '23', '0', '1.00');
INSERT INTO `checkout` VALUES ('348', null, '\0', '1', '2017-08-03 18:12:15', '0', '1', '2017-08-03 18:12:15', '23', '0', '1.00');

-- ----------------------------
-- Table structure for testjpa
-- ----------------------------
DROP TABLE IF EXISTS `testjpa`;
CREATE TABLE `testjpa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL DEFAULT b'0',
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of testjpa
-- ----------------------------
INSERT INTO `testjpa` VALUES ('1', '2017-08-03 10:18:46', '\0', 'JPA');
