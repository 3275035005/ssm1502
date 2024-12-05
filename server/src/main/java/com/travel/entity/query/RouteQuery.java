package com.travel.entity.query;

import lombok.Data;

@Data
public class RouteQuery {


    /**
     * 景点名称
     */
    private String rname;



    /**
     * 发布状态，必输，0代表未发布，1代表是已发布
     */
    private String rflag;



    /**
     * 是否为推荐景点，必输，0代表不是，1代表是
     */
    private String isThemeTour;
}
