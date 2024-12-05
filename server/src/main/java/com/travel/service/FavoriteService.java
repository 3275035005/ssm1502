package com.travel.service;

import com.travel.service.BaseService;
import com.travel.entity.Favorite;

import java.util.List;

/**
 * <p>
 * 收藏表 服务类
 * </p>
 *
 */
public interface FavoriteService extends BaseService<Favorite> {

    Favorite getByFavorite(Favorite favorite);

    List<Favorite> getByFavoriteList(Favorite favorite1);

}
