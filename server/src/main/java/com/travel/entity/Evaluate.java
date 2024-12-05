package com.travel.entity;


import lombok.Data;
import lombok.ToString;

/**
 * 评论表
 */
@ToString
@Data
public class Evaluate {



    private String id;

    /**
     * 评价内容
     */
    private String content;


    /**
     * 评价用户id
     */
    private String uid;

    /**
     * 评价景点id
     */
    private String rid;

    /**
     * 评价时间
     */

    private String created;

    /**
     * 评价用户姓名
     */
    private String userName;

    /**
     * 景点名称
     */
    private String routeName;
}
