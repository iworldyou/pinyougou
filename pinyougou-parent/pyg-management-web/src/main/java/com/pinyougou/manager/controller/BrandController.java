package com.pinyougou.manager.controller;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.entity.PageResult;
import com.pinyougou.entity.Result;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;

@RestController
@RequestMapping("/brand")
public class BrandController {
	
	@Reference
	private BrandService brandService;
	
	//查询所有
	@RequestMapping("/findAll")
	public List<TbBrand> findAll(){
		List<TbBrand> list = brandService.findAll();
		return list;
	}
	
	//分页查询
	@RequestMapping("/findPage")
	public PageResult findPage(int page,int rows){
		PageResult pageResult = brandService.findPage(page, rows);
		return pageResult;
	}
	
	//添加品牌
	@RequestMapping("/add")
	public Result addBrand(@RequestBody TbBrand tbBrand){
		try {
			if (tbBrand.getName()!=null && tbBrand.getFirstChar()!=null) {
				brandService.addBrand(tbBrand);
				return new Result(true,"添加成功");
			}else {
				return new Result(false,"添加失败");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,"添加失败");
		}
	}
	
	
	//根据id查询一个
	@RequestMapping("/findOne")
	public TbBrand findOne(Integer id){
		TbBrand brand = brandService.findOne(id);
		return brand;
	}
	
	//品牌修改操作
	@RequestMapping("/update")
	public Result updateBrand(@RequestBody TbBrand tbBrand){
		try {
			brandService.updateBrand(tbBrand);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}
	
	//批量删除
	@RequestMapping("/delete")
	public Result delete(Long[] ids){
		try {
			brandService.deleteById(ids);
			return new Result(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
	//根据品牌名称和首字母查询
	@RequestMapping("/search")
	public PageResult search(int page,int rows,@RequestBody TbBrand tbBrand){
		PageResult pageResult = brandService.search(page, rows, tbBrand);
		return pageResult;
	}
	
	
	
	

}
