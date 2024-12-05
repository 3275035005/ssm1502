package com.travel.entity.query;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 评论表
 * </p>
 */
@Data
public class EvaluateQuery implements Serializable {

    /**
     * 用户id
     */
    private String uid;

    /**
     * 景点id
     */
    private String rid;

    /**
     * 评论内容
     */
    private String content;


}
