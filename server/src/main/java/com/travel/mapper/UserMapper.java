package com.travel.mapper;

import com.travel.entity.User;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    User getOne(User user);

}
