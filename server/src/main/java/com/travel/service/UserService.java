package com.travel.service;

import com.travel.service.BaseService;
import com.travel.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 */
public interface UserService extends BaseService<User> {

    User getOne(User user);

}
