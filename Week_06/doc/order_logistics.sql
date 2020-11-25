/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : elbf_prod

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 25/11/2020 19:30:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hx_order_logistics
-- ----------------------------
DROP TABLE IF EXISTS `hx_order_logistics`;
CREATE TABLE `hx_order_logistics`  (
  `id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `entity_status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '实体状态，1为有效，-1为删除',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改时间',
  `delivery_company` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  `delivery_no` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '快递单号',
  `delivery_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '物流相关备注',
  `delivery_status` tinyint(4) NOT NULL DEFAULT 10 COMMENT '包裹物流状态',
  `delivery_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单发货时间',
  `order_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单号',
  `shipment_type` tinyint(4) NOT NULL COMMENT '配送类型',
  `confirm_receipt_time` datetime(0) NULL DEFAULT NULL COMMENT '包裹签收时间',
  `package_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '包裹标题',
  `source_type` tinyint(4) NOT NULL COMMENT '包裹来源',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hx_order_logistics_items
-- ----------------------------
DROP TABLE IF EXISTS `hx_order_logistics_items`;
CREATE TABLE `hx_order_logistics_items`  (
  `id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `entity_status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '实体状态，1为有效，-1为删除',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改时间',
  `logs_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '物流ID',
  `order_items_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单详情ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
