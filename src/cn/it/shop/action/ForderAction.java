package cn.it.shop.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Status;
import cn.it.shop.model.User;
@Controller("forderAction")
@Scope("prototype")
public class ForderAction extends BaseAction<Forder> {
	@Override
	public Forder getModel(){
		model=(Forder) session.get("forder");
		return model;
	}
	
	public String save(){
//		Set<Forder> forders=new HashSet<Forder>();
//		Status status=new Status(1);
//		forders.add(model);
//		status.setForders(forders);
		model.setUser((User) session.get("user"));
		model.setStatus(new Status(1));
		forderService.save(model);
		//从订单支付页面后退后，清空原来的购物车
		session.put("oldForder", session.get("forder"));
		session.put("forder", new Forder());
		System.out.println(model.getTotal());
		return "bank";
	}
}
