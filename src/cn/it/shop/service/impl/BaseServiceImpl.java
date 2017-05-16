package cn.it.shop.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cn.it.shop.service.BaseService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
@Service("baseService")
@Lazy(true)
public class BaseServiceImpl<T> implements BaseService<T> {
	private Class clazz;//clazz中存储了当前操作的类型，即范型T
	@Resource
	private SessionFactory sessionFactory;//这个类不知道引入的是否正确，有坑
	
	public BaseServiceImpl(){
		//获取当前this对象的父类信息（包括泛型信息）
		ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
		clazz=(Class) type.getActualTypeArguments()[0];
	}
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}

	public void save(T t) {
		getSession().save(t);
	}
	
	public void update(T t) {
		getSession().update(t);
	}

	public void delete(int id) {
		System.out.println(clazz.getSimpleName());
		String hql="delete "+clazz.getSimpleName()+"as c where c.id=:id";
		//think这里为什么不直接用session的delete方法
		//直接使用delete方法的弊端是每删除一次就得先查询一次
		getSession().createQuery(hql).setInteger("id", id).executeUpdate();
	}

	
	public T get(int id) {
		return (T) getSession().get(clazz, id);
	}

	public List<T> query() {
		String hql="from "+clazz.getSimpleName();
		//think为什么不直接用session的get方法
		return getSession().createQuery(hql).list();
	}

}
