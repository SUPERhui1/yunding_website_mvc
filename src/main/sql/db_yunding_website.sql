/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : db_yunding_website

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 31/07/2018 19:27:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for w_article
-- ----------------------------
DROP TABLE IF EXISTS `w_article`;
CREATE TABLE `w_article`  (
  `article_id` int(11) NOT NULL COMMENT '文章ID',
  `article_state` int(2) NOT NULL COMMENT '状态：0：删除；1：通过',
  `article_content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '正文URL',
  `article_image` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配图URL',
  `article_category_id` int(2) NULL DEFAULT NULL COMMENT '类别：0：普通；1：教程；2：观点；3：资讯；4：文章',
  `article_introduce` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `article_praise` int(11) NOT NULL COMMENT '点赞数',
  `article_views` int(11) NOT NULL COMMENT '浏览量',
  `article_sender` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布者',
  `article_created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `article_updated_at` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_manager
-- ----------------------------
DROP TABLE IF EXISTS `w_manager`;
CREATE TABLE `w_manager`  (
  `manager_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `manager_permission` int(3) UNSIGNED NOT NULL COMMENT '管理员权限，1：新闻/作品；2.：新闻/作品/成员',
  `manager_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `manager_password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `manager_created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `manager_updated_at` datetime(0) NOT NULL COMMENT '更新日期',
  PRIMARY KEY (`manager_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_member
-- ----------------------------
DROP TABLE IF EXISTS `w_member`;
CREATE TABLE `w_member`  (
  `member_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `member_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `member_photo` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '照相',
  `member_grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业年级',
  `member_position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份',
  `member_achievement` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代表作',
  `member_joined_at` datetime(0) NULL DEFAULT NULL COMMENT '加入时间',
  `member_created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `member_updated_at` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`member_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_message
-- ----------------------------
DROP TABLE IF EXISTS `w_message`;
CREATE TABLE `w_message`  (
  `message_id` int(11) NOT NULL COMMENT 'ID',
  `message_content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `message_place` int(2) NOT NULL COMMENT '位置：0：删除；1：位置1；2：位置2；3：位置3',
  `message_sender` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布者',
  `message_created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `message_updated_at` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_news
-- ----------------------------
DROP TABLE IF EXISTS `w_news`;
CREATE TABLE `w_news`  (
  `news_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT ' id',
  `news_state` int(2) NOT NULL DEFAULT 1 COMMENT '状态：0：删除；1：通过；',
  `news_title` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `news_content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '新闻内容url',
  `news_image` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '展示配图',
  `news_place` int(2) UNSIGNED NULL DEFAULT 0 COMMENT '位置：0：删除；1：第一张；2：第二张；3：第三张；4：第四张',
  `news_introduce` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `news_sender` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布者',
  `news_created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `news_updated_at` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`news_id`) USING BTREE,
  INDEX `news_categoryId_index`(`news_place`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for w_work
-- ----------------------------
DROP TABLE IF EXISTS `w_work`;
CREATE TABLE `w_work`  (
  `work_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `work_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `work_content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作品内容url',
  `work_introduce` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '介绍',
  `work_url` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网址',
  `work_image` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '展示配图',
  `work_sender` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布者',
  `work_created_at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `work_updated_at` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`work_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
