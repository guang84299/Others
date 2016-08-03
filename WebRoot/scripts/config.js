$("#addConfig").click(function(){
	var d_addConfig = $("#d_addConfig");
	if(d_addConfig.css("display") == "none")
	{
		d_addConfig.css("display","");
	}
	else
	{
		d_addConfig.css("display","none");
	}
});

$("#addTimeSlot1").click(function(){
	$("#tr_sel_date").show();
	$("#tr_sel_day").hide();
	var sel_date = $("#sel_date");
	var sel_hours = $("#sel_hours");
	var sel_minute = $("#sel_minute");
	var sel_hours_end = $("#sel_hours_end");
	var sel_minute_end = $("#sel_minute_end");
	
	var myDate = new Date();
	var year = myDate.getFullYear();   
	var month = myDate.getMonth() + 1;       
	var date = myDate.getDate(); 
	var d = new Date(year,month,0).getDate();
	var str = year+"-"+month+"-";	
	for(var i = date;i <= d;i++)
	{
		var s = "<option value='" + str+i + "' selected>" +str+i + "</option>";
		sel_date.append(s);	
	}
	sel_date.val("");
	
	for(var i = 0;i <= 23;i++)
	{
		var s = "<option value='" + i + "' selected>" + i + "</option>";
		sel_hours.append(s);	
		sel_hours_end.append(s);
	}
	sel_hours.val("");
	sel_hours_end.val("");
	
	for(var i = 0;i <= 59;i++)
	{
		var s = "<option value='" + i + "' selected>" + i + "</option>";
		sel_minute.append(s);	
		sel_minute_end.append(s);	
	}
	sel_minute.val("");
	sel_minute_end.val("");
});

$("#addTimeSlot2").click(function(){
	$("#tr_sel_date").hide();
	$("#tr_sel_day").show();
	var sel_day = $("#sel_day");
	var sel_hours = $("#sel_hours2");
	var sel_minute = $("#sel_minute2");
	var sel_hours_end = $("#sel_hours_end2");
	var sel_minute_end = $("#sel_minute_end2");
	
	var arr = ["星期一","星期二","星期三","星期四","星期五","星期六","星期日"];
	for(var i = 0;i < 7;i++)
	{
		var s = "<option value='" + i + "' selected>" + arr[i] + "</option>";
		sel_day.append(s);	
	}
	sel_day.val("");
	
	for(var i = 0;i <= 23;i++)
	{
		var s = "<option value='" + i + "' selected>" + i + "</option>";
		sel_hours.append(s);	
		sel_hours_end.append(s);
	}
	sel_hours.val("");
	sel_hours_end.val("");
	
	for(var i = 0;i <= 59;i++)
	{
		var s = "<option value='" + i + "' selected>" + i + "</option>";
		sel_minute.append(s);	
		sel_minute_end.append(s);	
	}
	sel_minute.val("");
	sel_minute_end.val("");
});

$("#tr_sel_date_add").click(function(){
	var sel_date = $("#sel_date").val();
	var sel_hours = $("#sel_hours").val();
	var sel_minute = $("#sel_minute").val();
	var sel_hours_end = $("#sel_hours_end").val();
	var sel_minute_end = $("#sel_minute_end").val();
	
	var td_timeSlot = $("#td_timeSlot");
	
	var str = sel_date + " " + sel_hours +":"+sel_minute + "--" +sel_hours_end +":"+sel_minute_end;	
	var s = "<label><input type='checkbox' name='timeSlot' checked='checked' value='1' />" + str + "</label>";
	td_timeSlot.append(s);
});