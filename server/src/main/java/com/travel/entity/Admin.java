package com.travel.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 管理员表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Admin implements Serializable {

    /**
     * 主键
     */

    private String id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;


    /**
     * 创建时间
     */

    private String created;

    /**
     * 账号状态(0禁用 1正常)
     */
    private String status;

    /**
     * 管理员姓名
     */
    private String name;


}
