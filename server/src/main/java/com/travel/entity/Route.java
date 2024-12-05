package com.travel.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 景点线路规划信息表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Route implements Serializable {

    /**
     * 主键id
     */

    private String id;

    /**
     * 景点路线
     */
    private String rname;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 景点介绍
     */
    private String routeIntroduce;

    /**
     * 是否上架，必输，0代表没有上架，1代表是上架
     */
    private String rflag;

    /**
     * 封面
     */
    private String rimage;


    /**
     * 咨询电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 发布时间
     */

    private String created;

    /**
     * 是否为推荐景点，必输，0代表不是，1代表是
     */
    private String isThemeTour;

    /**
     * 热度值
     */
    private Integer count;

    /**
     * 景点须知
     */
    private String content;

}
