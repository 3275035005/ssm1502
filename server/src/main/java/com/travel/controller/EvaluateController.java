package com.travel.controller;

import com.travel.entity.*;
import com.travel.entity.query.EvaluateQuery;
import com.travel.service.EvaluateService;
import com.travel.service.RouteService;
import com.travel.service.UserService;
import com.travel.utils.PageResult;
import com.travel.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 留言表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/evaluate")
public class EvaluateController {


    @Autowired
    private EvaluateService evaluateService;

    /**
     * 分页条件查询
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("pageQuery/{page}/{limit}")
    public ResultSet pageQuery(@PathVariable int page,
                               @PathVariable int limit,
                               @RequestBody Evaluate evaluate){

        PageResult<Evaluate> evaluatePageResult = evaluateService.pageQuery(evaluate, page, limit);
        return ResultSet.ok().data("rows",evaluatePageResult.getRows()).data("total",evaluatePageResult.getTotal());

    }


    /**
     * 通过id删除
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public ResultSet delete(@PathVariable String id){
        evaluateService.delete(id);
        return ResultSet.ok();
    }

}

