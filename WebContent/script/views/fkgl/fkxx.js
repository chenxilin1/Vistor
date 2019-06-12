function serializeObject(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};
$(function() {
	$("#fkxx_list").datagrid({
		url : 'fkgl/findFkxx?time=' + new Date().getTime(),
		title : "车辆通行情况列表",
		fit : true,
		fitColumns : true,
		pagination : true,
		border : false,
		idField : 'yhm',
		rownumbers : true,
		striped : true,/*隔行变色*/
		height : "530",
		loadMsg : '正在玩命加载，请耐心等待。。。。。。',
		//条的上下
		pagination : true,
		pagePosition : 'bottom',
		remoteSort : false,//是否向服务器端请求排序
		pageSize : 20,
		pageList : [ 5, 10, 20, 30, 40, 50 ],
		checkOnSelect : true,
		selectOnCheck : true,
		singleSelect:true,
		columns : [ [ {
			field : 'xuanKuang',
			checkbox : true
			//,hidden:"false"
		}, {
			field : 'id',
			title : '编号',
			width : 130,
			//align:'center',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
			,hidden:true
		}, {
			field : 'hp',
			title : '号牌',
			width : 30,
			sortable : true
			,hidden:true
		}, {
			field : 'hm',
			title : '号码',
			width : 30,
			sortable : true
			,hidden:true
		}, {
			field : 'zhi',
			title : '值',
			width : 30,
			sortable : true
			,hidden:true
		}, {
			field : 'userName',
			width : 200,
			title : '拜访人姓名',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'sex',
			width : 80,
			title : '性别',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'idCard',
			width : 200,
			title : '身份证号',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'phone',
			width : 150,
			title : '电话',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'hphm',
			title : '号牌号码',
			width : 150,
			//align:'center',
			sortable : true,
			formatter : function(value) {
				var a="";
				if (null == value) {
					value="没开车";
					a = '<span style="color: red;">'+value+'</span>';
				}else {
					a=value;
				}
				return "<span title='" + value + "'>" + a + "</span>";
			}
		}, {
			field : 'userpath',
			width : 200,
			title : '经过地点',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'visitStartTime',
			width : 200,
			title : '拜访开始时间',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'visitEndTime',
			width : 200,
			title : '拜访结束时间',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'statar',
			width : 200,
			title : '拜访状态',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}else {
					if ("0"==value) {
						value = '<span style="color: #8f02e8;">取消预约</span>';
					}else if ("2"==value) {
						value = '<span style="color: #03cdeb;">预约中</span>';
					}else if ("3"==value) {
						value = '<span style="color: #46bb93;font-size: 17px;">拜访中。。.</span>';
					}else if ("4"==value) {
						value = '<span style="color: red;">拜访超时</span>';
					}else if ("15"==value) {
						value = '<span style="color: red;">拜访状态异常</span>';
					}else if ("14"==value) {
						value = '<span style="color: #0a66fe;">数据有误</span>';
					}
				}
				return "<span >" + value + "</span>";
			}
		}, {
			field : 'createTime',
			width : 200,
			title : '创建时间',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'email',
			width : 200,
			title : '受访人姓名',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'editor',
			width : 200,
			title : '受访人科室',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'fkyy',
			width : 200,
			title : '拜访原因',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		//,hidden:true
		} ] ],
		toolbar : [ {
			text : '添加 ',
			iconCls : 'icon-add',
			handler : function() {
				var id = $("#isAdminQJ").val();
				if ("3" == id) {
					alert("没有权限操作");
					//}else if ("2"==id){
				} else {
					$('#fkxx_form').form("reset");
					$('#jgdd').combobox("clear");
					if ("1" != id) {
						$("#tj1").attr("disabled", "true");
					}
					append();
				}
			}
		}, '-', {
			text : '取消预约',
			iconCls : 'icon-remove',
			handler : function() {
				var id = $("#isAdminQJ").val();
				if ("3" == id) {
					alert("没有权限操作");
				} else {
					remove();
				}
			}
		}, '-', {
			text : '修改',
			iconCls : 'icon-search',
			handler : function() {
				var id = $("#isAdminQJ").val();
				if ("3" == id) {
					alert("没有权限操作");
				} else {
					editFun();
				}
			}
		}, '-', {
			text : '条件查询',
			iconCls : 'icon-search',
			handler : function() {
				$('#tjcxan').layout('expand', 'north');
			}
		}, '-', {
			text : '<span style="font-size:20px;color: #6cb9f3">︽</span>',
			handler : function() {
				$("div.easyui-layout").layout('collapse', 'north');
			}
		} ]
	});
});
/**
 * 查询
 */
function searchFun() {
	$("#fkxx_list").datagrid("unselectAll");
	$("#fkxx_list").datagrid('load', serializeObject($("#findBh")))

}


/**
 * 打开注册对话框
 */
function append() {
	var dqsj=new Date();
	//alert(dqsj.getDate());
	dqsj.setDate(dqsj.getDate()+7);//当前时间加7天 因为格式化的时候要减7天
	dqsj.setHours(dqsj.getHours()+1, dqsj.getMinutes(), dqsj.getSeconds());//加一个小时
	$('#add_kssj').datetimebox('setValue', formatterDate(dqsj));
	dqsj.setHours(dqsj.getHours()+1, dqsj.getMinutes(), dqsj.getSeconds());//在上面的基础上再加一个小时
	$('#add_jssj').datetimebox('setValue', formatterDate(dqsj));
	$("#fkxxDuiHuaKuang").dialog({
		title : "添加信息",
		buttons : [ {
			id : "save",
			text : "保存",
			handler : function() {
				add();
			}
		}, {
			text : "关闭",
			handler : function() {
				$("#fkxxDuiHuaKuang").dialog("close");
			}
		} ]
	});
	$("#fkxxDuiHuaKuang").dialog('open');
	//清空表单数据
	$("#save").css({
		"text-algin" : "center"
	});
}
/**
 * 添加信息提交
 */
function add() {
	//validate该方法可以校验表单的输入是否合法
	var jiaoYan = $("#fkxx_form").form('validate');
	if (jiaoYan) {
		//合法提交
		$.ajax({
			url : 'fkgl/addFkxx?time=' + new Date().getTime(),
			data : $("#fkxx_form").serialize(),//序列化表单数据,方便提交
			type : "POST",//提交方式
			dataType : "text", //服务器响应回来数据类型
			success : function(msg) {//接收服务器响应的处理函数
				//msg表示接收到的服务器响应内容
				if ("YES" === msg) {
					alert("添加成功");
					//刷新表格数据
					$("#fkxx_list").datagrid("reload");
					//关闭窗口
					$("#fkxxDuiHuaKuang").dialog("close");
					//window.location.reload();
				}else if("KLRR"===msg){
					alert("掉线状态,请重新登录");
				} else {
					alert("添加失败,请检查选项或刷新页面重试！");
				}
			}
		});
	} else {
		alert("输入格式有误");
	}
}
/**
 * 取消预约功能
 */
function remove() {
	var ids = [];
	var rows = $('#fkxx_list').datagrid('getChecked');
	if (rows.length > 0) {
		if (false) {
			alert("同级别权限和自己,不可操作");
		} else {
			$.messager.confirm('提示框', "确定要取消预约吗？</br></br><span style='color: red;font-size: 18px;'>操作不可逆,请谨慎操作!</span>", function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						type : 'post',
						data : {
							ids : ids.join(",")
						},
						dataType : 'json',
						url : 'fkgl/zhuxiaoFkxx?time=' + new Date().getTime(),
						success : function(date) {
							if (date) {
								$.messager.show({
									title : '提示',
									timeout : 2000,
									msg : '取消成功',
									showType : 'slide'
								});
								$("#fkxx_list").datagrid('clearChecked');
								$("#fkxx_list").datagrid('load');
							} else {
								$.messager.show({
									title : '提示',
									timeout : 2000,
									msg : '注销失败',
									showType : 'slide'
								});
							}
						}
					});
				}
			});
		}
	} else {
		$.messager.show({
			title : '提示',
			timeout : 2000,
			msg : '没有选中数据',
			showType : 'slide'
		});
	}
}
/**
 * 修改功能
 */
function editFun() {
	var rows = $("#fkxx_list").datagrid("getChecked");
	if (rows.length == 1) {
		if (null!=rows[0].userpath) {
			var ddmc=rows[0].userpath.split(',');
			var dd='';
			for ( var i = 0; i < ddmc.length; i++) {
				if ('地点已失效'!=ddmc[i]) {
					dd+=ddmc[i]+',';
				}
			}
			dd=dd.substring(0, dd.length-1);
			rows[0].userpath=dd;
		}
		if (false) {
			alert("已取消预约,暂不可操作");
		} else {
			$("#fkxxDuiHuaKuang").dialog({
				title : "修改信息",
				buttons : [ {
					//id : "save",
					text : "确定",
					handler : function() {
						var id=rows[0].id;
						 $.ajax({
		                        url:'fkgl/updateFkxx?yhid='+id+'&time='+new Date().getTime(),
		                        data:$("#fkxx_form").serialize(),//序列化
		                        type:"POST",
		                        dataType:"json",
		                        success:function(msg){
		                            if(msg){
		                                alert("修改成功");
		                                $("#fkxx_list").datagrid("unselectAll");
		                                //刷新表格数据
		                                $("#fkxx_list").datagrid("reload");
		                                //关闭窗口
		                                $("#fkxxDuiHuaKuang").dialog("close");
		                            }else {
		                            	alert("修改失败");
									}
		                        }
		                    });
					}
				}, {
					text : "关闭",
					handler : function() {
						$("#fkxxDuiHuaKuang").dialog("close");
						$("#fkxx_form").form("reset");
					}
				} ]
			});
			//显示修改前数据
			$("#fkxx_form").form("load", rows[0]);
			$("#fkxxDuiHuaKuang").dialog('open');
		}
	} else {
		//没有选中
		alert("请正确选择要修改项  并且只能修改一项");
	}
}






//全选 全不选
window.onload = function() {
	var qx = document.getElementById('ddmcqx');
	var qbx = document.getElementById('ddmcqbx');
	var qxnr = document.getElementById('jgdd').options
	qx.onclick = function() {
		var shuzus = [];
		for (i = 0; i < qxnr.length; i++) {
			select = document.getElementById('jgdd').options[i].text
			shuzus.push(select);
		}
		$('#jgdd').combobox('setValue', shuzus);
		// alert(shuzu)
	};
	// 全不选
	qbx.onclick = function() {
		$('#jgdd').combobox('clear');
	}
	//-----------------------------------------------------------
	var qxtj = document.getElementById('ddmcqxtj');
	var qbxtj = document.getElementById('ddmcqbxtj');
	var qxnrtj = document.getElementById('jgddtj').options
	qxtj.onclick = function() {
		var shuzus = [];
		for (i = 0; i < qxnrtj.length; i++) {
			selecttj = document.getElementById('jgddtj').options[i].text
			shuzus.push(selecttj);
		}
		$('#jgddtj').combobox('setValue', shuzus);
		// alert(shuzu)
	};
	// 全不选
	qbxtj.onclick = function() {
		$('#jgddtj').combobox('clear');
	}
}
//时间调节


