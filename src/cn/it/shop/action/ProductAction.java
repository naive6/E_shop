package cn.it.shop.action;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.Product;
@Controller("productAction")
@Scope("prototype")
public class ProductAction extends BaseAction<Product> {
	public String queryJoinCategory(){
		System.out.println(model.getName());
		System.out.println(page);
		System.out.println(rows);
		pageMap=new HashMap<String,Object>();
		List<Product> list=productService.queryJoinCategory(model.getName(), page, rows);
		pageMap.put("rows", list);
		Long total=productService.getCount(model.getName());
		pageMap.put("total", total);
		return "jsonMap";
	}
	public String deleteByIds(){
		productService.deleteByIds(ids);
		inputStream=new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	public void save(){
		String pic=fileUpload.uploadFile(fileImage);
		System.out.println(fileImage.getFileName());
		model.setPic(pic);
		model.setDate(new Date());
		System.out.println(model);
		productService.save(model);
	}
	public void update(){
		String pic=fileUpload.uploadFile(fileImage);
		model.setPic(pic);
		model.setDate(new Date());
		System.out.println(model);
		System.out.println(model.getCategory().getId());
		productService.update(model);
	}
	
	public String get(){
		request.put("product", productService.get(model.getId()));
		return "detail";
	}
}
