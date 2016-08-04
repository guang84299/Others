var baseUrl =  window.location.protocol + "//" + window.location.host;


$("#addOffer").click(function(){
	var d_addOffer = $("#d_addOffer");
	if(d_addOffer.css("display") == "none")
	{
		d_addOffer.css("display","");
		$("#div_table").hide();
		$("#d_updateOffer").hide();
	}
	else
	{
		d_addOffer.css("display","none");
	}
});


$("#findOffer").click(function(){
	$("#d_addOffer").hide();
	$("#d_updateOffer").hide();
	$("#div_table").show();
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

$("#find").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll = baseUrl + "/offer_findOffer?data=";
	urll = urll + data;
	var res = $.ajax({url:urll,async:false});
	var obj = res.responseText;
	var jsonobj = eval("("+obj+")");
	
	$("#update_id").val(jsonobj.id);
	$("#update_name").val(jsonobj.name);	
	$("#update_apkDownloadPath").val(jsonobj.apkDownloadPath);
	$("#update_apkPackageName").val(jsonobj.apkPackageName);
	$("#update_apk_developer").val(jsonobj.apk_developer);
	$("#update_apk_downloads").val(jsonobj.apk_downloads);
	$("#update_apk_version").val(jsonobj.apk_version);
	$("#update_apk_updatedDate").val(jsonobj.apk_updatedDate);
	$("#update_apk_summary").val(jsonobj.apk_summary);
	$("#update_apk_describe").val(jsonobj.apk_describe);
	$("#update_apk_size").val(jsonobj.apk_size);
	$("#update_openSpotPicPath").text(jsonobj.openSpotPicPath);
	$("#update_bannerPicPath").text(jsonobj.bannerPicPath);
	$("#update_apk").text(jsonobj.apkDownloadPath);
	$("#update_apk_icon_path").text(jsonobj.apk_icon_path);
	$("#update_apk_pic_path_1").text(jsonobj.apk_pic_path_1);
	$("#update_apk_pic_path_2").text(jsonobj.apk_pic_path_2);
	$("#update_apk_pic_path_3").text(jsonobj.apk_pic_path_3);
	$("#update_apk_pic_path_4").text(jsonobj.apk_pic_path_4);
	$("#update_apk_pic_path_5").text(jsonobj.apk_pic_path_5);
	$("#update_apk_pic_path_6").text(jsonobj.apk_pic_path_6);
	
	
	$("#d_addOffer").hide();
	$("#d_updateOffer").show();
	$("#div_table").hide();
	$("#div_update").hide();
});

$("#delete").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll =  baseUrl +"/offer_deleteOffer?data=";
	urll = urll + data;
	$.ajax({url:urll,async:false});
	$("#div_update").hide();
	location.href = baseUrl + "/offer_list";
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
if(index >= maxIndex )
{
	a_2.style.display = "none";
}
else
{
	a_2.style.display = "";
}

a_1.href = "config_list?index=" + (parseInt(index)-1);
a_2.href = "config_list?index=" + (parseInt(index)+1);	
};

resf();