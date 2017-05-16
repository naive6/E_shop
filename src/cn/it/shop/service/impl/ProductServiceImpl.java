package cn.it.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.it.shop.model.Product;
import cn.it.shop.service.BaseService;
import cn.it.shop.service.ProductService;
@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {
	public List<Product> queryJoinCategory(String name,int page,int size){
		String hql="from Product p left join fetch p.category where p.name like :name";
		return getSession().createQuery(hql).setString("name", "%"+name+"%").setFirstResult(size*(page-1)).setMaxResults(size).list();
	}
	public Long getCount(String name){
		String hql="select count(p) from Product p where p.name like :name";
		return (Long) getSession().createQuery(hql).setString("name", "%"+name+"%").uniqueResult();
	}
	@Override
	public void deleteByIds(String ids) {
		String hql="delete from Product p where p.id in ( "+ids+" )";
		getSession().createQuery(hql).executeUpdate();
	}
	@Override
	public List<Product> queryByCategoryId(int cid) {
		String hql="from Product p join fetch p.category where p.commend=true and p.open=true and p.category.id=:cid order by p.date desc";
		return getSession().createQuery(hql).setInteger("cid", cid).setFirstResult(0).setMaxResults(4).list();
	}
}
