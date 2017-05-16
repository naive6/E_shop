package cn.it.shop.service;

import java.util.List;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Product;
import cn.it.shop.model.Sorder;

public interface SorderService extends BaseService<Sorder> {
	//��ӹ�������µĹ��ﳵ
	public Forder addSorder(Forder forder,Product product);
	//����Ʒ����ת��Ϊ������
	public Sorder productToSorder(Product product);
	
	public Forder updateSorder(Sorder sorder,Forder forder);
	
	public List<Object> querySale(int number);
}
