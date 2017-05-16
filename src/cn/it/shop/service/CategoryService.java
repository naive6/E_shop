package cn.it.shop.service;

import java.util.List;

import hibernateEntity.Category;

public interface CategoryService extends BaseService<Category> {
	//增加新方法即可
	public List<Category> queryJoinAccount(String type,int page,int size);
    public Long getCount(String type);
    public void deleteByIds(String ids);
    public List<Category> queryByHot(boolean hot);
}
