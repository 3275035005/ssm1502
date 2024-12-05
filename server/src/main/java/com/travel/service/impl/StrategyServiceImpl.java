package com.travel.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.entity.Strategy;
import com.travel.entity.query.StrategyQuery;
import com.travel.mapper.RouteStrategyMapper;
import com.travel.mapper.StrategyMapper;
import com.travel.service.StrategyService;
import com.travel.utils.PageResult;
import com.travel.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 旅游攻略路线 服务实现类
 * </p>
 */
@Service
public class StrategyServiceImpl  implements StrategyService {

    @Autowired
    private RouteStrategyMapper routeStrategyMapper;

    @Autowired
    private StrategyMapper baseMapper;

    @Override
    public PageResult<Strategy> pageQuery(Strategy data, int current, int size) {
        PageHelper.startPage(current, size);
        List<Strategy> queryList = baseMapper.pageQuery(data);
        for (Strategy strategy : queryList) {
            List<String> routeNameList = routeStrategyMapper.getRouteNameByStrategyId(strategy.getId());
            for (String routeName : routeNameList) {
                strategy.setTitles(strategy.getTitles() == null? routeName+" " : strategy.getTitles() +routeName+" ");
            }
            strategy.setTitles(strategy.getTitle()+"  "+ strategy.getTitles());
            List<String> routeIdList = routeStrategyMapper.getRouteIdByStrategyId(strategy.getId());
            strategy.setRouteIdList(routeIdList);
        }
        PageInfo<Strategy> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }


    @Override
    public List<Strategy> listIsThemeTourTwo() {
        Strategy strategyQuery = new Strategy();
        strategyQuery.setIsThemeTour("1");
        List<Strategy> queryList = baseMapper.pageQuery(strategyQuery);
        for (Strategy strategy : queryList) {
            List<String> routeNameList = routeStrategyMapper.getRouteNameByStrategyId(strategy.getId());
            for (String routeName : routeNameList) {
                strategy.setTitles(strategy.getTitles() == null? routeName+" " : strategy.getTitles() +routeName+" ");
            }
            strategy.setTitles(strategy.getTitle()+"  "+ strategy.getTitles());
        }

        return queryList;
    }

    @Override
    public Strategy getStrategyDetailsById(String id) {
        Strategy strategy = baseMapper.getById(id);
        List<String> routeNameList = routeStrategyMapper.getRouteNameByStrategyId(strategy.getId());
        for (String routeName : routeNameList) {
            strategy.setTitles(strategy.getTitles() == null? routeName+" " : strategy.getTitles() +routeName+" ");
        }
        strategy.setTitles(strategy.getTitle()+"  "+ strategy.getTitles());
        return strategy;
    }



    @Override
    public int save(Strategy data) {
        data.setCreated(Util.getTime());
        return baseMapper.insert(data);
    }

    @Override
    public int update(Strategy data) {
        return baseMapper.update(data);
    }

    @Override
    public List<Strategy> getAll() {
        return baseMapper.getAll();
    }

    @Override
    public void delete(String id) {
        baseMapper.delete(id);
    }

    @Override
    public Strategy getById(String id) {
        return  baseMapper.getById(id);
    }
}
