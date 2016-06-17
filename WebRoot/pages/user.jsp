<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<h1>当前所有用户</h1>

<table id="tableList" class="tablesorter" cellspacing="1">
	<thead>
		<tr>			
			<th>在线</th>
			<th>ID</th>
			<th>用户ID</th>
			<th>设备ID</th>
			<th>手机型号</th>
			<th>手机号码</th>
			<%--<th>网络类型</th>--%>
			<th>运营商</th>
			<th>网络</th>
			<%--<th>国家</th>--%>
			<th>系统版本</th>
			<th>省份</th>
			<th>城市</th>
			<th>总在线时长</th>
			<th>上次在线时长</th>
			<th>登录日期</th>	
			<th>注册日期</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="userList" var="user">
			<tr>
				<td align="center">
				<s:if test="#user.online == true"><img src="images/user-online.png" /></s:if>
				<s:else><img src="images/user-offline.png" /></s:else>			
				</td>
				<td><s:property value="#user.id" /></td>
				<td><s:property value="#user.name" /></td>
				<td><s:property value="#user.deviceId" /></td>
				<td><s:property value="#user.model" /></td>
				<td><s:property value="#user.phoneNumber" /></td>
				<td><s:property value="#user.networkOperatorName" /></td>
				<td><s:property value="#user.networkType" /></td>
				<td><s:property value="#user.release" /></td>
				<td><s:property value="#user.province" /></td>
				<td><s:property value="#user.city" /></td>
				<td><s:property value="#user.onlineTime" />分钟</td>
				<td><s:property value="#user.lastOnlineTime" />分钟</td>
				
				<td align="center"><s:date name="#user.updatedDate" format="yyyy-MM-dd HH:mm:ss" /></td>
				<td align="center"><s:date name="#user.createdDate" format="yyyy-MM-dd HH:mm:ss" /></td>
				<td class="thUpdate"><input type="button" value="操作"/></td>
			</tr>
		</s:iterator>
	</tbody>
</table>

<div id="my_div" title="${maxNum}">

<a id="a_1" href="#" > 上一页    </a>
<a id="a_2" href="#"> 下一页</a>

<a  herf="#">总记录数：${maxNum}</a>
</div>

<div id="div_update" style="display:none;position:absolute;width:100px;">
<table  class="tablesorter" cellspacing="1">
	<thead>
		<tr>			
			<th>操作</th>
		</tr>
	</thead>		
	<tr><td><input type="button" value="删除" id="delete"/></td></tr>
</table>
</div>

<script type="text/javascript">
$(function() {
	$('#tableList').tablesorter();
	$('table tr:nth-child(even)').addClass('even');	 
});

$("#delete").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll = "<%out.print(basePath); %>user_deleteUser?data=";
	urll = urll + data;
	$.ajax({url:urll,async:false});
	$("#div_update").hide();
	location.reload();
});

$(".thUpdate").click(function(){	
	var x = $(this).offset().top; 
	var y = $(this).offset().left - 100; 
	var div = $("#div_update");
	div.css("left",y + "px"); 
	div.css("top",x + "px");
	var preall = $(this).prevAll();
	var id = preall[preall.length-2].innerHTML;
	
	div.attr("title",id);
	div.show();
});

$("html").mousedown(function(e){
	var div = $("#div_update");
	
	if(div.css('display') != "none")
	{
		var w = div.width();
		var h = div.height();
		
		var left =  div.offset().left;
		var top = div.offset().top;
		if(e.pageX <= left+w && e.pageX >= left && e.pageY >= top && e.pageY <= top + h)
		{
			return;			
		}
		else
		{
			div.hide();
		}
	}
});

var div = document.getElementById("my_div");
var a_1 = document.getElementById("a_1");
var a_2 = document.getElementById("a_2");

var resf = function()
{
var maxNum = div.title;
var maxIndex = Math.ceil(maxNum / 20)-1;
var index = location.href.split("=")[1];

if(!index || index > maxIndex)
index = 0;

if(index == 0)
{
	a_1.style.display = "none";
}
else
{
	a_1.style.display = "";
}
if(index >= maxIndex)
{
	a_2.style.display = "none";
}
else
{
	a_2.style.display = "";
}

a_1.href = "user_list?index=" + (parseInt(index)-1);
a_2.href = "user_list?index=" + (parseInt(index)+1);	
}

resf();
</script>
