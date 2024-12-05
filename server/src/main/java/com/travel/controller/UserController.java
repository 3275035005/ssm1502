package com.travel.controller;

import com.travel.entity.User;
import com.travel.service.UserService;
import com.travel.utils.PageResult;
import com.travel.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    /**
     * 分页条件查询
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("pageQuery/{page}/{limit}")
    public ResultSet pageTeacherCondition(@PathVariable int page,
                                          @PathVariable int limit,
                                          @RequestBody User user){
        PageResult<User> userPageResult = userService.pageQuery(user, page, limit);


        return ResultSet.ok().data("rows",userPageResult.getRows()).data("total",userPageResult.getTotal());

    }


    /**
     * 通过id删除
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public ResultSet delete(@PathVariable String id){
        userService.delete(id);
        return ResultSet.ok();
    }

    /**
     * 新增
     * @return
     */
    @PostMapping("save")
    public ResultSet save(@RequestBody User user){

        userService.save(user);
        return ResultSet.ok();
    }

    /**
     * 修改
     * @param user
     * @return
     */
    @PutMapping("update")
    public ResultSet update(@RequestBody User user){
        userService.update(user);
        return ResultSet.ok();
    }

    /**
     * 查询所有用户
     * @return
     */
    @GetMapping("getUserAll")
    public ResultSet getUserAll(){
        return ResultSet.ok().data("rows", userService.getAll());
    }
}

