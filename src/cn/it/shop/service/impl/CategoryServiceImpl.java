package cn.it.shop.service.impl;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import hibernateEntity.Category;
import cn.it.shop.service.CategoryService;
@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> queryJoinAccount(String type,int page,int size) {
		String hql=" from Category c left join fetch c.account where c.type like :type";
		//String hql = "from Category c where c.type like :type";  
		return getSession().createQuery(hql).setString("type", "%"+type+"%").setFirstResult((page-1)*size).setMaxResults(size).list();
	}
	//添加新方法即可

	@Override
	public Long getCount(String type) {
		String hql="select count(c) from Category c where c.type like :type";
		return (Long) getSession().createQuery(hql).setString("type", "%"+type+"%").uniqueResult();
	}
	@Override
	public void deleteByIds(String ids) {
		String hql="delete from Category c where c.id in ( "+ids+" ) ";
		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public List<Category> queryByHot(boolean hot) {
		String hql="from Category c where c.hot= :hot";
		return getSession().createQuery(hql).setBoolean("hot", hot).list();
	}
}
