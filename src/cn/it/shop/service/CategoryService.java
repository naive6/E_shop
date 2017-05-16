package cn.it.shop.service;

import java.util.List;

import hibernateEntity.Category;

public interface CategoryService extends BaseService<Category> {
	//�����·�������
	public List<Category> queryJoinAccount(String type,int page,int size);
    public Long getCount(String type);
    public void deleteByIds(String ids);
    public List<Category> queryByHot(boolean hot);
}
