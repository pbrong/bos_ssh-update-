<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>layout</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js""></script>

</head>
<body >
	<!-- 方式三，调用easyui的api进行渲染 -->
	<table id="myTable"></table>
	<script type="text/javascript">
	 $(function(){
		 //定义被编辑的行号
		 var rowNumber = 0;
		//页面加载完成就渲染表格
			$("#myTable").datagrid({
					columns:[[
					         {title:"编号",field:"id",checkbox:true},
					         {title:"姓名",field:"name",width:100,editor:{
					        	 								type:'validatebox',
					        	 								options:{}
					         								   }},
					         {title:"学号",field:"studentNumber",width:100,editor:{
								 								type:'numberbox',
								 								options:{}
							 								   }},
					         {title:"性别",field:"gender",width:100,editor:{
								 								type:'validatebox',
								 								options:{}
							 								   }}
					         ]],
					 url:'${pageContext.request.contextPath}/data.json',
					 
					 rownumbers:true,
					 singleSelect:true,
					 toolbar:
						 [{
							text:'添加数据',
							iconCls:'icon-add',
							handler:function(){
								$("#myTable").datagrid("insertRow",{
																	index:0,
																	row:{}
																	});	
								$("#myTable").datagrid("beginEdit",0);
								
												}
						   },
						   {
							text:'编辑数据',
							iconCls:'icon-edit',
							handler:function(){
								var hasSelect = $("#myTable").datagrid("getSelections");
								if(hasSelect.length == 1){
									//alert(hasSelect[0].name);
									var row = hasSelect[0];
									rowNumber = $("#myTable").datagrid("getRowIndex",row);
									$("#myTable").datagrid("beginEdit",rowNumber);
								}
								
								
							}
						   },
						   {
							text:'保存修改',
							iconCls:'icon-save',
							handler:function(){
								$("#myTable").datagrid("endEdit",rowNumber);
							}
							}],
							
					  pagination:true,
					  onAfterEdit:function(index,data,changes){
						 /*  alert(index);
						  console.info(data); */
						  $.post(
								"${pageContext.request.contextPath}/"	  
						  );
					  }
					 
			
			}); 
		
				
	 })
		
	 
	</script>
	
	
</body>
</html>