/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : db_yunding_website

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-08-09 08:15:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for w_work
-- ----------------------------
DROP TABLE IF EXISTS `w_work`;
CREATE TABLE `w_work` (
  `work_id` int(11) NOT NULL AUTO_INCREMENT,
  `work_name` varchar(255) NOT NULL,
  `work_content` varchar(1024) NOT NULL,
  `work_introduce` varchar(1024) NOT NULL,
  `work_url` varchar(1024) DEFAULT NULL,
  `work_image` varchar(1024) NOT NULL,
  `work_sender` varchar(50) NOT NULL,
  `work_created_at` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `work_updated_at` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `permission` varchar(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`work_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of w_work
-- ----------------------------
INSERT INTO `w_work` VALUES ('10', '作品一号', '这是内容', '这是描述', null, 'http://127.0.0.1:8080/static/upload/works/86916c13ef9040568846aa6f83a34a8c.jpg', '这是发布者', '2018-08-06 15:30:12', '2018-08-06 15:30:12', '1');
INSERT INTO `w_work` VALUES ('11', '作品二号', '这是内容', '这是描述', null, 'http://127.0.0.1:8080/static/upload/works/dcbdcbbdf70c4520af3846e10981649e.jpg', '这是发布者', '2018-08-06 15:30:17', '2018-08-06 15:30:17', '1');
INSERT INTO `w_work` VALUES ('12', '作品三号', '这是内容', '这是描述', null, 'http://127.0.0.1:8080/static/upload/works/12db9c3781dc40849d10d41e26927986.jpg', '这是发布者', '2018-08-06 15:30:24', '2018-08-06 15:30:24', '1');
INSERT INTO `w_work` VALUES ('13', '作品四号', '这是内容', '这是描述', null, 'http://127.0.0.1:8080/static/upload/works/97447377dbbd42f48e8e001122f99e18.jpg', '这是发布者', '2018-08-06 15:30:29', '2018-08-06 15:30:29', '1');
INSERT INTO `w_work` VALUES ('14', '作品五号', '这是内容', '这是描述', null, 'http://127.0.0.1:8080/static/upload/works/2bf80b7091ef4d0f81ff8fb1625a2afe.jpg', '这是发布者', '2018-08-06 15:30:38', '2018-08-06 15:30:38', '1');
