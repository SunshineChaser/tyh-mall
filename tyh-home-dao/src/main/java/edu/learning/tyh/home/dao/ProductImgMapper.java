package edu.learning.tyh.home.dao;

import edu.learning.tyh.pojo.ProductImg;

public interface ProductImgMapper {
    int deleteByPrimaryKey(String pimgid);

    int insert(ProductImg record);

    int insertSelective(ProductImg record);

    ProductImg selectByPrimaryKey(String pimgid);

    int updateByPrimaryKeySelective(ProductImg record);

    int updateByPrimaryKey(ProductImg record);
}