package com.travel.controller;
import com.travel.entity.Favorite;
import com.travel.service.FavoriteService;
import com.travel.utils.PageResult;
import com.travel.utils.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 收藏表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/favorite")
public class FavoriteController {



    @Autowired
    private FavoriteService FavoriteService;



    /**
     * 分页条件查询
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("pageQuery/{page}/{limit}")
    public ResultSet pageTeacherCondition(@PathVariable int page,
                                          @PathVariable int limit,
                                          @RequestBody Favorite favorite){
        PageResult<Favorite> favoritePageResult = FavoriteService.pageQuery(favorite, page, limit);



        return ResultSet.ok().data("rows",favoritePageResult.getRows()).data("total",favoritePageResult.getTotal());

    }


    /**
     * 通过id删除
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public ResultSet delete(@PathVariable String id){
        FavoriteService.delete(id);
        return ResultSet.ok();
    }

}

