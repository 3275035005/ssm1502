package com.travel.controller;
import com.travel.entity.Leave;
import com.travel.entity.User;
import com.travel.entity.query.LeaveQuery;
import com.travel.service.LeaveService;
import com.travel.service.UserService;
import com.travel.utils.PageResult;
import com.travel.utils.ResultSet;
import com.travel.utils.Util;
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
@RequestMapping("/leave")
public class LeaveController {


    @Autowired
    private LeaveService leaveService;

    /**
     * 分页条件查询
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("pageQuery/{page}/{limit}")
    public ResultSet pageTeacherCondition(@PathVariable int page,
                                          @PathVariable int limit,
                                          @RequestBody Leave leave){
        PageResult<Leave> leavePageResult = leaveService.pageQuery(leave, page, limit);

        return ResultSet.ok().data("rows",leavePageResult.getRows()).data("total",leavePageResult.getTotal());

    }


    /**
     * 通过id删除
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public ResultSet delete(@PathVariable String id){
        leaveService.delete(id);
        return ResultSet.ok();
    }

    /**
     * 回复留言
     * @return
     */
    @PutMapping("update")
    public ResultSet update(@RequestBody Leave Leave){
        Leave.setAcreated(Util.getTime());
        leaveService.update(Leave);
        return ResultSet.ok();
    }
}

