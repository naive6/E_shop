package cn.it.shop.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.it.shop.model.Product;
import cn.it.shop.service.CategoryService;
import cn.it.shop.service.ProductService;
import cn.it.shop.utils.FileUpload;
import cn.it.shop.utils.impl.ProductTimerTask;
import hibernateEntity.Category;

public class InitDataListener implements ServletContextListener {
	private ProductTimerTask productTimerTask=null;
	private ApplicationContext context=null;
	private FileUpload fileUpload=null;
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
//		ProductService productService=(ProductService) context.getBean("productService");
//		CategoryService categoryService=(CategoryService) context.getBean("categoryService");
//		System.out.println(productService);
//		System.out.println(categoryService);
//		List<List<Product>> bigList=new ArrayList<List<Product>>();
//		for (Category category : categoryService.queryByHot(true)) {
//			List<Product> lst=productService.queryByCategoryId(category.getId());
//			bigList.add(lst);
//		}
//		event.getServletContext().setAttribute("bigList", bigList);
		
		fileUpload=(FileUpload) context.getBean("fileUpload");
		event.getServletContext().setAttribute("bankImageList", fileUpload.getBankImage());
		
		productTimerTask=(ProductTimerTask) context.getBean("productTimerTask");
		productTimerTask.setApplication(event.getServletContext());
		new Timer(true).scheduleAtFixedRate(productTimerTask, 0, 1000*60*60);
	}

}
