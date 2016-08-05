var baseUrl =  window.location.protocol + "//" + window.location.host;


$("#addAdPosition").click(function(){
	var d_addAdPosition = $("#d_addAdPosition");
	if(d_addAdPosition.css("display") == "none")
	{
		d_addAdPosition.css("display","");
		$("#div_table").hide();
		$("#d_updateAdPosition").hide();
	}
	else
	{
		d_addAdPosition.css("display","none");
	}
});


$("#findAdPosition").click(function(){
	$("#d_addAdPosition").hide();
	$("#d_updateAdPosition").hide();
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
	
	var urll = baseUrl + "/adPosition_findAdPosition?data=";
	urll = urll + data;
	var res = $.ajax({url:urll,async:false});
	var obj = res.responseText;
	var jsonobj = eval("("+obj+")");
	
	$("#update_id").val(jsonobj.id);
	$("#update_name").val(jsonobj.name);
	$("#update_type").val(jsonobj.type);
	
	$("#d_addAdPosition").hide();
	$("#d_updateAdPosition").show();
	$("#div_table").hide();
	$("#div_update").hide();
});

$("#delete").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll =  baseUrl +"/adPosition_deleteAdPosition?data=";
	urll = urll + data;
	$.ajax({url:urll,async:false});
	$("#div_update").hide();
	location.href = baseUrl + "/adPosition_list";
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

a_1.href = "adPosition_list?index=" + (parseInt(index)-1);
a_2.href = "adPosition_list?index=" + (parseInt(index)+1);	
};

resf();