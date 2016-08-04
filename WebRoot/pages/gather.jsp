<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"
+request.getServerPort()+path+"/";
%>
<h1 id="h3_text">app上传信息</h1>
<button id="see_runinfo">查看app运行信息 </button>	
<div id="appinfo">
	<table id="tableList" class="tablesorter" style="margin-top: 10px;" cellspacing="1">
		<thead>
			<tr>			
				<th>设备id</th>
				<th>日期</th>
				<th>包名</th>
				<th>类名</th>
				<th>是否内置</th>									
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="appInfoList" var="val">
				<tr>	
					<td style="display: none"><s:property value="#val.id" /></td>
					<td><s:property value="#val.deviceId" /></td>
					<td><s:property value="#val.gdate" /></td>
					<td><s:property value="#val.className" /></td>
					<td><s:property value="#val.pack" /></td>	
					<td><s:property value="#val.inlay" /></td>
					<td class="thUpdate"><input class="caozuo" type="button" value="操作" title="<s:property value="#val.id"/>"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	
	<div id="my_div" title="${maxNum}">
	<a id="a_0" href="#" >首页</a>
	<a id="a_1" href="#" >上一页</a>
	<a id="a_2" href="#">下一页</a>
	<a id="a_3" href="#" >尾页</a>
	<a  href="#">总记录：${maxNum}</a>
	</div>
</div>



<div id="runinfo" style="display:none;">
	<table id="tableList" class="tablesorter" style="margin-top: 10px;" cellspacing="1">
		<thead>
			<tr>			
				<th>设备id</th>
				<th>日期</th>
				<th>启动时间</th>
				<th>使用时长</th>
				<th>包名</th>									
				<th>类名</th>
				<th>是否内置</th>
				<th>是否WIFI</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="runInfoList" var="rval">
				<tr>	
					<td style="display: none"><s:property value="#rval.id" /></td>
					<td><s:property value="#rval.deviceId" /></td>
					<td><s:property value="#rval.gdate" /></td>
					<td><s:property value="#rval.startTime" /></td>
					<td><s:property value="#rval.useTime" /></td>	
					<td><s:property value="#rval.pack" /></td>
					<td><s:property value="#rval.className" /></td>
					<td><s:property value="#rval.inlay" /></td>
					<td><s:property value="#rval.wifi" /></td>
					<td class="runUpdate"><input class="runUp" type="button" value="操作" title="<s:property value="#rval.id"/>"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	
	<div id="my_div2" class="r_div" title="${rmaxNum}">
	<a id="r_0" href="#" >首页</a>
	<a id="r_1" href="#" >上一页</a>
	<a id="r_2" href="#">下一页</a>
	<a id="r_3" href="#" >尾页</a>
	<a  href="#">总记录：${rmaxNum}</a>
	</div>

</div>
	
<div id="div_update" style="display:none;position:absolute;width:100px;">
	<table style="background:#331;"class="tablesorter" cellspacing="1">
		<thead>
			<tr>			
				<th>操作</th>
			</tr>
		</thead>
		<tr><td><input type="button" value="删除" id="deleteinfo"/></td></tr>
	</table>
</div>


<div id="r_update" style="display:none;position:absolute;width:100px;">
	<table style="background:#331;"class="tablesorter" cellspacing="1">
		<thead>
			<tr>			
				<th>操作</th>
			</tr>
		</thead>
		<tr><td><input type="button" value="删除" id="deleteruninfo"/></td></tr>
	</table>
</div>
<script type="text/javascript">
</script>
<script type="text/javascript"src="<%=basePath%>scripts/gather.js"></script>