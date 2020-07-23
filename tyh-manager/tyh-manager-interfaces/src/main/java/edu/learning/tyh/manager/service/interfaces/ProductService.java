package edu.learning.tyh.manager.service.interfaces;

import java.util.List;

import edu.learning.tyh.manager.dto.ProductDto;
import edu.learning.tyh.pojo.Porduct;
import edu.learning.tyh.pojo.ProductImg;

/**
 * 商品管理接口
 * @author lenovo
 *
 */
public interface ProductService {

	/**
	 * 查询商品列表
	 * @param pro
	 * @return
	 */
	List<ProductDto> selectPro(Porduct pro);
	
	/**
	 * 增加商品
	 * @param pro
	 * @return
	 */
	int insertPro(Porduct pro);
	
	/**
	 * 根据id查询一条商品的详细信息
	 * @param id
	 * @return
	 */
	Porduct selectProductById(String id);
	/**
	 * 根据商品id查询商品的图片列表
	 * @param pid
	 * @return
	 */
	List<ProductImg> selectProductImgList(String pid);
	
	/**
	 * 保存商品图片
	 * @param img
	 * @return
	 */
	int insertProductImg(ProductImg img);
	/**
	 * 修改商品图片
	 * @param img
	 * @return
	 */
	int updateProductImg(ProductImg img);
	/**
	 * 根据id删除商品图片
	 * @param id
	 * @return
	 */
	int deleteProductImg(String id);
	
	/**
	 * 根据id查询一条商品图片的详细信息
	 * @param id
	 * @return
	 */
	ProductImg selectProductByIdImg(String id);
	/**
	 * 修改商品
	 * @param img
	 * @return
	 */
	int updateProduct(Porduct product);
}
