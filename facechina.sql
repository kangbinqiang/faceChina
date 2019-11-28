/*
 Navicat Premium Data Transfer

 Source Server         : faceChina
 Source Server Type    : MySQL
 Source Server Version : 50646
 Source Host           : 49.235.141.168:3306
 Source Schema         : facechina

 Target Server Type    : MySQL
 Target Server Version : 50646
 File Encoding         : 65001

 Date: 28/11/2019 16:48:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for F_ALBUM
-- ----------------------------
DROP TABLE IF EXISTS `F_ALBUM`;
CREATE TABLE `F_ALBUM`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ALBUM_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ALBUM_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ALBUM_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ALBUM_CREATETIME` date DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for F_BOARD
-- ----------------------------
DROP TABLE IF EXISTS `F_BOARD`;
CREATE TABLE `F_BOARD`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BOARD_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BOARD_TEXT` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `BOARD_CREATEUSER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BOARD_USERID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `BOARD_CREATETIME` date DEFAULT NULL,
  `BOARD_STATE` int(11) DEFAULT NULL COMMENT '0为未读状态，1为已读状态',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for F_COMMENT
-- ----------------------------
DROP TABLE IF EXISTS `F_COMMENT`;
CREATE TABLE `F_COMMENT`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMMENT_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `COMMENT_MESSAGE_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `COMMENT_TEXT` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `COMMENT_CREATETIME` date DEFAULT NULL,
  `COMMENT_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for F_LOGIN_LOG
-- ----------------------------
DROP TABLE IF EXISTS `F_LOGIN_LOG`;
CREATE TABLE `F_LOGIN_LOG`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOGIN_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `LOGIN_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `LOGIN_OPERCATION` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `LOGIN_CREATETIME` date DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for F_MESSAGE
-- ----------------------------
DROP TABLE IF EXISTS `F_MESSAGE`;
CREATE TABLE `F_MESSAGE`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MESSAGE_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MESSAGE_TEXT` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `MESSAGE_CREATETIME` date DEFAULT NULL,
  `MESSAGE_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MESSAGE_PHOTO_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for F_OPERATION
-- ----------------------------
DROP TABLE IF EXISTS `F_OPERATION`;
CREATE TABLE `F_OPERATION`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OPERATION_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `OPERATION_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `OPERATION_ACT` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `OPERATION_CREATETIME` date DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for F_PERMISSION
-- ----------------------------
DROP TABLE IF EXISTS `F_PERMISSION`;
CREATE TABLE `F_PERMISSION`  (
  `ID` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `PERMISSION_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限ID',
  `PERMISSION_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限名称',
  `PERMISSION_DESCRIPTION` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限描述',
  `CREATE_DATE` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of F_PERMISSION
-- ----------------------------
INSERT INTO `F_PERMISSION` VALUES (1, 'add', 'add', '新增', '2019-11-28');

-- ----------------------------
-- Table structure for F_PHOTO
-- ----------------------------
DROP TABLE IF EXISTS `F_PHOTO`;
CREATE TABLE `F_PHOTO`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PHOTO_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PHOTO_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PHOTO_URL` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PHOTO_CREATETIME` date DEFAULT NULL,
  `PHOTO_ALBUM_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for F_PRAISE
-- ----------------------------
DROP TABLE IF EXISTS `F_PRAISE`;
CREATE TABLE `F_PRAISE`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRAISE_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PRAISE_MESSAGE_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PRAISE_CREATETIME` date DEFAULT NULL,
  `PRAISE_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for F_RELATIONSHIP
-- ----------------------------
DROP TABLE IF EXISTS `F_RELATIONSHIP`;
CREATE TABLE `F_RELATIONSHIP`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RELATIONSHIP_USERID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `RELATIONSHIP_FRIENDID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `RELATIONSHIP_STATE` int(11) DEFAULT NULL COMMENT '0为未同意，1为已同意，2为已拒绝',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for F_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `F_ROLE`;
CREATE TABLE `F_ROLE`  (
  `ID` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ROLE_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `ROLE_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `ROLE_DESCRIPTION` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  `ROLE_CREATETIME` date DEFAULT NULL COMMENT '创建时间',
  `PERMISSION_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of F_ROLE
-- ----------------------------
INSERT INTO `F_ROLE` VALUES (1, 'admin', 'admin', '所有权限', '2019-11-28', 'add');

-- ----------------------------
-- Table structure for F_USER
-- ----------------------------
DROP TABLE IF EXISTS `F_USER`;
CREATE TABLE `F_USER`  (
  `ID` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USER_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户id',
  `USER_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户姓名',
  `USER_LOGIN_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户登录名',
  `USER_AGE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '年龄',
  `USER_EMAIL` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'email',
  `USER_PASSWORD` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `USER_SALT` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '盐值',
  `USER_STATE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户状态：0为禁用，1为未禁用',
  `USER_CREATETIME` date DEFAULT NULL COMMENT '创建时间',
  `USER_LASTTIME` date DEFAULT NULL COMMENT '最后登录时间',
  `USER_LOGIN_COUNT` int(20) DEFAULT NULL COMMENT '用户登录次数',
  `USER_ROLE_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `UNQ`(`USER_ID`, `USER_EMAIL`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of F_USER
-- ----------------------------
INSERT INTO `F_USER` VALUES (1, 'bfdeee2a28704928ab041fc720076197', 'tom', 'logintom', NULL, NULL, '8d1351fbc61415615665b05221a431b2', 'EBGhi30UZHfugIYVj98E8Q==', NULL, NULL, NULL, NULL, 'admin');

SET FOREIGN_KEY_CHECKS = 1;
