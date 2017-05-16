package cn.it.shop.utils.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import cn.it.shop.model.Product;
import cn.it.shop.service.CategoryService;
import cn.it.shop.service.ProductService;
import hibernateEntity.Category;
@Component
public class ProductTimerTask extends TimerTask {
	@Resource
	private ProductService productService=null;
	@Resource
	private CategoryService categoryService=null;
	private ServletContext application=null;
	
	public void setApplication(ServletContext application){
		this.application=application;
	}
	public void run() {
		System.out.println("-----run-------");
		List<List<Product>> bigList=new ArrayList<List<Product>>();
		for (Category category : categoryService.queryByHot(true)) {
			List<Product> lst=productService.queryByCategoryId(category.getId());
			bigList.add(lst);
		}
		application.setAttribute("bigList", bigList);
	}

	}


