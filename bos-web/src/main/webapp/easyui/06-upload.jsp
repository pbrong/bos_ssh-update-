<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ocupload-1.1.2.js"></script>
</head>
<body>
	<input id="myButton" type="button" value="submit">
	<script type="text/javascript">
		$(function(){
			$("#myButton").upload({
				action:'xxx.action',
				name:'myFile'
			});
		});
	</script>
</body>
</html>