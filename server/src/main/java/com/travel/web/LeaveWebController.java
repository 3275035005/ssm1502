package com.travel.web;


import com.travel.entity.Leave;
import com.travel.entity.User;
import com.travel.service.LeaveService;
import com.travel.utils.ResultSet;
import com.travel.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 留言表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("web/leave")
public class LeaveWebController {


    @Autowired
    private LeaveService leaveService;

    /**
     * 查询所有留言
     * @return
     */
    @GetMapping("getList")
    public ResultSet getList(){
        String uId = "";
        HttpServletRequest request = ServletUtils.getRequest();
        User user = (User)request.getSession().getAttribute("user");
        if(user != null){
            uId = user.getId();
        }
        List<Leave> list = leaveService.getByUid(uId);
        return ResultSet.ok().data("rows", list);
    }

    /**
     * 发布留言
     * @param content
     * @return
     */
    @PostMapping("saveLeave")
    public ResultSet saveLeave(@RequestParam("content") String content){
        String uId = "";
        HttpServletRequest request = ServletUtils.getRequest();
        User user = (User)request.getSession().getAttribute("user");
        if(user != null){
            uId = user.getId();
        }

        Leave leave = new Leave();
        leave.setUcontent(content);
        leave.setAcontent("");
        leave.setAcreated("");
        leave.setUid(uId);
        leaveService.save(leave);
        return ResultSet.ok();
    }
}

