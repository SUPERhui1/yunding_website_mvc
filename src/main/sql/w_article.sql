/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : db_yunding_website

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 04/08/2018 19:47:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for w_article
-- ----------------------------
DROP TABLE IF EXISTS `w_article`;
CREATE TABLE `w_article`  (
  `article_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `article_state` int(2) NOT NULL COMMENT '状态：0：删除；1：通过',
  `article_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '正文URL',
  `article_image` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配图URL',
  `article_category_id` int(2) NULL DEFAULT NULL COMMENT '类别：0：普通；1：教程；2：观点；3：资讯；4：文章',
  `article_introduce` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `article_praise` int(11) NOT NULL COMMENT '点赞数',
  `article_views` int(11) NOT NULL COMMENT '浏览量',
  `article_sender` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布者',
  `article_created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `article_updated_at` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of w_article
-- ----------------------------
INSERT INTO `w_article` VALUES (7, 1, '第一次测试\r\n“90-=[]\'/]\'/[;/.]\'/\'/5205204\\&*()__)(*&!@*()_!@#_)(!@*#*(', '/static/upload/article/2286fdf681654b4cb5e3f42ee486f50b.jpg', 1, '第一次测试1', 0, 0, '刘子豪', '2018-08-04 11:58:54', '2018-08-04 11:58:54');
INSERT INTO `w_article` VALUES (8, 1, '第一次测试\r\n“90-=[]\'/]\'/[;/.]\'/\'/5205204\\&*()__)(*&!@*()_!@#_)(!@*#*(', '/static/upload/article/eaec8e6ade1a4e93ba1e299e52b11f24.jpg', 1, '第一次测试2', 0, 0, '刘子豪', '2018-08-04 11:58:59', '2018-08-04 11:58:59');
INSERT INTO `w_article` VALUES (9, 1, '第一次测试\r\n“90-=[]\'/]\'/[;/.]\'/\'/5205204\\&*()__)(*&!@*()_!@#_)(!@*#*(', '/static/upload/article/c9c171e70af04e51802419bf4a196c82.jpg', 1, '第一次测试3', 0, 0, '刘子豪', '2018-08-04 11:58:59', '2018-08-04 11:58:59');
INSERT INTO `w_article` VALUES (10, 1, '第一次测试\r\n“90-=[]\'/]\'/[;/.]\'/\'/5205204\\&*()__)(*&!@*()_!@#_)(!@*#*(', '/static/upload/article/933d8e2cdcd343bfa1a1e0b6877758a9.jpg', 1, '第一次测试4', 0, 0, '刘子豪', '2018-08-04 11:59:00', '2018-08-04 11:59:00');
INSERT INTO `w_article` VALUES (11, 1, '第一次测试\r\n“90-=[]\'/]\'/[;/.]\'/\'/5205204\\&*()__)(*&!@*()_!@#_)(!@*#*(', '/static/upload/article/da3e2427462d4e55a8b5c85c06bc4a48.jpg', 1, '第一次测试5', 0, 0, '刘子豪', '2018-08-04 11:59:00', '2018-08-04 11:59:00');
INSERT INTO `w_article` VALUES (12, 1, '第一次测试\r\n“90-=[]\'/]\'/[;/.]\'/\'/5205204\\&*()__)(*&!@*()_!@#_)(!@*#*(', '/static/upload/article/cff2f98940b74b859198ebbbeec34c25.jpg', 1, '第一次测试6', 0, 0, '刘子豪', '2018-08-04 11:59:00', '2018-08-04 11:59:00');
INSERT INTO `w_article` VALUES (13, 0, '第一次测试\r\n“90-=[]\'/]\'/[;/.]\'/\'/5205204\\&*()__)(*&!@*()_!@#_)(!@*#*(', '/static/upload/article/65a320561d1b420ea0ea65154507a309.jpg', 1, '第一次测试1', 0, 0, '刘子豪', '2018-08-04 11:59:01', '2018-08-04 11:59:01');
INSERT INTO `w_article` VALUES (14, 1, '第二次测试的正文', '/static/upload/article/70dce2e5feb54a05b346e83dafd764bb.jpg', 1, '第好几次次测试的描述', 0, 0, '赵泽辉', '2018-08-04 17:08:41', '2018-08-04 17:08:41');
INSERT INTO `w_article` VALUES (15, 1, '第二次测试的正文', '/static/upload/article/cdb79c6bafd049fc954e57469ef32c61.jpg', 2, '第好几次次测试的描述', 0, 0, '赵泽辉', '2018-08-04 17:26:36', '2018-08-04 17:26:36');
INSERT INTO `w_article` VALUES (16, 1, '第二次测试的正文', '/static/upload/article/742916e798c340b3a3fa0ca24846f988.jpg', 2, '第好几次次测试的描述', 0, 0, '赵泽辉', '2018-08-04 17:26:37', '2018-08-04 17:26:37');
INSERT INTO `w_article` VALUES (17, 1, '第二次测试的正文', '/static/upload/article/2d7eaf84459a49a49e1e0f9e7a4cec4c.jpg', 2, '第好几次次测试的描述', 0, 0, '赵泽辉', '2018-08-04 17:27:41', '2018-08-04 17:27:41');
INSERT INTO `w_article` VALUES (18, 1, '第二次测试的正文', '/static/upload/article/e8e0cd5c7915426584a2c1758f399036.jpg', 2, '第好几次次测试的描述', 0, 0, '赵泽辉', '2018-08-04 17:28:06', '2018-08-04 17:28:06');
INSERT INTO `w_article` VALUES (19, 1, '第二次测试的正文', '/static/upload/article/062ec44372bd43bda03e52417cbc1081.jpg', 2, '第好几次次测试的描述', 0, 0, '赵泽辉', '2018-08-04 17:28:10', '2018-08-04 17:28:10');
INSERT INTO `w_article` VALUES (20, 1, '第二次测试的正文', '/static/upload/article/8f88265d6356438eac06bb5557f98803.jpg', 4, '第好几次次测试的描述', 0, 0, '赵泽辉', '2018-08-04 17:28:15', '2018-08-04 17:28:15');
INSERT INTO `w_article` VALUES (21, 1, '第二次测试的正文', '/static/upload/article/55c71750f7de4f37af8a5d825ca281a8.jpg', 4, '第好几次次测试的描述', 0, 0, '赵泽辉', '2018-08-04 17:28:19', '2018-08-04 17:28:19');
INSERT INTO `w_article` VALUES (22, 1, '第二次测试的正文', '/static/upload/article/56d63b50a56c482aa3a17cbb99faca8a.jpg', 4, '第好几次次测试的描述', 0, 0, '赵泽辉', '2018-08-04 17:28:20', '2018-08-04 17:28:20');
INSERT INTO `w_article` VALUES (23, 1, '第二次测试的正文', '/static/upload/article/33449587b65240bebd5f6e1695904d62.jpg', 4, '第好几次次测试的描述', 0, 0, '赵泽辉', '2018-08-04 17:28:29', '2018-08-04 17:28:29');
INSERT INTO `w_article` VALUES (24, 1, '第二次测试的正文', '/static/upload/article/412174e9df7c4842877eb54deee6c079.jpg', 3, '第好几次次测试的描述', 0, 0, '赵泽辉', '2018-08-04 17:28:38', '2018-08-04 17:28:38');
INSERT INTO `w_article` VALUES (25, 1, '第二次测试的正文', '/static/upload/article/deb0ba605ba54f2aa93b05b5f4c2ce6b.jpg', 3, '第好几次次测试的描述', 0, 0, '赵泽辉', '2018-08-04 17:28:41', '2018-08-04 17:28:41');
INSERT INTO `w_article` VALUES (26, 1, '第二次测试的正文', '/static/upload/article/9e158775557a415198850d8178e7dcbe.jpg', 3, '第好几次次测试的描述', 0, 0, '赵泽辉', '2018-08-04 17:28:44', '2018-08-04 17:28:44');
INSERT INTO `w_article` VALUES (27, 1, '第二次测试的正文', '/static/upload/article/4d79cfcfa80d4bdb924c2b1ad2e01dac.jpg', 3, '第好几次次测试的描述', 0, 0, '赵泽辉', '2018-08-04 17:28:46', '2018-08-04 17:28:46');
INSERT INTO `w_article` VALUES (28, 1, '第二次测试的正文', '/static/upload/article/fa49169beaaf445792551406c3bf8f04.jpg', 0, '第好几次次测试的描述', 0, 0, '赵泽辉', '2018-08-04 17:32:19', '2018-08-04 17:32:19');
INSERT INTO `w_article` VALUES (29, 1, '第二次测试的正文', '../static/upload/article/e9c7cc0772594cf78ae240c80cfddd51.jpg', 0, '第好几次次测试的描述', 0, 0, '赵泽辉', '2018-08-04 19:41:56', '2018-08-04 19:41:56');
INSERT INTO `w_article` VALUES (30, 1, '第二次测试的正文', '../static/upload/article/2c8377ae2f784db3b6ab9e9b6eb8187d.jpg', 0, '第好几次次测试的描述', 0, 0, '赵泽辉', '2018-08-04 19:43:01', '2018-08-04 19:43:01');

SET FOREIGN_KEY_CHECKS = 1;
