package com.travel.entity.query;

import lombok.Data;

@Data
public class OrderQuery {

    /**
     * 景点id
     */
    private String rid;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 订单状态(0购票成功 1退票成功 2订单完成)
     */
    private String status;
}
