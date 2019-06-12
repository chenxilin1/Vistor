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
    
    <title>查看摄像设备</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body style="width:100%;">
	<div id="divPlugin" class="plugin" align="center" style="float: left;width: 70%;height: 70%"></div>
	<div style="width: 300px;float: right;text-align: right;">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clickFullScreen()">
			<span style="font-size: 18px;">全屏</span>
		</a>

		<div id="tt" class="easyui-tabs" style="width:auto;height:auto;">   
		    <div title="预览选择" style="padding:20px;display:none;">   
		        <fieldset class="ptz"id="ptz">
					<legend style="text-align: left;">视频预览</legend>
					<table cellpadding="0" cellspacing="1" border="0" class="left" align="center">
						<tr style="display: none">
							<td style="text-align: center;">
								<input id="yldl" type="button" class="btn1" value="登录" style="width: 120px" />
							</td>
						</tr>
						<tr>
							<td><b style="font-size: 15px;">窗口分割数</b></td>
						</tr>
						<tr>
							<td style="text-align: center;">
								<select style="width: 135px;" class="sel2" onchange="changeWndNum(this.value);">
									<option value="1">1x1</option>
									<option value="2" selected>2x2</option>
									<option value="3">3x3</option>
									<option value="4">4x4</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>&nbsp;&nbsp;<b style="font-size: 15px;">选择相机</b></td>
						</tr>
						<tr>
							<td style="text-align: center;">
								
								<select id="sxsb" name="sxsb" style="width: 135px;">
								<option value="">不选择</option>
								<%--
								<option value="192.168.1.64,1,admin,admin12345">测试IP:192.168.1.64</option>
								<option value="192.168.3.18,1,admin,12345">测试IP:192.168.3.18</option>
								 --%>
									<c:forEach var="sbxj" items="${Allsb}">
										<option value="${sbxj.xjip},${sbxj.xjtd},${sbxj.xjzh},${sbxj.xjmm},${sbxj.xjmc}">${sbxj.xjmc}</option>
									</c:forEach>
								</select>
								<%--
								<input id="sxsb" class="easyui-combobox" name="sxsb" style="width: 135px;height: 20px;" 
		    								data-options="
												url:'sxgl/findAllxjmc',
												method:'get',
												valueField:'jgmc',
												textField:'jgmc',
												panelHeight:'auto'">
		 						--%>
							</td>
						</tr>
						<tr>
							<td style="text-align: center;">
								<input id="ksyl" type="button" class="btn1" value="开始预览" style="width: 60px" />
								&nbsp;&nbsp;
								<input id="tzyl" type="button" class="btn1" value="停止预览" style="width: 60px" />
							</td>
						</tr>
						<tr style="display: none">
							<td style="text-align: center;">
								<input id="qbtzyl" type="button" class="btn1" value="全部停止预览" style="width: 120px" />
							</td>
						</tr>
					</table>
					
					<form  id="xjxx">
						<table>
							<tr>
								<td colspan="2">
									<input id="xjxx_txxx" type="button" value="添加前清空表单" style="width: 100px"/>
									<input id="xjxx_bj" type="button" value="修改前编辑表单" style="width: 100px"/>
								</td>
							</tr>
							<tr>
								<td>选择地点：</td>
								<td>
									<select id="input_ddmc" name="ddmc" style="width: 125px;">
										<c:forEach var="dd" items="${ddmcs}">
											<option value="${dd.address}">${dd.address}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td>相机名称：</td>
								<td>
									<input id="input_xjmc" type="text" name="xjmc" style="width: 125px;"></input>
								</td>
							</tr>
							<tr>
								<td>相机IP：</td>
								<td><input id="input_xjip" type="text" name="xjip" style="width: 125px;"/></td>
							</tr>
							<tr>
								<td>相机通道：</td>
								<td><input id="input_xjtd" type="text" name="xjtd" style="width: 125px;"/></td>
							</tr>
							<tr>
								<td>相机账号：</td>
								<td><input id="input_xjzh" type="text" name="xjzh" style="width: 125px;"/></td>
							</tr>
							<tr>
								<td>相机密码：</td>
								<td><input id="input_xjmm" type="password" name="xjmm" style="width: 125px;"/></td>
							</tr>
							<tr>
								<td colspan="2" style="border: solid 0px red;" >
									<input id="xjxx_add" type="button"  value="添加" />
									<input id="xjxx_delete" type="button" value="删除"  />
									<input id="xjxx_update" type="button" value="修改"  />
								</td>
							</tr>
						</table>
					</form>
					<span id="span_cx" style="color: red;">此相机不可预览,请选择其他相机</span>
				</fieldset>  
		    </div>   
		    <div title="云台控制" style="overflow:auto;padding:20px;display:none;">   
		       <table cellpadding="0" cellspacing="3" border="0" class="left" align="center">
		        	<tr>
						<td align="center">
							<span style="font-size: 20px;">云台速度:</span>
							<select id="ptzspeed" class="sel" style="width: 50px;">
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option selected>4</option>
								<option>5</option>
								<option>6</option>
								<option>7</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="button" class="btn" value="左上" onmousedown="mouseDownPTZControl(5);" onmouseup="mouseUpPTZControl();" />
							<input type="button" class="btn" value=" 上  " onmousedown="mouseDownPTZControl(1);" onmouseup="mouseUpPTZControl();" />
							<input type="button" class="btn" value="右上" onmousedown="mouseDownPTZControl(7);" onmouseup="mouseUpPTZControl();" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="button" class="btn" value=" 左  " onmousedown="mouseDownPTZControl(3);" onmouseup="mouseUpPTZControl();" />
							<input type="button" class="btn" value="自动" onclick="mouseDownPTZControl(9);" />
							<input type="button" class="btn" value=" 右  " onmousedown="mouseDownPTZControl(4);" onmouseup="mouseUpPTZControl();" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="button" class="btn" value="左下" onmousedown="mouseDownPTZControl(6);" onmouseup="mouseUpPTZControl();" />
							<input type="button" class="btn" value=" 下  " onmousedown="mouseDownPTZControl(2);" onmouseup="mouseUpPTZControl();" />
							<input type="button" class="btn" value="右下" onmousedown="mouseDownPTZControl(8);" onmouseup="mouseUpPTZControl();" />
						</td>
					</tr>
				</table>
				<table cellpadding="0" cellspacing="3" border="0" class="left" align="center">
		            <tr>
		                <td class="tt" align="center">
							<input type="button" class="btn" value="变 倍+" onmousedown="PTZZoomIn()" onmouseup="PTZZoomStop()">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" class="btn" value="变 倍-" onmousedown="PTZZoomout()" onmouseup="PTZZoomStop()">
						</td>
		            </tr>
		            <tr>
		                <td class="tt" align="center">
							<input type="button" class="btn" value="变 焦+" onmousedown="PTZFocusIn()" onmouseup="PTZFoucusStop()">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" class="btn" value="变 焦-" onmousedown="PTZFoucusOut()" onmouseup="PTZFoucusStop()">
						</td>
		            </tr>
		            <tr>
		                <td class="tt" align="center">
							<input type="button" class="btn" value="光 圈+" onmousedown="PTZIrisIn()" onmouseup="PTZIrisStop()">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" class="btn" value="光 圈-" onmousedown="PTZIrisOut()" onmouseup="PTZIrisStop()">
						</td>
		            </tr>
					<tr>
						<td align="center">
							预置点名称:<%--<input id="preset" type="text" class="txt" value="1"  style="width: 30px"/> --%>
							<%-- <input id="preset" style="IME-MODE: disabled; WIDTH: 95px; HEIGHT: 15px" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" type="text"  class="txt" value="1"/>--%>
							<input id="preset" type="text" class="txt" style="width: 95px"/>
							<input type="button" class="btn" value="设 置 " onclick="clickSetPreset();" />
						</td>
					</tr>
					<tr>
						<td align="center">
							预置点选择:
							<select style="width: 95px;" class="sel2" id="ysddxz">
								
							</select>
							<input type="button" class="btn" value="调 用 " onclick="clickGoPreset();" />
						</td>
					</tr>
					<tr style="display: none;">
						<td align="center">
							<input type="button" class="btn" value="删除" onclick="clickDelPreset();" />
						</td>
					</tr>
					
					<tr>
						<td align="center">
							<input type="button" class="btn" value="修改" onclick="clickUpdPreset();" />
							<input type="button" class="btn" value="删除" onclick="clickDelPreset();" />
						</td>
					</tr>
					<tr style="display: none;">
						<td>
							<fieldset class="operate" style="height: 160px;">
								<legend>操作信息</legend>
								<div id="opinfo" class="opinfo"></div>
							</fieldset>
						</td>
					</tr>
	        	</table>
	        	<br><br>
	        	<span id="span_ysdsz" style="color: red;"></span>
		    </div>     
		</div>
	</div>
	<div style="width: 35px;float: left;"> </div>
	
	<script type="text/javascript" src="js/webVideoCtrl.js"></script>
	<script type="text/javascript" src="script/views/sxgl/cksxsb.js"></script>

</body>
<script type="text/javascript">
  $(function(){
	  param.init({
	  });
  });
  </script>
</html>
