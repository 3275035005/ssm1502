package com.travel.web;
import com.travel.entity.Favorite;
import com.travel.entity.Route;
import com.travel.entity.User;
import com.travel.service.FavoriteService;
import com.travel.service.RouteService;
import com.travel.utils.PageResult;
import com.travel.utils.ResultSet;
import com.travel.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Strings;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 对用户端提供景点接口
 */
@RestController
@RequestMapping("/web/route")
public class RouteWebController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private FavoriteService favoriteService;

    /**
     * 分页条件查询景点信息
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("pageQuery/{page}/{limit}")
    public ResultSet pageQuery(@PathVariable int page,
                               @PathVariable int limit,
                               @RequestParam("rname") String rname){

        Route route = new Route();

        if(!StringUtils.isEmpty(rname) && !"null".equals(rname)){
            route.setRname(rname);
        }
        PageResult<Route> pageResult = routeService.pageQuery(route, page, limit);


        // 获取总条数
        long totalCount = pageResult.getTotal();

        // 页码数
        int totalPages = (int) Math.ceil((totalCount/5.0));
        List<Route> records = pageResult.getRows();




        return ResultSet.ok().data("rows",records)
                .data("totalCount",totalCount)
                .data("totalPages",totalPages)
                .data("startPage",(page*5-5))
                .data("pageSize",5);

    }


    /**
     * 分页条件查询热门景点统计排行信息
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("countPageQuery/{page}/{limit}")
    public ResultSet countPageQuery(@PathVariable int page,
                                    @PathVariable int limit,
                                    @RequestParam("rname") String rname){
        Route route = new Route();

        if(!StringUtils.isEmpty(rname) && !"null".equals(rname)){
            route.setRname(rname);
        }
        PageResult<Route> pageResult = routeService.pageQueryOrderByCount(route, page, limit);
        // 获取总条数
        long totalCount = pageResult.getTotal();

        // 页码数
        int totalPages = (int) Math.ceil((totalCount/10.0));
        List<Route> records = pageResult.getRows();

        return ResultSet.ok().data("rows",records)
                .data("totalCount",totalCount)
                .data("totalPages",totalPages)
                .data("startPage",(page*10-10))
                .data("pageSize",10);

    }


    /**
     * 查询推荐景点，优先根据喜好推荐
     * @return
     */
    @GetMapping("getThemeTour")
    public ResultSet getThemeTour(){

        List<Route> list = new ArrayList<>();


        HttpServletRequest request = ServletUtils.getRequest();
        User user = (User)request.getSession().getAttribute("user");
        if(user != null){
            Favorite favorite1 = new Favorite();
            favorite1.setUid(user.getId());
            List<Favorite> favorites = favoriteService.getByFavoriteList(favorite1);
            for (Favorite favorite : favorites) {
                list.add(routeService.getById(favorite.getRid()));
            }
        }


        Route route = new Route();
        route.setRouteIntroduce("1");
        route.setRflag("1");
        List<Route> routeList =  routeService.getRoute(route, list);

        list.addAll(routeList);

        return ResultSet.ok().data("rows", list);
    }

    /**
     * 查询景点详情
     * @param id
     * @return
     */
    @GetMapping("getRouteDetails/{id}")
    public ResultSet getRouteDetails(@PathVariable String id){
        Route route = routeService.getById(id);
        // 热度值+1
        route.setCount(route.getCount() +1);
        routeService.update(route);
        return ResultSet.ok().data("rows", route);
    }

}
