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
	private Class clazz;//clazz�д洢�˵�ǰ���������ͣ�������T
	@Resource
	private SessionFactory sessionFactory;//����಻֪��������Ƿ���ȷ���п�
	
	public BaseServiceImpl(){
		//��ȡ��ǰthis����ĸ�����Ϣ������������Ϣ��
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
		//think����Ϊʲô��ֱ����session��delete����
		//ֱ��ʹ��delete�����ı׶���ÿɾ��һ�ξ͵��Ȳ�ѯһ��
		getSession().createQuery(hql).setInteger("id", id).executeUpdate();
	}

	
	public T get(int id) {
		return (T) getSession().get(clazz, id);
	}

	public List<T> query() {
		String hql="from "+clazz.getSimpleName();
		//thinkΪʲô��ֱ����session��get����
		return getSession().createQuery(hql).list();
	}

}
