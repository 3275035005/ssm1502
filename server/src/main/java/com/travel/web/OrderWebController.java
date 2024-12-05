package com.travel.web;

import com.travel.entity.Order;
import com.travel.entity.Route;
import com.travel.entity.User;
import com.travel.service.OrderService;
import com.travel.service.RouteService;
import com.travel.utils.ResultSet;
import com.travel.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 对用户端提供订单接口
 */
@RestController
@RequestMapping("/web/order")
public class OrderWebController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RouteService routeService;

    /**
     * 提交订单
     * @param orderNew
     * @return
     */
    @PostMapping("sendOrder")
    public ResultSet sendOrder(Order orderNew){
        HttpServletRequest request = ServletUtils.getRequest();
        User user = (User)request.getSession().getAttribute("user");
        if(user != null){
            orderNew.setUid(user.getId());
        }
        Order orderQuery = new Order();
        orderQuery.setStatus("0");
        orderQuery.setRid(orderNew.getRid());
        orderQuery.setUid(orderNew.getUid());
        Order order = orderService.getByOrder(orderQuery);

        if(order != null){
            return ResultSet.error("您已经购票成功了，无法重复购买！");
        }
        Route route = routeService.getById(orderNew.getRid());

        orderNew.setPrice(route.getPrice());
        orderNew.setStatus("0");
        orderService.save(orderNew);
        return ResultSet.ok().message("购票成功");
    }


    /**
     * 查询我的订单
     * @return
     */
    @GetMapping("getOrderByUid")
    public ResultSet getOrderByUid(){
        HttpServletRequest request = ServletUtils.getRequest();
        User user = (User)request.getSession().getAttribute("user");
        String uid = "";
        if(user != null){
            uid = user.getId();
        }
        List<Order> order = orderService.getOrderByUid(uid);
        return ResultSet.ok().data("data", order);
    }
    /**
     * 取消订单
     * @return
     */
    @PostMapping("getCancelOrder/{id}")
    public ResultSet getCancelOrder(@PathVariable String id){
        Order order = new Order();
        order.setStatus("1");
        order.setId(id);
        orderService.update(order);
        return ResultSet.ok();
    }

}
