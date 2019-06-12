<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/model.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>收费情况统计</title>
<script src="js/echarts.js"></script>
<base href="<%=basePath%>">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
</head>
<body>
	<div class="pd-20">
		<div class="text-c1" style="margin-top: -15px;">
			<%--<form id="findDate" action="${pageContext.request.contextPath}/sjtj/findTJt_Cljl" > --%>
			<form id="findDate">
				<b>统计方式：</b>
				<label for="tj1">
					<input id="tj1" type="radio" name="tjfs" value="1" />
					年
				</label>
				<label for="tj2">
					<input id="tj2" type="radio" name="tjfs" value="2" />
					月
				</label>
				<label for="tj3">
					<input id="tj3" type="radio" name="tjfs" value="3" checked="checked" />
					日
				</label>
				<label for="tj6">
					<input id="tj6" type="radio" name="tjfs" value="4"/>
					时
				</label>
				<br> <br> <b>标签类型：</b>
				<label for="tj4">
					<input id="tj4" type="radio" name="bqlx" value="1" checked="checked" />
					机动车标签
				</label>
				<label for="tj5">
					<input id="tj5" type="radio" name="bqlx" value="0" />
					驾驶人标签
				</label>
				<br> <br> <b>统计时间：</b>
				<input type="text" id="datemax" class="easyui-datetimebox " name="kssj" style="width:150px;">
				到
				<input type="text" id="datemin" class="easyui-datetimebox " name="jssj" style="width:150px;">

				<button id="sjtjs" class="btn btn-success search" type="button">
					<i class="Hui-iconfont">&#xe665;</i>
					统计
				</button>
			</form>
		</div>

		<div class="mt-20" style="border: solid 0px;">
			<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
			<div id="main" style="width: 100%;height:420px"></div>
		</div>
	</div>
	<div></div>

	<script type="text/javascript">
	//填写时间空间
	var date = new Date();
	var times = date.getTime();
	var moths = 1000 * 60 * 60 * 24 * 7
	var dates = new Date(times - moths);
	var day = dates.getDate() > 9 ? dates.getDate() : "0" + dates.getDate();
	var month = (dates.getMonth() + 1) > 9 ? (dates.getMonth() + 1) : "0" + (dates.getMonth() + 1);
	var shi = dates.getHours() > 9 ? dates.getHours() : "0" + dates.getHours();
	var fen = dates.getMinutes() > 9 ? dates.getMinutes() : "0" + dates.getMinutes();
	var miao = dates.getSeconds() > 9 ? dates.getSeconds() : "0" + dates.getSeconds();
	var ks = dates.getFullYear() + '-' + month + '-' + day + ' ' + shi + ':' + fen + ':' + miao;
	$('#datemax').val(ks);
	
	var date_k = new Date();
	//var times = date.getTime();
	//var moths = 1000 * 60 * 60 * 24 * 7
	//var dates = new Date(times - moths);
	var day_k = date_k.getDate() > 9 ? date_k.getDate() : "0" + date_k.getDate();
	var month_k = (date_k.getMonth() + 1) > 9 ? (date_k.getMonth() + 1) : "0" + (date_k.getMonth() + 1);
	var shi_k = date_k.getHours() > 9 ? date_k.getHours() : "0" + date_k.getHours();
	var fen_k = date_k.getMinutes() > 9 ? date_k.getMinutes() : "0" + date_k.getMinutes();
	var miao_k = date_k.getSeconds() > 9 ? date_k.getSeconds() : "0" + date_k.getSeconds();
	var js = date_k.getFullYear() + '-' + month_k + '-' + day_k + ' ' + shi_k + ':' + fen_k + ':' + miao_k;
	$('#datemin').val(js);
	var myChart = echarts.init(document.getElementById('main'));
	var btName=[];//饼图全局名字	
	  var btValue=[];//饼图全局数量
    var xtext=[]//时间轴
    var lukou1=[]//
    var lukou=''//路口 
    var tiaoshu=[];//数据
    var tjlx='';//名称
    var nhphm='';//临时存放排除的号牌号码
    var djr='';//临时存放登记人
    //初始化统计图
    //alert(ks);
    //alert(js);
    $.ajax({
		url : 'sjtj/CxTJt_Fyqk?time=' + new Date().getTime() + '&kssj=' + ks + '&jssj=' + js + '&tjfs=3'+'&bqlx=1',
		async : false, // 同步执行
		dataType : "json", // 服务器响应回来数据类型
		success : function(date) {
			tjlx = date.titlew
			lukou = date.legendw
			tiaoshu = date.shuliang
			xtext = date.beiyong
			lukou1 = date.legends
		},
		error : function(date) {
			// alert("1"+date)
			alert("系统发生错误.");
		}
	})
	// 指定图表的配置项和数据
    var option = {
	    title: {
	        text: '车辆记录-统计图'
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    grid: {
	        top:'20%',
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    legend: {
	    	top:'6%',
	    	//left: '13%',
	        //right: '11%',
	        data:lukou1,//路口
	        containLabel: true
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        data: xtext	//时间轴
	    },
	    yAxis: {
	        type: 'value'
	    },
	    series:[{name:lukou,type:'line',smooth:true,data:tiaoshu}]
	};
    // 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
	
	//单击统计按钮
	$("#sjtjs").click(function(){
		//alert("0");
		 $.ajax({
				url : 'sjtj/CxTJt_Fyqk?time=' + new Date().getTime(),
				async : false, // 同步执行
				data:$("#findDate").serialize(),//序列化表单数据,方便提交
				type:"POST",//提交方式
				dataType : "json", // 服务器响应回来数据类型
				success : function(date) {
		      	tjlx=date.titlew;
				djr=date.djry;
		      	lukou=date.legendw;
		      	tiaoshu=date.shuliang
		      	xtext=date.beiyong;
		      	lukou1=date.legends
		      	myChart.setOption({
		      		title: {
		    	        text: tjlx+'统计图'
		    	    },
		      	    legend: {
		      	    	top:'6%',
		      	        data:lukou1,//路口
		      	        containLabel: true
		      	    },
		      	    xAxis: {
		      	        type: 'category',
		      	        boundaryGap: false,
		      	        data: xtext	//时间轴
		      	    },
		      	    yAxis: {
		      	        type: 'value'
		      	    },
		      	    series:[{name:lukou,type:'line',smooth:true,data:tiaoshu}]
		      	})
				},
				error : function(date) {
					alert("系统发生错误");
				}
			})
	})
	
	
	</script>


</body>
</html>
