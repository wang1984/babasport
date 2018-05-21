package cn.itcast;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class JerseyDemo {
 
	public static void main(String[] args) throws IOException {
		//实例化一个Jersey
		Client client=new Client();
		//另一台服务器的请求路径是？
		String url="http://localhost:8088/image-web/upload/qqqq.jpg";
		//设置请求路径
		WebResource resource = client.resource(url);
		//本地路径
		String path="D:\\desktop\\023.jpg";
		
		//将图片读到内存中
		byte[] readFileToByteArray = FileUtils.readFileToByteArray(new File(path));
		
		//发送开始 POST GET PUT
		resource.put(String.class,readFileToByteArray);
		System.out.println("发送完毕");
	}

}
