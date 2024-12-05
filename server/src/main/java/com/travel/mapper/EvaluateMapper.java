package com.travel.mapper;

import com.travel.entity.Evaluate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluateMapper extends BaseMapper<Evaluate> {


    List<Evaluate> getByRid(String rid);

}
