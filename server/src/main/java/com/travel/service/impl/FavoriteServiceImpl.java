package com.travel.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.travel.entity.Favorite;
import com.travel.mapper.FavoriteMapper;
import com.travel.service.FavoriteService;
import com.travel.utils.PageResult;
import com.travel.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;


    @Override
    public PageResult<Favorite> pageQuery(Favorite data, int current, int size) {
        PageHelper.startPage(current, size);
        List<Favorite> queryList = favoriteMapper.pageQuery(data);
        PageInfo<Favorite> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public int save(Favorite data) {
        data.setId(Util.getId());
        data.setCreated(Util.getTime());
        return favoriteMapper.insert(data);
    }

    @Override
    public int update(Favorite data) {
        return favoriteMapper.update(data);
    }

    @Override
    public List<Favorite> getAll() {
        return favoriteMapper.getAll();
    }

    @Override
    public void delete(String id) {
        favoriteMapper.delete(id);
    }

    @Override
    public Favorite getById(String id) {
        return favoriteMapper.getById(id);
    }

    @Override
    public Favorite getByFavorite(Favorite favorite) {
        return favoriteMapper.getByFavorite(favorite);
    }

    @Override
    public List<Favorite> getByFavoriteList(Favorite favorite1) {
        return favoriteMapper.getByFavoriteList(favorite1);
    }
}
