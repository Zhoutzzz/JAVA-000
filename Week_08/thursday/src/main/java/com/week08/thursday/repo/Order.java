package com.week08.thursday.repo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.apache.ibatis.type.JdbcType;

import static org.apache.ibatis.type.JdbcType.*;

@Data
@ToString
@TableName(value = "t_order", autoResultMap = true)
public class Order {

//    public Order(String buyerId, Integer orderStatus) {
//        this.buyerId = buyerId;
//        this.orderStatus = orderStatus;
//    }

    @TableId
    private Long orderId;

    @TableField(value = "buyer_id", jdbcType= VARCHAR)
    private String buyerId;

    @TableField("order_status")
    private Integer orderStatus;
}
