package edu.learning.tyh.manager.dao;

import java.util.List;

import edu.learning.tyh.manager.dto.ProductDto;
import edu.learning.tyh.pojo.Porduct;

public interface PorductMapper {
    int deleteByPrimaryKey(String pid);

    int insert(Porduct record);

    int insertSelective(Porduct record);

    Porduct selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(Porduct record);

    int updateByPrimaryKeyWithBLOBs(Porduct record);

    int updateByPrimaryKey(Porduct record);
    
    List<ProductDto> selectProductList(Porduct record);
}