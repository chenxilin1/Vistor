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
    
    <title>日志管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css" rel="stylesheet">
		#tjcx table {
			margin: 5px 20px;
		}
		
		#tjcx tr {
			height: 26px;
		}
	</style>
  </head>
  
  <body>
    <div id="tjcxan" class="easyui-layout" data-options="fit:true,border:false" style="height:100%;width: 100%;">
		<div region="north" title="查询条件" collapsed=true style="height:160px;width: 200px;" id="tjcx">
			<form id="findBh" method="post">
				<table>
					<tr>
						<td>操作用户：</td>
						<td>
							<select name="ppms" style="width: 185px" class="easyui-combobox"editable="false" multiple="true">
								<c:forEach var="czyh" items="${rzgl_czyh}">
									<option value="${czyh.yhm}">${czyh.yhm}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>操作内容：</td>
						<td>
							<input style="width: 185px" type="text" name="bh" class="easyui-textbox" />
						</td>
					</tr>
					<tr>
						<td>操作时间：</td>
						<td>
							<input style="width: 185px" id="dd" type="text" class="easyui-datetimebox" name="kssj"></input>
							&nbsp;到&nbsp;
							<input style="width: 185px" id="jssjs" type="text" class="easyui-datetimebox easyui-validatebox" name="jssj" value="0" data-options="validType:'equaldDate[\'#dd\']'"></input>
						</td>
					</tr>
					<tr>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',planin:true" onclick="tjcx()">查询</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="rzgl_list"></table>
		</div>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<script type="text/javascript" src="script/views/xtgl/rzgl.js"></script>
  </body>
</html>
