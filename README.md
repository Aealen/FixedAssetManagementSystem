# 课题简介:

城科固定资产维修系统的设计与实现:
"系统角色：管理员、负责人、报修员、维修员
管理员：用户管理、设备类型管理、设备信息管理、设备状态管理、费用管理、维修统计等
报修员：维修申请、查看维修状态、维修确认等
维修员：维修确认、费用结算等
负责人：维修审核、查看维修记录等"

# 状态码:

## 成功:200

## 用户:

- 101 用户名或密码错误

- 102 用户已存在

- 103 用户不存在

- 104 用户未登录

- 105 用户登出

- 106 Token非法

  

## 数据库:

- 150 数据库操作异常!请尽快联系系统管理员!
- 151 数据库连接失败
- 152 参数非法

## 权限:

- 403 权限不足
- 402 未认证
- 401 用户认证失败请重新登陆

## 资产:

- 170 类型已存在
- 171 类型不存在

# 角色:

## 管理员：

- 用户管理

- 设备类型管理
- 设备信息管理
- 设备状态管理
- 费用管理
- 维修统计

## 报修员：

- 维修申请
- 查看自己提交的订单维修状态
- 维修确认等；

## 维修员：

- 维修确认
- 查看被分配到的订单维修状态
- 费用结算

## 负责人：

- 维修审核
- 查看所负责的固定资产的维修记录

















# 数据库

```sql
/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : fams

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 13/02/2023 23:08:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fa_department
-- ----------------------------
DROP TABLE IF EXISTS `fa_department`;
CREATE TABLE `fa_department`  (
  `did` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  PRIMARY KEY (`did`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fa_fixedasset
-- ----------------------------
DROP TABLE IF EXISTS `fa_fixedasset`;
CREATE TABLE `fa_fixedasset`  (
  `fid` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'FixedAsset Id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'FixedAsset Name',
  `type` int NOT NULL COMMENT 'FixedAsset Type',
  `model` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'FixedAsset Model',
  `producer` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'FixedAsset Producer',
  `price` decimal(10, 2) NOT NULL COMMENT 'FixedAsset Per Price',
  `dep` int NOT NULL COMMENT 'FixedAsset Department',
  `custodian` int NOT NULL COMMENT 'FixedAsset Sub-custodian\n\n',
  `del_flag` int NOT NULL COMMENT '0 表示未删除  1表示删除',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fa_type
-- ----------------------------
DROP TABLE IF EXISTS `fa_type`;
CREATE TABLE `fa_type`  (
  `tid` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `oid` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Order id',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `fa` int NOT NULL COMMENT '涉及报修的FA 信息  包含负责人',
  `status` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '报修单状态 0未处理  1已处理',
  `reporter` int NOT NULL COMMENT '报修人',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '删除标识',
  `up_time` date NOT NULL,
  PRIMARY KEY (`oid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志产生位置',
  `operator` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'system' COMMENT '日志涉及用户或系统',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for um_permission
-- ----------------------------
DROP TABLE IF EXISTS `um_permission`;
CREATE TABLE `um_permission`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名',
  `perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限标识',
  `del_flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for um_role
-- ----------------------------
DROP TABLE IF EXISTS `um_role`;
CREATE TABLE `um_role`  (
  `rid` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for um_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `um_role_permission`;
CREATE TABLE `um_role_permission`  (
  `rid` int NOT NULL COMMENT 'role id',
  `permid` int NOT NULL COMMENT 'permission id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for um_user
-- ----------------------------
DROP TABLE IF EXISTS `um_user`;
CREATE TABLE `um_user`  (
  `uid` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department` int NOT NULL DEFAULT 0,
  `del_flag` int NOT NULL DEFAULT 0,
  `phone_num` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `reg_time` datetime NOT NULL,
  `login_time` datetime NOT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE COMMENT '用户名唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for um_user_role
-- ----------------------------
DROP TABLE IF EXISTS `um_user_role`;
CREATE TABLE `um_user_role`  (
  `uid` int NOT NULL COMMENT 'userid',
  `rid` int NOT NULL COMMENT 'role id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for fixedassets
-- ----------------------------
DROP VIEW IF EXISTS `fixedassets`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `fixedassets` AS select `fa_fixedasset`.`fid` AS `id`,`fa_fixedasset`.`name` AS `fa_name`,`fa_type`.`name` AS `fa_type`,`fa_fixedasset`.`model` AS `fa_model`,`fa_fixedasset`.`producer` AS `fa_producer`,`fa_fixedasset`.`price` AS `fa_price`,`fa_department`.`name` AS `fa_dept`,`um_user`.`username` AS `fa_custodian` from (((`fa_fixedasset` left join `fa_type` on((`fa_fixedasset`.`type` = `fa_type`.`tid`))) left join `fa_department` on((`fa_fixedasset`.`dep` = `fa_department`.`did`))) left join `um_user` on((`um_user`.`uid` = `fa_fixedasset`.`custodian`))) where ((`fa_fixedasset`.`del_flag` = 0) and (`um_user`.`del_flag` = 0));

-- ----------------------------
-- View structure for orders
-- ----------------------------
DROP VIEW IF EXISTS `orders`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `orders` AS select `order`.`oid` AS `id`,`order`.`description` AS `order_desc`,`fixedassets`.`fa_name` AS `fa_name`,`fixedassets`.`fa_type` AS `fa_type`,`fixedassets`.`fa_model` AS `fa_model`,`fixedassets`.`fa_price` AS `fa_price`,`fixedassets`.`fa_custodian` AS `order_custodian`,`order`.`status` AS `order_status`,`u`.`nickname` AS `order_reporter`,`order`.`up_time` AS `order_up_time` from ((`order` left join `fixedassets` on((`order`.`fa` = `fixedassets`.`id`))) left join `um_user` `u` on((`u`.`uid` = `order`.`reporter`))) where ((`order`.`del_flag` = 0) and (`u`.`del_flag` = 0));

-- ----------------------------
-- View structure for users
-- ----------------------------
DROP VIEW IF EXISTS `users`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `users` AS select distinct `um_user`.`uid` AS `id`,`um_user`.`username` AS `username`,`um_user`.`password` AS `password`,`um_user`.`phone_num` AS `phone_num`,`um_user`.`email` AS `email`,`fa_department`.`did` AS `dept_id`,`fa_department`.`name` AS `dept_name`,`um_role`.`name` AS `role_name` from (((`um_user` left join `fa_department` on((`um_user`.`department` = `fa_department`.`did`))) left join `um_user_role` on((`um_user_role`.`uid` = `um_user`.`uid`))) left join `um_role` on((`um_user_role`.`rid` = `um_role`.`rid`))) where (`um_user`.`del_flag` = 0);

SET FOREIGN_KEY_CHECKS = 1;

```





## RBAC五表

采用 RBAC模型（Role-Based Access Control：基于角色的访问控制）建表便于职责分离和权限控制.

### 用户表



### 角色表



### 权限表



### 用户-角色表



### 角色-权限表



## 资产表

根据如下内容确定固定资产各个属性相关字段

> [学校固定资产管理制度 (pingdu.gov.cn)](http://pingdu.gov.cn/n6865/n6867/n6881/n7057/n7058/n8021/n11121/220926153312000488.html)
>
> [我校固定资产管理制度 (gdpnc.edu.cn)](https://zwc.gdpnc.edu.cn/2021/0701/c537a33765/page.htm)
>
> [学校固定资产管理制度 (yjbys.com)](https://www.yjbys.com/zhidu/3019403.html)
>
> [学校固定资产如何分类-教育行业法律知识-华律网 (66law.cn)](https://www.66law.cn/zhuanti/jyhy/zs/359.aspx)



| 字段名    | 键   | 类型    | 备注                     |
| --------- | ---- | ------- | ------------------------ |
| fid       | PK   | int     | FixedAsset Id            |
| name      |      | varchar | FixedAsset Name          |
| type      |      | int     | FixedAsset Type          |
| model     |      | varchar | FixedAsset Model         |
| producer  |      | varchar | FixedAsset Producer      |
| price     |      | decimal | FixedAsset Per Price     |
| dep       |      | int     | FixedAsset Department    |
| custodian |      | int     | FixedAsset Sub-custodian |
| del_flag  |      | int     | 0 表示未删除  1表示删除  |

# Logs

- 23/02/01 主体框架完成
- 23/02/13 引入Swagger接口文档
- 23/02/14 用户认证  鉴权模块完成
- 23/02/16 用户管理开坑
- 23/02/18 用户管理模块基本完成