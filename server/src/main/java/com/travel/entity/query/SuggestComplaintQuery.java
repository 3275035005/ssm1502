package com.travel.entity.query;

import lombok.Data;

@Data
public class SuggestComplaintQuery {

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
}
