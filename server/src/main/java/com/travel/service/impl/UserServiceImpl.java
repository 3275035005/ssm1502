package com.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.entity.SuggestComplaint;
import com.travel.entity.User;
import com.travel.mapper.UserMapper;
import com.travel.service.UserService;
import com.travel.utils.PageResult;
import com.travel.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper baseMapper;

    @Override
    public PageResult<User> pageQuery(User data, int current, int size) {
        PageHelper.startPage(current, size);
        List<User> queryList = baseMapper.pageQuery(data);
        PageInfo<User> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public int save(User data) {
        data.setId(Util.getId());
        data.setCreated(Util.getTime());
        return baseMapper.insert(data);
    }

    @Override
    public int update(User data) {
        return baseMapper.update(data);
    }

    @Override
    public List<User> getAll() {
        return baseMapper.getAll();
    }

    @Override
    public void delete(String id) {
        baseMapper.delete(id);
    }

    @Override
    public User getById(String id) {
        return baseMapper.getById(id);
    }

    @Override
    public User getOne(User user) {
        return baseMapper.getOne(user);
    }
}
