package edu.learning.tyh.manager.dao;

import java.util.List;

import edu.learning.tyh.pojo.ItemCat;

public interface ItemCatMapper {
    int deleteByPrimaryKey(String catid);

    int insert(ItemCat record);

    int insertSelective(ItemCat record);

    ItemCat selectByPrimaryKey(String catid);

    int updateByPrimaryKeySelective(ItemCat record);

    int updateByPrimaryKey(ItemCat record);
    
    List<ItemCat> selectAll();
}