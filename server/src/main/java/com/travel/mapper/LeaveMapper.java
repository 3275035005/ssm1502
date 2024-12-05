package com.travel.mapper;

import com.travel.entity.Leave;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 留言表 Mapper 接口
 * </p>
 *
 */
@Repository
public interface LeaveMapper extends BaseMapper<Leave> {

    List<Leave> getByUid(@Param("uid") String uId);
}
