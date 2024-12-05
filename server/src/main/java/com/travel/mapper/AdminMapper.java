package com.travel.mapper;

import com.travel.entity.Admin;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 */
@Repository
public interface AdminMapper extends BaseMapper<Admin> {

    Admin getByAdmin(Admin adminQuery);
}
