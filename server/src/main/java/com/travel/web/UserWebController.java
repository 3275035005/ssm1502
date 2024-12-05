package com.travel.web;

import com.travel.entity.User;
import com.travel.service.UserService;
import com.travel.utils.ResultSet;
import com.travel.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 对用户端提供用户表接口
 */
@RestController
@RequestMapping("/web/user")
public class UserWebController {

    @Autowired
    private UserService userService;

    /**
     * 获取登录用户信息
     * @param request
     * @return
     */
    @GetMapping("find")
    public ResultSet find(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return ResultSet.ok().success(false);
        }else{
            User byId = userService.getById(user.getId());

            return ResultSet.ok().success(true).data("rows", byId);
        }
    }



    /**
     * 登录功能
     * @param user
     * @return
     */
    @PostMapping("login")
    public ResultSet login(User user){
        User userOne = userService.getOne(user);
        if(userOne == null){
            return ResultSet.error("账号或密码不正确!");
        }
        String status = userOne.getStatus();
        if("0".equals(status)){
            return ResultSet.error("账号已禁用!");
        }

        HttpServletRequest request = ServletUtils.getRequest();
        request.getSession().setAttribute("user", userOne);
       return ResultSet.ok();
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("register")
    public ResultSet sign(User user){
        User userQuery = new User();
        userQuery.setUsername(user.getUsername());
        User userOne = userService.getOne(user);
        if(userOne != null){
            return ResultSet.error("账号已被注册");
        }
        user.setStatus("1");
        userService.save(user);
        return ResultSet.ok();
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PostMapping("updateUser")
    public ResultSet updateUser(User user){

        userService.update(user);
        return ResultSet.ok();
    }

}
