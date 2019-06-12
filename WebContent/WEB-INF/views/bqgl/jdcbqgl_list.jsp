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

<title>机动车标签管理</title>

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
#jdcbqDuiHuaKuang table {
	margin: 5px 20px;
}

#jdcbqDuiHuaKuang tr {
	height: 23px;
}
#table_dyys tr th {
	width: 1px;
	color: red;
}
</style>
<script language="javascript" src="js/LodopFuncs.js"></script>
<object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
	<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0 pluginspage="install_lodop32.exe"></embed>
</object> 
</head>

<body>
	<input id="isAdminQJ" type="hidden" value="${isAdmin}">
	<div id="tjcxan" class="easyui-layout" data-options="fit:true,border:false" style="height:100%;width: 100%;">
		<div region="north" title="查询条件" collapsed=true style="height:250px;width: 200px;" id="tjcx">
			<form id="findBh" method="post">
				<table style="float: left;">
					<tr>
						<td align="right">标签情况：</td>
						<td>
							<label for="m7">
								<input id="m7" type="checkbox" name="bqffqk" value="0" checked="checked" />
								已发
							</label>
							<label for="m8">
								<input id="m8" type="checkbox" name="bqffqk" value="1" checked="checked" />
								未发
							</label>
						</td>
					</tr>
					<!-- 
					<tr>
						<td align="right">启用状态：</td>
						<td>
							<label for="m1">
								<input id="m1" type="checkbox" name="bqzt" value="0" checked="checked" />
								注销
							</label>
							<label for="m2">
								<input id="m2" type="checkbox" name="bqzt" value="1" checked="checked" />
								已启用
							</label>
							<label for="m3">
								<input id="m3" type="checkbox" name="bqzt" value="2" checked="checked" />
								未启用
							</label>
						</td>
					</tr>
					 -->
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
						<td align="right">号牌种类：</td>
						<td>
							<select id="myselect" name="hpzl" style="width: 240px" class="easyui-combobox" multiple="true">
								<c:forEach var="hpzl" items="${jdcbq_hpzl}">
									<option value="${hpzl.dmsm1}">${hpzl.dmsm1}</option>
								</c:forEach>
							</select>
							<input type="button" value="全选" class="btn" id="dwAllwf">
							<input type="button" value="全不选" class="btn" id="qbxwf">
						</td>
					</tr>
					<tr>
						<td align="right">可通行地点：</td>
						<td>
							<select id="jgdd_jdctj" name="ddmcsz" style="width: 240px" class="easyui-combobox"editable="false" multiple="true">
								<c:forEach var="txddjdctj" items="${ktxdd_jdcbq}">
									<option value="${txddjdctj.address}">${txddjdctj.address}</option>
								</c:forEach>
							</select>
							<input type="button" value="全选" class="btn" id="ddmcqxtj">
							<input type="button" value="全不选" class="btn" id="ddmcqbxtj">
						</td>
					</tr>
					<tr>
						<!-- 
							<td>
								<label for="bx5">
									<input id="bx5" type="checkbox" name="bqzt" value="5"/>
									发签时间：
								</label>
							</td>
						 -->
						<td align="right">
							<label for="jdcfqtjxk">
								<input id="jdcfqtjxk" type="checkbox" name="bqzt" value="5"/>
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
						<td colspan="2" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',planin:true" onclick="searchFun()">查询</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-microsoft_excel',planin:true" onclick="outExcel()">导出到Excel</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-print',planin:true" onclick="printdata()">打印预览</a>
						</td>
					</tr>
				</table>
				<table style="float: left;">
					<tr>
						<td align="right">所有人：</td>
						<td>
							<input type="text" class="easyui-textbox" name="syr" style="width: 235px"></input>
						</td>
					</tr>
					<tr>
						<td align="right">联系电话：</td>
						<td>
							<input type="text" class="easyui-textbox" name="lxdh" style="width: 235px"></input>
						</td>
					</tr>
					<tr>
						<td align="right">所属领导：</td>
						<td>
							<input type="text" class="easyui-textbox" name="ld" style="width: 235px"></input>
						</td>
					</tr>
					<tr>
						<td align="right">所属单位：</td>
						<td>
							<input type="text" class="easyui-textbox" name="ssdw" style="width: 235px"></input>
						</td>
					</tr>
					<tr>
						<td align="right">车牌号码：</td>
						<td>
							<select name="hp" style="width: 50px" class="easyui-combobox">
								<option value="新">新</option>
								<option value="全部">全部</option>
								<c:forEach var="hp" items="${jdcbq_hp}">
									<option value="${hp}">${hp}</option>
								</c:forEach>
							</select>
							<select name="hm" style="width: 50px" class="easyui-combobox">
								<option value="A">A</option>
								<option value="全部">全部</option>
								<c:forEach var="hm" items="${jdcbq_hm}">
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
			<table id="jdcbqgl_list"></table>
		</div>
	</div>
	<!--列表对话框start-->
	<div id="jdcbqDuiHuaKuang" style="width:870px;height:530px;" class="easyui-dialog" closed="true" modal="true">
		<div style="width: 54%;float: left;">
			<form id="jdcbq_form">
				<table>
					<!-- 
					<tr>
						<td align="right">号牌号码：</td>
						<td>
							<select id="hp" name="hp" style="width: 70px" class="easyui-combobox" data-options="required:true" editable="false" validType="ddmc_bsx">
								<option value="">请选择</option>
								<option value="新">新</option>
								<c:forEach var="form_hp" items="${jdcbq_hp}">
									<option value="${form_hp}">${form_hp}</option>
								</c:forEach>
							</select>
							<select id="hm" name="hm" style="width: 70px" class="easyui-combobox"editable="false" data-options="required:true"validType="ddmc_bsx">
							<option value="">请选择</option>
								<option value="J">J</option>
								<c:forEach var="form_hm" items="${jdcbq_hm}">
									<option value="${form_hm}">${form_hm}</option>
								</c:forEach>
							</select>
							<input id="zhi" type="text" class="easyui-textbox" name="zhi" style="width: 112px"data-options="required:true,validType:['jdcbq_yzhphmgs','jdcbq_yzhphmwy']"></input>
						</td>
					</tr>
					 -->
					<tr>
						<td align="right">号牌号码：</td>
						<td>
							<span id="bqgl_jdcbq_dhk_hp">
								<select id="hp" name="hp" style="width: 70px" class="easyui-combobox" editable="false">
									<option value="">不选</option>
									<option value="新">新</option>
									<c:forEach var="form_hp" items="${jdcbq_hp}">
										<option value="${form_hp}">${form_hp}</option>
									</c:forEach>
								</select>
							</span>
							<span id="bqgl_jdcbq_dhk_hm">
								<select id="hm" name="hm" style="width: 70px" class="easyui-combobox"editable="false" >
									<option value="">不选</option>
									<option value="A">A</option>
									<c:forEach var="form_hm" items="${jdcbq_hm}">
										<option value="${form_hm}">${form_hm}</option>
									</c:forEach>
								</select>
							</span>
							<input id="zhi" type="text" class="easyui-textbox" name="zhi" style="width: 112px"data-options="required:true,validType:['jdcbq_yzhphmwy[\'#jdcbqid\']']"></input>
							<input id="jdcbqid" type="hidden" name="id"/>
						</td>
					</tr>
					<tr>
						<td align="right">号牌种类：</td>
						<td>
							<select id="myselect" name="hpzl" style="width: 260px" class="easyui-combobox" data-options="required:true" editable="false" validType="ddmc_bsx">
								<option value="">请选择</option>
								<c:forEach var="form_hpzl" items="${jdcbq_hpzl}">
									<option value="${form_hpzl.dmsm1}">${form_hpzl.dmsm1}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">车辆类型：</td>
						<td>
							<select id="myselect" name="cllx" style="width: 260px" class="easyui-combobox"editable="false" >
								<option value="">不选择</option>
								<c:forEach var="form_cllx" items="${jdcbq_cllx}">
									<option value="${form_cllx.dmsm1}">${form_cllx.dmsm1}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">所有人：</td>
						<td>
							<input style="width: 260px" type="text" name="syr" class="easyui-textbox"/>
						</td>
					</tr>
					<tr>
						<td align="right">车身颜色：</td>
						<td>
							<select name="csys" style="width: 260px" class="easyui-combobox"editable="false">
								<option value="">不选择</option>
								<c:forEach var="form_csys" items="${jdcbq_csys}">
									<option value="${form_csys.dmsm1}">${form_csys.dmsm1}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">车辆识别代码：</td>
						<td>
							<input style="width: 260px" type="text" name="clsbdh" class="easyui-textbox"  />
						</td>
					</tr>
					<tr>
						<td align="right">发动机号：</td>
						<td>
							<input style="width: 260px" type="text" name="fdjh" class="easyui-textbox" />
						</td>
					</tr>
					<tr>
						<td align="right">所属品牌：</td>
						<td>
							<input style="width: 260px" type="text" name="clpp" class="easyui-textbox"  />
						</td>
					</tr>
					<tr>
						<td align="right">所属单位：</td>
						<td>
							<input style="width: 260px" type="text" name="ssdw" class="easyui-textbox"/>
						</td>
					</tr>
					<tr>
						<td align="right">所属科室：</td>
						<td>
							<input style="width: 260px" type="text" name="ssks" class="easyui-textbox" />
						</td>
					</tr>
					<tr>
						<td align="right">联系电话：</td>
						<td>
							<input style="width: 260px" type="text" name="lxdh" class="easyui-numberbox" validType="jylxdh"/>
						</td>
					</tr>
					<tr>
						<td align="right">所属领导：</td>
						<td>
							<input style="width: 260px" type="text" name="ld" class="easyui-textbox"/>
						</td>
					</tr>
					<tr style="display: none;">
						<td align="right">是否启用：</td>
						<td>
							<label for="m4">
								<input id="m4" type="radio" name="bqzt" value="1" checked="checked"/>
								启用
							</label>
							<label for="m5">
								<input id="m5" type="radio" name="bqzt" value="2"/>
								不启用
							</label>
							金额：
							<input style="width: 50px" type="text" name="je" class="easyui-numberbox"/>
						</td>
					</tr>
					<tr>
						<td align="right">可通行地点：</td>
						<td>
							<select id="jgdd_jdc" name="ktxdd" style="width: 160px" class="easyui-combobox"editable="false"data-options="required:true" multiple="true">
								<c:forEach var="txddjdc" items="${ktxdd_jdcbq}">
									<option value="${txddjdc.address}">${txddjdc.address}</option>
								</c:forEach>
							</select>
							<input type="button" value="全选" class="btn" id="ddmcqx">
							<input type="button" value="全不选" class="btn" id="ddmcqbx">
						</td>
					</tr>
					<tr>
					<tr>
						<td align="right">副车牌号码：</td>
						<td>
							<input style="width: 260px" type="text" name="fcphm" class="easyui-textbox"/>
						</td>
					</tr>
					<tr>
						<td align="right">有效截止日期：</td>
						<td>
							<input id="yxjzrq_jdc" style="width: 150px" type="text" class="easyui-datetimebox" name="yxjzrq"></input>
							&nbsp; 金额：<input style="width: 50px" type="text" name="je" class="easyui-numberbox"/>
						</td>
					</tr>
						<td align="right">车辆近照名称：</td>
						<td>
							<input style="width: 150px" id="jdcjztjdz" type="text" name="sctpdz" class="easyui-textbox" readOnly="true"/>
							<span style="color: red;font-size: 12px;">*点击上传图片获取</span>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div style="width: 46%;float: left;">
			<form id="jdcjz_form" enctype="multipart/form-data" method="post">
				<table>
					<tr>
						<td >
							车辆近照：
							<input id="sctpdz" style="width: 105px" type="file" name="jdcjztjdz" onchange="selectFile(this)"/>
	                		<button onclick="sctp_jdcjz()" >上传</button>
	                		<span id="jdcjzts" style="color: red;font-size: 12px;"></span>
						</td>
					</tr>
					<tr>
						<td align="center" >
	                		<div id="jdcjz"></div>
	                	</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!--用户列表对话框 END-->









	<script type="text/javascript">
	function selectFile(input) { 
	   //清空div内容
	   document.getElementById("jdcjzts").innerHTML = "";
		//$("#pp").remove();	
	    var fileName = input.value; 
	    if(fileName.length > 1 && fileName ) {        
	        var ldot = fileName.lastIndexOf("."); 
	        var type = fileName.substring(ldot + 1); 
	        //alert(type);
	        if(type != "jpg"&&type != "jpeg"&&type != "png"&&type != "bmp"&&type != "gif") { 
	        	//$("#photo").after("<p style='color: red' id='pp'>请正确选择图片!</p>"); 
	        	document.getElementById("jdcjzts").innerHTML = "请正确选择图片!";
	            //清除当前所选文件             
	            input.outerHTML=input.outerHTML.replace(/(value=\").+\"/i,"$1\""); 
	        }
	        //$("#photo").empty();
	        //$("input[name='code']").val("").focus();
	        //$("#sfphoto").val("");
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
	<script type="text/javascript" src="script/views/bqgl/jdcbqgl.js"></script>
	<script type="text/javascript" src="script/views/inputYz/inputYz.js"></script>
</body>
</html>
