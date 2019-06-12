<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>系统后台网站</title>
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
	#divrztc td{
		width: 210px;
	}
</style>
<link href="css/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="css/themes/icon.css" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min-1.5.2.js"></script>
<script type="text/javascript" src='js/outlook2.js'></script>
<script type="text/javascript">
	
</script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no" onload="tree()">
	<noscript>
		<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
			<img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div region="north" split="true" border="false" style="overflow: hidden; height: 50px;
        background: url(images/ssss.bmp) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
		<span style="float:right; padding-right:30px; margin-top: 10" class="head">
			<span style="margin-right:10px; margin-top: 20;" id="yue_fen"></span>
			欢迎&nbsp;&nbsp;
			<span id="hqyhm" style="color: red;font-size: 17px;">${username}</span>
			&nbsp;&nbsp;
			<a id="editpass" href="javascript:void(0)" style="text-decoration: none;">修改密码</a>
			<a id="loginOut" href="javascript:void(0)" style="text-decoration: none;">安全退出</a>
		</span>
		<span style="padding-left:10px;  ">
			<!-- <img src="images/zlkj.png" width="40px" height="40px" align="absmiddle" /> -->
			<img id="animation" src="images/zlkj.png" width="41px" height="41px" align="absmiddle" style="margin-top: 2px;" />
		</span>
		<span style="margin-left:20px; margin-top: 20px;font-size: 24px;">国家重要机关反恐防范系统</span>
	</div>
	<div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
		<div class="footer">
			
		</div>
	</div>
	<%--<div region="west" hide="true" split="true" title="导航菜单" style="width:190px;" id="west">
	<div region="west" hide="true" split="true" title="导航菜单" style="width:190px;" id="west">
		<div id="nav" class="easyui-accordion" fit="true" border="false">
			<!--  导航内容 -->
		</div>
	</div>--%>

	<!--左侧导航start-->
	<div data-options="region:'west',title:'导航菜单',split:true" style="width:210px;"id="west">
		<div id="nav" class="easyui-accordion" fit="true" border="false">
			<!--  导航内容 -->
		</div>
	</div>
	<!--左侧导航 END-->

	<!--  右侧公告栏 -->
	<%--
	<div data-options="region:'east',iconCls:'icon-reload',title:'公告',split:true" style="width:210px;" align="center" id="east">
		<div>
			<object type="application/x-shockwave-flash" style="outline:none;" data="flash/hamster/hamster.swf?up_bodyColor=f0e9cc&amp;up_feetColor=D4C898&amp;up_eyeColor=000567&amp;up_wheelSpokeColor=DEDEDE&amp;up_wheelColor=FFFFFF&amp;up_waterColor=E0EFFF&amp;up_earColor=b0c4de&amp;up_wheelOuterColor=FF4D4D&amp;up_snoutColor=F7F4E9&amp;up_bgColor=F0E4E4&amp;up_foodColor=cba920&amp;up_wheelCenterColor=E4EB2F&amp;up_tailColor=E6DEBE&amp;" width="168" height="168">
				<param name="movie" value="flash/hamster.swf?up_bodyColor=f0e9cc&amp;up_feetColor=D4C898&amp;up_eyeColor=000567&amp;up_wheelSpokeColor=DEDEDE&amp;up_wheelColor=FFFFFF&amp;up_waterColor=E0EFFF&amp;up_earColor=b0c4de&amp;up_wheelOuterColor=FF4D4D&amp;up_snoutColor=F7F4E9&amp;up_bgColor=F0E4E4&amp;up_foodColor=cba920&amp;up_wheelCenterColor=E4EB2E&amp;up_tailColor=E6DEBE&amp;">
				<param name="AllowScriptAccess" value="always">
				<param name="wmode" value="opaque">
			</object>
		</div>
		<div>
			<div id="cc" class="easyui-calendar" style="width:180px;height:180px;"></div>
		</div>
		<div style="display: block;">
			<br>
			<div align="center" style="font-size: 15px;">日志信息</div>
			<hr>
			<div id="divrztc">
				
			</div>
		</div>
	</div>
	 --%>
	<div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<!--
			<div title="欢迎使用" style="padding:20px;overflow:hidden; color:red; ">
				<h1 style="font-size:45px; text-align: center;margin-top: 20%;">欢迎使用智能停车场管理系统</h1>
			</div>
			-->
			<div title="欢迎使用">
				<img alt="" src="images/hyymklmy.jpg" style="height: 99%;width: 100%;" >
			</div>
		</div>
	</div>
	<!--修改密码窗口-->
	<div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false" maximizable="false" icon="icon-save" style="width: 300px; height: 300px; padding: 5px;
        background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<table cellpadding=3>
					<tr>
						<td>原密码：</td>
						<td>
							<input id="ysmm" type="Password" class="txt01" />
						</td>
					</tr>
					<tr>
						<td>新密码：</td>
						<td>
							<input id="txtNewPass" type="Password" class="txt01" />
						</td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td>
							<input id="txtRePass" type="Password" class="txt01" />
						</td>
					</tr>
				</table>
			</div>
			<div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
				<!-- 
				<a id="btnEp" class="easyui-linkbutton" icon="icon-ok">确定</a>
				<a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
				 -->
				<a id="btnEp" class="easyui-linkbutton" icon="icon-ok">&nbsp;&nbsp;</a>
				<a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">&nbsp;&nbsp;</a>
			</div>
		</div>
	</div>
	<div id="mm" class="easyui-menu" style="width:160px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
	<script type="text/javascript" src="script/views/index/indexMenu.js"></script>
	<script type="text/javascript" src="script/views/index/syUtil.js"></script>
</body>
</html>