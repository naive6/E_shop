package cn.it.shop.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.User;
import cn.it.shop.service.UserService;
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	public String login(){
		model=userService.login(model);
		if(model==null){
			session.put("error", "�û������������");
			return "login";
		}else{
			session.put("user", model);
			session.put("error", "���Ѿ���¼�����������µ�¼");
			if(session.get("goURL")==null){
				return "index";
			}else{
				return "goURL";
			}
		}
	}
	public String register(){
		String login=model.getLogin();
		List<User> list=userService.query();
		for (User user : list) {
			if(user.getLogin().equals(login)){
				session.put("error1", "�û����ѱ�ռ��");
				return "registerFail";
			}
		}
		userService.save(model);
		return "registerSuccess";
	}
}
