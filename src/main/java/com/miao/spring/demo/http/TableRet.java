package com.miao.spring.demo.http;

import lombok.Data;

import java.util.List;

@Data
public class TableRet<T> {

    private int total;

    private int startRow;

    private int endRow;

    private boolean pageable;

    private Integer pageSize;

    private Integer pageNum;

    private Integer totalPage;

    private List<T> rows;

}
