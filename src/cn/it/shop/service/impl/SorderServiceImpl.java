package cn.it.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Product;
import cn.it.shop.model.Sorder;
import cn.it.shop.service.SorderService;
@Service("sorderService")
public class SorderServiceImpl extends BaseServiceImpl<Sorder> implements SorderService {

	@Override
	public Forder addSorder(Forder forder, Product product) {
		boolean isHave=false;
		Sorder sorder=productToSorder(product);
		for (Sorder old : forder.getSorders()) {
			if(old.getProduct().getId().equals(sorder.getProduct().getId())){
				old.setNumber(old.getNumber()+sorder.getNumber());
				isHave=true;
				break;
			}
		}
		if(!isHave){
			//我们在这里插入一句：  
            //在向购物车中添加购物项之前，先建立购物项与购物车的关联，但是此时forder.id为null，  
            //但是在入库的时候是先入库购物车，再入库购物项，那时候就有主键了  
			sorder.setForder(forder);
			forder.getSorders().add(sorder);
		}
		return forder;
	}

	@Override
	public Sorder productToSorder(Product product) {
		Sorder sorder=new Sorder();
		sorder.setName(product.getName());
		sorder.setNumber(1);
		sorder.setPrice(product.getPrice());
		sorder.setProduct(product);
		return sorder;
	}

	@Override
	public Forder updateSorder(Sorder sorder, Forder forder) {
		for (Sorder temp : forder.getSorders()) {
			if(temp.getProduct().getId().equals(sorder.getProduct().getId())){
				temp.setNumber(sorder.getNumber());
			}
		}
		return forder;
	}

	@Override
	public List<Object> querySale(int number) {
		String hql="select s.name, sum(s.number) from Sorder s join s.product group by s.product.id";
		return getSession().createQuery(hql).setFirstResult(0).setMaxResults(number).list();
	}

	

}
