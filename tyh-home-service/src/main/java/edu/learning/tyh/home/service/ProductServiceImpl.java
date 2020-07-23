package edu.learning.tyh.home.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.learning.tyh.home.dao.ItemCatMapper;
import edu.learning.tyh.home.dao.PorductMapper;
import edu.learning.tyh.home.interfaces.ProductService;
import edu.learning.tyh.pojo.ItemCat;
import edu.learning.tyh.pojo.Porduct;

@Path("/product")
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private PorductMapper promapper;
	
	@Autowired
	private ItemCatMapper catmapper;
	
	@Path("/first")
	@GET
	@Produces({MediaType.APPLICATION_JSON+";"+MediaType.CHARSET_PARAMETER+"=UTF-8"})
	@Consumes({MediaType.APPLICATION_JSON+";"+MediaType.CHARSET_PARAMETER+"=UTF-8"})
	@Override
	public List<Porduct> selectProByFirst() {
		return promapper.selectProByFirst();
	}

	@Path("/type")
	@GET
	@Produces({MediaType.APPLICATION_JSON+";"+MediaType.CHARSET_PARAMETER+"=UTF-8"})
	@Consumes({MediaType.APPLICATION_JSON+";"+MediaType.CHARSET_PARAMETER+"=UTF-8"})
	@Override
	public List<ItemCat> selectItemCatList() {
		return catmapper.selectItemCatList();
	}

	@Path("/tlist/{catid}")
	@GET
	@Produces({MediaType.APPLICATION_JSON+";"+MediaType.CHARSET_PARAMETER+"=UTF-8"})
	@Consumes({MediaType.APPLICATION_JSON+";"+MediaType.CHARSET_PARAMETER+"=UTF-8"})
	@Override
	public List<Porduct> selectProByItemCat(@PathParam("catid") String catid){
		return promapper.selectByItemCat(catid);
	}
}
