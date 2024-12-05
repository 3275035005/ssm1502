package com.travel.entity.query;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 收藏表
 * </p>
 */
@Data
public class FavoriteQuery implements Serializable {

    /**
     * 用户id
     */
    private String uid;

    /**
     * 景点id
     */
    private String rid;


}
