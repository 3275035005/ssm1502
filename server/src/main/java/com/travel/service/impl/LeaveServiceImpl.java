package com.travel.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.entity.Leave;
import com.travel.mapper.LeaveMapper;
import com.travel.service.LeaveService;
import com.travel.utils.PageResult;
import com.travel.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 留言表 服务实现类
 * </p>
 *
 */
@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveMapper leaveMapper;


    @Override
    public PageResult<Leave> pageQuery(Leave data, int current, int size) {
        PageHelper.startPage(current, size);
        List<Leave> queryList = leaveMapper.pageQuery(data);
        PageInfo<Leave> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public int save(Leave data) {
        data.setId(Util.getId());
        data.setCreated(Util.getTime());
        return leaveMapper.insert(data);
    }

    @Override
    public int update(Leave data) {
        return leaveMapper.update(data);
    }

    @Override
    public List<Leave> getAll() {
        return leaveMapper.getAll();
    }

    @Override
    public void delete(String id) {
        leaveMapper.delete(id);
    }

    @Override
    public Leave getById(String id) {
        return leaveMapper.getById(id);
    }

    @Override
    public List<Leave> getByUid(String uId) {
        return leaveMapper.getByUid(uId);
    }
}
