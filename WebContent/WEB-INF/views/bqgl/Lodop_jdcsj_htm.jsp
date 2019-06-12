<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../common/model.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>打印页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script language="javascript" src="js/LodopFuncs.js"></script>
<object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
	<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0 pluginspage="install_lodop32.exe"></embed>
</object>

</head>

<body>
	<div id="table02">

		<table border="1" width="100%" height="100%" style="border-collapse:collapse;border:1px solid black;" border-collapse:collapse>
			<tr>
				<td align="center" style="border:1px solid">你猜</td>
				<td align="center" style="border:1px solid">B1</td>
				<td align="center" style="border:1px solid">C1</td>
				<td align="center" style="border:1px solid">D1</td>
				<td align="center" style="border:1px solid">E1</td>
				<td align="center" style="border:1px solid">E1</td>
				<td align="center" style="border:1px solid">E1</td>
				<td align="center" style="border:1px solid">E1</td>
				<td align="center" style="border:1px solid">E1</td>
				<td align="center" style="border:1px solid">E1</td>
			</tr>
			<tr>
				<td align="center" style="border:1px solid">A2</td>
				<td align="center" style="border:1px solid">B2</td>
				<td align="center" style="border:1px solid">C2</td>
				<td align="center" style="border:1px solid">D2</td>
				<td align="center" style="border:1px solid">E2</td>
				<td align="center" style="border:1px solid">E2</td>
				<td align="center" style="border:1px solid">E2</td>
				<td align="center" style="border:1px solid">E2</td>
				<td align="center" style="border:1px solid">E2</td>
			</tr>
		</table>
	</div>
	
	
	
	<script language="javascript" type="text/javascript"> 
		var LODOP; //声明为全局变量 
		myCreatePage();
		LODOP.SET_PRINT_PAGESIZE(2,0,0,"");
		LODOP.SET_PREVIEW_WINDOW(0,0,0,0,0,"");	
		LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",1);//横向时的正向显示
		LODOP.PREVIEW();
		function myCreatePage() {		
			LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM')); 		
			//LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_百分比单位演示");	
			//LODOP.ADD_PRINT_ELLIPSE("0%","0%","100%","50%",0,1);
			LODOP.ADD_PRINT_TABLE("5%",1,"100%","49%","<body style='margin:0'>"+document.getElementById("table02").innerHTML+"</body>");
		};
		function myDesign() {		
			myCreatePage();
			LODOP.PRINT_DESIGN();		
		};		
		function myPreview() {		
			myCreatePage();
			LODOP.PREVIEW();		
		};		
	</script>
	
	
	
</body>
</html>
