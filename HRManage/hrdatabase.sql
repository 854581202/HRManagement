/*
Navicat MySQL Data Transfer

Source Server         : firstConnection
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : hrdatabase

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-07-17 09:38:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `assesschange`
-- ----------------------------
DROP TABLE IF EXISTS `assesschange`;
CREATE TABLE `assesschange` (
  `operatorId` int(11) NOT NULL AUTO_INCREMENT,
  `pId` int(11) NOT NULL,
  `pName` varchar(255) NOT NULL,
  `oldAssess` varchar(255) NOT NULL,
  `newAssess` varchar(255) NOT NULL,
  `modifyTime` int(11) NOT NULL,
  `modifyDate` varchar(255) NOT NULL,
  PRIMARY KEY (`operatorId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assesschange
-- ----------------------------
INSERT INTO `assesschange` VALUES ('1', '2', '张三', '未考核', '优秀', '1', '2018-07-17 09:35:04');

-- ----------------------------
-- Table structure for `depttable`
-- ----------------------------
DROP TABLE IF EXISTS `depttable`;
CREATE TABLE `depttable` (
  `deptId` int(11) NOT NULL,
  `FatherDept` varchar(255) NOT NULL,
  `SonDept` varchar(255) NOT NULL,
  PRIMARY KEY (`deptId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of depttable
-- ----------------------------
INSERT INTO `depttable` VALUES ('1', '办公室', '综合科');
INSERT INTO `depttable` VALUES ('2', '办公室', '文秘科');
INSERT INTO `depttable` VALUES ('3', '办公室', '机要科');
INSERT INTO `depttable` VALUES ('4', '人事处', '人事科');

-- ----------------------------
-- Table structure for `jobchange`
-- ----------------------------
DROP TABLE IF EXISTS `jobchange`;
CREATE TABLE `jobchange` (
  `operatorId` int(11) NOT NULL AUTO_INCREMENT,
  `pId` int(11) NOT NULL,
  `pName` varchar(255) NOT NULL,
  `oldDept` varchar(255) NOT NULL,
  `newDept` varchar(255) NOT NULL,
  `modifyTime` int(11) NOT NULL,
  `modifyDate` varchar(255) NOT NULL,
  PRIMARY KEY (`operatorId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jobchange
-- ----------------------------
INSERT INTO `jobchange` VALUES ('1', '3', '李四', '4-人事处-人事科', '1-办公室-综合科', '1', '2018-07-17 09:35:21');

-- ----------------------------
-- Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `pId` int(11) NOT NULL,
  `pName` varchar(255) NOT NULL,
  `pSex` varchar(255) NOT NULL,
  `pBirth` varchar(255) NOT NULL,
  `pNation` varchar(255) NOT NULL,
  `pAddress` varchar(255) NOT NULL,
  `DeptId` int(11) NOT NULL,
  `pSalary` int(11) NOT NULL,
  `pAccess` varchar(255) NOT NULL,
  `pOther` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', '温姑娘', '女', '1996-10', '汉', 'XX省XX市', '2', '5000', '优秀', '可爱的姑娘');
INSERT INTO `person` VALUES ('2', '张三', '男', '1994-07', '满', '北京', '0', '3000', '优秀', '不错的小伙子');
INSERT INTO `person` VALUES ('3', '李四', '男', '1996-04', '汉', '浙江杭州', '1', '3000', '合格', '');

-- ----------------------------
-- Table structure for `salarychange`
-- ----------------------------
DROP TABLE IF EXISTS `salarychange`;
CREATE TABLE `salarychange` (
  `operatorId` int(11) NOT NULL AUTO_INCREMENT,
  `pId` int(11) NOT NULL,
  `pName` varchar(255) NOT NULL,
  `oldSalary` int(11) NOT NULL,
  `newSalary` int(11) NOT NULL,
  `modifyTime` int(11) NOT NULL,
  `modifyDate` varchar(255) NOT NULL,
  PRIMARY KEY (`operatorId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of salarychange
-- ----------------------------
INSERT INTO `salarychange` VALUES ('1', '3', '李四', '0', '5000', '1', '2018-07-17 09:34:32');
INSERT INTO `salarychange` VALUES ('2', '3', '李四', '5000', '3000', '2', '2018-07-17 09:34:45');

-- ----------------------------
-- Table structure for `unuseddeptid`
-- ----------------------------
DROP TABLE IF EXISTS `unuseddeptid`;
CREATE TABLE `unuseddeptid` (
  `deptId` int(11) NOT NULL,
  PRIMARY KEY (`deptId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of unuseddeptid
-- ----------------------------

-- ----------------------------
-- Table structure for `unusedpersonid`
-- ----------------------------
DROP TABLE IF EXISTS `unusedpersonid`;
CREATE TABLE `unusedpersonid` (
  `pId` int(11) NOT NULL,
  PRIMARY KEY (`pId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of unusedpersonid
-- ----------------------------
