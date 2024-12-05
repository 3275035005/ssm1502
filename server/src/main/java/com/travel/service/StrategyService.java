package com.travel.service;

import com.travel.service.BaseService;
import com.travel.entity.Strategy;
import com.travel.entity.query.StrategyQuery;
import com.travel.utils.PageResult;

import java.util.List;

/**
 * <p>
 * 旅游攻略路线 服务类
 * </p>
 */
public interface StrategyService extends BaseService<Strategy> {



    List<Strategy> listIsThemeTourTwo();

    Strategy getStrategyDetailsById(String id);
}
