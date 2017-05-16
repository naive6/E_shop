package cn.it.shop.model;
// Generated 2017-4-19 18:52:57 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import hibernateEntity.Category;

@Entity
public class Product implements java.io.Serializable {

	private Integer id;
	private Category category;
	private String name;
	private BigDecimal price;
	private String pic;
	private String remark;
	private String xremark;
	private Date date;
	private Boolean commend;
	private Boolean open;

	public Product() {
	}

	public Product(Date date) {
		this.date = date;
	}

	public Product(Category category,String name, BigDecimal price, String pic, String remark, String xremark, Boolean commend,
			Boolean open) {
		this.category=category;
		this.name = name;
		this.price = price;
		this.pic = pic;
		this.remark = remark;
		this.xremark = xremark;
		this.commend = commend;
		this.open = open;
	}
@Id
@GeneratedValue
@Column(name="id",unique=true,nullable=false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getXremark() {
		return this.xremark;
	}

	public void setXremark(String xremark) {
		this.xremark = xremark;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getCommend() {
		return this.commend;
	}

	public void setCommend(Boolean commend) {
		this.commend = commend;
	}

	public Boolean getOpen() {
		return this.open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}
@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="cid")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
