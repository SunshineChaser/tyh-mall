package edu.learning.tyh.ckeditor.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.learning.fastdfs.client.FastDFSClient;
import edu.learning.tyh.common.Config;

@Controller
@RequestMapping("/ckeditor/upload")
public class UploadImgController {

	@RequestMapping("/img")
	public void uploadImg(@RequestParam("upload") MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws Exception {
		FastDFSClient dfs = new FastDFSClient();
		// 获取商品的后缀名
		String filename = file.getOriginalFilename();
		String extName = filename.substring(filename.lastIndexOf(".") + 1);
		// 保存文件
		String url = dfs.uploadFile(file.getBytes(), extName);
		//构建文件的完整路径。包含：文件服务器地址+文件位置
		url=Config.getFileserver()+url;
		//需要向客户端响应如下消息
		response.setContentType("text/html;charset=UTF-8");
		String callback=request.getParameter("CKEditorFuncNum");
		PrintWriter out=response.getWriter();
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction("+callback+",'"+url+"',''"+")");
		out.println("</script>");
		out.flush();
		out.close();
	}
}
