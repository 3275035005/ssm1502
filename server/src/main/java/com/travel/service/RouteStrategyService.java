package com.travel.service;

import com.travel.service.BaseService;
import com.travel.entity.RouteStrategy;

/**
 * <p>
 * 景点攻略路线关联表 服务类
 * </p>
 */
public interface RouteStrategyService extends BaseService<RouteStrategy> {

    void deleteByStrategyId(String id);

}
