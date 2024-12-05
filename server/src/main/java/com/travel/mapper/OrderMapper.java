package com.travel.mapper;

import com.travel.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> getOrderByUid(@Param("uid") String uid);

    Order getByOrder(Order orderQuery);

}
