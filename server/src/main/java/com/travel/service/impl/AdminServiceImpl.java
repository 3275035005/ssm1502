package com.travel.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.entity.Admin;
import com.travel.mapper.AdminMapper;
import com.travel.service.AdminService;
import com.travel.utils.PageResult;
import com.travel.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public PageResult<Admin> pageQuery(Admin data, int current, int size) {
        PageHelper.startPage(current, size);
        List<Admin> queryList = adminMapper.pageQuery(data);
        PageInfo<Admin> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public int save(Admin data) {
        data.setId(Util.getId());
        data.setCreated(Util.getTime());
        return adminMapper.insert(data);
    }

    @Override
    public int update(Admin data) {
        return adminMapper.update(data);
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.getAll();
    }

    @Override
    public void delete(String id) {
        adminMapper.delete(id);
    }

    @Override
    public Admin getById(String id) {
        return adminMapper.getById(id);
    }


    @Override
    public Admin getByAdmin(Admin adminQuery) {
        return adminMapper.getByAdmin(adminQuery);
    }
}
