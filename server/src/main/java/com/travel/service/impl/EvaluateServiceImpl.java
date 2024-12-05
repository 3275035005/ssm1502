package com.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.entity.Evaluate;
import com.travel.mapper.EvaluateMapper;
import com.travel.service.EvaluateService;
import com.travel.utils.PageResult;
import com.travel.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluateServiceImpl implements EvaluateService {


    @Autowired
    private EvaluateMapper evaluateMapper;


    @Override
    public PageResult<Evaluate> pageQuery(Evaluate data, int current, int size) {
        PageHelper.startPage(current, size);
        List<Evaluate> queryList = evaluateMapper.pageQuery(data);
        PageInfo<Evaluate> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public int save(Evaluate data) {
        data.setId(Util.getId());
        data.setCreated(Util.getTime());
       return evaluateMapper.insert(data);
    }

    @Override
    public int update(Evaluate data) {
        return evaluateMapper.update(data);
    }

    @Override
    public List<Evaluate> getAll() {
        return evaluateMapper.getAll( );
    }

    @Override
    public void delete(String id) {
        evaluateMapper.delete(id);
    }

    @Override
    public Evaluate getById(String id) {
        return evaluateMapper.getById(id);
    }

    @Override
    public List<Evaluate> getByRid(String rid) {
        return evaluateMapper.getByRid(rid);
    }
}
