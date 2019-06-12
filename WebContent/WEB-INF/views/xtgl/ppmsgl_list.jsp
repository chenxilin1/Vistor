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

<title>车辆匹配模式</title>

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

#ppmsDuiHuaKuang table {
	margin: 5px 20px;
}

#ppmsDuiHuaKuang tr {
	height: 28px;
}
</style>
</head>

<body>
	<input id="isAdminQJ" type="hidden" value="${isAdmin}">
	<div id="tjcxan" class="easyui-layout" data-options="fit:true,border:false" style="height:100%;width: 100%;">
		<div region="north" title="查询条件" collapsed=true style="height:200px;width: 200px;" id="tjcx">
			<form id="findBh" method="post">
				<table>
					<tr>
						<td>相机状态：</td>
						<td>
						<label for="tj8">
							<input id="tj8" type="checkbox" name="xjsbbh" value="1" />
							启用
						</label>
						<label for="tj9">
							<input id="tj9" type="checkbox" name="xjsbbh" value="0" />
							未启用
						</label>
						</td>
					</tr>
					<tr>
						<td>进出口：</td>
						<td>
						<label for="tj14">
							<input id="tj14" type="checkbox" name="ckip" value="0" />
							进口
						</label>
						<label for="tj15">
							<input id="tj15" type="checkbox" name="ckip" value="1" />
							出口
						</label>
						</td>
					</tr>
					<tr>
						<td align="right">RFID优先：</td>
						<td>
							<label for="tj22">
								<input id="tj22" type="checkbox" name="rfidms" value="1" />
								是
							</label>
							<label for="tj23">
								<input id="tj23" type="checkbox" name="rfidms" value="0" />
								否
							</label>
						</td>
					</tr>
					<tr>
						<td>地点名称：</td>
						<td>
							<select id="ppmsdd" name="ddmcsz" style="width: 150px" class="easyui-combobox" multiple="true"editable="false">
								<c:forEach var="ddmctj" items="${ppms_ddmc}">
									<option value="${ddmctj.address}">${ddmctj.address}</option>
								</c:forEach>
							</select>
							<input type="button" value="全选" class="btn" id="ddmcqx">
							<input type="button" value="全不选" class="btn" id="ddmcqbx">
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
			<table id="ppms_list"></table>
		</div>
	</div>
	<!--用户列表对话框start-->
	<div id="ppmsDuiHuaKuang" style="width:440px;height:440px;" class="easyui-dialog" closed="true" modal="true">
		<form id="ppms_form">
			<table>
				<tr>
					<td align="right">地点名称：</td>
					<td>
						<select name="jgmc" style="width: 260px" class="easyui-combobox" data-options="required:true,validType:['ddmc_bsx','yzddmc_wy_ppms[\'#ppmsid\']']" editable="false">
							<option value="">请选择</option>
							<c:forEach var="ddmc" items="${ppms_ddmc}">
								<option value="${ddmc.address}">${ddmc.address}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">匹配模式：</td>
					<td>
						<%--
						<label for="tj5">
							<input id="tj5" type="checkbox" name="ppms" value="1" />
							车签
						</label>
						<label for="tj6">
							<input id="tj6" type="checkbox" name="ppms" value="2" />
							人签
						</label>
						<label for="tj7">
							<input id="tj7" type="checkbox" name="ppms" value="3" checked="checked" />
							车牌
						</label>
						 --%>
						<select name="ppms" style="width: 260px" class="easyui-combobox" data-options="required:true" editable="false">
							<option value="车签">车签</option>
							<option value="人签">人签</option>
							<option value="车牌">车牌</option>
							<option value="车签 且 人签 且 车牌">车签 且 人签 且 车牌</option>
							<option value="车签 或 人签 或 车牌">车签 或 人签 或 车牌</option>
							<option value="车签 且 （人签或车牌）">车签 且 （人签或车牌）</option>
							<option value="人签 且 （车签或车牌）">人签 且 （车签或车牌）</option>
							<option value="车牌 且 （车签或人签）">车牌 且 （车签或人签）</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">进出口：</td>
					<td>
						<label for="tj10">
							<input id="tj10" type="radio" name="ckip" value="0" checked="checked" />
							进口
						</label>
						<label for="tj2">
							<input id="tj2" type="radio" name="ckip" value="1" />
							出口
						</label>
					</td>
				</tr>
				<tr>
					<td align="right">相机状态：</td>
					<td>
						<label for="tj3">
							<input id="tj3" type="radio" name="xjsbbh" value="1" checked="checked" />
							启用
						</label>
						<label for="tj4">
							<input id="tj4" type="radio" name="xjsbbh" value="0" />
							未启用
						</label>
					</td>
				</tr>
				<tr>
					<td align="right">是否RFID优先：</td>
					<td>
						<label for="tj20">
							<input id="tj20" type="radio" name="rfidms" value="1" checked="checked" />
							是
						</label>
						<label for="tj21">
							<input id="tj21" type="radio" name="rfidms" value="0" />
							否
						</label>
					</td>
				</tr>
				<tr>
					<td align="right">IP地址：</td>
					<td>
						<input style="width: 260px" type="text" name="jkip" class="easyui-textbox easyui-validatebox" data-options="required:true,validType:['yzip','ppmsip_wy[\'#ppmsid\']']" />
						<input id="ppmsid" type="hidden" name="id"/>
					</td>
				</tr>
				<tr>
					<td align="right">道闸IP：</td>
					<td>
						<input style="width: 260px" type="text" name="dzip" class="easyui-textbox easyui-validatebox" data-options="required:true" validType="yzip" />
					</td>
				</tr>
				<tr>
					<td align="right">读卡器IP：</td>
					<td>
						<input style="width: 260px" type="text" name="dkqip" class="easyui-textbox easyui-validatebox" data-options="required:true" validType="yzip" />
					</td>
				</tr>
				<tr>
					<td align="right">端口号：</td>
					<td>
						<input style="width: 260px" type="text" name="dk" class="easyui-textbox" data-options="required:true" />
					</td>
				</tr>
				<tr id="tr_show" style="display: none;">
					<td align="right">出口处理方式：</td>
					<td>
						<select id="ckclfs" name="ckclfs" style="width: 260px" class="easyui-combobox" data-options="panelHeight:'auto'" editable="false">
							 
							<option value="处理数据发命令" selected="selected">处理数据发命令</option>
							<option value="处理数据不发命令">处理数据不发命令</option>
							<option value="走匹配模式">走匹配模式</option>
							 
						</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!--用户列表对话框 END-->

	<script type="text/javascript" src="script/views/xtgl/ppms.js"></script>
	<script type="text/javascript" src="script/views/inputYz/inputYz.js"></script>
</body>
</html>
