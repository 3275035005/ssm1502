package com.travel.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.entity.RouteStrategy;
import com.travel.mapper.RouteStrategyMapper;
import com.travel.service.RouteStrategyService;
import com.travel.utils.PageResult;
import com.travel.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 景点攻略路线关联表 服务实现类
 * </p>
 */
@Service
public class RouteStrategyServiceImpl implements RouteStrategyService {

    @Autowired
    private RouteStrategyMapper routeStrategyMapper;


    @Override
    public PageResult<RouteStrategy> pageQuery(RouteStrategy data, int current, int size) {
        PageHelper.startPage(current, size);
        List<RouteStrategy> queryList = routeStrategyMapper.pageQuery(data);
        PageInfo<RouteStrategy> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public int save(RouteStrategy data) {
        data.setId(Util.getId());
        return routeStrategyMapper.insert(data);
    }

    @Override
    public int update(RouteStrategy data) {
        return routeStrategyMapper.update(data);
    }

    @Override
    public List<RouteStrategy> getAll() {
        return routeStrategyMapper.getAll();
    }

    @Override
    public void delete(String id) {
        routeStrategyMapper.delete(id);
    }

    @Override
    public RouteStrategy getById(String id) {
        return routeStrategyMapper.getById(id);
    }

    @Override
    public void deleteByStrategyId(String id) {
        routeStrategyMapper.deleteByStrategyId(id);
    }
}
