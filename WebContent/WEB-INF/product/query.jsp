<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/public/head.jspf" %>
<style type="text/css">  
        body {  
            margin: 1px;  
        }  
       .searchbox {  
                    margin: -3;  
                }  
    </style>  
<script type="text/javascript">
	$(function(){
		$('#dg').datagrid({
			/*  url:'datagrid_data1.json', */ 
			url:'product_queryJoinCategory.action', 
			loadMsg:'Lodaing....',
			queryParams:{name:''},
			fitColumns:true,//水平自动展开，如果设置此属性，则不会有水平滚动条，演示冻结列时，该参数不要设置  
            //显示斑马线  
            striped:true,  
            //当数据多的时候不换行  
            nowrap:true,  
            singleSelect:false, //如果为真，只允许单行显示，全显功能失效  
            //设置分页  
            pagination:true,
            pageSize:5,  
            pageList:[5,10,15,20],  
            idField:'id',
            //新增
            toolbar: [{  
                iconCls: 'icon-add',  
                text:'添加商品',  
                handler: function(){  
                   parent.$("#win").window({
                	   title:"添加商品",
                	   width:350,
                	   height:150,
                	   content:'<iframe title="商品管理" src="send_product_save.action" frameborder="0" width="100%" height="100%"/>'
                   })
                }  
            },'-',{  
                iconCls: 'icon-edit',  
                text:'更新商品',  
                handler: function(){  
                   var rows=$("#dg").datagrid("getSelections");
                   if(rows.length==0){
                	   $.messager.show({
                		   title:'错误提示',
                		   msg:'至少要选择一条记录',
                		   timeout:2000,
                		   showType:'slide'
                	   });
                   }else if(rows.length!=1){
                	   $.messager.show({
                		   title:'错误提示',
                		   msg:'每次只能更新一条记录',
                		   timeout:2000,
                		   showType:'slide'
                	   });
                   }else{
                	   parent.$("#win").window({
                		  title:'更新商品',
                		  width:350,
                		  height:250,
                		  content:'<iframe src="send_product_update.action" frameborder="0" width="100%" height="100%"/>'
                	   });
                   }
                }  
            },'-',{  
                iconCls: 'icon-remove',  
                text:'删除商品',  
                handler: function(){  
                    //判断是否有选中行记录，使用getSelections获取选中的所有行  
                    var rows = $("#dg").datagrid("getSelections");  
                    //返回被选中的行，如果没有任何行被选中，则返回空数组  
                    if(rows.length == 0) {  
                        //弹出提示信息  
                        $.messager.show({ //语法类似于java中的静态方法，直接对象调用  
                            title:'错误提示',  
                            msg:'至少要选择一条记录',  
                            timeout:2000,  
                            showType:'slide',  
                        });  
                    } else {  
                        //提示是否确认删除，如果确认则执行删除的逻辑  
                        $.messager.confirm('删除的确认对话框', '您确定要删除此项吗？', function(r){  
                            if (r){  
                            	var ids="";
                            	for(var i=0;i<rows.length;i++){
                            		ids+=rows[i].id+",";
                            	}
                            	ids=ids.substr(0,ids.lastIndexOf(","));
                            	$.post("product_deleteByIds.action",{ids:ids},function(result){
                            		if(result=="true"){
                            			$('#dg').datagrid("uncheckAll");
                            			$('#dg').datagrid("reload");
                            		}else{
                            			$.messager.show({
                            				title:'删除异常',
                            				msg:'删除失败，请检查操作',
                            				timeout:2000,
                            				showType:'slide'
                            			},"text");
                            		}
                            	})
                            }  
                        });  
                    }                         
                }  
             },'-',{ //查询按钮不是LinkButton，它有语法，但是也支持解析HTML标签  
                 text:"<input id='ss' name='serach' />"  
             }],  
            //
            rowStyler: function(index,row){
            	console.info("index"+index+","+row)
        		if (index%2==0){
        			return 'background-color:#fff;';
        		}else{
        			return 'background-color:#ff0;'; 
        		}
        	},
        	 frozenColumns:[[  
                 {field:'checkbox',checkbox:true},  
                 {field:'id',title:'商品编号',width:200}                   
             ]],  
             columns:[[
            	 {field:'name',title:'商品名称',width:100},      
                 {field:'price',title:'商品价格',width:100},  
                 {field:'remark',title:'简单描述',width:100},  
                 {field:'xremark',title:'详细描述',width:100},  
                 {field:'date',title:'上架时间',width:100},  
                 {field:'commend',title:'推荐商品',width:100,    
                     formatter: function(value,row,index){  
                         if(value) {  
                             return "<input type='checkbox' checked='checked' disabled='true'";  
                         } else {  
                             return "<input type='checkbox' disabled='true'";  
                         }  
                      }  
                 },  
                 {field:'open',title:'有效商品',width:100,    
                     formatter: function(value,row,index){  
                         if(value) {  
                             return "<input type='checkbox' checked='checked' disabled='true'";  
                         } else {  
                             return "<input type='checkbox' disabled='true'";  
                         }  
                     }  
                  },  
                 {field:'category.type',title:'所属商品类别',width:200, //category.type是商品类别  
                     formatter: function(value,row,index){  
                         if(row.category != null && row.category.type != null) {  
                             return row.category.type; //如果商品类别不为空，返回商品类别  
                         } else {  
                             return "此商品暂时未分类";  
                         }  
                      }    
                 }  
             ]]      
		});
		//把普通的文本框转化为查询搜索文本框  
        $('#ss').searchbox({   
            //触发查询事件  
            searcher:function(value,name){ //value表示输入的值  
            	$('#dg').datagrid('load',{
					name: value
				});                    //查询操作  
            },   
            prompt:'请输入搜索关键字' //默认的显示  
        });   
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>类别</title>
</head>
<body>
<table id="dg"></table>
</body>
</html>