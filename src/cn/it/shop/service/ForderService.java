package cn.it.shop.service;

import java.math.BigDecimal;

import cn.it.shop.model.Forder;

public interface ForderService extends BaseService<Forder> {
	public BigDecimal cluTotal(Forder forder);
	public void updateStatusById(int id,int sid);
}
