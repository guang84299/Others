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
<h1>offer管理</h1>
<table  cellspacing="1">
	<tr>			
		<td>新增offer：</td>
		<td> <input type="button" id="addOffer" value="新增" />${requestScope.addOffer }</td>		
	</tr>	
	<tr>			
		<td>查看offer：</td>
		<td> <input type="button" id="findOffer" value="查看" />${requestScope.updateOffer }</td>		
	</tr>	
</table>

<div id="d_addOffer" style="display:none; ">
<h1 align="center">新增offer</h1>
<form action="offer_addOffer" method="post" style="margin: 0px;" enctype="multipart/form-data" class="g_from">
<table width="800" cellpadding="4" cellspacing="0" border="0">
<tr style="display:none; ">
	<td >ID:</td>
	<td><input type="text" id="id" name="id" value="" style="width:180px;" /></td>
</tr>
<tr >
	<td>应用名称:</td>
	<td><input type="text" id="name" name="name" value="" style="width:180px;" /></td>
</tr>

<tr  >
	<td>开屏图片:</td>
	<td><input type="file" id="openSpotPicPath" name="openSpotPic" value="浏览" style="width:180px;" /> 建议480*800</td>
</tr>

<tr  >
	<td>banner图路径:</td>
	<td><input type="file" id="bannerPicPath" name="bannerPic" value="浏览" style="width:180px;" /> 建议480*80</td>
</tr>

<tr >
	<td>下载路径:</td>
	<td><input type="text" id="apkDownloadPath" name="apkDownloadPath" value="" style="width:180px;" /></td>
</tr>

<tr >
	<td>包名:</td>
	<td><input type="text" id="apkPackageName" name="apkPackageName" value="" style="width:180px;" /></td>
</tr>

<tr  >
	<td>apk路径:</td>
	<td><input type="file" id="apk" name="apk" value="浏览" style="width:180px;" />注意:如果选择apk路径，下载路径和包名将不起作用</td>
	
</tr>

<tr  >
	<td>icon路径:</td>
	<td><input type="file" id="apk_icon_path" name="apk_icon" value="浏览" style="width:180px;" /></td>
</tr>

<tr  >
	<td>图片路径1:</td>
	<td><input type="file" id="apk_pic_path_1" name="apk_pic_1" value="浏览" style="width:180px;" /></td>	
</tr>
<tr  >
	<td>图片路径2:</td>
	<td><input type="file" id="apk_pic_path_2" name="apk_pic_2" value="浏览" style="width:180px;" /></td>	
</tr>
<tr  >
	<td>图片路径3:</td>
	<td><input type="file" id="apk_pic_path_3" name="apk_pic_3" value="浏览" style="width:180px;" /></td>	
</tr>
<tr  >
	<td>图片路径4:</td>
	<td><input type="file" id="apk_pic_path_4" name="apk_pic_4" value="浏览" style="width:180px;" /></td>	
</tr>
<tr  >
	<td>图片路径5:</td>
	<td><input type="file" id="apk_pic_path_5" name="apk_pic_5" value="浏览" style="width:180px;" /></td>	
</tr>
<tr  >
	<td>图片路径6:</td>
	<td><input type="file" id="apk_pic_path_6" name="apk_pic_6" value="浏览" style="width:180px;" /></td>	
</tr>

<tr >
	<td>开发者:</td>
	<td><input type="text" id="apk_developer" name="apk_developer" value="" style="width:180px;" /></td>
</tr>

<tr >
	<td>下载次数:</td>
	<td><input type="text" id="apk_downloads" name="apk_downloads" value="" style="width:180px;" /></td>
</tr>
<tr >
	<td>版本:</td>
	<td><input type="text" id="apk_version" name="apk_version" value="" style="width:180px;" /></td>
</tr>

<tr >
	<td>更新日期:</td>
	<td><input type="text" id="apk_updatedDate" name="apk_updatedDate" value="" style="width:180px;" /></td>
</tr>

<tr  >
	<td>简介:</td>
	<td><input type="text" id="apk_summary" name="apk_summary" value="" style="width:180px;" /></td>
</tr>

<tr >
	<td>应用描述:</td>
	<td><textarea type="text" id="apk_describe" name="apk_describe" value="" style="width:180px;height:80px;"></textarea></td>
</tr>

<tr >
	<td>应用大小:</td>
	<td><input type="text" id="apk_size" name="apk_size" value="" style="width:80px;" />M</td>
</tr>

<tr>
	
	<td>&nbsp;</td>
	<td  ><input type="submit" value="提交" /></td>
</tr>
</table> 
</form>
</div>


<div id="d_updateOffer" style="display:none; ">
<h1 align="center">更改offer</h1>
<form action="offer_updateOffer" method="post" style="margin: 0px;" enctype="multipart/form-data" class="g_from">
<table width="800" cellpadding="4" cellspacing="0" border="0">
<tr style="display:none; ">
	<td >ID:</td>
	<td><input type="text" id="update_id" name="id" value="" style="width:180px;" /></td>
</tr>
<tr >
	<td>应用名称:</td>
	<td><input type="text" id="update_name" name="name" value="" style="width:180px;" /></td>
</tr>

<tr  >
	<td>开屏图片:</td>
	<td><input type="file"  name="openSpotPic" value="浏览" style="width:180px;" /><font id="update_openSpotPicPath"></font> 建议480*800</td>
</tr>

<tr  >
	<td>banner图路径:</td>
	<td><input type="file" name="bannerPic" value="浏览" style="width:180px;" /><font id="update_bannerPicPath"></font> 建议480*80</td>
</tr>

<tr >
	<td>下载路径:</td>
	<td><input type="text" id="update_apkDownloadPath" name="apkDownloadPath" value="" style="width:180px;" /></td>
</tr>

<tr >
	<td>包名:</td>
	<td><input type="text" id="update_apkPackageName" name="apkPackageName" value="" style="width:180px;" /></td>
</tr>

<tr  >
	<td>apk路径:</td>
	<td><input type="file" name="apk" value="浏览" style="width:180px;" /><font id="update_apk"></font></td>
	
</tr>

<tr  >
	<td>icon路径:</td>
	<td><input type="file"  name="apk_icon" value="浏览" style="width:180px;" /><font id="update_apk_icon_path"></font></td>
</tr>

<tr  >
	<td>图片路径1:</td>
	<td><input type="file"  name="apk_pic_1" value="浏览" style="width:180px;" /><font id="update_apk_pic_path_1"></font></td>	
</tr>
<tr  >
	<td>图片路径2:</td>
	<td><input type="file" name="apk_pic_2" value="浏览" style="width:180px;" /><font id="update_apk_pic_path_2"></font></td>	
</tr>
<tr  >
	<td>图片路径3:</td>
	<td><input type="file" name="apk_pic_3" value="浏览" style="width:180px;" /><font id="update_apk_pic_path_3"></font></td>	
</tr>
<tr  >
	<td>图片路径4:</td>
	<td><input type="file" name="apk_pic_4" value="浏览" style="width:180px;" /><font id="update_apk_pic_path_4"></font></td>	
</tr>
<tr  >
	<td>图片路径5:</td>
	<td><input type="file" name="apk_pic_5" value="浏览" style="width:180px;" /><font id="update_apk_pic_path_5"></font></td>	
</tr>
<tr  >
	<td>图片路径6:</td>
	<td><input type="file" name="apk_pic_6" value="浏览" style="width:180px;" /><font id="update_apk_pic_path_6"></font></td>	
</tr>

<tr >
	<td>开发者:</td>
	<td><input type="text" id="update_apk_developer" name="apk_developer" value="" style="width:180px;" /></td>
</tr>

<tr >
	<td>下载次数:</td>
	<td><input type="text" id="update_apk_downloads" name="apk_downloads" value="" style="width:180px;" /></td>
</tr>
<tr >
	<td>版本:</td>
	<td><input type="text" id="update_apk_version" name="apk_version" value="" style="width:180px;" /></td>
</tr>

<tr >
	<td>更新日期:</td>
	<td><input type="text" id="update_apk_updatedDate" name="apk_updatedDate" value="" style="width:180px;" /></td>
</tr>

<tr  >
	<td>简介:</td>
	<td><input type="text" id="update_apk_summary" name="apk_summary" value="" style="width:180px;" /></td>
</tr>

<tr >
	<td>应用描述:</td>
	<td><textarea type="text" id="update_apk_describe" name="apk_describe" value="" style="width:180px;height:80px;"></textarea></td>
</tr>

<tr >
	<td>应用大小:</td>
	<td><input type="text" id="update_apk_size" name="apk_size" value="" style="width:80px;" />M</td>
</tr>

<tr>
	
	<td>&nbsp;</td>
	<td  ><input type="submit" value="提交" /></td>
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
			<th>开屏图片</th>	
			<th>banner图片</th>	
			<th>下载路径</th>	
			<th>APK包名</th>	
			<th>icon路径</th>
			<th>APK图片1</th>
			<th>APK图片2</th>
			<th>APK图片3</th>
			<th>APK图片4</th>
			<th>APK图片5</th>
			<th>APK图片6</th>
			<th>开发者</th>
			<th>APK大小</th>
			<th>APK下载次数</th>	
			<th>APK版本</th>
			<th>APK更新日期</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="list" var="val">
			<tr>				
				<td><s:property value="#val.id" /></td>
				<td><s:property value="#val.name" /></td>							
				<td><s:property value="#val.openSpotPicPath" /></td>
				<td><s:property value="#val.bannerPicPath" /></td>
				<td><s:property value="#val.apkDownloadPath" /></td>
				<td><s:property value="#val.apkPackageName" /></td>
				<td><s:property value="#val.apk_icon_path" /></td>
				<td><s:property value="#val.apk_pic_path_1" /></td>
				<td><s:property value="#val.apk_pic_path_2" /></td>
				<td><s:property value="#val.apk_pic_path_3" /></td>
				<td><s:property value="#val.apk_pic_path_4" /></td>
				<td><s:property value="#val.apk_pic_path_5" /></td>
				<td><s:property value="#val.apk_pic_path_6" /></td>				
				<td><s:property value="#val.apk_developer" /></td>
				<td><s:property value="#val.apk_size" /></td>
				<td><s:property value="#val.apk_downloads" /></td>
				<td><s:property value="#val.apk_version" /></td>
				<td><s:property value="#val.apk_updatedDate" /></td>
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
//创建日期选择
laydate({
			istime: true,
            elem: '#apk_updatedDate',
            format: 'YYYY-MM-DD'
        });
        
laydate({
		istime: true,
         elem: '#update_apk_updatedDate',
         format: 'YYYY-MM-DD'
     });
</script>

<script type="text/javascript" src="<%=basePath%>scripts/offer.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/offer.css" />
