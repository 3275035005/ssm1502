package com.travel.mapper;

import com.travel.entity.Favorite;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 收藏表 Mapper 接口
 * </p>
 *
 */
@Repository
public interface FavoriteMapper extends BaseMapper<Favorite> {

    Favorite getByFavorite(Favorite favorite);

    List<Favorite> getByFavoriteList(Favorite favorite1);

}
