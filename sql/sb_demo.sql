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
`job_name` varchar(64) DEFAULT NULL COMMENT '任务名',
`job_group` varchar(64) DEFAULT NULL COMMENT '任务分组',
`job_class` varchar(255) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
`cron_expression` varchar(64) DEFAULT NULL COMMENT 'cron表达式',
`job_status` tinyint(2) DEFAULT NULL COMMENT '任务状态 -1:删除 0:创建 1:运行 2:停止',
`description` varchar(255) DEFAULT NULL COMMENT '任务描述',
`gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
`is_deleted` tinyint(2) DEFAULT 0 COMMENT '逻辑删除 0:未删除 1:已删除',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='job任务';


CREATE TABLE `task_record` (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
`job_name` varchar(64) DEFAULT NULL COMMENT '任务名',
`job_group` varchar(64) DEFAULT NULL COMMENT '任务分组',
`job_class` varchar(255) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
`task_result` tinyint(2) DEFAULT NULL COMMENT '运行结果 1：成功 0：失败',
`task_message` varchar(255) DEFAULT NULL COMMENT '运行信息',
`gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
`is_deleted` tinyint(2) DEFAULT 0 COMMENT '逻辑删除 0:未删除 1:已删除',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='job任务运行记录';



SET FOREIGN_KEY_CHECKS = 1;
