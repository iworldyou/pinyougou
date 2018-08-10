package com.pinyougou.sellergoods.service;

import java.util.List;

import com.pinyougou.entity.PageResult;
import com.pinyougou.pojo.TbBrand;

public interface BrandService {
	
	public List<TbBrand> findAll();
	
	public PageResult findPage(int pageNum,int pageSize);
	
	public void addBrand(TbBrand tbBrand);
	
	public TbBrand findOne(long id);
	
	public void updateBrand(TbBrand tbBrand);
	
	public void deleteById(Long[] ids);
	
	public PageResult search(int page,int rows,TbBrand tbBrand);

}
