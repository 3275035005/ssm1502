package com.travel.web;

import com.travel.entity.SuggestComplaint;
import com.travel.entity.User;
import com.travel.service.SuggestComplaintService;
import com.travel.utils.ResultSet;
import com.travel.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 对用户端提供建议和投诉接口
 */
@RestController
@RequestMapping("/web/suggestComplaint")
public class SuggestComplaintWebController {

    @Autowired
    private SuggestComplaintService suggestComplaintService;


    /**
     * 提交投诉和建议
     * @param suggestComplaint
     * @return
     */
    @PostMapping("sendSuggestComplaint")
    public ResultSet sendSuggestComplaint(SuggestComplaint suggestComplaint){
        HttpServletRequest request = ServletUtils.getRequest();
        User user = (User)request.getSession().getAttribute("user");
        if(user != null){
            suggestComplaint.setUid(user.getId());
        }
        suggestComplaintService.save(suggestComplaint);
        return ResultSet.ok();
    }

}
