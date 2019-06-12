<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../common/model.jsp"%>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div>
	<div style="float:left;width:98%; height:460px; ">
		<table style="height:99%; width:98%; " class="tab">

			<tr style="margin-top:10px ">
				<th colspan="4">数据信息</th>
			</tr>
			<tr>
				<td>标签ID</td>
				<td>${Jsrk.bqid}</td>
				<td>所有人</td>
				<td>${Jsrk.syr}</td>
			</tr>
			<tr>
				<td>性别</td>
				<td>${Jsrk.sex}</td>
				<td>身份证号</td>
				<td>${Jsrk.sfzh}</td>
			</tr>
			<tr>
				<td>身份证发证时间</td>
				<td>${Jsrk.fzrq}</td>
				<td>身份证到期时间</td>
				<td>${Jsrk.dqsj}</td>
			</tr>
			<tr>
				<td>标签发签日期</td>
				<td>${Jsrk.fqsj}</td>
				<td>标签有效截止日期</td>
				<td>${Jsrk.yxjzrq}</td>
			</tr>
			<tr>
				<td>准驾车型</td>
				<td>${Jsrk.zjcx}</td>
				<td>国家</td>
				<td>${Jsrk.gj}</td>
			</tr>
			<tr>
				<td>所属单位</td>
				<td>${Jsrk.ssdw}</td>
				<td>可通行地点</td>
				<td>
					<textarea cols="40" rows="3" class="easyui-validatebox" >${Jsrk.ktxdd}</textarea>
				</td>
			</tr>
			<tr>
				<td>车牌号码</td>
				<td>${Jsrk.cphm}</td>
				<td>金额</td>
				<td>${Jsrk.je}</td>
			</tr>
			<tr>
				<td>标签状态</td>
				<td style="color: red;">${Jsrk.zt}</td>
				<td>所属领导</td>
				<td>${Jsrk.ld}</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td>照片</td>
				<td align="center" style="height: 150px;">
					<img id="fdtp1" width='150px' height="150px" src="upload/${Jsrk.sctpdz}" />
				</td>
			</tr>
		</table>
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

