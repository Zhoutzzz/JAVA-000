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

 Date: 25/11/2020 19:29:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hx_goods_sku
-- ----------------------------
DROP TABLE IF EXISTS `hx_goods_sku`;
CREATE TABLE `hx_goods_sku`  (
  `id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `entity_status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '实体状态，1为有效，-1为删除',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改时间',
  `cost_price` decimal(14, 2) NULL DEFAULT NULL COMMENT '成本价',
  `market_price` decimal(14, 2) NOT NULL COMMENT '市场价',
  `sales_attribute_values` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品销售属性value',
  `sales_attributes` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品销售属性keyId:valueId',
  `settlement_price` decimal(14, 2) NULL DEFAULT NULL COMMENT '结算价',
  `sku_type` tinyint(4) NOT NULL DEFAULT 1 COMMENT 'sku类型',
  `spu_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'spu id',
  `stock` int(11) NOT NULL COMMENT '对应类型sku库存量',
  `freight_template_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '运费模板id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `spu_idx`(`spu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hx_goods_sku_info
-- ----------------------------
DROP TABLE IF EXISTS `hx_goods_sku_info`;
CREATE TABLE `hx_goods_sku_info`  (
  `id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `entity_status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '实体状态，1为有效，-1为删除',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改时间',
  `detail` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `detail_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sku_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `spu_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hx_goods_spu
-- ----------------------------
DROP TABLE IF EXISTS `hx_goods_spu`;
CREATE TABLE `hx_goods_spu`  (
  `id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `entity_status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '实体状态，1为有效，-1为删除',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改时间',
  `brand_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品品牌ID',
  `category_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品类别id',
  `cost_price` decimal(14, 2) NULL DEFAULT NULL COMMENT '成本价',
  `goods_name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名称',
  `market_price` decimal(14, 2) NOT NULL COMMENT '市场价',
  `non_sales_attributes` varchar(1100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'json结构,商品非销售属性key:value',
  `product_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品货号',
  `seller_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '卖家ID',
  `shop_category_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品所属店铺类目id',
  `shop_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商铺ID',
  `source_type` tinyint(4) NOT NULL DEFAULT 1 COMMENT '销售类型 1:自营 2：京东',
  `stock_total` int(11) NOT NULL COMMENT '所有sku的总库存量',
  `title` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品标题',
  `with_sku` bit(1) NULL DEFAULT NULL,
  `delivery_type` tinyint(2) NULL DEFAULT NULL COMMENT '配送类型',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `shop_idx`(`shop_id`) USING BTREE,
  INDEX `seller_brand_category_idx`(`seller_id`, `brand_id`, `category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hx_goods_spu_info
-- ----------------------------
DROP TABLE IF EXISTS `hx_goods_spu_info`;
CREATE TABLE `hx_goods_spu_info`  (
  `id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `entity_status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '实体状态，1为有效，-1为删除',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近修改时间',
  `after_service` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authentication` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `detail` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `detail_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `donate_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `orgin` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `packing_list` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `spu_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `spu_idx`(`spu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
