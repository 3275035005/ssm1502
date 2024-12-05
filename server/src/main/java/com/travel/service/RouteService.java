package com.travel.service;

import com.travel.service.BaseService;
import com.travel.entity.Route;
import com.travel.utils.PageResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface RouteService extends BaseService<Route> {

    PageResult<Route> pageQueryOrderByCount(Route route, int page, int limit);

    List<Route> getRoute(Route route, List<Route> list);

}
