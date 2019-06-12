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
    
    <title>访客信息</title>
    
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
#jdcbqDuiHuaKuang table {
	margin: 5px 20px;
}

#jdcbqDuiHuaKuang tr {
	height: 25px;
}
</style>
</head>

<body>
	<input id="isAdminQJ" type="hidden" value="${isAdmin}">
	<div id="tjcxan" class="easyui-layout" data-options="fit:true,border:false" style="height:100%;width: 100%;">
		<div region="north" title="查询条件" collapsed=true style="height:190px;width: 200px;" id="tjcx">
			<form id="findBh" method="post">
				<table>
					<tr>
						<td align="right">拜访人姓名：</td>
						<td>
							<input style="width: 155px" type="text" name="userName" class="easyui-textbox" />
						</td>
					</tr>
					<tr>
						<td align="right">受访人姓名：</td>
						<td>
							<input style="width: 155px" type="text" name="email" class="easyui-textbox" />
						</td>
					</tr>
					<tr>
						<td align="right">经过地点：</td>
						<td>
							<select id="jgddtj" name="ddmcsz" style="width: 235px" class="easyui-combobox"editable="false" multiple="true">
								<c:forEach var="tj_dd" items="${fkxx_add_jgdd}">
									<option value="${tj_dd.address}">${tj_dd.address}</option>
								</c:forEach>
							</select>
							<input type="button" value="全选" class="btn" id="ddmcqxtj">
							<input type="button" value="全不选" class="btn" id="ddmcqbxtj">
						</td>
					</tr>
					<tr>
						<td align="right">创建时间：</td>
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
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="fkxx_list"></table>
		</div>
	</div>
	<!--列表对话框start-->
	<div id="fkxxDuiHuaKuang" style="width:480px;height:480px;" class="easyui-dialog" closed="true" modal="true">
		<form id="fkxx_form">
			<table>
				<tr>
					<td align="right">受访人姓名：</td>
					<td>
						<input style="width: 284px" type="text" name="email" class="easyui-textbox" data-options="required:true" />
					</td>
				</tr>
				<tr>
					<td align="right">受访人科室：</td>
					<td>
						<input style="width: 284px" type="text" name="editor" class="easyui-textbox" data-options="required:true" />
					</td>
				</tr>
				<tr>
					<td align="right">拜访人姓名：</td>
					<td>
						<input style="width: 284px" type="text" name="userName" class="easyui-textbox" data-options="required:true" />
					</td>
				</tr>
				<tr>
					<td align="right">民族：</td>
					<td>
						<select id="myselect" name="mz" style="width: 284px" class="easyui-combobox" data-options="required:true" editable="false" validType="ddmc_bsx">
							<option value="">请选择</option>
							<c:forEach var="mz" items="${fkxx_mz}">
								<option value="${mz}">${mz}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">性别：</td>
					<td>
						<label for="x1">
							<input id="x1" type="radio" name="sex" value="男"/>
							男
						</label>
						<label for="x2">
							<input id="x2" type="radio" name="sex" value="女" checked="checked"/>
							女
						</label>
					</td>
				</tr>
				<tr>
					<td align="right">出生日期：</td>
					<td>
						<input style="width: 284px" type="text" name="birthday" class="easyui-textbox"/>
					</td>
				</tr>
				<tr>
					<td align="right">身份证号：</td>
					<td>
						<input style="width: 284px" type="text" name="idCard" class="easyui-textbox" data-options="required:true" />
					</td>
				</tr>
				<tr>
					<td align="right">号牌号码：</td>
					<td>
						<select id="hp" name="hp" style="width: 70px" class="easyui-combobox" editable="false" >
							<option value="">不选择</option>
							<option value="新">新</option>
							<c:forEach var="form_hp" items="${fkxx_add_hp}">
								<option value="${form_hp}">${form_hp}</option>
							</c:forEach>
						</select>
						<select id="hm" name="hm" style="width: 70px" class="easyui-combobox"editable="false">
						<option value="">不选择</option>
							<option value="J">J</option>
							<c:forEach var="form_hm" items="${fkxx_add_hm}">
								<option value="${form_hm}">${form_hm}</option>
							</c:forEach>
						</select>
						<input type="text" class="easyui-textbox" name="zhi" style="width: 136px"data-options="validType:['jdcbq_yzhphmgs']"></input>
					</td>
				</tr>
				<tr>
					<td align="right">电话：</td>
					<td>
						<input style="width: 284px" type="text" name="phone" class="easyui-textbox" data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td align="right">经过地点：</td>
					<td>
						<select id="jgdd" name="userpath" style="width: 180px" class="easyui-combobox"editable="false"data-options="required:true" multiple="true">
							<c:forEach var="form_dd" items="${fkxx_add_jgdd}">
								<option value="${form_dd.address}">${form_dd.address}</option>
							</c:forEach>
						</select>
						<input type="button" value="全选" class="btn" id="ddmcqx">
						<input type="button" value="全不选" class="btn" id="ddmcqbx">
					</td>
				</tr>
				<tr>
					<td align="right">拜访时间起始：</td>
					<td>
						<input id="add_kssj" type="text" class="easyui-datetimebox" name="visitStartTime"style="width: 140px" value="0"data-options="validType:'equaldDateks'"></input>
						<input id="add_jssj" type="text" class="easyui-datetimebox" name="visitEndTime"style="width: 140px"data-options="validType:'equaldDate[\'#add_kssj\']'"></input>
					</td>
				</tr>
				<tr>
					<td align="right">拜访原因：</td>
					<td>
						<textarea cols="33" rows="4" name="fkyy" class="easyui-validatebox" ></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!--用户列表对话框 END-->









	<script type="text/javascript">
	$(function() {
		formatterDate = function(date) {
			var times = date.getTime();
			var moths = 1000 * 60 * 60 * 24 * 7
			var dates = new Date(times - moths);
			var day = dates.getDate() > 9 ? dates.getDate() : "0" + dates.getDate();
			var month = (dates.getMonth() + 1) > 9 ? (dates.getMonth() + 1) : "0" + (dates.getMonth() + 1);
			var hor = dates.getHours() > 9 ? dates.getHours() : "0" + dates.getHours();
			var min = dates.getMinutes() > 9 ? dates.getMinutes() : "0" + dates.getMinutes();
			var sec = dates.getSeconds() > 9 ? dates.getSeconds() : "0" + dates.getSeconds();
			return dates.getFullYear() + '-' + month + '-' + day + " " + hor + ":" + min + ":" + sec;
		};
		//alert(formatterDate(new Date()));
		$('#dd').datetimebox('setValue', formatterDate(new Date()));
		
		
		$.extend($.fn.validatebox.defaults.rules, {
			equaldDate : {
				validator : function(value, param) {
					var start = $(param[0]).datetimebox('getValue'); //获取开始时间    
					return value > start; //有效范围为当前时间大于开始时间    
				},
				message : '结束日期应大于开始日期!' //匹配失败消息  
			},
			equaldDateks : {
				validator : function(value, param) {
					
					var kssj = value; //开始时间
					var dqsj = new Date();
					dqsj.setDate(dqsj.getDate()+7)
					var gshdqsj = formatterDate(dqsj);
					
					//alert(gshdqsj);
					
					return kssj > gshdqsj; //有效范围为当前时间大于开始时间    
					//return true; //有效范围为当前时间大于开始时间    
				},
				message : '开始日期应大于当前日期!' //匹配失败消息  
			}
		});
	});
	</script>
	<script type="text/javascript" src="script/views/fkgl/fkxx.js"></script>
	<script type="text/javascript" src="script/views/inputYz/inputYz.js"></script>
  </body>
</html>
