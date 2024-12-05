package com.travel.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.entity.Announcement;
import com.travel.mapper.AnnouncementMapper;
import com.travel.service.AnnouncementService;
import com.travel.utils.PageResult;
import com.travel.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 公告表 服务实现类
 * </p>
 *
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public PageResult<Announcement> pageQuery(Announcement data, int current, int size) {
        PageHelper.startPage(current, size);
        List<Announcement> queryList = announcementMapper.pageQuery(data);
        PageInfo<Announcement> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public int save(Announcement data) {
        data.setId(Util.getId());
        data.setCreated(Util.getTime());
        return announcementMapper.insert(data);
    }

    @Override
    public int update(Announcement data) {
        return announcementMapper.update(data);
    }

    @Override
    public List<Announcement> getAll() {
        return announcementMapper.getAll();
    }

    @Override
    public void delete(String id) {
        announcementMapper.delete(id);
    }

    @Override
    public Announcement getById(String id) {
        return announcementMapper.getById(id);
    }
}
