package cn.it.shop.service;

import java.util.List;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Product;
import cn.it.shop.model.Sorder;

public interface SorderService extends BaseService<Sorder> {
	//添加购物项返回新的购物车
	public Forder addSorder(Forder forder,Product product);
	//把商品数据转化为购物项
	public Sorder productToSorder(Product product);
	
	public Forder updateSorder(Sorder sorder,Forder forder);
	
	public List<Object> querySale(int number);
}
