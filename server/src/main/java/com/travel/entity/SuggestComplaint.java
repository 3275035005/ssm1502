package com.travel.entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 景点建议投诉表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SuggestComplaint implements Serializable {

    private String id;


    /**
     * 内容
     */
    private String content;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 景点id
     */
    private String rid;

    /**
     * 1投诉 2建议
     */
    private String type;

    /**
     * 创建时间
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
