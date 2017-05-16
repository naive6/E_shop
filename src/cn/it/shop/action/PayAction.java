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
		//�����ʱ����֧��ͨ������Ĳ��������ص�ʱ��û��
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
		sendData.setP2_Order(forder.getId().toString()); //�̻�������
        sendData.setP3_Amt(forder.getTotal().toString()); //֧�����
        sendData.setPa_MP(user.getEmail() + "," + user.getPhone()); //�̻���չ��Ϣ
        //2. �Բ�������׷��        
        //3. ���ܻ�ȡǩ��     
        //4. �洢��request����
        payService.saveDataToRequest(request, sendData); //2,3,4��ҵ���߼�����service��������
        //5. ��ת��֧��ҳ��        
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
