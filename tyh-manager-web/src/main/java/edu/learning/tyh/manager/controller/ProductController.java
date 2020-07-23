package edu.learning.tyh.manager.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.learning.fastdfs.client.FastDFSClient;
import edu.learning.tyh.common.Config;
import edu.learning.tyh.manager.dto.ProductDto;
import edu.learning.tyh.manager.service.interfaces.ItemCatService;
import edu.learning.tyh.manager.service.interfaces.ProductService;
import edu.learning.tyh.pojo.ItemCat;
import edu.learning.tyh.pojo.Porduct;
import edu.learning.tyh.pojo.ProductImg;

/**
 * 商品管理控制层
 * @author lenovo
 *
 */
@Controller
@RequestMapping("/manager/product")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@Autowired
	private ItemCatService catService;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Resource(name="topicDestination")
	private Topic topic;
	
	/**
	 * 商品列表
	 * @param pro
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@RequestMapping("/list")
	public String prolist(Porduct pro,Model model) throws FileNotFoundException, IOException {
		List<ProductDto>list=service.selectPro(pro);
		model.addAttribute("list", list);
		
		List<ItemCat>catlist=catService.selectAll();
		model.addAttribute("catlist", catlist);
		model.addAttribute("fserver", Config.getFileserver());
		return "product/list";
	}
	
	/**
	 * 进入到商品增加页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/toadd")
	public String toadd(Model model) {
		List<ItemCat> catlist=catService.selectAll();
		model.addAttribute("catlist", catlist);
		return "product/add";
	}
	
	/**
	 * 增加商品方法
	 * @param pro
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Porduct product, @RequestParam("file") MultipartFile file,Model model) {
		// 1、保存文件到fastdfs
		if (file != null && !file.isEmpty()) {
			try {
				FastDFSClient dfs = new FastDFSClient();
				// 获取商品的后缀名
				String filename = file.getOriginalFilename();
				String extName = filename.substring(filename.lastIndexOf(".") + 1);
				// 保存文件
				String url = dfs.uploadFile(file.getBytes(), extName);
				//System.out.println("url="+url);
				product.setPimg(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		product.setPid(UUID.randomUUID().toString());
		//保存商品信息到数据库
		int rtn = service.insertPro(product);
		
		//处理保存结果
		if(rtn>0){//保存成功
			return "redirect:/manager/product/list";
		}else{//失败
			model.addAttribute("msg", "保存商品失败");
			return "product/add";
		}
	}
	
	/**
	 * 进入到商品图片编辑页面
	 * @param id
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@RequestMapping("/toimg/{id}")
	public String toimg(@PathVariable String id,Model model) throws FileNotFoundException, IOException {
		List<ProductImg> list=service.selectProductImgList(id);
		
		Porduct pro=service.selectProductById(id);
		model.addAttribute("list", list);
		model.addAttribute("pro", pro);
		model.addAttribute("fserver",Config.getFileserver());
		return "product/imgs";
				
	}
	
	/**
	 * 增加商品图片
	 * @param productImg
	 * @param imgfile
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/addimg")
	public String addimg(ProductImg productImg,@RequestParam("imgfile") MultipartFile imgfile,Model model) throws Exception {
		String url="";
		
		if(imgfile!= null && !imgfile.isEmpty()) {
			FastDFSClient dfs=new FastDFSClient();
			String filename=imgfile.getOriginalFilename();
			String extname=filename.substring(filename.lastIndexOf(".")+1);
			url=dfs.uploadFile(imgfile.getBytes(),extname);
		}
		
		if(productImg.getPimgid()!=null && !productImg.getPimgid().equals("")) {
			if(!url.equals("")) {
				productImg.setPimgaddr(url);
			}
			service.updateProductImg(productImg);
		}else {
			productImg.setPimgid(UUID.randomUUID().toString());
			productImg.setPimgaddr(url);
			service.insertProductImg(productImg);
		}
		
		return "redirect:/manager/product/toimg/"+productImg.getPid();
	}
	
	/** 
	 * 初始化修改商品信息页面
	 * @param id
	 * @param model
	 * @return 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@RequestMapping("/toedit/{id}")
	public String toedit(@PathVariable String id, Model model) throws FileNotFoundException, IOException {
		//查询商品类别列表
		List<ItemCat> catlist=catService.selectAll();
		Porduct product = service.selectProductById(id);
		model.addAttribute("fserver", Config.getFileserver());
		model.addAttribute("catlist",catlist);
		model.addAttribute("product",product);
		return "product/edit";
	}
	
	/**
	 * 修改商品信息
	 * @param product
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(Porduct product,@RequestParam("file") MultipartFile file,Model model) {
		// 保存文件到fastdfs
		if (file != null && !file.isEmpty())
			try {
				FastDFSClient dfs = new FastDFSClient();
				// 获取文件的后缀名
				String filename = file.getOriginalFilename();
				String extName = filename.substring(filename.lastIndexOf(".") + 1);
				// 保存文件
				String url = dfs.uploadFile(file.getBytes(), extName);
				product.setPimg(url);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		int rtn = service.updateProduct(product);
		if (rtn > 0) {
			return "redirect:/manager/product/list"; // 转到列表页面
		}
		else {
			model.addAttribute("msg", "修改商品类型失败");
			return "product/edit";
		}
	}
	
	/**
	 * 删除指定图片
	 */
	@RequestMapping("/del/{id}")
	
	public String del(@PathVariable String id,Model model){
		
		ProductImg pi=service.selectProductByIdImg(id);
		int rtn=service.deleteProductImg(id);
		
		//System.out.println(pi.getPid());
		if(rtn<0)
		model.addAttribute("msg", "删除失败");		
		
		
		return "redirect:/manager/product/toimg/"+pi.getPid();
	}
	
	/*@RequestMapping("/publish/{id}")
	@ResponseBody
	public String publish(@PathVariable final String id) {
		jmsTemplate.send(topic,new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage=session.createTextMessage(id);
				return textMessage;
			}
		});
		return "publish success!";
	}*/
}
