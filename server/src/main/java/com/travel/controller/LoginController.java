package com.travel.controller;

import com.travel.entity.Admin;
import com.travel.service.AdminService;
import com.travel.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping
public class LoginController {


    @Autowired
    private AdminService adminService;

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("login")
    public ResultSet login(@RequestBody Map<String, String> map) {
        Admin adminQuery = new Admin();
        adminQuery.setAccount(map.get("account"));
        adminQuery.setPassword(map.get("password"));

        Admin admin = adminService.getByAdmin(adminQuery);
        if (admin == null) {
            return ResultSet.error("账号或密码不正确");
        }
        if ("0".equals(admin.getStatus())) {
            return ResultSet.error("账号已停用");
        }
        return ResultSet.ok().data("row", admin);

    }
}
