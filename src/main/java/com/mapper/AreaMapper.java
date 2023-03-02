package com.mapper;

import com.pojo.Area;

import java.util.List;


public interface AreaMapper {
    /**
     *
     * 列出区域列表
     * @return areaList
     */
    List<Area> queryArea();
}
