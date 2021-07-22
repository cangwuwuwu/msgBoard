/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3307
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3307
 Source Schema         : db_01

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 07/05/2020 17:48:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_access
-- ----------------------------
DROP TABLE IF EXISTS `tb_access`;
CREATE TABLE `tb_access`  (
  `accesstimes` int(10) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_access
-- ----------------------------
INSERT INTO `tb_access` VALUES (111);

-- ----------------------------
-- Table structure for tb_msgboard
-- ----------------------------
DROP TABLE IF EXISTS `tb_msgboard`;
CREATE TABLE `tb_msgboard`  (
  `mname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mmsg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `mtime` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` int(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_msgboard
-- ----------------------------
INSERT INTO `tb_msgboard` VALUES ('翟大佬', '我第一', '2020-04-28 22:09:21', 1, 1);
INSERT INTO `tb_msgboard` VALUES ('cangwuwuwu', '第二', '2020-04-28 22:12:50', 2, 1);
INSERT INTO `tb_msgboard` VALUES ('小翟', '真牛*', '2020-04-28 22:54:18', 3, 1);
INSERT INTO `tb_msgboard` VALUES ('小翟', '优秀', '2020-04-28 22:55:00', 4, 1);
INSERT INTO `tb_msgboard` VALUES ('root', '大家好', '2020-04-29 16:16:57', 5, 1);
INSERT INTO `tb_msgboard` VALUES ('cangwu', '???', '2020-04-30 16:52:48', 6, 1);
INSERT INTO `tb_msgboard` VALUES ('xyc', 'xyc到此一游', '2020-04-30 15:17:38', 7, 1);
INSERT INTO `tb_msgboard` VALUES ('test', '测试', '2020-05-06 13:50:38', 8, 0);
INSERT INTO `tb_msgboard` VALUES ('张三', '发表一条留言', '2020-05-06 21:17:11', 9, 1);
INSERT INTO `tb_msgboard` VALUES ('forigner', 'something news', '2020-05-07 10:50:16', 10, 1);
INSERT INTO `tb_msgboard` VALUES ('test1', 'test', '2020-05-07 10:54:00', 11, 0);
INSERT INTO `tb_msgboard` VALUES ('134234', 'test', '2020-05-07 10:59:12', 12, 0);
INSERT INTO `tb_msgboard` VALUES ('te', 'fasdfad', '2020-05-07 10:59:29', 13, 0);
INSERT INTO `tb_msgboard` VALUES ('adfad', 'safd', '2020-05-07 11:01:40', 14, 0);
INSERT INTO `tb_msgboard` VALUES ('adfad', 'safd', '2020-05-07 11:01:40', 15, 0);
INSERT INTO `tb_msgboard` VALUES ('adfad', 'safd', '2020-05-07 11:01:46', 16, 0);
INSERT INTO `tb_msgboard` VALUES ('asdfad', 'asdfdfasdf', '2020-05-07 11:04:13', 17, 0);
INSERT INTO `tb_msgboard` VALUES ('俺是个十分', '阿迪斯发士大夫', '2020-05-07 11:07:08', 18, 0);
INSERT INTO `tb_msgboard` VALUES ('暗示的', '啊多发点是', '2020-05-07 11:31:07', 19, 0);
INSERT INTO `tb_msgboard` VALUES ('fasdfasdf', '我已经登录了', '2020-05-07 12:01:39', 20, 1);
INSERT INTO `tb_msgboard` VALUES ('asdfasdfad', 'asdf', '2020-05-07 12:12:56', 21, 0);
INSERT INTO `tb_msgboard` VALUES ('cangwu', '我是苍梧', '2020-05-07 14:50:16', 22, 1);
INSERT INTO `tb_msgboard` VALUES ('cangwu', '1334', '2020-05-07 15:30:20', 23, 0);
INSERT INTO `tb_msgboard` VALUES ('cangwu', '使用netty+Vue在写一个聊天室的例子的时候, 发现vue 好像没有类似jquery的append实现有先后顺序的插入dom节点的*作。于是查阅资料，发现可以使用v-for读一个数组，', '2020-05-07 15:55:08', 24, 1);
INSERT INTO `tb_msgboard` VALUES ('cangwu', '1314', '2020-05-07 15:57:36', 25, 0);
INSERT INTO `tb_msgboard` VALUES ('cangwu', '萨芬', '2020-05-07 15:58:19', 26, 0);

-- ----------------------------
-- Table structure for tb_web
-- ----------------------------
DROP TABLE IF EXISTS `tb_web`;
CREATE TABLE `tb_web`  (
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phonenum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `natives` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `headimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'myhead.jpg',
  `auth` int(1) NOT NULL DEFAULT 0 COMMENT '权限',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `number`(`number`) USING BTREE,
  UNIQUE INDEX `number_2`(`number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_web
-- ----------------------------
INSERT INTO `tb_web` VALUES ('631009339@qq.com', '951753', '2017101919', '王鑫伟', '秘密', '17679260468', '01/05/1999', NULL, NULL, 'myhead.jpg', 0, 1);
INSERT INTO `tb_web` VALUES ('444@qq.com', '123', '2017101926', 'zcr', '男', '131', '04/09/2019', NULL, NULL, 'myhead.jpg', 0, 2);
INSERT INTO `tb_web` VALUES ('2450833252@qq.com', '123456', '2017101927', '李大钊', '男', '', '', NULL, NULL, 'myhead.jpg', 0, 3);
INSERT INTO `tb_web` VALUES ('294352178@qq.com', 'xu1234', '2017101943', 'cangwu', '男', '17606701258', '07/08/1999', '浙江衢州', 'Hi~~', '2017101943.jpg', 1, 4);

SET FOREIGN_KEY_CHECKS = 1;
