package com.travel.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 订单表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Order implements Serializable {

    private String id;

    /**
     * 景点id
     */
    private String rid;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 订单状态(0 购票成功 1 退票成功 2 订单完成)
     */
    private String status;

    /**
     * 购票时间
     */

    private String created;

    /**
     * 景点名称
     */
    private String rname;

    /**
     * 用户姓名
     */
    private String name;

}
