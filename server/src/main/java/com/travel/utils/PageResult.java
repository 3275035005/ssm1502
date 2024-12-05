package com.travel.utils;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private long total;

    private List<T> rows;

    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

}
