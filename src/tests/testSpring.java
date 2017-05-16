package tests;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.ContextLoaderListener;

import cn.it.shop.model.Account;
import cn.it.shop.model.Product;
import cn.it.shop.service.AccountService;
import cn.it.shop.service.CategoryService;
import cn.it.shop.service.ProductService;
import cn.it.shop.service.SorderService;
import cn.it.shop.service.impl.CategoryServiceImpl;
import cn.it.shop.utils.EmailUtil;
import cn.it.shop.utils.FileUpload;
import cn.it.shop.utils.impl.FileUploadUtil;
import hibernateEntity.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:beans.xml")
public class testSpring {
	@Resource
	private CategoryService categoryService;
	@Test
	public void  test(){
		List<Category> list=categoryService.query();
		for (Category category : list) {
			System.out.println(category);
			System.out.println(category.getAccount());
		}
	}
}
