package com.travel.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 景点攻略路线关联表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RouteStrategy implements Serializable {


    private String id;

    /**
     * 攻略id
     */
    private String strategyId;

    /**
     * 景点id
     */
    private String routeId;


}
