package com.travel.web;

import com.travel.entity.Favorite;
import com.travel.entity.User;
import com.travel.service.FavoriteService;
import com.travel.service.RouteService;
import com.travel.utils.PageResult;
import com.travel.utils.ResultSet;
import com.travel.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 对用户端提供收藏接口
 * </p>
 */
@RestController
@RequestMapping("/web/favorite")
public class FavoriteWebController {



    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private RouteService routeService;



    /**
     * 收藏分页查询
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("pageQuery/{page}/{limit}")
    public ResultSet pageTeacherCondition(@PathVariable int page,
                                          @PathVariable int limit){

        String uId = "";
        HttpServletRequest request = ServletUtils.getRequest();
        User user = (User)request.getSession().getAttribute("user");
        if(user != null){
            uId = user.getId();
        }
        Favorite favorite1 = new Favorite();
        favorite1.setUid(uId);
        PageResult<Favorite> favoritePageResult = favoriteService.pageQuery(favorite1, page, limit);
        List<Favorite> rows = favoritePageResult.getRows();
        for (Favorite row : rows) {
            row.setRoute(routeService.getById(row.getRid()));
        }
        return ResultSet.ok().data("rows",rows).data("total",favoritePageResult.getTotal());

    }


    /**
     * 查询景点是否收藏
     * @param rid
     * @return
     */
    @GetMapping("getIsFavorite/{rid}")
    public ResultSet getIsFavorite(@PathVariable String rid){
        String uId = "";
        HttpServletRequest request = ServletUtils.getRequest();
        User user = (User)request.getSession().getAttribute("user");
        if(user != null){
            uId = user.getId();
        }
        Favorite favorite1 = new Favorite();
        favorite1.setUid(uId);
        favorite1.setRid(rid);
        Favorite favorite = favoriteService.getByFavorite(favorite1);
        if(favorite != null){
            return ResultSet.ok().success(true);
        }
        return ResultSet.ok().success(false);
    }


    /**
     * 收藏景点 和 取消收藏功能
     */
    @PostMapping("favoritesSwitch/{rid}")
    public ResultSet favoritesSwitch(@PathVariable String rid){
        HttpServletRequest request = ServletUtils.getRequest();
        User user = (User)request.getSession().getAttribute("user");

        Favorite favorite1 = new Favorite();
        favorite1.setUid(user.getId());
        favorite1.setRid(rid);
        Favorite favorite = favoriteService.getByFavorite(favorite1);

        if(favorite == null){ // 执行收藏
            favorite = new Favorite();
            favorite.setRid(rid);
            favorite.setUid(user.getId());
            favoriteService.save(favorite);
            return ResultSet.ok();
        }else{ // 取消收藏
            favoriteService.delete(favorite.getId());
            return ResultSet.error();
        }




    }

}

