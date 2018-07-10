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
	<!-- 方式一：将静态HTML渲染为datagrid样式 -->
	<table class="easyui-datagrid">
		<thead>
			<tr>
				<th data-options="field:'id'">编号</th>
				<th data-options="field:'name'">姓名</th>
				<th data-options="field:'age'">年龄</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>001</td>
				<td>小明</td>
				<td>90</td>
			</tr>
			<tr>
				<td>002</td>
				<td>老王</td>
				<td>3</td>
			</tr>
		</tbody>
	</table>
	
	<!-- 方式三，调用easyui的api进行渲染 -->
	<table id="myTable"></table>
	<script type="text/javascript">
	 $(function(){
		//页面加载完成就渲染表格
			$("#myTable").datagrid({
					columns:[[
					         {title:"编号",field:"id",checkbox:true},
					         {title:"姓名",field:"name"},
					         {title:"学号",field:"studentNumber"},
					         {title:"性别",field:"gender"}
					         ]],
					 url:'${pageContext.request.contextPath}/data.json',
					 
					 rownumbers:true,
					 
					 toolbar:
						 [{
							text:'Add',
							iconCls:'icon-add',
							handler:function(){alert('add')}
						   },
						   {
							text:'Cut',
							iconCls:'icon-cut',
							handler:function(){alert('cut')}
						   },'-',
						   {
							text:'Save',
							iconCls:'icon-save',
							handler:function(){alert('save')}
							}],
							
					  pagination:true
			
			}); 
		
				
	 })
		
	 
	</script>
	
	<hr>
	<table id="myTable2"></table>
	<script type="text/javascript">
		window.onload = function(){
			$("#myTable2").datagrid({
				columns:[[
				          {title:"姓名",field:"name",checkbox:true},
				          {title:"学号",field:"card"},
				          {title:"性别",field:"gender"}
				          
				          ]],
			url:'${pageContext.request.contextPath}/json/datagrid_data.json',
			
			});
		}
	
	</script>
	
</body>
</html>