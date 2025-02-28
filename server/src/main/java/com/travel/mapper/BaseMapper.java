package com.travel.mapper;

import java.util.List;

public interface BaseMapper<T> {

   List<T> pageQuery(T data);


   int insert(T data);

   int update(T data);

   List<T> getAll();

   void delete(String id);

   T getById(String id);
}
