package com.travel.controller;


import com.travel.entity.Order;
import com.travel.service.OrderService;
import com.travel.utils.PageResult;
import com.travel.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private  OrderService orderService;

    /**
     * 分页条件查询
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("pageQuery/{page}/{limit}")
    public ResultSet pageTeacherCondition(@PathVariable int page,
                                          @PathVariable int limit,
                                          @RequestBody Order order){

        PageResult pageResult = orderService.pageQuery(order, page, limit);
        return ResultSet.ok().data("rows",pageResult.getRows()).data("total", pageResult.getTotal());
    }


    /**
     * 通过id删除
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public ResultSet delete(@PathVariable String id){
        orderService.delete(id);
        return ResultSet.ok();
    }



    /**
     * 处理订单
     * @return
     */
    @PutMapping("update")
    public ResultSet update(@RequestBody Order order){
        orderService.update(order);
        return ResultSet.ok();
    }

}

