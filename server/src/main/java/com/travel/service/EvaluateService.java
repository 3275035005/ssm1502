package com.travel.service;

import com.travel.service.BaseService;
import com.travel.entity.Evaluate;

import java.util.List;

public interface EvaluateService extends BaseService<Evaluate> {

    /**
     * 通过景点id查询评论数据
     * @param rid
     * @return
     */
    List<Evaluate> getByRid(String rid);
}
