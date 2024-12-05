package com.travel.entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 留言表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Leave implements Serializable {

    private String id;

    /**
     * 留言用户id
     */
    private String uid;

    /**
     * 留言内容
     */
    private String ucontent;

    /**
     * 回复内容
     */
    private String acontent;

    /**
     * 回复时间
     */
    private String acreated;

    /**
     * 留言时间
     */

    private String created;


    /**
     * 留言用户姓名
     */
    private String userName;



}
