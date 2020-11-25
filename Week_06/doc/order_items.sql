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

 Date: 24/11/2020 19:00:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hx_order_items
-- ----------------------------
DROP TABLE IF EXISTS `hx_order_items`;
CREATE TABLE `hx_order_items`  (
  `id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `entity_status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '实体状态，1为有效，-1为删除',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改时间',
  `beneficiaries_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '受助者id',
  `beneficiaries_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '受助者名称',
  `coupon_amount` decimal(14, 2) NOT NULL COMMENT '优惠券优惠金额',
  `is_donated` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否赞助',
  `donation_amount` decimal(14, 2) NOT NULL COMMENT '赞助总金额',
  `donation_type` tinyint(4) NULL DEFAULT 1 COMMENT '赞助类型，1为固定金额，2为按售价比例',
  `donation_unit` decimal(14, 2) NULL DEFAULT NULL COMMENT '单个商品赞助金额或者比例',
  `freight_template_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '运费模版id',
  `goods_image` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品主图URL',
  `goods_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `market_price` decimal(14, 2) NOT NULL COMMENT '对应的市场价',
  `market_price_display` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '对应的市场价名称',
  `sales_attribute_values` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品销售属性value的组合',
  `seller_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '卖家ID',
  `selling_price` decimal(14, 2) NOT NULL COMMENT '对应的实际售价',
  `selling_price_display` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '对应的实际售价名称',
  `shop_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '店铺id',
  `sku_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'sku id',
  `spu_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'spu id',
  `member_discount_amount` decimal(14, 2) NOT NULL COMMENT '会员折扣金额',
  `order_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单id',
  `original_amount` decimal(14, 2) NOT NULL COMMENT '实际总额',
  `payment_amount` decimal(14, 2) NOT NULL COMMENT '实际支付总金额',
  `promotion_amount` decimal(14, 2) NOT NULL COMMENT '促销优惠金额',
  `quantity` smallint(6) NOT NULL COMMENT '购买数量',
  `total_discount_amount` decimal(14, 2) NOT NULL COMMENT '总优惠金额',
  `items_status` tinyint(4) NULL DEFAULT 1 COMMENT '单品状态 1：未发货 ,2：已发货,3：待评价,4：已评价',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id_index`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
