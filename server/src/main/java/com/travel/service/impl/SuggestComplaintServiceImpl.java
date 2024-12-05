package com.travel.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.entity.SuggestComplaint;
import com.travel.entity.query.SuggestComplaintQuery;
import com.travel.mapper.SuggestComplaintMapper;
import com.travel.service.SuggestComplaintService;
import com.travel.utils.PageResult;
import com.travel.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 景点建议投诉表 服务实现类
 * </p>
 */
@Service
public class SuggestComplaintServiceImpl  implements SuggestComplaintService {


    @Autowired
    private SuggestComplaintMapper baseMapper;


    @Override
    public PageResult<SuggestComplaint> pageQuery(SuggestComplaint data, int current, int size) {
        PageHelper.startPage(current, size);
        List<SuggestComplaint> queryList = baseMapper.pageQuery(data);
        PageInfo<SuggestComplaint> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public int save(SuggestComplaint data) {
        data.setId(Util.getId());
        data.setCreated(Util.getTime());
        return baseMapper.insert(data);
    }

    @Override
    public int update(SuggestComplaint data) {
        return baseMapper.update(data);
    }

    @Override
    public List<SuggestComplaint> getAll() {
        return baseMapper.getAll();
    }

    @Override
    public void delete(String id) {
        baseMapper.delete(id);
    }

    @Override
    public SuggestComplaint getById(String id) {
        return  baseMapper.getById(id);
    }

}
