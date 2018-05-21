package cn.itcast.core.controller.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.fckeditor.response.UploadResponse;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.itcast.common.web.ResponseUtils;
import cn.itcast.core.web.Constants;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * 上传图片包括：
 * 商品的图片
 * 品牌的图片
 * 商品介绍Fck的图片
 */
@Controller
public class UploadController {
	
	//上传图片
	@RequestMapping(value = "/upload/uploadPic.do")
	public void uploadPic(@RequestParam(required = false) MultipartFile pic,HttpServletResponse response){
		                 //异步上传也是需要接收表单的name属性值  pic
		//扩展名
		String ext = FilenameUtils.getExtension(pic.getOriginalFilename());
		
		//图片名称生成策略
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			//图片名称一部分
			String format = df.format(new Date());
			
			//随机三位数
			Random r = new Random();
			// n 1000   0-999   99
			for(int i=0 ; i<3 ;i++){
				format += r.nextInt(10);
			}
		
		//实例化一个Jersey
		Client client = new Client();
		//保存数据库
		String path = "upload/" + format + "." + ext;
		
		//另一台服务器的请求路径是?
		String url = Constants.IMAGE_URL  + path;
		//设置请求路径
		WebResource resource = client.resource(url);
		
		//发送开始  POST  GET   PUT
		try {
			resource.put(String.class, pic.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//返回二个路径
		JSONObject jo = new JSONObject();
		jo.put("url", url);
		jo.put("path",path);
		
		ResponseUtils.renderJson(response, jo.toString());
	}
	
	//上传Fck图片
		@RequestMapping(value = "/upload/uploadFck.do")
		public void uploadFck(HttpServletRequest request,HttpServletResponse response){
			//强转request    可以上传多张图片所以我们按照上传多张图片处理
			MultipartHttpServletRequest mr= (MultipartHttpServletRequest)request;
			//获取值                             可以上传多张图片所以我们按照上传多张图片处理
			Map<String, MultipartFile> fileMap = mr.getFileMap();
			//遍历Map
			Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
			
			for(Entry<String, MultipartFile> entry : entrySet){
				//上传上来的图片
				MultipartFile pic = entry.getValue();
				//扩展名
				String ext = FilenameUtils.getExtension(pic.getOriginalFilename());
				
				//图片名称生成策略
				DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				//图片名称一部分
				String format = df.format(new Date());
				
				//随机三位数
				Random r = new Random();
				// n 1000   0-999   99
				for(int i=0 ; i<3 ;i++){
					format += r.nextInt(10);
				}
				
				//实例化一个Jersey
				Client client = new Client();
				//保存数据库
				String path = "upload/" + format + "." + ext;
				
				//另一台服务器的请求路径是?
				String url = Constants.IMAGE_URL  + path;
				//设置请求路径
				WebResource resource = client.resource(url);
				
				//发送开始  POST  GET   PUT
				try {
					resource.put(String.class, pic.getBytes());
				} catch (IOException e) {
				 
					e.printStackTrace();
				}
				//返回url给Fck即可 因为他不属于商品图片，只是富文本编辑器的图片所以不需要返回path   java-core-2.6.jar
				UploadResponse ok = UploadResponse.getOK(url);
				//response可以返回字符串怎么返回对象？ 
				//response  write   返回字符串     //字符流
				//response  print   返回对象         //字节流
	 
				try {
					response.getWriter().print(ok);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
}
