package com.travel.controller;


import com.travel.entity.SuggestComplaint;
import com.travel.entity.query.SuggestComplaintQuery;
import com.travel.service.SuggestComplaintService;
import com.travel.utils.PageResult;
import com.travel.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 景点建议和投诉表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/suggest-complaint")
public class SuggestComplaintController {


    @Autowired
    private SuggestComplaintService suggestComplaintService;

    /**
     * 分页条件查询
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("pageQuery/{page}/{limit}")
    public ResultSet pageTeacherCondition(@PathVariable int page,
                                          @PathVariable int limit,
                                          @RequestBody SuggestComplaint suggestComplaint){


        PageResult pageResult = suggestComplaintService.pageQuery(suggestComplaint, page, limit);
        return ResultSet.ok().data("rows", pageResult.getRows()).data("total", pageResult.getTotal());

    }


    /**
     * 通过id删除
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public ResultSet delete(@PathVariable String id){
        suggestComplaintService.delete(id);
        return ResultSet.ok();
    }
}

