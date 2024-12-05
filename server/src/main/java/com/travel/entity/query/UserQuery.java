package com.travel.entity.query;

import lombok.Data;

@Data
public class UserQuery {



    /**
     * 姓名
     */
    private String name;


    /**
     * 用户状态(0，禁用 1,正常)
     */
    private String status;

    /**
     * 账号
     */
    private String username;
}
