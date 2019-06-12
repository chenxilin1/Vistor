<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<meta name="author" content="Jophy" />
<title>用户登录</title>
<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="css/font-awesome.css" rel="stylesheet">
<link type="text/css" href="css/loginPage.css" rel="stylesheet"></link>
<link type="text/css" href="css/themes/default/easyui.css" rel="stylesheet"></link>
<link type="text/css" href="css/themes/icon.css" rel="stylesheet"></link>
<style type="text/css">
.mask {
	position: absolute;
	top: 0px;
	filter: alpha(opacity = 60);
	background-color: #777;
	z-index: 902;
	left: 0px;
	opacity: 0.5;
	-moz-opacity: 0.5;
}

#jzz {
	position: absolute;
	z-index: 9999;
    top: 48%;
    left:48%;
    font-size: 15px;
   
}
.changecolor0{color:#f00;}
.changecolor1{color:#0f0;}
.changecolor2{color:#00f;}
.changecolor3{color:#f0f;}
.changecolor4{color:#0ff;}
.changecolor5{color:#000;}

#div_sdk table {
	margin: 5px 20px;
}
#div_sdk tr {
	height: 40px;
}
#div_IP table {
	margin: 15px 20px;
}
#div_IP tr {
	height: 40px;
}

</style>

<script src="js/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="js/jquery.easyui.min-1.5.2.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
placeholderfun();
	//密码得到焦点
	$("#password").focus(function(){
		$("#left_hand").animate({
			left: "160",
			top: " -38"
		},{step: function(){
			if(parseInt($("#left_hand").css("left"))>140){
				$("#left_hand").attr("class","left_hand");
			}
		}}, 2000);
		$("#right_hand").animate({
			right: "-74",
			top: "-38px"
		},{step: function(){
			if(parseInt($("#right_hand").css("right"))> -80){
				$("#right_hand").attr("class","right_hand");
			}
		}}, 2000);
	});

	//账号得到焦点
	$("#username").focus(function(){
		$("#left_hand").attr("class","initial_left_hand");
		$("#left_hand").attr("style","left:110px;top:-12px;");
		$("#right_hand").attr("class","initial_right_hand");
		$("#right_hand").attr("style","right:-122px;top:-12px");
	});
	//验证码得到焦点
	$("#yzm").focus(function(){
		$("#left_hand").attr("class","initial_left_hand");
		$("#left_hand").attr("style","left:150px;top:-12px;");
		$("#right_hand").attr("class","initial_right_hand");
		$("#right_hand").attr("style","right:-82px;top:-12px");
	});
});
</script>
<script type="text/javascript">
function sb(){
	$("#InitInfoForm").submit(); 
}
</script>
</head>
<body>
	<div id="mask" class="mask"></div>
	<div id="jzz" style="display: none">
		<b>加载中。。<span id="remaincolor">。</span></b>
	</div>
	<div class="top_div">
		<div style="text-align:center;font-size:42px;color:#FFFFFF;font-weight:bold;letter-spacing:20px;padding-top:100px;">
			车辆管理系统
		</div>
	</div>
	<div style="background: rgb(255, 255, 255); margin: -150px auto auto; border: 1px solid rgb(231, 231, 231);
        border-image: none; width: 400px; height: 250px; text-align: center;">
		<div style="width: 165px; height: 96px; position: absolute;">
			<div class="tou"></div>
			<div class="initial_left_hand" id="left_hand"></div>
			<div class="initial_right_hand" id="right_hand"></div>
		</div>
		<form action="login/toIndex1" name="InitInfoForm" id="InitInfoForm">
			<h3 style="color:rgb(0, 142, 173)">普通用户登录</h3>
			<p style="padding: 20px 0px 10px; position: relative;">
				<span class="spanLable"> 用户名：</span> <span class="u_logo"></span>
				<input class="ipt" id="username" type="text" placeholder="请输入用户名" name="yhm" value="">
			</p>
			<p style="position: relative;">
				<span class="spanLable"> 密 &nbsp;&nbsp;码：</span> <span class="p_logo"></span>
				<input class="ipt" id="password" type="password" placeholder="请输入密码" name="yhmm" value="">
			</p>
			<!-- 
			<p style="position: relative;padding: 10px 0px 10px;">
				<span class="spanLable" style="float:left;margin-left:15px;"> 验证码：</span> <span class="y_logo"></span>
				<input class="ipt code" id="yzm" style="width:180px;margin-left:-30px;" type="text" placeholder="请输入验证码" name="code" value="">
				<span style="margin-left:20px;"><img src="hzyzm?time=<%= new Date().getTime() %>" alt="验证码" id="dianji" /> </span>
			</p>
			 -->
			<div style="height: 50px; line-height: 50px; margin-top: 10px; border-top-color: rgb(231, 231, 231);
            border-top-width: 1px; border-top-style: solid;">
				<p style="margin: 0px 35px 20px 45px;">
					 
					<span id="SDKYZ" style="float: left;display: none;">
						<a style="background: rgb(0, 142, 173); padding: 7px 10px;color: rgb(255, 255, 255);" href="#" >SDK或Mac验证 </a>
					</span> 
					<span style="float: right;"> 
						<%--
	                	<a style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px;
	                        border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255);
	                        font-weight: bold;"  onclick="sb()">登录</a>
	                 	--%> 
                 	<span id="login"> 
                 		<a style="background: rgb(0, 142, 173); padding: 7px 10px;color: rgb(255, 255, 255);" href="#" >登录 </a>
                 	</span> 
                 </span>
				</p>
			</div>
		</form>
	</div>
	<div style="margin-top: 90px;text-align: center;">
  		<span style="font-size: 20px;color: #008ead;letter-spacing:10px;">
  			<b></b>
  		</span>
  	</div>
  	<div id="div_sdk" style="display: none;">
  		<form id="MacOrIpForm">
  			<table>
  				<tr>
  					<td align="right">选择购买版本：</td>
  					<td>
  						<label for="bb1">
							<input id="bb1" type="radio" name="gmbb" value="0" checked="checked"/>
							Mac版
						</label>
						<label for="bb2">
							<input id="bb2" type="radio" name="gmbb" value="1" />
							SDK版
						</label>
  					</td>
  				</tr>
  				<tr id="macdztr">
  					<td align="right">Mac地址：</td>
  					<td>
  						<!-- 
  							<input type="text" class="easyui-textbox" name="macdz" style="width: 235px"></input>
  						 -->
  						 <div id="macinput">
  						 	<input type="text" name="macdz" maxlength="2"style="width: 25px;">-
				            <input type="text" name="macdz" maxlength="2"style="width: 25px;">-
				            <input type="text" name="macdz" maxlength="2"style="width: 25px;">-
				            <input type="text" name="macdz" maxlength="2"style="width: 25px;">-
				            <input type="text" name="macdz" maxlength="2"style="width: 25px;">-
				            <input type="text" name="macdz" maxlength="2"style="width: 25px;">
  						 </div>
  					</td>
  				</tr>
  				<tr id="Sdkdztr" style="display: none;">
  					<td align="right">SDK名称：</td>
  					<td>
  						<input type="text" class="easyui-textbox" name="sdkmc" style="width: 235px"></input>
  					</td>
  				</tr>
  			</table>
  		</form>
  	</div>
  	<div id="div_IP" style="display: none;">
  		<form id="SjkIpForm">
  			<table>
  				<tr id="ipdztr">
  					<td align="right">IP新地址：</td>
  					<td>
  						<input type="text" class="easyui-textbox" name="sjkip" style="width: 235px"></input>
  					</td>
  				</tr>
  			</table>
  		</form>
  	</div>
  	
  	<script type="text/javascript">
		var i=0;
		function blink(){
			document.getElementById("remaincolor").className="changecolor"+i%6;
			i++;
		}
		setInterval(blink, 500);
		window.onload = function() {
			var demo=document.getElementById('macinput');
	        input=demo.getElementsByTagName('input');
	        var iNow=0;
	        type   = !-[1,] ? 'onpropertychange' : 'oninput',
	                limit  = 2; //满足自动切换焦点的字符数
	        for(var i=0;i<input.length-1;i++){
	            input[i].index=i;
	            input[i][type]=function () {
	                iNow=this.index;
	                var that=this;
	                setTimeout(function () {
	                   that.value.length>limit-1&&input[iNow+1].focus();
	                },0)
	            }
	        }
		}
	</script>
	<script type="text/javascript" src="script/views/login/logins.js"></script>
	<script type="text/javascript" src="script/views/index/syUtil.js"></script>
</body>
</html>















