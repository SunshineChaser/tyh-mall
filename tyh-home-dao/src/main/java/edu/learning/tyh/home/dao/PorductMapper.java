package edu.learning.tyh.home.dao;

import java.util.List;

import edu.learning.tyh.pojo.Porduct;

public interface PorductMapper {
    int deleteByPrimaryKey(String pid);

    int insert(Porduct record);

    int insertSelective(Porduct record);

    Porduct selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(Porduct record);

    int updateByPrimaryKeyWithBLOBs(Porduct record);

    int updateByPrimaryKey(Porduct record);
    
    /**
     * 查询首推商品
     * @return
     */
    List<Porduct> selectProByFirst();
    
    /**
     * 按照分类查询商品列表
     * @param catid
     * @return
     */
    List<Porduct> selectByItemCat(String catid);
}