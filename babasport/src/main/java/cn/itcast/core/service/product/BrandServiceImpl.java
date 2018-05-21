package cn.itcast.core.service.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.common.page.Pagination;
import cn.itcast.core.bean.product.Brand;
import cn.itcast.core.dao.product.BrandDao;
import cn.itcast.core.query.product.BrandQuery;

/**
 * 品牌事务
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService{
	
	@Resource
	private BrandDao brandDao;

	@Transactional(readOnly = true)
	public Pagination getBrandListWithPage(Brand brand){
		//new Pagination(param1,param2,param3)
		//param1:起始页  startRow = (pageNo - 1)*pageSize
		//param2:每页数
		//param3:总记录数
		Pagination  pagination = new Pagination(
				brand.getPageNo(),
				brand.getPageSize(),
				brandDao.getBrandCount(brand)
				);
		//Brand集合
		pagination.setList(brandDao.getBrandListWithPage(brand));
		
		return pagination;
	}
	//添加品牌
	@Override
	public void addBrand(Brand brand) {
		brandDao.addBrand(brand);
	}
	//删除
	@Override
	public void deleteBrandByKey(Integer id) {
		brandDao.deleteBrandByKey(id);
		
	}
	//删除 批量
	@Override
	public void deleteBrandByKeys(Integer[] ids) {
		brandDao.deleteBrandByKeys(ids);
		
	}
	//修改
	@Override
	public void updateBrandByKey(Brand brand) {
		brandDao.updateBrandByKey(brand);
		
	}
	@Override
	public Brand getBrandByKey(Integer id) {
		return brandDao.getBrandByKey(id);
	}
	
	@Override
	public List<Brand> getBrandList(BrandQuery brandQuery) {

		return brandDao.getBrandList(brandQuery);
	}
}
