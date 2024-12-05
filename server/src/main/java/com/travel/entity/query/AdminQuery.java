package com.travel.entity.query;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 管理员表
 * </p>
 *
 */
@Data
public class AdminQuery implements Serializable {

    /**
     * 管理员账号
     */
    private String account;


    /**
     * 状态(0.停用 1.正常)
     */
    private String status;



}
