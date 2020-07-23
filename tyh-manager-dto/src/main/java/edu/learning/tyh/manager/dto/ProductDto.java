package edu.learning.tyh.manager.dto;

import java.io.Serializable;

import edu.learning.tyh.pojo.Porduct;

/**
 * 商品列表dto
 * @author lenovo
 *
 */
public class ProductDto extends Porduct implements Serializable{

	private String catname;

	public String getCatname() {
		return catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}
	
}
