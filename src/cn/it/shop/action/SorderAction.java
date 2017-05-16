package cn.it.shop.action;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Product;
import cn.it.shop.model.Sorder;
@Controller
@Scope("prototype")
public class SorderAction extends BaseAction<Sorder> {
	public String addSorder(){
		Product product=productService.get(model.getProduct().getId());
		if(session.get("forder")==null){
			session.put("forder", new Forder(new ArrayList<Sorder>()));
		}
		Forder forder=(Forder) session.get("forder");
		forder=sorderService.addSorder(forder, product);
		forder.setTotal(forderService.cluTotal(forder));
		session.put("forder", forder);
		return "showCart";
	}
	
	public String updateSorder(){
		Forder forder=(Forder) session.get("forder");
		forder=sorderService.updateSorder(model, forder);
		forder.setTotal(forderService.cluTotal(forder));
		session.put("forder", forder);
		inputStream=new ByteArrayInputStream(forder.getTotal().toString().getBytes());
		return "stream";
	}
	
	public String querySale(){
		List<Object> jsonList=sorderService.querySale(model.getNumber());
		ActionContext.getContext().getValueStack().push(jsonList);
		return "jsonList";
	}
}
