package edu.learning.tyh.home.interfaces;

import java.util.List;

import edu.learning.tyh.pojo.ItemCat;
import edu.learning.tyh.pojo.Porduct;

public interface ProductService {

	/**
     * 查询首推商品
     * @return
     */
    List<Porduct> selectProByFirst();
    
    /**
     * 查询所有可用的商品类型列表，并进行升序排列
     * @return
     */
    List<ItemCat> selectItemCatList();
    
    /**
     * 根据商品类型查询商品列表
     * @return
     */
    List<Porduct> selectProByItemCat(String catid);
}
