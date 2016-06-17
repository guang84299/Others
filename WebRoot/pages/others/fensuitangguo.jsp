<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
        
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<title>《粉碎糖果》</title>

<script type="text/javascript" src="<%=basePath%>scripts/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/others/common_layout.css" />

<script>  
 
</script> 
</head>
<body>

	<div id="wrap">
		
        	<a href="<%=basePath%>apks/PopStarTG2016041092.apk">
			<div class="bg1">
				<img src="<%=basePath%>images/others/fensui.jpg">
			</div>
			
        	</a>
		
	</div>
</body>

</html>
