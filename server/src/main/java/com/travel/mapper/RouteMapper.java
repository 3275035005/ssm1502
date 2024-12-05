package com.travel.mapper;

import com.travel.entity.Route;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 */
@Repository
public interface RouteMapper extends BaseMapper<Route> {

    List<Route> pageQueryOrderByCount(Route route);

    List<Route> getRoute(@Param("route")Route route, @Param("list") List<Route> list);
}
