package com.travel.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 旅游攻略路线
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Strategy implements Serializable {


    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面
     */
    private String image;

    /**
     * 是否为推荐路线，必输，0代表不是，1代表是
     */
    private String isThemeTour;

    /**
     * 攻略须知
     */
    private String content;

    /**
     * 攻略介绍
     */
    private String strategyIntroduce;

    /**
     * 发布时间
     */

    private String created;


    private List<String> routeIdList;

    /**
     * 显示标题
     */
    private String titles;
}
