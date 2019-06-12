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

<title>用户管理</title>

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

#userDuiHuaKuang table {
	margin: 25px 20px;
}

#userDuiHuaKuang tr {
	height: 35px;
}
</style>
</head>
<body>
	<input id="isAdminQJ" type="hidden" value="${isAdmin}">
	<div id="tjcxan" class="easyui-layout" data-options="fit:true,border:false" style="height:100%;width: 100%;">
		<div region="north" title="查询条件" collapsed=true style="height:170px;width: 200px;" id="tjcx">
			<form id="findBh" method="post">
				<table>

				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="user_list"></table>
		</div>
	</div>
	<!--用户列表对话框start-->
	<div id="userDuiHuaKuang" style="width:450px;height:350px;" class="easyui-dialog" closed="true" modal="true">
		<form id="user_form">
			<table>
				<tr>
					<td align="right">用户名：</td>
					<td>
						<input id="yhm" style="width: 260px" type="text" name="yhm" class="easyui-textbox easyui-validatebox" data-options="required:true"validType="yzyhmwy"/>
					</td>
				</tr>
				<tr>
					<td align="right">性别：</td>
					<td>
						<label for="tj3">
							<input id="tj3" type="radio" name="sex" value="0" checked="checked" />
							女
						</label>
						<label for="tj2">
							<input id="tj2" type="radio" name="sex" value="1" />
							男
						</label>
					</td>
				</tr>
				<tr>
					<td align="right">可通行地点：</td>
					<td>
					
						<select id="user_ktxdd" name="ktxdd" style="width: 150px" class="easyui-combobox"editable="false"data-options="required:true" multiple="true">
							<c:forEach var="userdd" items="${user_ktxdd}">
								<option value="${userdd.address}">${userdd.address}</option>
							</c:forEach>
						</select>
						
						&nbsp;
						<input type="button" value="全选" class="btn" id="ddmcqx">
						<input type="button" value="全不选" class="btn" id="ddmcqbx">
					</td>
				</tr>
				<tr>
					<td align="right">是否管理员：</td>
					<td>
						<select name="isAdmin" style="width: 260px" class="easyui-combobox" editable="false">
							<option value="1">超级管理员</option>
							<option value="3">普通管理员</option>
						</select>
					</td>
				</tr>
				<tr id="mmtr">
					<td align="right">密码：</td>
					<td>
						<inputn id="yhmm" style="width: 260px" type="password" name="yhmm" class="easyui-textbox" data-options="required:true"/>
					</td>
				</tr>
				<tr id="qrmmtr">
					<td align="right">确认密码：</td>
					<td>
						<input id="yhqrmm" style="width: 260px" type="password" name="qrmm" class="easyui-textbox" required="required" validType="equals['#yhmm']" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!--用户列表对话框 END-->

	<script type="text/javascript" src="script/views/xtgl/user.js"></script>
	<script type="text/javascript" src="script/views/inputYz/inputYz.js"></script>
</body>
</html>
