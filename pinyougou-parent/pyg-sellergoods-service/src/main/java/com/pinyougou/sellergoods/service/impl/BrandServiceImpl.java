package com.pinyougou.sellergoods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.entity.PageResult;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.pojo.TbBrandExample.Criteria;
import com.pinyougou.sellergoods.service.BrandService;

@Service
@Transactional
public class BrandServiceImpl implements BrandService{
	@Autowired
	private TbBrandMapper tbBrandMapper;

	@Override
	public List<TbBrand> findAll() {
		List<TbBrand> list = tbBrandMapper.selectByExample(null);
		return list;
	}

	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbBrand> page=   (Page<TbBrand>) tbBrandMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}
	
	//新加
	@Override
	public void addBrand(TbBrand tbBrand) {
		tbBrandMapper.insert(tbBrand);
	}
	
	//修改
	@Override
	public void updateBrand(TbBrand tbBrand) {
		tbBrandMapper.updateByPrimaryKey(tbBrand);
		
	}

	//根据id查询
	@Override
	public TbBrand findOne(long id) {
		TbBrand tbBrand = tbBrandMapper.selectByPrimaryKey(id);
		return tbBrand;
	}

	@Override
	public void deleteById(Long[] ids) {
		for (Long id : ids) {
			tbBrandMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public PageResult search(int page, int rows, TbBrand tbBrand) {
		PageHelper.startPage(page, rows);
		TbBrandExample example = new TbBrandExample();
		Criteria criteria = example.createCriteria();
		if (tbBrand.getName()!=null && tbBrand.getName().length()>0) {
			criteria.andNameLike("%"+tbBrand.getName()+"%");
		}
		
		if (tbBrand.getFirstChar()!=null && tbBrand.getFirstChar().length()>0) {
			criteria.andFirstCharEqualTo(tbBrand.getFirstChar());
		}
		Page<TbBrand> pagedata= (Page<TbBrand>)tbBrandMapper.selectByExample(example);
		return new PageResult(pagedata.getTotal(),pagedata.getResult());
	}

}
