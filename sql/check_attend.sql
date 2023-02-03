/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : check_attend

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 22/05/2022 19:55:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attend_time
-- ----------------------------
DROP TABLE IF EXISTS `attend_time`;
CREATE TABLE `attend_time`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '考勤时段表id',
  `attend_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考勤时段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of attend_time
-- ----------------------------
INSERT INTO `attend_time` VALUES ('0', '上午上班');
INSERT INTO `attend_time` VALUES ('1', '上午下班');
INSERT INTO `attend_time` VALUES ('2', '下午上班');
INSERT INTO `attend_time` VALUES ('3', '下午下班');

-- ----------------------------
-- Table structure for business_trip_record
-- ----------------------------
DROP TABLE IF EXISTS `business_trip_record`;
CREATE TABLE `business_trip_record`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '出差表id',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '出差时间',
  `duration` int NULL DEFAULT NULL COMMENT '出差天数',
  `companion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '同行人员',
  `destination` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目的地',
  `trip_mode_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出行方式',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出差事由',
  `employe_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工id',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `auditor_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of business_trip_record
-- ----------------------------
INSERT INTO `business_trip_record` VALUES ('976992606774886403', '2022-05-19 13:55:00', 365, 'tzy, xy, dcy, yj, zlc, wzh', '南通大学', '2', '出去玩', '976853692433039361', '1', '976853692433039361');

-- ----------------------------
-- Table structure for check_category
-- ----------------------------
DROP TABLE IF EXISTS `check_category`;
CREATE TABLE `check_category`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '考勤类型id',
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考勤类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of check_category
-- ----------------------------
INSERT INTO `check_category` VALUES ('0', '迟到');
INSERT INTO `check_category` VALUES ('1', '早退');
INSERT INTO `check_category` VALUES ('2', '漏签卡');
INSERT INTO `check_category` VALUES ('3', '早会未到');
INSERT INTO `check_category` VALUES ('4', '早操未到');
INSERT INTO `check_category` VALUES ('5', '会议缺席');

-- ----------------------------
-- Table structure for check_record
-- ----------------------------
DROP TABLE IF EXISTS `check_record`;
CREATE TABLE `check_record`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '考勤表id',
  `check_category_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考勤状态',
  `check_time` timestamp NULL DEFAULT NULL COMMENT '登记时间',
  `employe_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工id',
  `launch_time_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考勤时段',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '考勤说明',
  `recorder_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '记录人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of check_record
-- ----------------------------
INSERT INTO `check_record` VALUES ('977596271516712964', '0', '2022-05-21 15:38:30', '976991807936135171', '1', 'sd', '976853692433039361');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门id',
  `department` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('0', '未分配部门');

-- ----------------------------
-- Table structure for employe
-- ----------------------------
DROP TABLE IF EXISTS `employe`;
CREATE TABLE `employe`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工表编号',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工姓名',
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工性别',
  `age` int NULL DEFAULT NULL COMMENT '员工年龄',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工邮箱',
  `education` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工学历',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工联系方式',
  `duty` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职务',
  `department_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of employe
-- ----------------------------
INSERT INTO `employe` VALUES ('976853692433039361', '0001', 'lincheon', '男', 20, 'lincheon@outlook.com', '本科', '15951318373', '管理员', '0');
INSERT INTO `employe` VALUES ('976991807936135171', '0002', 'tzy', '男', 20, 'lincheon@outlook.com', '本科', '15951318373', '管理员', '0');
INSERT INTO `employe` VALUES ('976991875246325761', '0003', 'dcy', '男', 20, 'lincheon@outlook.com', '本科', '15951318373', '管理员', '0');
INSERT INTO `employe` VALUES ('976991950215315456', '0004', 'wzh', '男', 20, 'lincheon@outlook.com', '本科', '15951318373', '管理员', '0');
INSERT INTO `employe` VALUES ('976991988609974274', '0005', 'xy', '男', 20, 'lincheon@outlook.com', '本科', '15951318373', '管理员', '0');
INSERT INTO `employe` VALUES ('976992018754437121', '0006', 'yj', '男', 20, 'lincheon@outlook.com', '本科', '15951318373', '管理员', '0');
INSERT INTO `employe` VALUES ('976992046965325826', '0007', 'zlc', '男', 20, 'lincheon@outlook.com', '本科', '15951318373', '管理员', '0');

-- ----------------------------
-- Table structure for overtime_record
-- ----------------------------
DROP TABLE IF EXISTS `overtime_record`;
CREATE TABLE `overtime_record`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '加班表id',
  `gmt_create` timestamp NULL DEFAULT NULL COMMENT '申请时间',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '加班时间',
  `duration` float NULL DEFAULT NULL COMMENT '加班时长',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '加班原因',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `auditor_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核人id',
  `employe_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发起人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of overtime_record
-- ----------------------------
INSERT INTO `overtime_record` VALUES ('976998980929978372', '2022-05-20 00:05:09', '2022-05-20 00:03:00', 0.1, '我爱加班', '1', '976853692433039361', '976853692433039361');
INSERT INTO `overtime_record` VALUES ('977195624111800321', '2022-05-20 13:06:32', '2022-05-21 10:00:00', 0.1, '没人做饭', '1', '976853692433039361', '976853692433039361');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '英文名称，与vue中route的name一致',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '中文名称，显示到权限配置界面中的名称，可以与vue中的title一致',
  `level` tinyint(1) NULL DEFAULT NULL COMMENT '功能的级别',
  `sort` tinyint(1) NULL DEFAULT NULL COMMENT '排序，同一父级别下的排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('0', 'test:list', '测试列表', NULL, 1);
INSERT INTO `permission` VALUES ('1', 'test:create', '测试新增', NULL, 2);
INSERT INTO `permission` VALUES ('2', 'test:selectList', '测试下拉列表', NULL, 3);
INSERT INTO `permission` VALUES ('3', 'test:delete', '测试删除', NULL, 4);
INSERT INTO `permission` VALUES ('4', 'test:update', '测试更新', NULL, 5);

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` bigint NOT NULL COMMENT '主键',
  `user_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `module` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模块',
  `operation_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作结果',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (949430016511447042, 'admin', '127.0.0.1', '添加', '添加工作人员', '工作人员模块', '2022-03-04 22:15:55', '添加成功');

-- ----------------------------
-- Table structure for rest_category
-- ----------------------------
DROP TABLE IF EXISTS `rest_category`;
CREATE TABLE `rest_category`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请假类别表id',
  `rest_category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请假类别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rest_category
-- ----------------------------

-- ----------------------------
-- Table structure for rest_record
-- ----------------------------
DROP TABLE IF EXISTS `rest_record`;
CREATE TABLE `rest_record`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调休表id',
  `gmt_create` timestamp NULL DEFAULT NULL COMMENT '申请时间',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '调休开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '调休结束时间',
  `rest_residue` float NULL DEFAULT NULL COMMENT '未休时长',
  `duration` float NULL DEFAULT NULL COMMENT '调休时长',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调休原因',
  `employe_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '申请人id',
  `auditor_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批人id',
  `status` int NULL DEFAULT NULL COMMENT '申请状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rest_record
-- ----------------------------
INSERT INTO `rest_record` VALUES ('977007584504446979', '2022-05-20 00:39:20', '2022-05-20 00:31:00', '2022-05-21 00:31:00', 0, 1, '遇到困难睡大觉', '976853692433039361', '976853692433039361', 1);
INSERT INTO `rest_record` VALUES ('977203483247640579', '2022-05-20 13:37:46', '2022-05-17 00:00:00', '2022-05-20 05:00:00', 0, 3.2, '睡大觉', '976853692433039361', '976853692433039361', 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('0', 'ordinary', '默认用户级别');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `role_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色Id，role表主键',
  `permission_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能Id，function表主键',
  `allow` tinyint(1) NOT NULL COMMENT '允许访问，true/false',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('0', '0', '0', 1);
INSERT INTO `role_permission` VALUES ('1', '0', '1', 1);
INSERT INTO `role_permission` VALUES ('2', '0', '2', 1);
INSERT INTO `role_permission` VALUES ('3', '0', '3', 1);
INSERT INTO `role_permission` VALUES ('4', '0', '4', 1);

-- ----------------------------
-- Table structure for summary
-- ----------------------------
DROP TABLE IF EXISTS `summary`;
CREATE TABLE `summary`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '汇总表id',
  `employe_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工id',
  `vacation_time_sum` float NULL DEFAULT NULL COMMENT '请假总时长',
  `business_trip_freq` int NULL DEFAULT NULL COMMENT '出差次数',
  `over_time_sum` float NULL DEFAULT NULL COMMENT '加班总时长',
  `rest_time_sum` float NULL DEFAULT NULL COMMENT '调休总时长',
  `late_freq` int NULL DEFAULT NULL COMMENT '迟到次数',
  `early_freq` int NULL DEFAULT NULL COMMENT '早退次数',
  `leakage_freq` int NULL DEFAULT NULL COMMENT '漏打卡次数',
  `morning_absent` int NULL DEFAULT NULL COMMENT '早会未到次数',
  `morning_exercise_absent` int NULL DEFAULT NULL COMMENT '早操未到次数',
  `meeting_absent` int NULL DEFAULT NULL COMMENT '会议缺席次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of summary
-- ----------------------------

-- ----------------------------
-- Table structure for trip_mode
-- ----------------------------
DROP TABLE IF EXISTS `trip_mode`;
CREATE TABLE `trip_mode`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '出行方式表id',
  `mode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出行方式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of trip_mode
-- ----------------------------
INSERT INTO `trip_mode` VALUES ('0', '大巴');
INSERT INTO `trip_mode` VALUES ('1', '飞机');
INSERT INTO `trip_mode` VALUES ('2', '火车');
INSERT INTO `trip_mode` VALUES ('3', '自驾');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `role_id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色Id，role表主键',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加密盐值',
  `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '状态：正常（1）、锁定（2）、离职（0）',
  `employe_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '员工id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('976856005553946626', '0', 'tzy', 'b53de84b4939dded5b0017af1575b50f', 'abcd*', 'lincheon@outlook.com', '15951318373', 1, '976853692433039361', NULL);
INSERT INTO `user` VALUES ('977972313486000128', '0', 'dcy', 'b53de84b4939dded5b0017af1575b50f', 'abcd*', NULL, NULL, NULL, '976991875246325761', NULL);

-- ----------------------------
-- Table structure for vacate_category
-- ----------------------------
DROP TABLE IF EXISTS `vacate_category`;
CREATE TABLE `vacate_category`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请假类型id',
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请假类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of vacate_category
-- ----------------------------
INSERT INTO `vacate_category` VALUES ('0', '病假');
INSERT INTO `vacate_category` VALUES ('1', '事假');
INSERT INTO `vacate_category` VALUES ('2', '产假');

-- ----------------------------
-- Table structure for vacation_record
-- ----------------------------
DROP TABLE IF EXISTS `vacation_record`;
CREATE TABLE `vacation_record`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '假期申请表id',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '请假开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '请假结束时间',
  `day` int NULL DEFAULT NULL COMMENT '请假时长(天)',
  `hour` int NULL DEFAULT NULL COMMENT '请假时长(小时)',
  `vacate_category_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请假类别',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请假原因',
  `employe_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请假人id',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态(0：已提交，1：通过，2：未通过)',
  `auditor_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of vacation_record
-- ----------------------------
INSERT INTO `vacation_record` VALUES ('976978792939520002', '2022-05-19 22:42:00', '2023-05-20 06:42:00', 365, 8, '1', '没睡好，休个假睡个好觉', '976853692433039361', '1', '976853692433039361');

-- ----------------------------
-- Table structure for wx_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user`  (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信用户表主键',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'open_id',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户性别',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `union_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'union_id',
  `country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国家',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '城市',
  `language` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '语言',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `enabled` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否可用',
  `lastPasswordResetTime` timestamp NULL DEFAULT NULL COMMENT '上一次密码更新时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wx_user
-- ----------------------------
INSERT INTO `wx_user` VALUES ('1480131142794379265', NULL, NULL, 'onUBC5CLn76NxpDjPsH3uBGtyPOU', '微信用户', '0', 'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132', NULL, '', '', '', '', NULL, NULL, NULL, NULL, NULL, '2022-01-09 18:55:38', NULL);

SET FOREIGN_KEY_CHECKS = 1;
