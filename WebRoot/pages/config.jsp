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
<h1>配置管理</h1>

<table  cellspacing="1">
	<tr>			
		<th>新增配置：</th>
		<th> <input type="button" id="addConfig" value="新增" /></th>		
	</tr>		
</table>

<div id="d_addConfig" style="display:none; ">
<h1>新增配置</h1>
	<form action="config_addConfig" method="post" class="g_from" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr >
				<td>配置名称:</td>
				<td><input type="text" id="name" name="name"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>是否开启:</td>
				<td width="80%"><input type="radio" id="open_state1"
					name="open_state" value="1" checked="checked" /> 是 <input
					type="radio" id="open_state2" name="open_state" value="0" /> 否</td>
			</tr>
			
			<tr >
				<td>应用和包名:</td>
				<td><textarea type="text" id="whiteList" name="whiteList" value="" style="width:380px;height:80px;"></textarea></td>
			</tr>
			
			<tr >
				<td>每天广告展示次数:</td>
				<td><input type="text" id="showNum" name="showNum"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>广告时间间隔:</td>
				<td><input type="text" id="showTimeInterval" name="showTimeInterval"
					value="" style="width:180px;" />
				</td>
			</tr>
									
			<tr >
				<td>媒体开关：</td>
				<td><input type="text" id="appSwitch" name="appSwitch"
					value="媒体开关1 媒体开关2 媒体开关3 媒体开关4 媒体开关5" style="width:180px;" />*空格分开</td>
			</tr>
			
			<tr >
				<td>广告位开关：</td>
				<td >
				<label><input type="checkbox" id="adPositionSwitch1" name="adPositionSwitch" value="1" />开屏</label>
				<label><input type="checkbox" id="adPositionSwitch2" name="adPositionSwitch" value="1" />banner</label> 
				<label><input type="checkbox" id="adPositionSwitch3" name="adPositionSwitch" value="1" />充电锁</label> 
				<label><input type="checkbox" id="adPositionSwitch4" name="adPositionSwitch" value="1" />快捷方式</label> 
				<label><input type="checkbox" id="adPositionSwitch5" name="adPositionSwitch" value="1" />浏览器截取</label> 
				<label><input type="checkbox" id="adPositionSwitch6" name="adPositionSwitch" value="1" />安装卸载</label>  
				</td>
			</tr>
			
			<tr >
				<td>时间段：</td>
				<td>
					<input type="button" id="addTimeSlot1" name="addTimeSlot" value="添加日期时间段" />
					<input type="button" id="addTimeSlot2" name="addTimeSlot" value="添加星期时间段" />
				</td>
			</tr>
			<tr id="tr_sel_date" style="display:none; ">
				<td>选择日期:</td>
				<td>
				<select id='sel_date'style='width:88px'></select> 
				时<select id='sel_hours'  style='width:48px'></select>
				分<select id='sel_minute'  style='width:48px'></select>
				----时<select id='sel_hours_end'  style='width:48px'></select>
				分<select id='sel_minute_end'  style='width:48px'></select>
				<input type="button" id="tr_sel_date_add"  value="添加" />
				</td>
			</tr>
			<tr id="tr_sel_day" style="display:none; ">
				<td>选择日期:</td>
				<td>
				<select id='sel_day'style='width:88px'></select> 
				时<select id='sel_hours2'  style='width:48px'></select>
				分<select id='sel_minute2'  style='width:48px'></select>
				----时<select id='sel_hours_end2'  style='width:48px'></select>
				分<select id='sel_minute_end2'  style='width:48px'></select>
				<input type="button" id="tr_sel_day_add"  value="添加" />
				</td>
			</tr>
			<tr >
				<td>当前时间段：</td>
				<td id="td_timeSlot">
				</td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="添加" />
				 ${requestScope.addConfig }</td>
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

<table id="tableList" class="tablesorter" cellspacing="1"  style="display:none; ">
	<thead>
		<tr>			
			<th>ID</th>
			<th>配置名称</th>
			<th>是否打开</th>									
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="list" var="val">
			<tr>				
				<td><s:property value="#val.id" /></td>
				<td><s:property value="#val.name" /></td>
				
				<td align="center">				
				<s:if test="#val.open == true">是</s:if>
				<s:else>否</s:else>			
				</td>
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

<script type="text/javascript">

$(function() {
	$('#tableList').tablesorter();
	$('table tr:nth-child(even)').addClass('even');	 
});

</script>

<script type="text/javascript" src="<%=basePath%>scripts/config.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/config.css" />
