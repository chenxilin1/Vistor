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

<title>地点管理维护</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css" rel="stylesheet">
#tjcx table {
	margin: 5px 20px;
}

#tjcx tr {
	height: 26px;
}

#ddwhDuiHuaKuang table {
	margin: 5px 20px;
}

#ddwhDuiHuaKuang tr {
	height: 35px;
}
</style>
</head>

<body>
	<input id="isAdminQJ" type="hidden" value="${isAdmin}">
	<div id="tjcxan" class="easyui-layout" data-options="fit:true,border:false" style="height:100%;width: 100%;">
		<div region="north" title="查询条件" collapsed=true style="height:90px;width: 200px;" id="tjcx">
			<form id="findBh" method="post">
				<table>
					<tr>
						<td>地点名称：</td>
						<td>
							<input style="width: 195px" type="text" name="ddmc" class="easyui-textbox" />
						</td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',planin:true" onclick="tjcx()">查询</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="ddwh_list"></table>
		</div>
	</div>
	<!--用户列表对话框start-->
	<div id="ddwhDuiHuaKuang" style="width:430px;height:180px;" class="easyui-dialog" closed="true" modal="true">
		<form id="ddwh_form">
			<table>
				<tr>
					<td align="right">地点名称：</td>
					<td>
						<input style="width: 260px" type="text" name="address" class="easyui-textbox" data-options="required:true"validType="yzddwy"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!--用户列表对话框 END-->

	<script type="text/javascript" src="script/views/xtgl/ddwh.js"></script>
	<script type="text/javascript" src="script/views/inputYz/inputYz.js"></script>
</body>
</html>
