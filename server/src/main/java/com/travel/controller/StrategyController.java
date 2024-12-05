package com.travel.controller;
import com.travel.entity.RouteStrategy;
import com.travel.entity.Strategy;
import com.travel.entity.query.StrategyQuery;
import com.travel.service.RouteStrategyService;
import com.travel.service.StrategyService;
import com.travel.utils.PageResult;
import com.travel.utils.ResultSet;
import com.travel.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 旅游攻略路线 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/strategy")
public class StrategyController {

    @Autowired
    private StrategyService strategyService;

    @Autowired
    private RouteStrategyService routeStrategyService;

    /**
     * 分页条件查询
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("pageQuery/{page}/{limit}")
    public ResultSet pageTeacherCondition(@PathVariable int page,
                                          @PathVariable int limit,
                                          @RequestBody Strategy strategy){

        PageResult pageResult = strategyService.pageQuery(strategy, page, limit);
        return ResultSet.ok().data("rows",pageResult.getRows()).data("total", pageResult.getTotal());

    }


    /**
     * 通过id删除
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public ResultSet delete(@PathVariable String id){
        strategyService.delete(id);
        // 删除景点攻略路线关联表
        routeStrategyService.deleteByStrategyId(id);
        return ResultSet.ok();
    }

    /**
     * 新增
     * @return
     */
    @PostMapping("save")
    public ResultSet save(@RequestBody Strategy strategy){
        String id = Util.getId();

        strategy.setId(id);
        strategyService.save(strategy);

        // 新增关联表数据
        for (String routeId : strategy.getRouteIdList()) {
            RouteStrategy routeStrategy = new RouteStrategy();
            routeStrategy.setRouteId(routeId);
            routeStrategy.setStrategyId(id);
            routeStrategyService.save(routeStrategy);
        }
        return ResultSet.ok();
    }


    /**
     * 修改
     * @return
     */
    @PutMapping("update")
    public ResultSet update(@RequestBody Strategy strategy){
        strategyService.update(strategy);

        // 删除数据
        routeStrategyService.deleteByStrategyId(strategy.getId());

        // 新增关联表数据
        for (String routeId : strategy.getRouteIdList()) {
            RouteStrategy routeStrategy = new RouteStrategy();
            routeStrategy.setRouteId(routeId);
            routeStrategy.setStrategyId(strategy.getId());
            routeStrategyService.save(routeStrategy);
        }
        return ResultSet.ok();
    }
}

