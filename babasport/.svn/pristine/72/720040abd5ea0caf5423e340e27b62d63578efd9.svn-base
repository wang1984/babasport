package cn.itcast.core.controller.admin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.service.product.BrandService;

/**
 * 品牌
 */
@Controller
public class BrandController {
	//@Autowired  默认按类型装配（这个注解是属业spring的）
	//@Resource   默认按照名称进行装配（这个注解是JDK1.6支持的）
	
	@Autowired
	private BrandService brandService;
	
	//品牌列表页面
	@RequestMapping(value = "/brand/list.do")
	public String list(String name,Integer isDisplay,Integer pageNo,ModelMap model){
		
		//参数
		StringBuilder params = new StringBuilder();
		Brand brand = new Brand();
		//判断传进来的名称是否为Null并且还要判断 是否为空串
		//blank(  "" 与   "   " 是空字符串 )   
		//emtpy(  "" 是空字符串   "  "不是空字符串 )
		
		if(StringUtils.isNotBlank(name)){
			brand.setName(name);
			params.append("name=").append(name);
		}

		//是  不是
		if(null != isDisplay){
			brand.setIsDisplay(isDisplay);
			params.append("&").append("isDisplay=").append(isDisplay);
		}else{
			brand.setIsDisplay(1);
			params.append("&").append("isDisplay=").append(1);
		}
		
		//如果页号为null 或小于1  置为1
		//Pagination.cpn(pageNo)
		//页号
		brand.setPageNo(Pagination.cpn(pageNo));
		//每页数
		brand.setPageSize(5);
		
		//分页对象
		Pagination pagination = brandService.getBrandListWithPage(brand);
		
		//分页展示每一个分页链接   /brand/list.do?name=瑜伽树（yogatree）&isDisplay=1&pageNo=2
		String url = "/brand/list.do";
		pagination.pageView(url, params.toString());
		
		model.addAttribute("pagination", pagination);//model.addAttribute==request.setAttribute
		//搜索条件回显
		model.addAttribute("name", name);//model.addAttribute==request.setAttribute
		model.addAttribute("isDisplay", isDisplay);//model.addAttribute==request.setAttribute
		
		return "brand/list";
	}
	//跳转品牌添加页面
	@RequestMapping(value= "/brand/toAdd.do")
	public String toAdd(){
		return "brand/add";
	}
	//添加品牌
	@RequestMapping(value = "/brand/add.do")
	public String add(Brand brand){
		//添加开始
		brandService.addBrand(brand);
		return "redirect:/brand/list.do";
	}
	//删除一个品牌
	@RequestMapping(value = "/brand/delete.do")
	public String delete(Integer id,String name,Integer isDisplay,ModelMap model){
		// 删除
		brandService.deleteBrandByKey(id);
		// 删除后也要回显查询条件
		if(StringUtils.isNotBlank(name)){
			model.addAttribute("name", name);
		}
		if(null != isDisplay){
			model.addAttribute("isDisplay", isDisplay);
		}
		//怎么将当前的查询条件的参数重定向到其他页面
		//使用 ModelMap model解决   如果是使用链接加？号 & 当遇到中文的时候会有问题，即使tomcat中配置了URIEncoding="UTF-8"
		return "redirect:/brand/list.do";
	}
	
	//删除多个品牌
		@RequestMapping(value = "/brand/deletes.do")
		public String deletes(Integer[] ids,String name,Integer isDisplay,ModelMap model){
			// 删除多个
			brandService.deleteBrandByKeys(ids);
			if(StringUtils.isNotBlank(name)){
				model.addAttribute("name", name);
			}
			if(null != isDisplay){
				model.addAttribute("isDisplay", isDisplay);
			}
			
			return "redirect:/brand/list.do";
		}
	
		//去修改页面
		@RequestMapping(value = "/brand/toEdit.do")
		public String toEdit(Integer id,ModelMap model){
			
			Brand brand = brandService.getBrandByKey(id);
			
			model.addAttribute("brand", brand);
			
			return "brand/edit";
		}
		//去修改页面
		@RequestMapping(value = "/brand/edit.do")
		public String edit(Brand brand,ModelMap model){
			
			brandService.updateBrandByKey(brand);
			
			return "redirect:/brand/list.do";
		}	
}
