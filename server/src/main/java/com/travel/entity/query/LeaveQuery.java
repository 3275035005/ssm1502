package com.travel.entity.query;

import lombok.Data;

@Data
public class LeaveQuery {



    /**
     * 留言用户id
     */
    private String uid;


    /**
     * 留言内容
     */
    private String ucontent;
}
