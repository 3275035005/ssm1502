package com.travel.mapper;

import com.travel.entity.SuggestComplaint;
import com.travel.entity.query.SuggestComplaintQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 景点建议投诉表 Mapper 接口
 * </p>
 */
@Repository
public interface SuggestComplaintMapper extends BaseMapper<SuggestComplaint> {

    List<SuggestComplaint> pageQuery(SuggestComplaintQuery suggestComplaintQuery);

}
