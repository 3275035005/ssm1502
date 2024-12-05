package com.travel.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.entity.Route;
import com.travel.mapper.RouteMapper;
import com.travel.service.RouteService;
import com.travel.utils.PageResult;
import com.travel.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 */
@Service
public class RouteServiceImpl  implements RouteService {

    @Autowired
    private RouteMapper routeMapper;

    @Override
    public PageResult<Route> pageQuery(Route data, int current, int size) {
        PageHelper.startPage(current, size);
        List<Route> queryList = routeMapper.pageQuery(data);
        PageInfo<Route> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public int save(Route data) {
        data.setId(Util.getId());
        data.setCreated(Util.getTime());
        return routeMapper.insert(data);
    }

    @Override
    public int update(Route data) {
        return routeMapper.update(data);
    }

    @Override
    public List<Route> getAll() {
        return routeMapper.getAll();
    }

    @Override
    public void delete(String id) {
        routeMapper.delete(id);
    }

    @Override
    public Route getById(String id) {
        return routeMapper.getById(id);
    }

    @Override
    public PageResult<Route> pageQueryOrderByCount(Route route, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Route> queryList = routeMapper.pageQueryOrderByCount(route);
        PageInfo<Route> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);

    }

    @Override
    public List<Route> getRoute(Route route, List<Route> list) {
        return routeMapper.getRoute(route, list);

    }
}
