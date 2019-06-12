<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>图片放大对比</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
.zoomPan{width:300px;left:0px;top:0px;position:relative; float: left;}
.sh{zoom:1;background:#eee;filter:progid:DXImageTransform.Microsoft.dropShadow(color='#54000000', OffX=2,OffY=2);-webkit-box-shadow:4px 4px 4px #666;-moz-box-shadow:4px 4px 4px #666;}
#zoom{position:absolute;width:254px;height:254px;border:3px solid #fff;left:-9999px;top:0;overflow:hidden;background:#fff;}
#zoom img{position:relative;}
img {
	border-width: 0px;
}
body, p {
	font-size: 12px;
   }
</style>
  </head>
  
  <body>

	<div style="margin-top: 50px;margin-left: 20px;">
		<div style="float: left;">
			<div class="zoomPan" id="zoomPan">
				<img src="${cltxtp}" alt="" width="380px" height="380px" />
				<div id="zoom" class="sh">
					<img src="${cltxtp}" alt="通行图片" />
				</div>
			</div>
		</div>

		<div style="float: right;margin-right: 20px;">
			<a href="${clsctp}" id="zoom1" rel="zoom-position: inner; zoom-width:380px; zoom-height:380px" class="MagicZoom">
				<img src="${clsctp}" width="380px" height="380px" alt="上传图片" />
			</a>
		</div>
	</div>














	<script type="text/javascript">
//<![CDATA[
function zoomBox() {this.index.apply(this, arguments)}
zoomBox.prototype = {
    index: function(win,zoom) {
        var win=document.getElementById(win);
        var box=document.getElementById(zoom);
        var img=box.getElementsByTagName('IMG')[0];
        var zoom=img.width/win.getElementsByTagName('IMG')[0].width;
        var z=Math.round(box.offsetWidth/2);
        win.onmousemove=function (e){
            e = e || window.event;
            var x=e.clientX,y=e.clientY, ori=win.getBoundingClientRect();
            if (x>ori.right+20||y>ori.bottom+20||x<ori.left-20||y<ori.top-20)box.style.display='none';
            x-=ori.left;
            y-=ori.top;
            box.style.left=x-z+'px';
            box.style.top=y-z+'px';
            img.style.left=-x*zoom+z+'px';
            img.style.top=-y*zoom+z+'px';
        }
        win.onmouseover=function (){box.style.display=''}
    }
};
window.onload=function (){
    x=new zoomBox('zoomPan','zoom')
}
 //]]>
</script>
<script src="js/packed.js" type="text/javascript"></script>
  </body>
</html>
