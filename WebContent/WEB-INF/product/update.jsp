<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/public/head.jspf"%>
<style type="text/css">
form div {
	margin: 5px;
}
</style>
<script type="text/javascript">
	$(function() {
		$.extend($.fn.validatebox.defaults.rules,{
			format:{
				validator:function(value,param){
					var ext=value.substring(value.lastIndexOf('.')+1);
					var arr=param[0].split(",");
					for(var i=0;i<arr.length;i++){
						if(ext==arr[i]){
							return true;
						}
					}
					return false;
				},
				message:'文件后缀必须为:{0}'
			}
		});   
		//iframe中的datagrid对象  
		var dg = parent.$("iframe[title='商品管理']").get(0).contentWindow.$("#dg");

		//对管理员的下拉列表框进行远程加载  
		$("#cc").combobox({
			//将请求发送给accountAction中的query方法处理，这里需要将处理好的数据返回到这边来显示了 ，所以后台需要将数据打包成json格式发过来  
			url : 'category_query.action',
			valueField : 'id',
			textField : 'type', //我们下拉列表中显示的是管理员的登录名  
			panelHeight : 'auto', //自适应高度  
			panelWidth : 120,//下拉列表是两个组件组成的  
			width : 120, //要同时设置两个宽度才行  
			editable : false,
			required:true,
			missingMessage:'请选择所属类别'  
		});

		// 完成数据的回显，更新时，用户肯定先选择了要更新的那一行，首先我们得拿到那一行  
		var rows = dg.datagrid("getSelections");
		//将拿到的那一行对应的数据字段加载到表单里，实现回显  
		$("#ff").form('load', {
			id:rows[0].id,
			name:rows[0].name,
			price:rows[0].price,
			remark:rows[0].remark,
			xremark:rows[0].xremark,
			commend:rows[0].commend,
			open:rows[0].open,
			'category.id':rows[0].category.id
		//EasyUI不支持account.id这种点操作，所以要加个引号  
		});

		//回显完了数据后，设置一下验证功能  
		$("input[name=name]").validatebox({  
                required:true,  
                missingMessage:'请输入类别名称'  
            });   
            $("input[name=price]").numberbox({  
                required:true,  
                missingMessage:'请输入商品价格',  
                min:0,  
                precision:2, //保留两位小数  
                prefix:'$'  
            });  
            $("input[name='fileImage.upload']").validatebox({  
                required:true,  
                missingMessage:'请上传商品图片',  
                //设置自定义方法  
                validType:"format['gif,jpg,jpeg,png']"//中括号里面是参数  
            });  
      
            $("textarea[name=remark]").validatebox({  
                required:true,  
                missingMessage:'请输入商品的简单描述'  
            });  
              
            $("textarea[name=xremark]").validatebox({  
                required:true,  
                missingMessage:'请输入商品的简单描述'  
            });  
		//窗体弹出默认时禁用验证  
		$("#ff").form("disableValidation");
		//注册button的事件  
		$("#btn").click(function() {
			//开启验证  
			$("#ff").form("enableValidation");
			//如果验证成功，则提交数据  
			if ($("#ff").form("validate")) {
				//调用submit方法提交数据  
				$("#ff").form('submit', {
					url : 'product_update.action', //提交时将请求传给categoryAction的update方法执行  
					success : function() {
						//如果成功了，关闭当前窗口，并刷新页面  
						parent.$("#win").window("close");
						dg.datagrid("reload");
					}
				});
			}
		});
	});
</script>
</head>
<body>
	<form title="更新商品" id="ff" method="post" enctype="multipart/form-data">
		<div>
			<label>商品名称：</label><input type="text" name="name" />
		</div>
		<div>
			<label>商品价格：</label><input type="text" name="price" />
		</div>
		<div>
			<label>更新图片：</label><input type="file" name="fileImage.upload" />
		</div>
		<div>
			<label>所属类别:</label>
			<input id="cc" name="category.id"/>
		</div>
		<div>
			<label>简单描述：</label>
			<textarea name="remark" rows="4" cols="40"></textarea>
		</div>
		<div>
			<label>详细描述：</label>
			<textarea name="xremark" rows="8" cols="40"></textarea>
		</div>
		<div>
			<label>加入推荐：</label>
			推荐：<input type="radio" name="commend" checked="checked" value="true" /> 
			不推荐：<input type="radio" name="commend" value="false" />
		</div>
		<div>
			<label>是否有效：</label>
			上架：<input type="radio" name="open" checked="checked" value="true" /> 
			下架：<input type="radio" name="open" value="false" />
		</div>
		<div>
			<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">更新</a>
			<input type="hidden" name="id"/>
		</div>
	</form>
</body>
</html>