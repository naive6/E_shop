package cn.it.shop.action;

import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.BackData;
import cn.it.shop.model.Forder;
import cn.it.shop.model.SendData;
import cn.it.shop.model.User;
@Controller("payAction")
@Scope("prototype")
public class PayAction extends BaseAction<Object> implements ParameterAware {
	private Map<String,String[]> parameters;
	@Override
	public void setParameters(Map<String, String[]> arg0) {
		this.parameters=arg0;
	}
	public Object getModel(){
		//付款的时候有支付通道编码的参数，返回的时候没有
		if(parameters.get("pd_FrpId")!=null){
			model=new SendData();
		}else{
			model=new BackData();
		}
		return model;
	}
	public String goBank(){
		SendData sendData=(SendData) model;
		Forder forder=(Forder) session.get("oldForder");
		User user=(User) session.get("user");
		sendData.setP2_Order(forder.getId().toString()); //商户订单号
        sendData.setP3_Amt(forder.getTotal().toString()); //支付金额
        sendData.setPa_MP(user.getEmail() + "," + user.getPhone()); //商户扩展信息
        //2. 对参数进行追加        
        //3. 加密获取签名     
        //4. 存储到request域中
        payService.saveDataToRequest(request, sendData); //2,3,4的业务逻辑交给service层来处理
        //5. 跳转到支付页面        
        return "pay";
	}
	
	public String backBank(){
		BackData backData=(BackData) model;
		System.out.println("backData:"+model);
		boolean isOK=payService.checkBackData(backData);
		if(isOK){
			forderService.updateStatusById(Integer.parseInt(backData.getR6_Order()),2);
			String emailAddress=backData.getR8_MP().split(",")[0];
			emailUtil.sendEmail(emailAddress, backData.getR6_Order());
			System.out.println("success");
		}else{
			System.out.println("false");
		}
		return "sendSuccess";
	}
	

}
