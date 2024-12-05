package com.travel.controller;


import com.travel.entity.Route;
import com.travel.service.RouteService;
import com.travel.utils.PageResult;
import com.travel.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  景点信息管理
 * </p>
 *
 */
@RestController
@RequestMapping("/route")
public class RouteController {


    @Autowired
    private RouteService routeService;

    /**
     * 分页条件查询
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("pageQuery/{page}/{limit}")
    public ResultSet pageTeacherCondition(@PathVariable int page,
                                          @PathVariable int limit,
                                          @RequestBody Route route){

        PageResult<Route> pageResult = routeService.pageQuery(route, page, limit);
        return ResultSet.ok().data("rows",pageResult.getRows()).data("total",pageResult.getTotal());

    }


    /**
     * 通过id删除
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public ResultSet delete(@PathVariable String id){
        routeService.delete(id);
        return ResultSet.ok();
    }

    /**
     * 新增
     * @return
     */
    @PostMapping("save")
    public ResultSet save(@RequestBody Route Route){

        routeService.save(Route);
        return ResultSet.ok();
    }

    /**
     * 修改
     * @param Route
     * @return
     */
    @PutMapping("update")
    public ResultSet update(@RequestBody Route Route){
        routeService.update(Route);
        return ResultSet.ok();
    }


    /**
     * 查询所有景点信息
     * @return
     */
    @GetMapping("getRouteAll")
    public ResultSet getRouteAll(){
        return ResultSet.ok().data("rows", routeService.getAll());
    }
}

