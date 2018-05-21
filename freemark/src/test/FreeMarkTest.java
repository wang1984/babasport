package test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 测试环境搭建
 */
public class FreeMarkTest {
    @Test
	public void testenv() throws IOException, TemplateException{
    	//配置对象
    	Configuration configuration = new Configuration();
    	//模板路径
    	String dir="D:\\Workspaces\\Eclipse\\freemark\\ftl\\";//freemarktemplate.html
    	//导入模板目录
    	configuration.setDirectoryForTemplateLoading(new File(dir));
    	//获取模板
    	Template template = configuration.getTemplate("freemark.html");
    	
    	//数据
    	Map root = new HashMap();
    	root.put("k1", "hello");
    	root.put("k2", "world");
    	//测试对象
    	Person person = new Person();
    	person.setId(1);
    	person.setName("张三");
    	root.put("person", person);
    	
    	//测试 LIST
    	List<String> persons = new ArrayList<String>();
    	persons.add("范冰冰");
    	persons.add("李冰冰");
    	persons.add("何灵");
    	root.put("persons", persons);
    	
    	//测试 Map 	
    	Map mx = new HashMap();
    	mx.put("fbb","范冰冰");
    	mx.put("lbb","李冰冰");
    	root.put("mx",mx);
        
    	//测试 List<Map>
    	List<Map> maps = new ArrayList<Map>();
    	Map pms1 = new HashMap();
    	pms1.put("id1", "范冰冰");
    	pms1.put("id2", "李冰冰");
    	Map pms2 = new HashMap();
    	pms2.put("id1", "曾志伟");
    	pms2.put("id2", "何炅");
    	maps.add(pms1);
    	maps.add(pms2);
    	root.put("maps", maps);
    	
    	//在模板中赋值
    	root.put("world", "中土世界"); 
    	
    	
        //时间
    	root.put("cur_time",new Date());
    
    	//null值处理
    	root.put("val",null);
    	
    	
    	//输出流 (现在暂时先输出到ftl文件夹中)
    	Writer out= new FileWriter(new File(dir+"freemarkTemplate.html"));
    	
    	//生成开始    	
    	template.process(root, out);
    	
    	//关闭输出流
    	out.close();
    	System.out.println("数据+模板=输出");
    }
	
}
