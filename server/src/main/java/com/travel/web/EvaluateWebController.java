package com.travel.web;

import com.travel.entity.Evaluate;
import com.travel.entity.User;
import com.travel.service.EvaluateService;
import com.travel.service.UserService;
import com.travel.utils.ResultSet;
import com.travel.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 对用户端提供评价接口
 * </p>
 */
@RestController
@RequestMapping("/web/evaluate")
public class EvaluateWebController {

    @Autowired
    private EvaluateService evaluateService;


    @Autowired
    private UserService userService;


    /**
     * 查询所有评价
     * @return
     */
    @GetMapping("getList/{rid}")
    public ResultSet getList(@PathVariable String rid){
        List<Evaluate> list = evaluateService.getByRid(rid);

        for (Evaluate evaluate : list) {
            User user = userService.getById(evaluate.getUid());
            evaluate.setUserName(user.getName());
        }
        return ResultSet.ok().data("rows", list);
    }

    /**
     * 发布留言
     * @param content
     * @return
     */
    @PostMapping("saveEvaluate")
    public ResultSet saveLeave(@RequestParam("content") String content, @RequestParam("rid") String rid){
        String uId = "";
        HttpServletRequest request = ServletUtils.getRequest();
        User user = (User)request.getSession().getAttribute("user");
        if(user != null){
            uId = user.getId();
        }
        Evaluate evaluate = new Evaluate();
        evaluate.setUid(uId);
        evaluate.setRid(rid);
        evaluate.setContent(content);
        evaluateService.save(evaluate);
        return ResultSet.ok();
    }
}
