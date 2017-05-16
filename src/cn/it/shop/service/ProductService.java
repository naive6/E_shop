package cn.it.shop.service;

import java.util.List;

import cn.it.shop.model.Product;

public interface ProductService extends BaseService<Product> {
	public List<Product> queryJoinCategory(String name,int page,int size);
	public Long getCount(String name);
	public void deleteByIds(String ids);
	public List<Product> queryByCategoryId(int cid);
}
