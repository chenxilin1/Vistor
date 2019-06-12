<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../common/model.jsp"%>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div >
	<div style="float:left;width:513px; height:460px;">
		<table style="height:98%;width:98%; " class="tab">

			<tr style="margin-top:10px ">
				<th colspan="4">数据信息</th>
			</tr>
			<tr>
				<td>标签ID</td>
				<td>${Allmsg.bqid}</td>
				<td>所有人</td>
				<td>${Allmsg.syr}</td>
			</tr>
			<tr>
				<td>联系电话</td>
				<td>${Allmsg.lxdh}</td>
				<td>号牌号码</td>
				<td>${Allmsg.hphm}</td>
			</tr>
			<tr>
				<td>号牌种类</td>
				<td>${Allmsg.hpzl}</td>
				<td>车辆类型</td>
				<td>${Allmsg.cllx}</td>
			</tr>
			<tr>
				<td>车身颜色</td>
				<td>${Allmsg.csys}</td>
				<td>发动机号</td>
				<td>${Allmsg.fdjh}</td>
			</tr>
			<tr>
				<td>车辆识别代码</td>
				<td>${Allmsg.clsbdh}</td>
				<td>车辆品牌</td>
				<td>${Allmsg.clpp}</td>
			</tr>
			<tr>
				<td>所属单位</td>
				<td>${Allmsg.ssdw}</td>
				<td>所属科室</td>
				<td>${Allmsg.ssks}</td>
			</tr>
			<tr>
				<td>发签日期</td>
				<td>${Allmsg.fqrq}</td>
				<td>有效截止日期</td>
				<td>${Allmsg.yxjzrq}</td>
			</tr>
			<tr>
				<td>副车牌号码</td>
				<td>${Allmsg.fcphm}</td>
				<td>标签状态</td>
				<td style="color: red">${Allmsg.bqzt}</td>
			</tr>
			<tr>
				<td>所属领导</td>
				<td>${Allmsg.ld}</td>
				<td>金额</td>
				<td>${Allmsg.je}</td>
			</tr>
			<tr>
				<td>可通行地点</td>
				<td colspan="3" style="height: 10px;">
					<textarea cols="42%" rows="2%" class="easyui-validatebox" >${Allmsg.ktxdd}</textarea>
				</td>
			</tr>
		</table>
	</div>
	<div style="float:left;width:400px; height:400px;" id="p">
		<img id="fdtp1" style='width:98%;' height="98%" src="upload/${Allmsg.sctpdz}" />
	</div>
</div>
<style>
.tab tr {
	background: #f1f1f1;
}

.tab tr:nth-child(even) {
	background: #ccc;
}
</style>


<script type="text/javascript">
	$(function() {
		var a = '${Allmsg.sctpdz}';

		// str=str.replace(/\+/g, "%20B");
		a = a.replace(/\+/g, "%2B");

		$("#imgStr").attr("src", "bqgl/showImage?imgSrc=" + a);

	})
</script>