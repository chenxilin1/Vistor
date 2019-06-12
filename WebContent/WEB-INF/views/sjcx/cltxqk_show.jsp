<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../common/model.jsp"%>
<link rel="stylesheet" type="text/css" href="css/tpfdj.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<div>
	<div style="float:left;width:610px; height:500px;">
		<table style="height:495px; width :50%;float: left;" class="tab">
			<tr style="margin-top:10px ">
				<th colspan="2">车辆数据</th>
			</tr>
			<tr>
				<td>标签ID</td>
				<td>${jdcbq_txcl.bqid}</td>
			</tr>
			<tr>
				<td>联系电话</td>
				<td>${jdcbq_txcl.lxdh}</td>
			</tr>
			<tr>
				<td>号牌号码</td>
				<td>${jdcbq_txcl.hphm}</td>
			</tr>
			<tr>
				<td>号牌种类</td>
				<td>${jdcbq_txcl.hpzl}</td>
			</tr>
			<tr>
				<td>车辆类型</td>
				<td>${jdcbq_txcl.cllx}</td>
			</tr>
			<tr>
				<td>车辆识别代码</td>
				<td>${jdcbq_txcl.clsbdh}</td>
			</tr>
			<tr>
				<td>车辆品牌</td>
				<td>${jdcbq_txcl.clpp}</td>
			</tr>
			<tr>
				<td>所属单位</td>
				<td>${jdcbq_txcl.ssdw}</td>
			</tr>
			<tr>
				<td>所属科室</td>
				<td>${jdcbq_txcl.ssks}</td>
			</tr>
			<tr>
				<td>发签日期</td>
				<td>${jdcbq_txcl.fqrq}</td>
			</tr>
			<tr>
				<td>可通行地点</td>
				<td>
					<textarea cols="22" rows="3" class="easyui-validatebox" >${jdcbq_txcl.ktxdd}</textarea>
				</td>
			</tr>
			<tr>
				<td>领导</td>
				<td>${jdcbq_txcl.ld}</td>
			</tr>
			<tr>
				<td>有效截止日期</td>
				<td>${jdcbq_txcl.yxjzrq}</td>
			</tr>
			<tr>
				<td>副车牌号码</td>
				<td>${jdcbq_txcl.fcphm}</td>
			</tr>
		</table>
		<table style="height:40px; width:50%;float: left;" class="tab">
			<tr style="margin-top:10px ">
				<th colspan="2">通行数据</th>
			</tr>
			<tr>
				<td>经过时间</td>
				<td>${tCltxkb.jgsj}</td>
			</tr>
			<tr>
				<td>进出口方向</td>
				<td>${tCltxkb.fx}</td>
			</tr>
			<tr>
				<td>通行状态</td>
				<td>${tCltxkb.txzt}</td>
			</tr>
			<tr>
				<td>通行地点</td>
				<td>${tCltxkb.jgMc}</td>
			</tr>
		</table>
		<table style="height:300px; width:50%;float: left;" class="tab">
			<tr style="margin-top:10px ">
				<th colspan="2">驾驶人数据</th>
			</tr>
			<tr>
				<td>标签ID</td>
				<td>${jsrbq_txcl.bqid}</td>
			</tr>
			<tr>
				<td>所有人</td>
				<td>${jsrbq_txcl.syr}</td>
			</tr>
			<tr>
				<td>性别</td>
				<td>${jsrbq_txcl.sex}</td>
			</tr>
			<tr>
				<td>身份证号</td>
				<td>${jsrbq_txcl.sfzh}</td>
			</tr>
			<tr>
				<td>发签日期</td>
				<td>${jsrbq_txcl.fqsj}</td>
			</tr>
			<tr>
				<td>有效截止日期</td>
				<td>${jsrbq_txcl.yxjzrq}</td>
			</tr>
			<tr>
				<td>准驾车型</td>
				<td>${jsrbq_txcl.zjcx}</td>
			</tr>
			<tr>
				<td>可通行地点</td>
				<td>
					<textarea cols="22" rows="3" class="easyui-validatebox" >${jsrbq_txcl.ktxdd}</textarea>
				</td>
			</tr>
			<tr>
				<td>车牌号码</td>
				<td>${jsrbq_txcl.cphm}</td>
			</tr>
			<tr>
				<td>领导</td>
				<td>${jsrbq_txcl.ld}</td>
			</tr>
			<tr>
				<td>驾驶人照片</td>
				<td align="center" style="height: 100px;">
					<img style='width:100px' height="100px" src="upload/${jsrbq_txcl.sctpdz}" />
				</td>
			</tr>
		</table>
	</div>
	<div id="shuangjitp" style="float:left;width:300px; height:320px;display:inline;">
		<div style="width: 98%;height: 2%" align="center">
			<span>通行图片</span>
		</div>
		<div style="width: 98%;height: 48%" align="center" id="p">
			<img id="imgStr" style='width:98%;' class="sampleimage" height="98%" alt="通行图片"/>
		</div>
		
		<div style="width: 98%;height: 2%" align="center">
			<span>上传图片</span>
		</div>
		<div style="width: 98%;height: 48%" align="center">
			<img src="upload/${jdcbq_txcl.sctpdz}" style='width:98%;' height="98%" alt="上传图片"/>
		</div>
	</div>
</div>
<style>
img {
		border-width: 0px;
	}
	body, p {
		font-size: 12px;
    }
.tab tr {
	background: #f1f1f1;
}

.tab tr:nth-child(even) {
	background: #ccc;
}
</style>


<script type="text/javascript">
	$(function() {
		var a = '${tCltxkb.tp1}';
		//a = a.replace(/\+/g, "%2B");
		a=encodeURI(encodeURIComponent(a));
		$("#imgStr").attr("src", "sjcx/showImage?imgSrc=" + a);
	})
	//双击图片查看详细对比图片
	//$("#shuangjitp").dblclick(function() {
	//单击图片查看详细对比图片
	$("#shuangjitp").click(function() {
		var clsctp=encodeURI(encodeURIComponent('upload/${jdcbq_txcl.sctpdz}'));
		var cltxtp=encodeURI(encodeURIComponent('${tCltxkb.tp1}'));
		
		//alert('车辆上传图片='+clsctp);
		//alert('车辆通行图片='+cltxtp);
		//window.open.href="${pageContext.request.contextPath}/ss.html";
		window.open('${pageContext.request.contextPath}/sjcx/tpdbt?clsctp='+clsctp+'&cltxtp='+cltxtp,'','height=500,width=911,left=300,top=150,scrollbars=yes,status =yes,location=no')
		
		
	})
	
	
	
</script>


