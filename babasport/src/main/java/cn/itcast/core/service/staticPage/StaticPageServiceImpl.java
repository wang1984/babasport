package cn.itcast.core.service.staticPage;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;


/**
 * 生成静态页实现类
 */
public class StaticPageServiceImpl implements StaticPageService,ServletContextAware{

	private Configuration conf;
	
	//在配置文件中注入freeMarkerConfigurer 最终的到是Configuration
	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.conf = freeMarkerConfigurer.getConfiguration();
	}


	//静态化方法
	public void productIndex(Map<String,Object> root,Integer id){
		//String dir = "C:\Users\lx\workspace\babasport12\";
		//设置模板的目录
		//conf.setDirectoryForTemplateLoading(dir);
		
		//输出流   从内存写出去  磁盘上
		Writer out = null;
		try {
			//读进来  UTF-8  内存中 读写都要使用UTF-8
			Template template = conf.getTemplate("productDetail.html");
			//获取Html的路径（根据商品的id生成该商品的静态详情页）
			String path = getPath("/html/product/" + id +  ".html");//278.html
			
			File f = new File(path);
			File parentFile = f.getParentFile();
			if(!parentFile.exists()){
				parentFile.mkdirs();
			}
			//输出流
			out = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			//处理模板
			template.process(root, out);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//获取路径
	public String getPath(String name){
		return servletContext.getRealPath(name);
	}

	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
