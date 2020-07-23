package edu.learning.tyh.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.learning.tyh.manager.dao.PorductMapper;
import edu.learning.tyh.manager.dao.ProductImgMapper;
import edu.learning.tyh.manager.dto.ProductDto;
import edu.learning.tyh.manager.service.interfaces.ProductService;
import edu.learning.tyh.pojo.Porduct;
import edu.learning.tyh.pojo.ProductImg;

/**
 * 商品管理服务层实现类
 * @author lenovo
 *
 */
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private PorductMapper mapper;
	
	@Autowired
	private ProductImgMapper imgmapper;
	
	@Override
	public List<ProductDto> selectPro(Porduct pro) {
		return mapper.selectProductList(pro);
	}

	@Override
	public int insertPro(Porduct pro) {
		return mapper.insertSelective(pro);
	}

	@Override
	public Porduct selectProductById(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ProductImg> selectProductImgList(String pid) {
		return imgmapper.selectPImgList(pid);
	}

	@Override
	public int insertProductImg(ProductImg img) {
		return imgmapper.insert(img);
	}

	@Override
	public int updateProductImg(ProductImg img) {
		// TODO 自动生成的方法存根
		return imgmapper.updateByPrimaryKeySelective(img);
	}

	@Override
	public int deleteProductImg(String id) {
		// TODO 自动生成的方法存根
		return imgmapper.deleteByPrimaryKey(id);
	}

	@Override
	public ProductImg selectProductByIdImg(String id) {
		// TODO 自动生成的方法存根
		return imgmapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateProduct(Porduct product) {
		// TODO 自动生成的方法存根
		return mapper.updateByPrimaryKeySelective(product);
	}

}
