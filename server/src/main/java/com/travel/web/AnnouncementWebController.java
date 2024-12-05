package com.travel.web;


import com.travel.entity.Announcement;
import com.travel.service.AnnouncementService;
import com.travel.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 对用户端提供公告接口
 * </p>
 *
 */
@RestController
@RequestMapping("web/announcement")
public class AnnouncementWebController {



    @Autowired
    private AnnouncementService announcementService;

    /**
     * 查询所有公告
     * @return
     */
    @GetMapping("getList")
    public ResultSet getList(){
        List<Announcement> list = announcementService.getAll();
        return ResultSet.ok().data("rows", list);
    }
}

