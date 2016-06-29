<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<h1>推送统计</h1>

<table id="tableList" class="tablesorter" cellspacing="1">
	<thead>
		<tr>
			<th>ID</th>
			<th>广告ID</th>
			<th>推送类型</th>
			<th>用户类型</th>	
			<th>推送人数</th>	
			<th>展示次数</th>
			<th>点击次数</th>	
			<th>下载次数</th>
			<th>安装次数</th>	
			<th>推送时间</th>	
			<th>操作</th>		
		</tr>
	</thead>
	<tbody>
		<s:iterator value="list" var="val">
			<tr>
				<td><s:property value="#val.id" /></td>
				<td><s:property value="#val.adId" /></td>
				<td>
					<s:if test="#val.type == 0">消息</s:if>
					<s:elseif test="#val.type == 1">插屏</s:elseif>
					<s:elseif test="#val.type == 2">大图消息</s:elseif>
				</td>
				<td>
					<s:if test="#val.userType == 0">所有在线</s:if>
					<s:elseif test="#val.userType == 1">应用用户</s:elseif>
					<s:elseif test="#val.userType == 2">单个用户</s:elseif>
					<s:else>其他用户</s:else>
				</td>
				<td><s:property value="#val.sendNum" /></td>
				<td><s:property value="#val.showNum" /></td>
				<td class="tdclickNum"><s:property value="#val.clickNum" /></td>
				<td class="tddownloadNum"><s:property value="#val.downloadNum" /></td>
				<td class="tdinstallNum"><s:property value="#val.installNum" /></td>
				<td align="center"><s:date name="#val.createdDate" format="yyyy-MM-dd HH:mm:ss" /></td>		
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

<div id="my_div2" style="display:none;position:absolute;width:100px;">
<table  class="tablesorter" cellspacing="1">
	<thead>
		<tr>			
			<th>用户ID</th>
		</tr>
	</thead>
	<tbody id="div2_body">		
	</tbody>
	<tr>
		<td>广告ID:<input type="text" id="adId" name="adId" value="" style="width:20px;" /></td>		
	</tr>
	<tr><td><input type="button" value="推送" id="tuisong"/></td></tr>
</table>
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

<script lanuage="javascript">

$("#delete").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll = "<%out.print(basePath); %>pushStatistics_deletePushStatistics?data=";
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
	var id = preall[preall.length-1].innerHTML;
	
	div.attr("title",id);
	div.show();
});

$("#tuisong").click(function()
{
	var data = $("#my_div2").attr("title").split(",");
	var adId = $("#adId").attr("value");
	var pushId = data[0];
	var type = data[1];
	
	var urll = "<%out.print(basePath); %>push_sendClickDownloadInstallAd?pushId=";
	urll = urll + pushId + "&adId="+adId+"&type="+type;
	$.ajax({url:urll,async:false});
	$("#my_div2").hide();
	
	location.reload();
});

var myHide = function(div,e)
{
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
}

$("html").mousedown(function(e){
	var div = $("#my_div2");
	
	myHide(div,e);
	myHide($("#div_update"),e);
});

$(".tdclickNum").mousedown(function(e){ 
 // 1 = 鼠标左键 left; 2 = 鼠标中键; 3 = 鼠标右键 
	if(e.which == 1)
	{
		var x = $(this).offset().top; 
		var y = $(this).offset().left - 125; 
		var my_div2 = $("#my_div2");
		my_div2.css("left",y + "px"); 
		my_div2.css("top",x + "px");
		var preall = $(this).prevAll();
		var pushId = preall[preall.length-1].innerHTML;
		var urll = "<%out.print(basePath); %>pushStatistics_getUserPushByClick?data=";
		urll = urll + pushId;
		
		htmlobj=$.ajax({url:urll,async:false});
		var arr = htmlobj.responseText;
		var jsonarr = eval("("+arr+")"); 
		$("#div2_body").html("");
		for(var i=0;i<jsonarr.length;i++)
		{
			var item = jsonarr[i];
			var str = "<tr><td>" + item.userId + "</td></tr>";
			$("#div2_body").append(str);
		}
		$("#my_div2").attr("title",pushId+",1");
		$("#my_div2").show();
	}
	return false;//阻止链接跳转 
});

$(".tddownloadNum").mousedown(function(e){ 
 // 1 = 鼠标左键 left; 2 = 鼠标中键; 3 = 鼠标右键 
	if(e.which == 1)
	{
		var x = $(this).offset().top ; 
		var y = $(this).offset().left - 125; 
		var my_div2 = $("#my_div2");
		my_div2.css("left",y + "px"); 
		my_div2.css("top",x + "px");
		var preall = $(this).prevAll();
		var pushId = preall[preall.length-1].innerHTML;
		var urll = "<%out.print(basePath); %>pushStatistics_getUserPushByDownload?data=";
		urll = urll + pushId;
		
		htmlobj=$.ajax({url:urll,async:false});
		var arr = htmlobj.responseText;
		var jsonarr = eval("("+arr+")"); 
		$("#div2_body").html("");
		for(var i=0;i<jsonarr.length;i++)
		{
			var item = jsonarr[i];
			var str = "<tr><td>" + item.userId + "</td></tr>";
			$("#div2_body").append(str);
		}
		$("#my_div2").attr("title",pushId+",2");
		$("#my_div2").show();
	}
	return false;//阻止链接跳转 
});

$(".tdinstallNum").mousedown(function(e){ 
 // 1 = 鼠标左键 left; 2 = 鼠标中键; 3 = 鼠标右键 
	if(e.which == 1)
	{
		var x = $(this).offset().top ; 
		var y = $(this).offset().left - 125; 
		var my_div2 = $("#my_div2");
		my_div2.css("left",y + "px"); 
		my_div2.css("top",x + "px");
		var preall = $(this).prevAll();
		var pushId = preall[preall.length-1].innerHTML;
		var urll = "<%out.print(basePath); %>pushStatistics_getUserPushByInstall?data=";
		urll = urll + pushId;
		
		htmlobj=$.ajax({url:urll,async:false});
		var arr = htmlobj.responseText;
		var jsonarr = eval("("+arr+")"); 
		$("#div2_body").html("");
		for(var i=0;i<jsonarr.length;i++)
		{
			var item = jsonarr[i];
			var str = "<tr><td>" + item.userId + "</td></tr>";
			$("#div2_body").append(str);
		}
		$("#my_div2").attr("title",pushId+",3");
		$("#my_div2").show();
	}
	return false;//阻止链接跳转 
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

a_1.href = "pushStatistics_list?index=" + (parseInt(index)-1);
a_2.href = "pushStatistics_list?index=" + (parseInt(index)+1);	
}

resf();
	
</script>

