package com.homework.thursday;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;

public class BatchInsert {

    @Autowired
    DataSource dataSource;

    public void go() throws SQLException {
        long start = System.currentTimeMillis();
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);

        PreparedStatement statement = connection.prepareStatement("INSERT INTO homework.hx_order (id, buyer_id, phone_number, order_amount, payment_amount, order_status, order_time, cancel_time, close_time, confirm_receipt_time, coupon_amount, created_time, platform_id, service_id, wechat_open_id, entity_status, is_evaluated, finish_time, freight, freight_model, is_invoiced, last_modified_time, member_discount_amount, order_goods_return_status, pay_succeed_time, payment_type, promotion_amount, seller_id, share_people_open_id, area_code, contact_name, full_address, shop_id, total_discount_amount, version, invoice_id, delivery_company, delivery_no, delivery_remark, delivery_time, shipment_type, delivery_type, address_id, unify_contact_id, is_parent_order, parent_order_id, goods_code, platform_order_id, logistic_information, settlement_status) VALUES (?, '123', '123123', 11.00, 11.00, 10, '2020-11-29 20:26:51', null, null, null, 100.00, '2020-11-29 20:27:23', 'aa2121', 'qqwe1', 'asdad211', 1, 0, '1970-01-01 00:00:00', 10.00, 10.00, 0, '2020-11-29 20:28:12', 10.00, 10, '2020-11-29 20:28:29', 10, 10.00, 'oqiwnoqn1213', null, null, '123123', 'linln1', 'qwdqw123', 10.00, 1, 'qw123', null, null, null, null, null, 10, '1980980912', 12, 0, null, null, 'o1ijeo1nie', 'we122e', 1)");
        for (int i = 1; i <= 10000000; i++) {
            statement.setString(1, String.valueOf(i));
            statement.executeUpdate();
//            statement.addBatch();
//            if (i % 1000 == 0) {
//                statement.executeLargeBatch();
//            }
        }

        connection.commit();
        long end = System.currentTimeMillis() - start;


        System.out.println("=========  end: " + Duration.ofMillis(end).getSeconds() + "s");
    }
}
// 100w 1次批量提交 --> 63s
// 100w 10次批量提交 --> 42s
// 100w 100次批量提交 --> 42s
// 100w 1000次批量提交 --> 37s
// 100w 10000次批量提交 --> 41s

// 100w 一条条插入 --> 188s

// 1000w 10次批量提交 --> 751s
// 1000w 100次批量提交 --> 563s
// 1000w 1000次批量提交 --> 431s
// 1000w 10000次批量提交 --> 497s

// 1000w 一条条插入 --> 1951s
