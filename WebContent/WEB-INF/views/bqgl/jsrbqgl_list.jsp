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
    
    <title>驾驶人标签管理</title>
    
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
#jsrbqDuiHuaKuang table {
	margin: 5px 20px;
}

#jsrbqDuiHuaKuang tr {
	height: 25px;
}
</style>
</head>

<body>
	<input id="isAdminQJ" type="hidden" value="${isAdmin}">
	<div id="tjcxan" class="easyui-layout" data-options="fit:true,border:false" style="height:100%;width: 100%;">
		<div region="north" title="查询条件" collapsed=true style="height:220px;" id="tjcx">
			<form id="findBh" method="post">
				<table>
					<tr>
						<td align="right">启用状态：</td>
						<td>
							<label for="m1">
								<input id="m1" type="checkbox" name="bh" value="0" checked="checked" />
								失效
							</label>
							<label for="m2">
								<input id="m2" type="checkbox" name="bh" value="1" checked="checked" />
								正常
							</label>
						</td>
					</tr>
					<tr>
						<td align="right">姓名：</td>
						<td>
							<input style="width: 230px" type="text" name="syr" class="easyui-textbox"/>
						</td>
					</tr>
					<tr>
						<td align="right">身份证号：</td>
						<td>
							<input style="width: 230px" type="text" name="sfzh" class="easyui-textbox"/>
						</td>
					</tr>
					<tr>
						<td align="right">可通行地点：</td>
						<td>
							<select id="jgdd_jsrtj" name="ddmcsz" style="width: 230px" class="easyui-combobox"editable="false" multiple="true">
								<c:forEach var="ddjsrtj" items="${ktxdd_jsrbq}">
									<option value="${ddjsrtj.address}">${ddjsrtj.address}</option>
								</c:forEach>
							</select>
							&nbsp;
							<input type="button" value="全选" class="btn" id="ddmcqxtj">
							<input type="button" value="全不选" class="btn" id="ddmcqbxtj">
						</td>
					</tr>
					<tr>
						<td>
							<label for="jsrfqtjxk">
								<input id="jsrfqtjxk" type="checkbox" name="bqzt" value="5"/>
								发签时间：
							</label>
						</td>
						<td>
							<input id="dd" type="text" class="easyui-datetimebox" name="kssj" disabled="true"></input>
							&nbsp;到&nbsp;
							<input id="jssjs" type="text" class="easyui-datetimebox easyui-validatebox" name="jssj" value="0" disabled="true" data-options="validType:'equaldDate[\'#dd\']'"></input>
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
			<table id="jsrbqgl_list"></table>
		</div>
	</div>
	<!--列表对话框start-->
	<div id="jsrbqDuiHuaKuang" style="width:870px;height:470px;" class="easyui-dialog" closed="true" modal="true">
		<div style="width: 54%;float: left;">
			<form id="jsrbq_form">
				<table>
					<tr>
						<td align="right">身份证号：</td>
						<td>
							<input id="sfzh" type="text" class="easyui-textbox" name="sfzh" style="width: 260px"data-options="required:true,validType:['jsrbq_yzsfzhwy[\'#jsrbqid\']']"></input>
							<input id="jsrbqid" type="hidden" name="id"/>
						</td>
					</tr>
					<tr>
						<td align="right">姓名：</td>
						<td>
							<input style="width: 260px" type="text" name="syr" class="easyui-textbox" data-options="required:true"/>
						</td>
					</tr>
					<tr>
						<td align="right">性别：</td>
						<td>
							<label for="xbnv">
								<input id="xbnv" type="radio" name="sex" value="0" checked="checked" />
								女
							</label>
							<label for="xbnan">
								<input id="xbnan" type="radio" name="sex" value="1" />
								男
							</label>
						</td>
					</tr>
					<tr>
						<td align="right">身份证起止时间：</td>
						<td>
							<input id="sfzqssj" style="width: 92px" type="text" class="easyui-datetimebox" name="fzrq"></input>
							&nbsp;到
							<input id="sfzdqsj" style="width: 92px" type="text" class="easyui-datetimebox" name="dqsj"></input>
						</td>
					</tr>
					
					<tr>
						<td align="right">准驾车型：</td>
						<td>
							<input style="width: 260px" type="text" name="zjcx" class="easyui-textbox"  />
						</td>
					</tr>
					<tr>
						<td align="right">国家：</td>
						<td>
							<input style="width: 260px" type="text" name="gj" class="easyui-textbox" />
						</td>
					</tr>
					<tr>
						<td align="right">档案编号：</td>
						<td>
							<input style="width: 260px" type="text" name="dabh" class="easyui-textbox" />
						</td>
					</tr>
					<tr>
						<td align="right">所属单位：</td>
						<td>
							<input style="width: 260px" type="text" name="ssdw" class="easyui-textbox"  />
						</td>
					</tr>
					<tr>
						<td align="right">可通行地点：</td>
						<td>
							<select id="jgdd_jsr" name="ktxdd" style="width: 150px" class="easyui-combobox"editable="false"multiple="true">
								<c:forEach var="txdd" items="${ktxdd_jsrbq}">
									<option value="${txdd.address}">${txdd.address}</option>
								</c:forEach>
							</select>
							&nbsp;
							<input type="button" value="全选" class="btn" id="ddmcqx">
							<input type="button" value="全不选" class="btn" id="ddmcqbx">
						</td>
					</tr>
					<tr>
						<td align="right">车牌号码：</td>
						<td>
							<input style="width: 260px" type="text" name="cphm" class="easyui-textbox" />
						</td>
					</tr>
					<tr>
						<td align="right">所属领导：</td>
						<td>
							<input style="width: 260px" type="text" name="ld" class="easyui-textbox" />
						</td>
					</tr>
					<tr style="display: none;">
						<td align="right">是否启用：</td>
						<td>
							<label for="m4">
								<input id="m4" type="radio" name="zt" value="1" checked="checked"/>
								启用
							</label>
							<label for="m5">
								<input id="m5" type="radio" name="zt" value="2"/>
								不启用
							</label>
							金额：
							<input style="width: 50px" type="text" name="je" class="easyui-numberbox"/>
						</td>
					</tr>
					<tr>
						<td align="right">有效截止日期：</td>
						<td>
							<input id="yxjzrq" style="width: 150px" type="text" class="easyui-datetimebox" name="yxjzrq"></input>
							金额：
							<input style="width: 50px" type="text" name="je" class="easyui-numberbox"/>
						</td>
					</tr>
					<tr>
						<td align="right">驾驶人近照名称：</td>
						<td>
							<input style="width: 150px" id="jsrjztjdz" type="text" name="sctpdz" class="easyui-textbox" readOnly="true"/>
							<span style="color: red;font-size: 12px;">*点击上传图片获取</span>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div style="width: 46%;float: left;">
			<form id="jsrjz_form" enctype="multipart/form-data" method="post">
				<table>
					<tr>
						<td >
							人员近照：
							<input id="sctpdz_jsr" style="width: 105px" type="file" name="jsrjztjdz" onchange="selectFileJsr(this)"/>
	                		<button onclick="sctp_jsrjz()" >上传</button>
	                		<span id="jsrjzts" style="color: red;font-size: 12px;"></span>
						</td>
					</tr>
					<tr>
						<td align="center" >
	                		<div id="jsrjz"></div>
	                	</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!--用户列表对话框 END-->









	<script type="text/javascript">
	function selectFileJsr(input) { 
		   //清空div内容
		   document .getElementById ("jsrjzts").innerHTML = "";
		    var fileName = input.value; 
		    if(fileName.length > 1 && fileName ) {        
		        var ldot = fileName.lastIndexOf("."); 
		        var type = fileName.substring(ldot + 1); 
		        if(type != "jpg"&&type != "jpeg"&&type != "png"&&type != "bmp"&&type != "gif") { 
		        	document .getElementById ("jsrjzts").innerHTML = "请正确选择图片!";
		            //清除当前所选文件             
		            input.outerHTML=input.outerHTML.replace(/(value=\").+\"/i,"$1\""); 
		        }
		    } 
		}
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
	<script type="text/javascript" src="script/views/bqgl/jsrbqgl.js"></script>
	<script type="text/javascript" src="script/views/inputYz/inputYz.js"></script>
  </body>
</html>
