package com.travel.mapper;

import com.travel.entity.RouteStrategy;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 景点攻略路线关联表 Mapper 接口
 * </p>
 */
@Repository
public interface RouteStrategyMapper extends BaseMapper<RouteStrategy> {

    List<String> getRouteNameByStrategyId(String strategyId);

    List<String> getRouteIdByStrategyId(String strategyId);

    void deleteByStrategyId(String id);
}
