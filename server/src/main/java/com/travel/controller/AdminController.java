package com.travel.controller;

import com.travel.entity.Admin;
import com.travel.service.AdminService;
import com.travel.utils.PageResult;
import com.travel.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    /**
     * 分页条件查询
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("pageQuery/{page}/{limit}")
    public ResultSet pageTeacherCondition(@PathVariable int page,
                                          @PathVariable int limit,
                                          @RequestBody Admin admin){
        PageResult<Admin> pageResult = adminService.pageQuery(admin, page, limit);

        return ResultSet.ok().data("rows",pageResult.getRows()).data("total",pageResult.getTotal());
    }


    /**
     * 通过id删除
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public ResultSet delete(@PathVariable String id){
        adminService.delete(id);
        return ResultSet.ok();
    }

    /**
     * 新增
     * @return
     */
    @PostMapping("save")
    public ResultSet save(@RequestBody Admin Admin){

        adminService.save(Admin);
        return ResultSet.ok();
    }

    /**
     * 修改
     * @param Admin
     * @return
     */
    @PutMapping("update")
    public ResultSet update(@RequestBody Admin Admin){
        adminService.update(Admin);
        return ResultSet.ok();
    }



    @GetMapping("getInfo/{id}")
    public ResultSet getInfo(@PathVariable String id){
        Admin byId = adminService.getById(id);
        return ResultSet.ok().data("rows", byId);
    }

}

