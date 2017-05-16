package hibernateEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.it.shop.model.Account;

@Entity
public class Category implements java.io.Serializable {

	private Integer id;
	private Boolean hot;
	private String type;
	private Account account;

	public Category() {
	}

	public Category(Boolean hot, String type, Account account) {
		this.hot = hot;
		this.type = type;
		this.account=account;
	}
@Id
@GeneratedValue
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getHot() {
		return this.hot;
	}

	public void setHot(Boolean hot) {
		this.hot = hot;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="account_id")
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
