package com.travel.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 收藏表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Favorite implements Serializable {



    private String id;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 景点id
     */
    private String rid;

    /**
     * 收藏时间
     */

    private String created;

    /**
     * 收藏用户姓名
     */
    private String userName;
    /**
     * 收藏景点名称
     */
    private String routeName;


    private Route route;
}
