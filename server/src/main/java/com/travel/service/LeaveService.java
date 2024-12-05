package com.travel.service;

import com.travel.service.BaseService;
import com.travel.entity.Leave;

import java.util.List;

/**
 * <p>
 * 留言表 服务类
 * </p>
 *
 */
public interface LeaveService extends BaseService<Leave> {

    List<Leave> getByUid(String uId);
}
