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
<h1>offer统计</h1>
<div style="width:600px;float:right;font-size: 14px;text-align:right;margin-top: -40px;">
<label><input type="checkbox" id="doubleSta" name="doubleSta" value="1" />间接</label>
<input type="button" value="今日" id="today"/>
<input type="button" value="一周内" id="oneWeek"/>
<input type="button" value="一月内" id="oneMonth"/>
<input type="text" id="from_date" name="from_date"  style="width:80px;" /> -
<input type="text" id="to_date" name="to_date" style="width:80px;" />
<input type="button" value="查询" id="find"/>
</div>
<table id="tableList" class="tablesorter" cellspacing="1">
	<thead>
		<tr>			
			<th>ID</th>
			<th>名称</th>
			<th>请求</th>
			<th>展示</th>
			<th>点击</th>
			<th>下载</th>
			<th>下载成功</th>
			<th>安装</th>
			<th>激活</th>
			<th>收入</th>			
		</tr>
	</thead>
	<tbody id="tbody">
		<s:iterator value="list" var="user">
			<tr>				
				<td><s:property value="#user.offerId" /></td>
				<td><s:property value="#user.offerName" /></td>
				<td><s:property value="#user.requestNum" /></td>
				<td><s:property value="#user.showNum" /></td>
				<td><s:property value="#user.clickNum" /></td>
				<td><s:property value="#user.downloadNum" /></td>
				<td><s:property value="#user.downloadSuccessNum" /></td>
				<td><s:property value="#user.installNum" /></td>
				<td><s:property value="#user.activateNum" /></td>
				<td><s:property value="#user.income" /></td>
			</tr>
		</s:iterator>
	</tbody>
</table>

<div id="my_div" title="${maxNum}">

<a id="a_1" href="#" > 上一页    </a>
<a id="a_2" href="#"> 下一页</a>

<a  herf="#">总记录数：${maxNum}</a>
</div>


<script type="text/javascript">

$(function() {
	$('#tableList').tablesorter();
	$('table tr:nth-child(even)').addClass('even');	 
});

//创建日期选择
laydate({
			istime: true,
            elem: '#from_date',
            format: 'YYYY-MM-DD'
        });
laydate({
			istime: true,
            elem: '#to_date',
            format: 'YYYY-MM-DD'
        });        
 $("#to_date").click(function(){
 	laydate.resetPosition(-100);
 });       
</script>

<script type="text/javascript" src="<%=basePath%>scripts/offerStatistics.js"></script>
