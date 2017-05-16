package cn.it.shop.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;

import hibernateEntity.Category;
import cn.it.shop.service.CategoryService;
@Controller("categoryAction")
@Scope("prototype")
public class CategoryAction extends BaseAction<Category>{
	private static final long serialVersionUID = 1L;

	public String queryJoinAccount(){
		System.out.println(model.getType());
		pageMap=new HashMap<String,Object>();
		List<Category> categoryList=categoryService.queryJoinAccount(model.getType(), page, rows);
		pageMap.put("rows", categoryList);
		Long  total=categoryService.getCount(model.getType());
		pageMap.put("total", total);
		return "jsonMap";
		
	}
	public String deleteByIds(){
		categoryService.deleteByIds(ids);
		inputStream=new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	public void save(){
		System.out.println(model);
		categoryService.save(model);
	}
	public void update(){
		System.out.println(model);
		categoryService.update(model);
	}
	public String query(){
		jsonList=categoryService.query();
		return "jsonList";
	}
}	

