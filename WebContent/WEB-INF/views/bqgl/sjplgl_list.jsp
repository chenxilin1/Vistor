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
    
    <title>数据批量管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css" rel="stylesheet">
     #form_pl table{
         margin: 15px 20px;
     }
     #form_pl tr{
         height: 50px;
     }
     .input_file_cd{
     	width: 150px;
     }
    </style>
  </head>
  
  <body>
  <form id="form_pl" enctype="multipart/form-data" method="post">
		<table>
			<tr>
				<td colspan="3"style="color: red;" align="center">建议空闲时间操作</td>
			</tr>
			<tr>
				<td align="right">机动车标签批量管理(.txt文件)：</td>
				<td align="right"></td>
				<td>
					<input class="input_file_cd" id="qkjdcbq" type="file" name="jdcbq" onchange="jdcbqFile(this)"/>
                	<button onclick="jdcbqsc()" >同步</button>&nbsp;&nbsp;
                	<button onclick="jdcbqbf()" >备份</button>
                	<!-- <a href="javascript:void(0)" onclick="beceshi()">备份</a> -->
				</td>
				<td id="jdcbqts" style="color: red;font-size: 13px;"></td>
				<td>
					<input id="jdcbqys" type="text" style="border: 0px;color: red;width: 40px;" >
					<input id="jdcbqmb" type="text" style="border: 0px;color: red;" >
				</td>
			</tr>
			<tr>
				<td align="right">驾驶人标签批量管理(.txt文件)：</td>
				<td align="right"></td>
				<td>
					<input class="input_file_cd" id="qkjsrbq" type="file" name="jsrbq" onchange="jsrbqFile(this)"/>
                	<button onclick="jsrbqsc()" >同步</button>&nbsp;&nbsp;
                	<button onclick="jsrbqbf()" >备份</button>
                	
				</td>
				<td id="jsrbqts" style="color: red;font-size: 13px;"></td>
				<td>
					<input id="jsrbqys" type="text" style="border: 0px;color: red;width: 40px;" >
					<input id="jsrbqmb" type="text" style="border: 0px;color: red;" >
				</td>
			</tr>
			<tr>
				<td align="right">机动车数据管理(Excel文件)：</td>
				<td align="right"></td>
				<td>
					<input class="input_file_cd" id="qkjdcsj" type="file" name="jdcsj" onchange="jdcsjFile(this)"/>
                	<button onclick="jdcsjcsh()" >同步</button>&nbsp;&nbsp;
                	<button onclick="jdcsjbf()" >备份</button>
				</td>
				<td id="jdcxx" style="color: red;font-size: 13px;"></td>
				<td>
					<input id="jdcsjys" type="text" style="border: 0px;color: red;width: 40px;" >
					<input id="jdcsjmb" type="text" style="border: 0px;color: red;" >
				</td>
			</tr>
			<tr>
				<td align="right">驾驶人数据管理(Excel文件)：</td>
				<td align="right"></td>
				<td>
					<input class="input_file_cd" id="qkjsrsj" type="file" name="jsrsj" onchange="jsrsjFile(this)"/>
                	<button onclick="jsrsjcsh()" >同步</button>&nbsp;&nbsp;
                	<button onclick="jsrsjbf()" >备份</button>
				</td>
				<td id="jsrxx" style="color: red;font-size: 13px;"></td>
				<td>
					<input id="jsrsjys" type="text" style="border: 0px;color: red;width: 40px;" >
					<input id="jsrsjmb" type="text" style="border: 0px;color: red;" >
				</td>
			</tr>
			<tr>
				<td align="right">机动车信息采集管理(Excel文件)：</td>
				<td align="right"></td>
				<td>
					<input class="input_file_cd" id="qkjdcxxcj" type="file" name="jdcxxcj" onchange="jdcxxcjFile(this)"/>
                	<button onclick="jdcxxcjrk()" >采集入库</button>&nbsp;&nbsp;
                	<button onclick="bfjdcxxcjmb()" >备份模板</button>
                	<%--<button onclick="csTextExcel()" >备份模板测试</button> --%>
				</td>
				<td id="jdcxxcjxx" style="color: red;font-size: 13px;"></td>
				<td>
					<input id="jdcxxcjys" type="text" style="border: 0px;color: red;width: 40px;" >
					<input id="jdcxxcjmb" type="text" style="border: 0px;color: red;" >
				</td>
			</tr>
			<tr>
				<td align="right">驾驶人信息采集管理(Excel文件)：</td>
				<td align="right"></td>
				<td>
					<input class="input_file_cd" id="qkjsrxxcj" type="file" name="jsrxxcj" onchange="jsrxxcjFile(this)"/>
                	<button onclick="jsrxxcjrk()" >采集入库</button>&nbsp;&nbsp;
                	<button onclick="bfjsrxxcjmb()" >备份模板</button>
				</td>
				<td id="jsrxxcjxx" style="color: red;font-size: 13px;"></td>
				<td>
					<input id="jsrxxcjys" type="text" style="border: 0px;color: red;width: 40px;" >
					<input id="jsrxxcjmb" type="text" style="border: 0px;color: red;" >
				</td>
			</tr>
		</table>
	</form>



	<script type="text/javascript">
	//--------------标签数据管理-------------------------------------------------------------
		function jdcsjFile(input) {
			document .getElementById ("jdcxx").innerHTML = "";
			var fileName = input.value;
			if (fileName.length > 1 && fileName) {
				var ldot = fileName.lastIndexOf(".");
				var type = fileName.substring(ldot + 1);
				$("#jdcsjmb").val("");
				$("#jdcsjys").val("");
				if (type != "xlsx" && type != "xls") {
					//$("#jdcxx").after("<p style='color: red' id='pp'>请选择txt文件!</p>");
					document .getElementById ("jdcxx").innerHTML = "请选择Excel文件!";
					//清除当前所选文件             
					input.outerHTML = input.outerHTML.replace(/(value=\").+\"/i, "$1\"");
				}
			}
		}
		function jsrsjFile(input) {
			document .getElementById ("jsrxx").innerHTML = "";
			var fileName = input.value;
			if (fileName.length > 1 && fileName) {
				var ldot = fileName.lastIndexOf(".");
				var type = fileName.substring(ldot + 1);
				$("#jsrsjmb").val("");
				$("#jsrsjys").val("");
				if (type != "xlsx" && type != "xls") {
					document .getElementById ("jsrxx").innerHTML = "请选择Excel文件!";
					//清除当前所选文件             
					input.outerHTML = input.outerHTML.replace(/(value=\").+\"/i, "$1\"");
				}
			}
		}
		//--------------标签管理-------------------------------------------------------------
		function jdcbqFile(input) {
			document .getElementById ("jdcbqts").innerHTML = "";
			var fileName = input.value;
			if (fileName.length > 1 && fileName) {
				var ldot = fileName.lastIndexOf(".");
				var type = fileName.substring(ldot + 1);
				$("#jdcbqmb").val("");
				$("#jdcbqys").val("");
				if (type != "txt") {
					//$("#jdcxx").after("<p style='color: red' id='pp'>请选择txt文件!</p>");
					document .getElementById ("jdcbqts").innerHTML = "请选择txt文件!";
					//清除当前所选文件             
					input.outerHTML = input.outerHTML.replace(/(value=\").+\"/i, "$1\"");
				}
			}
		}
		function jsrbqFile(input) {
			document.getElementById ("jsrbqts").innerHTML = "";
			var fileName = input.value;
			if (fileName.length > 1 && fileName) {
				var ldot = fileName.lastIndexOf(".");
				var type = fileName.substring(ldot + 1);
				$("#jsrbqmb").val("");
				$("#jsrbqys").val("");
				if (type != "txt") {
					//$("#jdcxx").after("<p style='color: red' id='pp'>请选择txt文件!</p>");
					document .getElementById ("jsrbqts").innerHTML = "请选择txt文件!";
					//清除当前所选文件             
					input.outerHTML = input.outerHTML.replace(/(value=\").+\"/i, "$1\"");
				}
			}
		}
		//--------------信息采集-------------------------------------------------------------
		function jdcxxcjFile(input) {
			document .getElementById ("jdcxxcjxx").innerHTML = "";
			var fileName = input.value;
			if (fileName.length > 1 && fileName) {
				var ldot = fileName.lastIndexOf(".");
				var type = fileName.substring(ldot + 1);
				$("#jdcxxcjmb").val("");
				$("#jdcxxcjys").val("");
				if (type != "xlsx" && type != "xls") {
					document .getElementById ("jdcxxcjxx").innerHTML = "请选择Excel文件!";
					//清除当前所选文件             
					input.outerHTML = input.outerHTML.replace(/(value=\").+\"/i, "$1\"");
				}
			}
		}
		function jsrxxcjFile(input) {
			document .getElementById ("jsrxxcjxx").innerHTML = "";
			var fileName = input.value;
			if (fileName.length > 1 && fileName) {
				var ldot = fileName.lastIndexOf(".");
				var type = fileName.substring(ldot + 1);
				$("#jsrxxcjmb").val("");
				$("#jsrxxcjys").val("");
				if (type != "xlsx" && type != "xls") {
					document .getElementById ("jsrxxcjxx").innerHTML = "请选择Excel文件!";
					//清除当前所选文件             
					input.outerHTML = input.outerHTML.replace(/(value=\").+\"/i, "$1\"");
				}
			}
		}
	</script>


	<script type="text/javascript" src="script/views/bqgl/sjplgl.js"></script>
  </body>
</html>
