package com.travel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {


    /**
     * 默认跳转首页
     * @param request
     * @return
     */
    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        return "/index.html";
    }


    /**
     * 退出
     */
    @RequestMapping("/out")
    public String out(HttpServletRequest request, HttpServletResponse response)  {
        request.getSession().invalidate();
        return "index.html";
    }
}
