package cn.it.shop.action;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.it.shop.model.FileImage;
import cn.it.shop.service.AccountService;
import cn.it.shop.service.CategoryService;
import cn.it.shop.service.ForderService;
import cn.it.shop.service.PayService;
import cn.it.shop.service.ProductService;
import cn.it.shop.service.SorderService;
import cn.it.shop.service.UserService;
import cn.it.shop.utils.EmailUtil;
import cn.it.shop.utils.FileUpload;
import hibernateEntity.Category;
@Controller("baseAction")
@Scope("prototype")
public class BaseAction<T> extends ActionSupport implements RequestAware, SessionAware, ApplicationAware,ModelDriven<T> {
	
	protected List<T> jsonList=null;
	
	protected FileImage fileImage;
	
	@Resource
	protected FileUpload fileUpload;
	
	public FileImage getFileImage() {
		return fileImage;
	}

	public void setFileImage(FileImage fileImage) {
		this.fileImage = fileImage;
	}

	public List<T> getJsonList() {
		return jsonList;
	}

	protected String ids;
	protected InputStream inputStream;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	protected Integer page;
	protected Integer rows;
	protected Map<String,Object> pageMap=null;
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Map<String, Object> getPageMap() {
		return pageMap;
	}

	protected Map<String,Object> application;
	protected Map<String,Object> request;
	protected Map<String,Object> session;
	protected T model;
	@Resource
	protected CategoryService categoryService;
	@Resource
	protected AccountService accountService;
	@Resource
	protected ProductService productService;
	@Resource
	protected SorderService sorderService;
	@Resource
	protected ForderService forderService;
	@Resource
	protected UserService userService;
	@Resource
	protected PayService payService;
	@Resource
	protected EmailUtil emailUtil;
	@Override
	public void setApplication(Map<String, Object> arg0) {
		this.application=arg0;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session=arg0;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request=arg0;
	}

	@Override
	public T getModel() {
		ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
		Class clazz=(Class)type.getActualTypeArguments()[0];
		try{
			model=(T)clazz.newInstance();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return model;
	}


}
