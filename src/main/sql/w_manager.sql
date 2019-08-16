/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : db_yunding_website

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-08-09 08:14:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for w_manager
-- ----------------------------
DROP TABLE IF EXISTS `w_manager`;
CREATE TABLE `w_manager` (
  `manager_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `manager_permission` int(3) unsigned NOT NULL COMMENT '管理员权限，1：新闻/作品；2.：新闻/作品/成员',
  `manager_name` varchar(50) NOT NULL COMMENT '用户名',
  `manager_password` varchar(100) NOT NULL COMMENT '密码',
  `manager_created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `manager_updated_at` datetime NOT NULL COMMENT '更新日期',
  PRIMARY KEY (`manager_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of w_manager
-- ----------------------------
INSERT INTO `w_manager` VALUES ('1', '1', '管理员1', '123', '2018-08-06 10:20:15', '2018-08-06 10:25:41');
INSERT INTO `w_manager` VALUES ('2', '2', '管理员2', '123', '2018-08-06 14:46:36', '2018-08-06 14:46:34');
