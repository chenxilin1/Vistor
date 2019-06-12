<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../common/model.jsp"%>
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

<title>车辆通行情况</title>

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
</style>
</head>

<body>
	<input id="isAdminQJ" type="hidden" value="${isAdmin}">
	<div id="tjcxan" class="easyui-layout" data-options="fit:true,border:false" style="height:100%;width: 100%;">
		<div region="north" title="查询条件" collapsed=true style="height:250px;width: 200px;" id="tjcx">
			<form id="findBh" method="post">
				<table style="float: left;">
					<tr>
						<td align="right">进出口：</td>
						<td>
							<label for="man3">
								<input id="man3" type="checkbox" name="xjfx" value="0" checked="checked" />
								进口
							</label>
							<label for="man4">
								<input id="man4" type="checkbox" name="xjfx" value="1" checked="checked" />
								出口
							</label>
						</td>
					</tr>
					<tr>
						<td align="right">通行状态：</td>
						<td>
							<label for="man1">
								<input id="man1" type="checkbox" name="txzt" value="1" checked="checked" />
								正常通行
							</label>
							<label for="man2">
								<input id="man2" type="checkbox" name="txzt" value="0" checked="checked" />
								禁止通行
							</label>
						</td>
					</tr>
					<tr>
						<td align="right">地点名称：</td>
						<td>
							<select id="xzdd" name="ddmcsz" style="width: 240px" class="easyui-combobox"editable="false" multiple="true">
								<c:forEach var="txdd" items="${ktxdd_txcltj}">
									<option value="${txdd.address}">${txdd.address}</option>
								</c:forEach>
							</select>
							<input type="button" value="全选" class="btn" id="ddqx">
							<input type="button" value="全不选" class="btn" id="ddqbx">
						</td>
					</tr>
					<tr>
						<td>号牌种类：</td>
						<td>
							<select id="myselect" name="hpzl" style="width: 240px" class="easyui-combobox" editable="false" multiple="true">
								<c:forEach var="hpzl" items="${txcl_hpzl}">
									<option value="${hpzl.dmsm1}">${hpzl.dmsm1}</option>
								</c:forEach>
							</select>
							<input type="button" value="全选" class="btn" id="dwAllwf">
							<input type="button" value="全不选" class="btn" id="qbxwf">
						</td>
					</tr>
					<tr>
						<td>通行时间：</td>
						<td>
							<input id="dd" type="text" class="easyui-datetimebox" name="kssj"></input>
							&nbsp;到&nbsp;
							<input id="jssjs" type="text" class="easyui-datetimebox easyui-validatebox" name="jssj" value="0" data-options="validType:'equaldDate[\'#dd\']'"></input>
						</td>
					</tr>
					<tr>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',planin:true" onclick="searchFun()">查询</a>
						</td>
						<td>
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',planin:true" onclick="zdcx()">自动查询(5秒)</a>
							&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no',planin:true" onclick="qxzdcx()">取消自动查询</a>
						</td>
					</tr>
				</table>
				<table style="float: left;">
					<tr>
						<td></td>
					</tr>
					<tr>
						<td></td>
					</tr>
					<tr>
						<td align="right">所属单位：</td>
						<td>
							<input type="text" class="easyui-textbox" name="ssdw" style="width: 235px"></input>
						</td>
					</tr>
					<tr>
						<td align="right">驾驶人身份证：</td>
						<td>
							<input type="text" class="easyui-textbox" name="sfzh" style="width: 235px"></input>
						</td>
					</tr>
					<tr>
						<td align="right">车牌号码：</td>
						<td>
							<select name="hp" style="width: 50px" class="easyui-combobox">
								<option value="新">新</option>
								<option value="全部">全部</option>
								<c:forEach var="hp" items="${txcl_hp}">
									<option value="${hp}">${hp}</option>
								</c:forEach>
							</select>
							<select name="hm" style="width: 50px" class="easyui-combobox">
								<option value="A">A</option>
								<option value="全部">全部</option>
								<c:forEach var="hm" items="${txcl_hm}">
									<option value="${hm}">${hm}</option>
								</c:forEach>
							</select>
							<input type="text" class="easyui-textbox" name="zhi" style="width: 127px"></input>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="cltxqk_list"></table>
		</div>
	</div>










	<script type="text/javascript">
	$(function(){
		formatterDate = function (date) {
			var times=date.getTime();
			
			var moths=1000*60*60*24*7
			
			var dates=new Date(times-moths);
			
			var day = dates.getDate() > 9 ? dates.getDate() : "0" + dates.getDate();
			
			var month = (dates.getMonth() + 1) > 9 ? (dates.getMonth() + 1) : "0"
			+ (dates.getMonth() + 1);
			
			var hor = dates.getHours();
			var min = dates.getMinutes();
			var sec = dates.getSeconds();
			return dates.getFullYear() + '-' + month + '-' + day+" "+hor+":"+min+":"+sec;
			};

			$('#dd').datetimebox('setValue', formatterDate(new Date()));

		$.extend($.fn.validatebox.defaults.rules, {  
		       equaldDate: {  
		           validator: function (value, param) {  
		               var start = $(param[0]).datetimebox('getValue');  //获取开始时间    
		               return value > start;                             //有效范围为当前时间大于开始时间    
		           },  
		           message: '结束日期应大于开始日期!'                     //匹配失败消息  
		       }  
		 });
	});
</script>
	<script type="text/javascript" src="script/views/sjcx/cltxqk.js"></script>
</body>
</html>
