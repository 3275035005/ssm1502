package com.travel.web;

import com.travel.entity.Strategy;
import com.travel.entity.query.StrategyQuery;
import com.travel.service.StrategyService;
import com.travel.utils.PageResult;
import com.travel.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对用户端提供旅游攻略路线接口
 */
@RestController
@RequestMapping("/web/strategy")
public class StrategyWebController {

    @Autowired
    private StrategyService strategyService;

    /**
     * 分页条件查询旅游攻略路线接口信息
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("pageQuery/{page}/{limit}")
    public ResultSet pageQuery(@PathVariable int page,
                               @PathVariable int limit){
        PageResult pageResult = strategyService.pageQuery(new Strategy(), page, limit);
        // 获取总条数
        long totalCount = pageResult.getTotal();

        // 页码数
        int totalPages = (int) Math.ceil((totalCount/5.0));
        List<Strategy> records = pageResult.getRows();

        return ResultSet.ok().data("rows",records)
                .data("totalCount",totalCount)
                .data("totalPages",totalPages)
                .data("startPage",(page*5-5))
                .data("pageSize",5);

    }

    /**
     * 查询推荐旅游攻略路线接口
     * @return
     */
    @GetMapping("getThemeTour")
    public ResultSet getThemeTour(){
        return ResultSet.ok().data("rows", strategyService.listIsThemeTourTwo());
    }

    /**
     * 查询旅游攻略路线接口详情
     * @param id
     * @return
     */
    @GetMapping("getStrategyDetails/{id}")
    public ResultSet getStrategyDetails(@PathVariable String id){
        Strategy strategy = strategyService.getStrategyDetailsById(id);
        return ResultSet.ok().data("rows", strategy);
    }

}
