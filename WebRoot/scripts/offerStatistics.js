var baseUrl =  window.location.protocol + "//" + window.location.host;

var updateTable = function(from,to)
{
	var data = $.ajax({
		  type: 'POST',
		  url: baseUrl+"/offerStatistics_list2",
		  data: {"from" : from,"to":to},
		  async:false
		});
	data = data.responseText;
	data = eval("("+data+")");
	
	var body = $("#tbody");
	var str = "";
	for(var i=0;i<data.length;i++)
	{
		var s = "<tr>";
		s+="<td>" + data[i].offerId + "</td>";
		s+="<td>" + data[i].offerName + "</td>";
		s+="<td>" + data[i].requestNum + "</td>";
		s+="<td>" + data[i].showNum + "</td>";
		s+="<td>" + data[i].clickNum + "</td>";
		s+="<td>" + data[i].downloadNum + "</td>";
		s+="<td>" + data[i].downloadSuccessNum + "</td>";
		s+="<td>" + data[i].installNum + "</td>";
		s+="<td>" + data[i].installSuccessNum + "</td>";
		s+="<td>" + data[i].activateNum + "</td>";
		s+="<td>" + data[i].income + "</td>";
		s+= "</tr>";
			
		str += s;	
	}
	body.html(str);
};

$("#today").click(function(){
	var date = new Date();
	var from = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate() + " 00:00:00";
	var to = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate()+1) + " 00:00:00";
	updateTable(from,to);
});

$("#oneWeek").click(function(){
	var date = new Date();
	date.setDate(date.getDate() - 7);
	var from = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate() + " 00:00:00";
	date.setDate(date.getDate() + 7);
	var to = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate()+1) + " 00:00:00";
	updateTable(from,to);
});

$("#oneMonth").click(function(){
	var date = new Date();
	date.setDate(date.getDate() - 30);
	var from = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate() + " 00:00:00";
	date.setDate(date.getDate() + 30);
	var to = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate()+1) + " 00:00:00";
	updateTable(from,to);
});

$("#find").click(function(){
	var from = $("#from_date").val() + " 00:00:00";
	var to = $("#to_date").val() + " 00:00:00";
	updateTable(from,to);
});

var div = document.getElementById("my_div");
var a_1 = document.getElementById("a_1");
var a_2 = document.getElementById("a_2");

var resf = function()
{
var maxNum = div.title;
var maxIndex = Math.ceil(maxNum / 20)-1;
var index = location.href.split("index=")[1];

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
a_1.href = "offerStatistics_list?index=" + (parseInt(index)-1);
a_2.href = "offerStatistics_list?index=" + (parseInt(index)+1);

var date = new Date();
var from = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
var to = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+(date.getDate()+1);
$("#from_date").val(from);
$("#to_date").val(to);
};

resf();