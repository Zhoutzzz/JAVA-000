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

 Date: 24/11/2020 18:59:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hx_order
-- ----------------------------
DROP TABLE IF EXISTS `hx_order`;
CREATE TABLE `hx_order`  (
  `id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `buyer_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '买家id',
  `phone_number` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `order_amount` decimal(14, 2) NOT NULL COMMENT '订单金额',
  `payment_amount` decimal(14, 2) NOT NULL COMMENT '实际支付金额',
  `order_status` tinyint(4) NOT NULL DEFAULT 10 COMMENT '订单状态',
  `order_time` datetime(0) NOT NULL COMMENT '下单时间',
  `cancel_time` datetime(0) NULL DEFAULT NULL COMMENT '订单取消时间',
  `close_time` datetime(0) NULL DEFAULT NULL COMMENT '订单关闭时间',
  `confirm_receipt_time` datetime(0) NULL DEFAULT NULL COMMENT '物流完成时间，从物流数据中获取',
  `coupon_amount` decimal(14, 2) NOT NULL COMMENT '优惠券优惠金额',
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `platform_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所属平台id',
  `service_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务id',
  `wechat_open_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信id',
  `entity_status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '实体状态，1为有效，-1为删除',
  `is_evaluated` tinyint(4) NOT NULL DEFAULT 0 COMMENT '买家需要评价订单(0:无,1:是)',
  `finish_time` datetime(0) NULL DEFAULT NULL COMMENT '订单完成时间',
  `freight` decimal(14, 2) NULL DEFAULT NULL COMMENT '运费',
  `freight_model` decimal(14, 2) NULL DEFAULT NULL COMMENT '运费模式',
  `is_invoiced` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否已经开具发票(0:无，0:有)',
  `last_modified_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最近修改时间',
  `member_discount_amount` decimal(14, 2) NOT NULL COMMENT '会员折扣金额',
  `order_goods_return_status` tinyint(4) NOT NULL DEFAULT 10 COMMENT '订单退货状态',
  `pay_succeed_time` datetime(0) NULL DEFAULT NULL COMMENT '订单支付成功时间',
  `payment_type` tinyint(4) NULL DEFAULT NULL COMMENT '支付类型',
  `promotion_amount` decimal(14, 2) NOT NULL COMMENT '促销优惠金额',
  `seller_id` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `share_people_open_id` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分享人的openId',
  `area_code` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `contact_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `full_address` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `shop_id` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `total_discount_amount` decimal(14, 2) NOT NULL COMMENT '总优惠金额',
  `version` mediumint(8) UNSIGNED NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `invoice_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delivery_company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delivery_no` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delivery_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `delivery_time` datetime(0) NULL DEFAULT NULL,
  `shipment_type` int(11) NULL DEFAULT NULL,
  `delivery_type` smallint(6) NOT NULL COMMENT '配送类型 1.快递  2。自提',
  `address_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址ID',
  `unify_contact_id` bigint(20) NULL DEFAULT NULL COMMENT '核销管理员id',
  `is_parent_order` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否是父订单',
  `parent_order_id` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父订单id',
  `goods_code` int(11) NULL DEFAULT NULL COMMENT '提货码',
  `platform_order_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '平台订单号',
  `logistic_information` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自提物流说明',
  `settlement_status` tinyint(4) NULL DEFAULT 1 COMMENT '结算状态，1出账，2待结算，3已结算',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `orderStatus`(`buyer_id`, `order_status`) USING BTREE,
  INDEX `FK6keg0arntrk2aos8rpflqkfa0`(`invoice_id`) USING BTREE,
  CONSTRAINT `FK6keg0arntrk2aos8rpflqkfa0` FOREIGN KEY (`invoice_id`) REFERENCES `hx_order_invoice` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
