<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/model.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>车辆记录统计</title>
<script src="js/echarts.common.min.js"></script>
<script language="javascript" src="js/LodopFuncs.js"></script>
<script language="javascript" src="js/jquery.jqprint-0.3.js"></script>
<object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
	<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0 pluginspage="install_lodop32.exe"></embed>
</object> 
<%--<object id="jatoolsprinter" classid="clsid:b43d3361-d075-4be2-87fe-057188254255" codebase="jatoolsprinter.cab#version=8,6,0,0"></object> --%>

<base href="<%=basePath%>">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

</head>
<body>

<!--startprint-->
		<div id="page1" class="text-c1 my_show" >
			<form id="findDate" >
				<b>统计方式：</b>
				<label for="tj1">
					<input id="tj1" type="radio" name="tjfs" value="1" />年
				</label>
				<label for="tj2">
					<input id="tj2" type="radio" name="tjfs" value="2" />月
				</label>
				<label for="tj3">
					<input id="tj3" type="radio" name="tjfs" value="3" checked="checked" />日
				</label>
				<label for="tj6">
					<input id="tj6" type="radio" name="tjfs" value="4"/>时
				</label>
				<b>通行情况：</b>
				<label for="tj4">
					<input id="tj4" type="radio" name="txqk" value="1" checked="checked"/>已通行
				</label>
				<label for="tj5">
					<input id="tj5" type="radio" name="txqk" value="0" />未通行
				</label>
				<b>统计时间：</b>
				<input type="text"  id="datemax" class="easyui-datetimebox" name="kssj" style="width:150px;">
				到
				<input type="text"  id="datemin" class="easyui-datetimebox" name="jssj" style="width:150px;">
				<br><br> 
				<b>地点名称：</b>
				<select id="xzdd" name="txdd" style="width: 300px" class="easyui-combobox"editable="false" multiple="true">
					<c:forEach var="txdd_tjt" items="${ktxdd_cljltjtj}">
						<option value="${txdd_tjt.address}">${txdd_tjt.address}</option>
					</c:forEach>
				</select>
				<input type="button" value="全选" class="btn" id="ddqx">
				<input type="button" value="全不选" class="btn" id="ddqbx">
				<b>号牌号码：</b>
				<select name="hp" style="width: 50px" class="easyui-combobox">
					<option value="全部">全部</option>
					<option value="新">新</option>
					<c:forEach var="hp" items="${cltj_hp}">
						<option value="${hp}">${hp}</option>
					</c:forEach>
				</select>
				<select name="hm" style="width: 50px" class="easyui-combobox">
					<option value="全部">全部</option>
					<option value="A">A</option>
					<c:forEach var="hm" items="${cltj_hm}">
						<option value="${hm}">${hm}</option>
					</c:forEach>
				</select>
				<input type="text" class="easyui-textbox" name="zhi" style="width: 120px"></input>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="sjtjs" class="btn btn-success search" type="button">
					<i class="Hui-iconfont">&#xe665;</i>
					统计
				</button>
				<%--<a href="javascript:;" onClick="window.print()">打印预览</a> --%>
				<%--<input type="button" id="print"value= "打印预览 1"/> --%>
				<input type="button"　name="button_show"   value= "打印预览数据表格 "onclick= "javascript:doPrint(); ">
				
				<%-- <input type="button" value="打印预览"   onclick=' doPrint()'> --%>
				
			</form>
		</div>

		<div id="dyqy0" class="mt-20" style="border: solid 0px;">
			<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
			<div id="main" style="width: 100%;height:460px"></div>
			<div  id="bt_xx">
		    	<div id="main10" style="display: none;">
		    		<div id="main1" style="width: 600px;height:260px"></div>
		    	</div>
		    	<div id="main2">
		    		<table id="main3"></table>
		    	</div>
		    </div>
			<%--
			<div style="width: 950px;height:220px;" id="bt_xx">
		    	<div id="main1" style="width: 900px;height:220px;"></div>
		    	<div id="main2">
		    		<table id="main3"></table>
		    	</div>
		    </div>
			 --%>
		</div>
	</div>
	<!--endprint-->
	


<script type="text/javascript">
	
</script>


	<script type="text/javascript" src="script/views/sjtj/cljltj.js"></script>
</body>
</html>

	
	
	
	