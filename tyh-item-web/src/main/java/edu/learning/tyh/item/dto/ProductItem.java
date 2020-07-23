package edu.learning.tyh.item.dto;

import java.io.Serializable;
import java.util.List;

import edu.learning.tyh.pojo.Porduct;
import edu.learning.tyh.pojo.ProductImg;

public class ProductItem implements Serializable{

	private Porduct product;
	
	private List<ProductImg>imglist;

	public Porduct getProduct() {
		return product;
	}

	public void setProduct(Porduct product) {
		this.product = product;
	}

	public List<ProductImg> getImglist() {
		return imglist;
	}

	public void setImglist(List<ProductImg> imglist) {
		this.imglist = imglist;
	}
}
