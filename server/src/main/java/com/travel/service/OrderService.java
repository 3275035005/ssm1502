package com.travel.service;

import com.travel.entity.Order;
import com.travel.entity.query.OrderQuery;
import com.travel.utils.PageResult;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 */
public interface OrderService extends BaseService<Order> {


    List<Order> getOrderByUid(String uid);

    Order getByOrder(Order orderQuery);

}
