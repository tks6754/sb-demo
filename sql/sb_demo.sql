/*
 Navicat Premium Data Transfer

 Source Server         : locat-root
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : sb_demo

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 08/11/2019 15:04:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nickname` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '昵称',
  `age` tinyint(4) NOT NULL COMMENT '年龄',
  `head_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;


CREATE TABLE `job_task` (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
`job_name` varchar(255) DEFAULT NULL COMMENT '任务名',
`description` varchar(255) DEFAULT NULL COMMENT '任务描述',
`cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
`job_class` varchar(255) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
`job_status` varchar(255) DEFAULT NULL COMMENT '任务状态',
`job_group` varchar(255) DEFAULT NULL COMMENT '任务分组',
`create_time` datetime DEFAULT NULL COMMENT '创建时间',
`update_time` datetime DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='job任务';




SET FOREIGN_KEY_CHECKS = 1;
