<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript"
	src="<%=basePath%>scripts/laydate.dev.js"></script>
<h1>媒体管理</h1>

<table  cellspacing="1">
	<tr>			
		<td>新增媒体：</td>
		<td> <input type="button" id="addMedia" value="新增" />${requestScope.addMedia }</td>		
	</tr>	
	<tr>			
		<td>查看媒体：</td>
		<td> <input type="button" id="findMedia" value="查看" />${requestScope.updateMedia }</td>		
	</tr>	
</table>

<div id="d_addMedia" style="display:none; ">
<h1 align="center">新增媒体</h1>
	<form action="media_addMedia" method="post" class="g_from" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr >
				<td>名称:</td>
				<td><input type="text" id="name" name="name"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>包名:</td>
				<td><input type="text" id="packageName" name="packageName"
					value="" style="width:180px;" />
				</td>
			</tr>
						
			<tr>
				<td>&nbsp;</td>
				<td align=center><input type="submit" value="添加" />
				 </td>
			</tr>
		</table>
	</form>
</div>


<div id="d_updateMedia" style="display:none; ">
<h1 align="center">更改配置</h1>
	<form action="media_updateMedia" method="post" class="g_from" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr style="display:none; ">
				<td>ID:</td>
				<td><input type="text" id="update_id" name="id"
					value="" style="width:80px;" />
				</td>
			</tr>
			
			<tr >
				<td>名称:</td>
				<td><input type="text" id="update_name" name="name"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>包名:</td>
				<td><input type="text" id="update_packageName" name="packageName"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
				<td align=center><input type="submit" value="更改" />
				 </td>
			</tr>
		</table>
	</form>
</div>

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

<div style="display:none; " id="div_table">
<table id="tableList" class="tablesorter" cellspacing="1"  >
	<thead>
		<tr>			
			<th>ID</th>
			<th>名称</th>
			<th>包名</th>									
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="list" var="val">
			<tr>				
				<td><s:property value="#val.id" /></td>
				<td><s:property value="#val.name" /></td>				
				<td><s:property value="#val.packageName" /></td>
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

</div>

<script type="text/javascript">

$(function() {
	$('#tableList').tablesorter();
	$('table tr:nth-child(even)').addClass('even');	 
});

</script>

<script type="text/javascript" src="<%=basePath%>scripts/media.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/media.css" />
