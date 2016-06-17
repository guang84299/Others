<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>广告平台</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>styles/console.css" />
<script type="text/javascript" src="<%=basePath%>scripts/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/tablesorter/style.css" />
<script type="text/javascript" src="<%=basePath%>scripts/jquery.tablesorter.js"></script>
</head>

<body>
	<div id="page">
		<div id="header">
			<jsp:include page="/includes/header.jsp" />
		</div>
		<div id="div_session" style="position:absolute; left:90%; margin-top: -30px;">
			<s:if test="#session.admin != null">
			<a style="color: red; font-size: 12px;">
			 <s:property value="#session.admin.name" /></a>
			<a style="color: red; font-size: 12px;" href="<%=basePath%>admin_loginout"> 退出</a>
			</s:if>
		</div>		
		<div id="content">
			<ul id="tabmenu">
				<li><a href="<%=basePath%>"
					class="<s:if test="#request.pages == null">current</s:if>">主页</a></li>
					
				<s:if test="#session.admin != null">
					
					<li><a href="user_list"
						class="<s:if test="#request.pages == 'user'">current</s:if>">所有用户</a>
					</li>
					<li><a href="session_list"
						class="<s:if test="#request.pages == 'session'">current</s:if>">在线用户</a>
					</li>
					<li><a href="push_list"
						class="<s:if test="#request.pages == 'push'">current</s:if>">推送</a>
					</li>
					<li><a href="ad_list"
						class="<s:if test="#request.pages == 'ad'">current</s:if>">广告管理</a>
					</li>
					<li><a href="adStatistics_list"
						class="<s:if test="#request.pages == 'adStatistics'">current</s:if>">广告统计</a>
					</li>
					<li><a href="pushStatistics_list"
						class="<s:if test="#request.pages == 'pushStatistics'">current</s:if>">推送统计</a>
					</li>
					<li><a href="app_list"
						class="<s:if test="#request.pages == 'app'">current</s:if>">应用统计</a>
					</li>
					<li><a href="pushCompare_list"
						class="<s:if test="#request.pages == 'pushCompare'">current</s:if>">推送对比</a>
					</li>
					
					<s:if test="#session.admin.permission.model_admin == true">
						<li><a href="admin_list"
						class="<s:if test="#request.pages == 'admin'">current</s:if>">管理员</a>
						</li>
					</s:if>
				
				</s:if>
			</ul>
			<div id="tabcontent">
			
				<s:if test="#request.pages == null">
				
					<s:if test="#session.admin == null">
						<jsp:include page="/pages/login.jsp" />
					</s:if>
					<s:else>
						<jsp:include page="/pages/home.jsp" />
					</s:else>
					
				</s:if>
			
				<s:else>
					<s:if test="#session.admin == null">
						<jsp:include page="/pages/login.jsp" />
					</s:if>
					
					<s:else>
					
						<s:if test="#request.pages == 'admin'">
							<s:if test="#session.admin.permission.model_admin == true">
								<jsp:include page="/pages/${requestScope.pages }.jsp" />
							</s:if>
						</s:if>
						
						<s:else>
							<jsp:include page="/pages/${requestScope.pages }.jsp" />
						</s:else>
						
					</s:else>		
				</s:else>
			</div>
		</div>
		<div id="footer">
			<jsp:include page="/includes/footer.jsp" />
		</div>
	</div>
</body>


</html>
