qjwz='';
var n= 0, timer=null;
//补零
 function toDub(n){
     return n<10?"0"+n:""+n;
 }
//初始化机动车数据
function jdcsjcsh() {
	n= 0;
	document .getElementById ("jdcxx").innerHTML = "同步中,请等待。。。";
	$("#jdcsjys").val("用时：");
	//开始计时
	clearInterval(timer);
    timer=setInterval(function () {
        n++;
        var m=parseInt(n/60);
        var s=parseInt(n%60);
        $("#jdcsjmb").val(toDub(m)+":"+toDub(s)+"秒");
    },1000/60);
	$('#form_pl').form('submit', {
		url : 'bqgl/cshjdcsj?time='+new Date().getTime(),
		dataType:"text", 
		success : function(data) {
			var sts=data.split(",");
			var msg=sts[0];
			if ("true"==msg) {
				clearInterval(timer);
				document .getElementById ("jdcxx").innerHTML = "成功，添加"+sts[1]+"条、更新"+sts[2]+"条、失败"+sts[3]+"条";
				$('#qkjdcsj').replaceWith($('#qkjdcsj').clone(true));
			}else if ("false"==msg){
				document .getElementById ("jdcxx").innerHTML = "同步失败";
				clearInterval(timer);
				$("#jdcsjmb").val("");
				$("#jdcsjys").val("");
				$('#qkjdcsj').replaceWith($('#qkjdcsj').clone(true));
			}else if ("sizeErr"==msg){
				document .getElementById ("jdcxx").innerHTML = "表格格式错误";
				clearInterval(timer);
				$("#jdcsjmb").val("");
				$("#jdcsjys").val("");
				$('#qkjdcsj').replaceWith($('#qkjdcsj').clone(true));
			}else if ("dateErr"==msg){
				document .getElementById ("jdcxx").innerHTML = "异常：数据牵涉到时间的格式,请更改为文本";
				clearInterval(timer);
				$("#jdcsjmb").val("");
				$("#jdcsjys").val("");
				$('#qkjdcsj').replaceWith($('#qkjdcsj').clone(true));
			}else if ("lxyw"==msg){
				document .getElementById ("jdcxx").innerHTML = "失败,请打开采集表另存为(文件类型选xls替换原文件)后重试";
				clearInterval(timer);
				$("#jdcsjmb").val("");
				$("#jdcsjys").val("");
				$('#qkjdcsj').replaceWith($('#qkjdcsj').clone(true));
			}
		}
	});
}
//备份机动车数据
function jdcsjbf() {
	//alert("备份");
	//document .getElementById ("jdcxx").innerHTML = "正在备份，请等待。。。";
	//window.location.href = 'bqgl/bfjdcsj?time=' + new Date().getTime();
	$('#form_pl').form('submit', {
		url : 'bqgl/bfjdcsj?time=' + new Date().getTime(),
		dataType:"text", 
		async : true,
		success : function(data) {
			
		}
	});
	$.ajax({
		url : 'bqgl/bfjdcsjCount?time=' + new Date().getTime(),
		//data : $("#form_pl").serialize(),//序列化表单数据,方便提交
		type : "POST",//提交方式
		dataType : "text", //服务器响应回来数据类型
		//async : true,
		success : function(data) {//接收服务器响应的处理函数
			var sts=data.split(",");
			var msg=sts[0];
			if ("true"==msg) {
				//document .getElementById ("jdcxx").innerHTML = "备份成功,成功备份"+sts[1]+"条数据";
				document .getElementById ("jdcxx").innerHTML = "共"+sts[1]+"条数据";
				//$('#qkjsrsj').replaceWith($('#qkjsrbq').clone(true));
			}else if ("false"==msg){
				document .getElementById ("jdcxx").innerHTML = "备份失败";
				
				//$('#qkjsrsj').replaceWith($('#qkjsrbq').clone(true));
			}
		}
	});
}
//------------------------------------------------------------------------------------------
//初始化驾驶人数据
function jsrsjcsh() {
	n= 0;
	document .getElementById ("jsrxx").innerHTML = "同步中,请等待。。。";
	//开始计时
	$("#jsrsjys").val("用时：");
	clearInterval(timer);
    timer=setInterval(function () {
        n++;
        var m=parseInt(n/60);
        var s=parseInt(n%60);
        $("#jsrsjmb").val(toDub(m)+":"+toDub(s)+"秒");
    },1000/60);
	$('#form_pl').form('submit', {
		url : 'bqgl/cshjsrsj?time='+new Date().getTime(),
		dataType:"text", 
		success : function(data) {
			var sts=data.split(",");
			var msg=sts[0];
			if ("true"==msg) {
				clearInterval(timer);
				document .getElementById ("jsrxx").innerHTML = "成功，添加"+sts[1]+"条、更新"+sts[2]+"条、失败"+sts[3]+"条";
				$('#qkjsrsj').replaceWith($('#qkjsrsj').clone(true));
			}else if ("false"==msg){
				document .getElementById ("jsrxx").innerHTML = "同步失败";
				clearInterval(timer);
				$("#jsrsjmb").val("");
				$("#jsrsjys").val("");
				$('#qkjsrsj').replaceWith($('#qkjsrsj').clone(true));
			}else if ("sizeErr"==msg){
				document .getElementById ("jsrxx").innerHTML = "表格格式错误";
				clearInterval(timer);
				$("#jsrsjmb").val("");
				$("#jsrsjys").val("");
				$('#qkjsrsj').replaceWith($('#qkjsrsj').clone(true));
			}else if ("dateErr"==msg){
				document .getElementById ("jsrxx").innerHTML = "异常：数据牵涉到时间的格式,请更改为文本";
				clearInterval(timer);
				$("#jsrsjmb").val("");
				$("#jsrsjys").val("");
				$('#lxyw').replaceWith($('#qkjsrsj').clone(true));
			}else if ("lxyw"==msg){
				document .getElementById ("jsrxx").innerHTML = "失败,请打开采集表另存为(文件类型选xls替换原文件)后重试";
				clearInterval(timer);
				$("#jsrsjmb").val("");
				$("#jsrsjys").val("");
				$('#qkjsrsj').replaceWith($('#qkjsrsj').clone(true));
			}
		}
	});
}
//备份驾驶人数据
function jsrsjbf() {
	$('#form_pl').form('submit', {
		url : 'bqgl/bfjsrsj?time='+ new Date().getTime(),
		dataType:"text", 
		async : true,
		success : function(data) {
			
		}
	});
	$.ajax({
		url : 'bqgl/bfjsrsjCount?time=' + new Date().getTime(),
		//data : $("#form_pl").serialize(),//序列化表单数据,方便提交
		type : "POST",//提交方式
		dataType : "text", //服务器响应回来数据类型
		//async : true,
		success : function(data) {//接收服务器响应的处理函数
			var sts=data.split(",");
			var msg=sts[0];
			if ("true"==msg) {
				//document .getElementById ("jdcxx").innerHTML = "备份成功,成功备份"+sts[1]+"条数据";
				document .getElementById ("jsrxx").innerHTML = "共"+sts[1]+"条数据";
				//$('#qkjsrsj').replaceWith($('#qkjsrbq').clone(true));
			}else if ("false"==msg){
				document .getElementById ("jsrxx").innerHTML = "备份失败";
				
				//$('#qkjsrsj').replaceWith($('#qkjsrbq').clone(true));
			}
		}
	});
}
//------------------------------------------------------------------------------------------
//初始化机动车标签库
function jdcbqsc() {
	n= 0;
	document .getElementById ("jdcbqts").innerHTML = "同步中,请等待。。。";
	//document .getElementById ("jdcbqts").innerHTML = qjwz;
	//开始计时
	$("#jdcbqys").val("用时：");
	clearInterval(timer);
    timer=setInterval(function () {
        n++;
        var m=parseInt(n/60);
        var s=parseInt(n%60);
        $("#jdcbqmb").val(toDub(m)+":"+toDub(s)+"秒");
    },1000/60);
	$('#form_pl').form('submit', {
		url : 'bqgl/jdcbqsc?time='+new Date().getTime(),
		dataType:"text", 
		success : function(data) {
			var sts=data.split(",");
			//alert(sts);
			var msg=sts[0];
			//alert(msg);
			//alert(sts[1]+","sts[2]);
			if ("true"==msg) {
				//document .getElementById ("jdcbqts").innerHTML = "同步成功,成功添加"+sts[1]+"条、失败"+sts[2]+"条";
				clearInterval(timer);
				document .getElementById ("jdcbqts").innerHTML = "同步成功，成功添加"+sts[1]+"条、失败"+sts[2]+"条";
				//qjwz="同步成功，成功添加"+sts[1]+"条、失败"+sts[2]+"条";
				//location.reload(); 
				$('#qkjdcbq').replaceWith($('#qkjdcbq').clone(true));
			}else if ("false"==msg){
				clearInterval(timer);
				$("#jdcbqmb").val("");
				$("#jdcbqys").val("");
				document .getElementById ("jdcbqts").innerHTML = "同步失败";
				$('#qkjdcbq').replaceWith($('#qkjdcbq').clone(true));
			}
		}
	});
}
//备份机动车标签
function jdcbqbf() {
	$('#form_pl').form('submit', {
		url : 'bqgl/bfjdcbq?time='+ new Date().getTime(),
		dataType:"text", 
		async : true,
		success : function(data) {
			
		}
	});
}

//------------------------------------------------------------------------------------------
//初始化驾驶人标签库
function jsrbqsc() {
	n= 0;
	document .getElementById ("jsrbqts").innerHTML = "同步中,请等待。。。";
	//开始计时
	$("#jsrbqys").val("用时：");
	clearInterval(timer);
    timer=setInterval(function () {
        n++;
        var m=parseInt(n/60);
        var s=parseInt(n%60);
        $("#jsrbqmb").val(toDub(m)+":"+toDub(s)+"秒");
    },1000/60);
	$('#form_pl').form('submit', {
		url : 'bqgl/jsrbqsc?time='+new Date().getTime(),
		dataType:"text", 
		success : function(data) {
			var sts=data.split(",");
			var msg=sts[0];
			if ("true"==msg) {
				//document .getElementById ("jsrbqts").innerHTML = "同步成功,成功添加"+sts[1]+"条、失败"+sts[2]+"条";
				clearInterval(timer);
				document .getElementById ("jsrbqts").innerHTML = "同步成功，成功添加"+sts[1]+"条、失败"+sts[2]+"条";
				$('#qkjsrbq').replaceWith($('#qkjsrbq').clone(true));
			}else if ("false"==msg){
				clearInterval(timer);
				$("#jsrbqmb").val("");
				$("#jsrbqys").val("");
				document .getElementById ("jsrbqts").innerHTML = "同步失败";
				$('#qkjsrbq').replaceWith($('#qkjsrbq').clone(true));
			}
		}
	});
}
//备份驾驶人标签
function jsrbqbf() {
	$('#form_pl').form('submit', {
		url : 'bqgl/bfjsrbq?time='+ new Date().getTime(),
		dataType:"text", 
		async : true,
		success : function(data) {
			
		}
	});
}
//------------------------------------------------------------------------------------------
//采集机动车数据
function jdcxxcjrk() {
	n= 0;
	document .getElementById ("jdcxxcjxx").innerHTML = "采集中,请等待。。。";
	$("#jdcxxcjys").val("用时：");
	//开始计时
	clearInterval(timer);
    timer=setInterval(function () {
        n++;
        var m=parseInt(n/60);
        var s=parseInt(n%60);
        $("#jdcxxcjmb").val(toDub(m)+":"+toDub(s)+"秒");
    },1000/60);
	$('#form_pl').form('submit', {
		url : 'bqgl/cjjdcxx?time='+new Date().getTime(),
		dataType:"text", 
		success : function(data) {
			var sts=data.split(",");
			var msg=sts[0];
			if ("true"==msg) {
				clearInterval(timer);
				document .getElementById ("jdcxxcjxx").innerHTML = "成功,添加"+sts[1]+"条、更新"+sts[2]+"条、失败"+sts[3]+"条";
				$('#qkjdcxxcj').replaceWith($('#qkjdcxxcj').clone(true));
			}else if ("false"==msg){
				document .getElementById ("jdcxxcjxx").innerHTML = "采集失败";
				clearInterval(timer);
				$("#jdcxxcjmb").val("");
				$("#jdcxxcjys").val("");
				$('#qkjdcxxcj').replaceWith($('#qkjdcxxcj').clone(true));
			}else if ("sizeErr"==msg){
				document .getElementById ("jdcxxcjxx").innerHTML = "表格格式错误";
				clearInterval(timer);
				$("#jdcxxcjmb").val("");
				$("#jdcxxcjys").val("");
				$('#qkjdcxxcj').replaceWith($('#qkjdcxxcj').clone(true));
			}else if ("dateErr"==msg){
				document .getElementById ("jdcxxcjxx").innerHTML = "异常：数据牵涉到时间的格式,请更改为文本";
				clearInterval(timer);
				$("#jdcxxcjmb").val("");
				$("#jdcxxcjys").val("");
				$('#qkjdcxxcj').replaceWith($('#qkjdcxxcj').clone(true));
			}else if ("lxyw"==msg){
				document .getElementById ("jdcxxcjxx").innerHTML = "失败,请打开采集表另存为(文件类型选xls替换原文件)后重试";
				clearInterval(timer);
				$("#jdcxxcjmb").val("");
				$("#jdcxxcjys").val("");
				$('#qkjdcxxcj').replaceWith($('#qkjdcxxcj').clone(true));
			}
		}
	});
}
//备份采集机动车数据模板
function bfjdcxxcjmb() {
	$('#form_pl').form('submit', {
		url : 'bqgl/bfmbjdcxx?time='+ new Date().getTime(),
		dataType:"text", 
		async : true,
		success : function(data) {
			
		}
	});
}
//------------------------------------------------------------------------------------------
//采集驾驶人数据
function jsrxxcjrk() {
	n= 0;
	document .getElementById ("jsrxxcjxx").innerHTML = "采集中,请等待。。。";
	$("#jsrxxcjys").val("用时：");
	//开始计时
	clearInterval(timer);
  timer=setInterval(function () {
      n++;
      var m=parseInt(n/60);
      var s=parseInt(n%60);
      $("#jsrxxcjmb").val(toDub(m)+":"+toDub(s)+"秒");
  },1000/60);
	$('#form_pl').form('submit', {
		url : 'bqgl/cjjsrxx?time='+new Date().getTime(),
		dataType:"text", 
		success : function(data) {
			var sts=data.split(",");
			var msg=sts[0];
			if ("true"==msg) {
				clearInterval(timer);
				document .getElementById ("jsrxxcjxx").innerHTML = "成功,添加"+sts[1]+"条、更新"+sts[2]+"条、失败"+sts[3]+"条";
				$('#qkjsrxxcj').replaceWith($('#qkjsrxxcj').clone(true));
			}else if ("false"==msg){
				document .getElementById ("jsrxxcjxx").innerHTML = "采集失败";
				clearInterval(timer);
				$("#jsrxxcjmb").val("");
				$("#jsrxxcjys").val("");
				$('#qkjsrxxcj').replaceWith($('#qkjsrxxcj').clone(true));
			}else if ("sizeErr"==msg){
				document .getElementById ("jsrxxcjxx").innerHTML = "表格格式错误";
				clearInterval(timer);
				$("#jsrxxcjmb").val("");
				$("#jsrxxcjys").val("");
				$('#qkjsrxxcj').replaceWith($('#qkjsrxxcj').clone(true));
			}else if ("dateErr"==msg){
				document .getElementById ("jsrxxcjxx").innerHTML = "异常：数据牵涉到时间的格式,请更改为文本";
				clearInterval(timer);
				$("#jsrxxcjmb").val("");
				$("#jsrxxcjys").val("");
				$('#qkjsrxxcj').replaceWith($('#qkjsrxxcj').clone(true));
			}else if ("lxyw"==msg){
				document .getElementById ("jsrxxcjxx").innerHTML = "失败,请打开采集表另存为(文件类型选xls替换原文件)后重试";
				clearInterval(timer);
				$("#jsrxxcjmb").val("");
				$("#jsrxxcjys").val("");
				$('#qkjsrxxcj').replaceWith($('#qkjsrxxcj').clone(true));
			}
		}
	});
}
//备份采集驾驶人数据模板
function bfjsrxxcjmb() {
	$('#form_pl').form('submit', {
		url : 'bqgl/bfmbjsrxx?time='+ new Date().getTime(),
		dataType:"text", 
		async : true,
		success : function(data) {
			
		}
	});
}

//http://www.longxintaiye.com/index.do



