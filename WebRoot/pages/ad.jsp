<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>


<h1>切换广告</h1>

<div style="margin:20px 0px;">
<form action="ad_changeAd" method="post" style="margin: 0px;" class="g_from">
<table width="600" cellpadding="4" cellspacing="0" border="0">
<tr>
	<td width="20%">用户类型:</td>
	<td width="80%">
		<input type="radio" name="broadcast" value="all" checked="checked" />  所有在线用户 
        <input type="radio" name="broadcast" value="single" /> 单个用户 
        <input type="radio" name="broadcast" value="app" /> 应用用户 
	</td>
</tr>
<tr id="trUsername" style="display:none;">
	<td>设备ID:</td>
	<td><input type="text" id="username" name="username" value="" style="width:380px;" /></td>
</tr>
<tr id="trApp" style="display:none;">
	<td>应用名字:</td>
	<td><input type="text" id="appname" name="appname" value="" style="width:380px;" /></td>
</tr>

<tr >
	<td>广告类型:</td>
	<td>
		<select id='ad_type' name="ad_platfrom" style='width:58px' title="${requestScope.platfrom}">
		<option value='0' selected>青露</option>
		<option value='1' >有米</option>
		</select>
	</td>
</tr>
<br/>

<tr>
	
	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	<td><input type="submit" value="切换" /></td>
</tr>
</table> 
</form>
</div>

<h1>上传广告</h1>

<div style="margin:20px 0px;">
<form action="ad_uploadAd" method="post" style="margin: 0px;" enctype="multipart/form-data" class="g_from">
<table width="800" cellpadding="4" cellspacing="0" border="0">

<tr >
	<td>公司名称:</td>
	<td><input type="text" id="company" name="company" value="" style="width:380px;" /></td>
</tr>
<tr  >
	<td>广告类型:</td>
	<td>
		<select id='type' name="type" style='width:80px' >
		<option value='1' selected>插屏</option>
		<option value='2' >广告条</option>
		</select>
	</td>
</tr>

<tr  >
	<td>图片路径:</td>
	<td><input type="file" id="pic" name="pic" value="浏览" style="width:380px;" /></td>
</tr>

<tr >
	<td>下载路径:</td>
	<td><input type="text" id="downloadPath" name="downloadPath" value="" style="width:380px;" /></td>
</tr>

<tr  >
	<td>apk路径:</td>
	<td><input type="file" id="apk" name="apk" value="浏览" style="width:180px;" />注意:如果选择apk路径，下载路径将不起作用</td>
	
</tr>

<br/>

<tr>
	
	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	<td><input type="submit" value="提交" /></td>
</tr>
</table> 
</form>
</div>

<h1>${requestScope.uploadAd }</h1>

<script lanuage="javascript">
$(function() {
	$('input[name=broadcast]').click(function() {
		if ($('input[name=broadcast]')[0].checked) {
			$('#trUsername').hide();
			$('#trApp').hide();
		}
		else if ($('input[name=broadcast]')[1].checked) {
			$('#trUsername').show();
			$('#trApp').hide();
		}
		else if ($('input[name=broadcast]')[2].checked) {
			$('#trApp').show();
			$('#trUsername').hide();
		}
	});
	
	if ($('input[name=broadcast]')[0].checked) {
		$('#trUsername').hide();
		$('#trApp').hide();
	}
	else if ($('input[name=broadcast]')[1].checked) {
		$('#trUsername').show();
		$('#trApp').hide();
	}
	else if ($('input[name=broadcast]')[2].checked) {
		$('#trApp').show();
		$('#trUsername').hide();
	}
	 
});
	
	
	var ad_type = document.getElementById("ad_type");
	var platfrom = parseInt(ad_type.title);
	var options = ad_type.getElementsByTagName("option");
		
	for(var i=0;i<options.length;i++)
	{
		var option = options[i];
		if(platfrom == parseInt(option.value))
		{
			option.selected = "selected";
		} 
		else
		{
			option.selected = "";
		}
	}
	
	
</script>

