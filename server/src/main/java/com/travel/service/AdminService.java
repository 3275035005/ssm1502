package com.travel.service;

import com.travel.service.BaseService;
import com.travel.entity.Admin;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 */
public interface AdminService extends BaseService<Admin> {

    Admin getByAdmin(Admin adminQuery);
}
