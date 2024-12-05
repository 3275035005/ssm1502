package com.travel.controller;

import com.travel.entity.Announcement;
import com.travel.entity.query.AnnouncementQuery;
import com.travel.service.AnnouncementService;
import com.travel.utils.PageResult;
import com.travel.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 公告表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {



    @Autowired
    private AnnouncementService announcementService;

    /**
     * 分页条件查询
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("pageQuery/{page}/{limit}")
    public ResultSet pageTeacherCondition(@PathVariable int page,
                                          @PathVariable int limit,
                                          @RequestBody Announcement announcement){
        PageResult<Announcement> announcementPageResult = announcementService.pageQuery(announcement, page, limit);
        return ResultSet.ok().data("rows",announcementPageResult.getRows()).data("total",announcementPageResult.getTotal());

    }


    /**
     * 通过id删除
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public ResultSet delete(@PathVariable String id){
        announcementService.delete(id);
        return ResultSet.ok();
    }

    /**
     * 新增
     * @return
     */
    @PostMapping("save")
    public ResultSet save(@RequestBody Announcement Announcement){
        announcementService.save(Announcement);
        return ResultSet.ok();
    }


    /**
     * 修改
     * @return
     */
    @PutMapping("update")
    public ResultSet update(@RequestBody Announcement Announcement){
        announcementService.update(Announcement);
        return ResultSet.ok();
    }

}

