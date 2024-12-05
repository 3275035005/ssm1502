package com.travel.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.entity.Order;
import com.travel.entity.query.OrderQuery;
import com.travel.mapper.OrderMapper;
import com.travel.service.OrderService;
import com.travel.utils.PageResult;
import com.travel.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper baseMapper;


    @Override
    public PageResult pageQuery(Order order, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Order> queryList = baseMapper.pageQuery(order);
        PageInfo<Order> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public List<Order> getOrderByUid(String uid) {
        return baseMapper.getOrderByUid(uid);
    }

    @Override
    public Order getByOrder(Order orderQuery) {
        return baseMapper.getByOrder(orderQuery);
    }

    @Override
    public int save(Order data) {
        data.setId(Util.getId());
        data.setCreated(Util.getTime());
        return baseMapper.insert(data);
    }

    @Override
    public int update(Order data) {
        return baseMapper.update(data);
    }

    @Override
    public List<Order> getAll() {
        return baseMapper.getAll();
    }

    @Override
    public void delete(String id) {
        baseMapper.delete(id);
    }

    @Override
    public Order getById(String id) {
        return baseMapper.getById(id);
    }
}
