package com.travel.entity.query;

import lombok.Data;

@Data
public class StrategyQuery {

    /**
     * 标题
     */
    private String title;

    /**
     * 是否为推荐路线，必输，0代表不是，1代表是
     */
    private String isThemeTour;
}
