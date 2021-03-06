<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div id="t_alluser">
<h1>广告统计</h1>

<table id="tableList" class="tablesorter" cellspacing="1">
	<thead>
		<tr>
			<th>广告ID</th>
			<th>应用名称</th>
			<th>广告类型</th>
			<th>包名</th>
			<th>竖图路径</th>
			<th>横图路径</th>
			<th>通知图路径</th>
			<th>下载路径</th>	
			<th>展示次数</th>
			<th>点击次数</th>	
			<th>下载次数</th>
			<th>安装次数</th>	
			<th>推送级别</th>
			<th>操作</th>	
		</tr>
	</thead>
	<tbody>
		<s:iterator value="list" var="val">
			<tr>
				<td><s:property value="#val.id" /></td>
				<td><s:property value="#val.company" /></td>
				<td><s:property value="#val.type" /></td>
				<td><s:property value="#val.packageName" /></td>
				<td><s:property value="#val.picPath" /></td>
				<td><s:property value="#val.picHorizontalPath" /></td>
				<td><s:property value="#val.picNotifyPath" /></td>
				<td><s:property value="#val.downloadPath" /></td>
				<td><s:property value="#val.showNum" /></td>
				<td><s:property value="#val.clickNum" /></td>
				<td><s:property value="#val.downloadNum" /></td>
				<td><s:property value="#val.installNum" /></td>	
				<td><s:property value="#val.showLevel" /></td>			
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
${requestScope.updateAdStatistics }
</div>

<center id="f_update" style="display: none;">
<h1>更改广告</h1>
	<form action="adStatistics_updateAdStatistics" method="post" class="g_from" 
	enctype="multipart/form-data" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr >
				<td>广告ID:</td>
				<td><input type="text" id="f_id" name="id"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>包名:</td>
				<td><input id="f_packageName" name="packageName"
						style="width:180px;"></input>
				</td>
			</tr>
			
			<tr  >
				<td>竖图路径:</td>
				<td><input type="file" id="f_pic" name="pic" value="浏览" style="width:280px;" /> 建议480*800  </td>
			</tr>
			
			<tr  >
				<td>横图路径:</td>
				<td><input type="file" id="f_picHorizontal" name="picHorizontal" value="浏览" style="width:280px;" /> 建议800*480  </td>
			</tr>
			
			<tr  >
				<td>通知图路径:</td>
				<td><input type="file" id="f_picNotify" name="picNotify" value="浏览" style="width:280px;" /> 建议480*80  </td>
			</tr>
			
			<tr >
				<td>推送级别:</td>
				<td><input id="f_showLevel" name="showLevel"
						style="width:180px;"></input>
				</td>
			</tr>
			
			
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="更改" />
				  </td>
			</tr>
		</table>
	</form>
</center>

<div id="div_update" style="display:none;position:absolute;width:100px;">
<table  class="tablesorter" cellspacing="1">
	<thead>
		<tr>			
			<th>操作</th>
		</tr>
	</thead>		
	<tr><td><input type="button" value="更改" id="find"/></td></tr>
	<tr><td><input type="button" value="删除" id="delete"/></td></tr>
</table>
</div>

<script lanuage="javascript">

$("#find").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll = "<%out.print(basePath); %>adStatistics_findAdStatistics?data=";
	urll = urll + data;
	var res = $.ajax({url:urll,async:false});
	var obj = res.responseText;
	var jsonobj = eval("("+obj+")");
	
	$("#f_id").val(jsonobj.id);
	$("#f_showLevel").val(jsonobj.showLevel);
	$("#f_packageName").val(jsonobj.packageName);
	$("#f_pic").parent().append(jsonobj.picPath);
	$("#f_picHorizontal").parent().append(jsonobj.picHorizontalPath);
	$("#f_picNotify").parent().append(jsonobj.picNotifyPath);
	
	$("#t_alluser").hide();
	$("#f_update").show();
	$("#div_update").hide();
});

$("#delete").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll = "<%out.print(basePath); %>adStatistics_deleteAdStatistics?data=";
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

a_1.href = "adStatistics_list?index=" + (parseInt(index)-1);
a_2.href = "adStatistics_list?index=" + (parseInt(index)+1);	
}

resf();
	
</script>

