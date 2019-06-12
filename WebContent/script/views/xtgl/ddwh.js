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
	$("#ddwh_list").datagrid({
		url : 'xtgl/findDdwh?time=' + new Date().getTime(),
		title : "地点维护列表",
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
			//,hidden:true
		}, {
			field : 'address',
			width : 200,
			title : '地点名称',
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
					$('#yhm').numberbox('enable', true);
					$('#userbjck_form').form("clear");
					if ("1" != id) {
						//$('#tj1').attr("disabled");
						$("#tj1").attr("disabled", "true");
					}
					append();
				}
			}
		}, '-', {
			text : '删除',
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
function tjcx() {
	$("#ddwh_list").datagrid('load', serializeObject($("#findBh")))

}

/**
 * 打开注册对话框
 */
function append() {
	$("#ddwh_form").form("reset");
	$("#ddwhDuiHuaKuang").dialog({
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
				$("#ddwhDuiHuaKuang").dialog("close");
				$("#ddwh_form").form("reset");
			}
		} ]
	});
	$("#ddwhDuiHuaKuang").dialog('open');
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
	var jiaoYan = $("#ddwh_form").form('validate');
	if (jiaoYan) {
		//合法提交
		$.ajax({
			url : 'xtgl/addDdwh?time=' + new Date().getTime(),
			data : $("#ddwh_form").serialize(),//序列化表单数据,方便提交
			type : "POST",//提交方式
			dataType : "text", //服务器响应回来数据类型
			success : function(msg) {//接收服务器响应的处理函数
				//msg表示接收到的服务器响应内容
				if ("YES" === msg) {
					alert("添加成功");
					//刷新表格数据
					$("#ddwh_list").datagrid("reload");
					//关闭窗口
					$("#ddwhDuiHuaKuang").dialog("close");
					//window.location.reload();
				} else if ("NO" === msg) {
					alert("请填写必选项,认真添加");
				} else if ("DDCZ" === msg) {
					alert("此地点已添加,请务重复添加");
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
 * 删除功能
 */
function remove() {
	var ids = [];
	var rows = $('#ddwh_list').datagrid('getChecked');
	if (rows.length > 0) {
		if (false) {
			alert("同级别权限和自己,不可操作");
		} else {
			$.messager.confirm('提示框', "确定要删除数据吗？</br></br><span style='color: red;font-size: 18px;'>删除不可逆,请谨慎操作!</span>", function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					//alert(ids[0]);
					$.ajax({
						type : 'post',
						data : {
							ids : ids.join(",")
						},
						dataType : 'json',
						url : 'xtgl/deleteDdwh?time=' + new Date().getTime(),
						success : function(date) {
							//alert(date)
							if (date) {
								$.messager.show({
									title : '提示',
									timeout : 2000,
									msg : '删除成功',
									showType : 'slide'
								});
								$("#ddwh_list").datagrid('clearChecked');
								$("#ddwh_list").datagrid('load');
							} else {
								$.messager.show({
									title : '提示',
									timeout : 2000,
									msg : '删除失败',
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
	var rows = $("#ddwh_list").datagrid("getChecked");
	//如果有选中行则返回对象,执行if语句块
	if (rows.length == 1) {
		if ('出口' == rows[0].ckip) {
			rows[0].ckip = '1';
		} else if ('进口' == rows[0].ckip) {
			rows[0].ckip = '0';
		}
		if ('启用' == rows[0].xjsbbh) {
			rows[0].xjsbbh = '1';
		} else if ('未启用' == rows[0].xjsbbh) {
			rows[0].xjsbbh = '0';
		}
		if (null!=rows[0].ppms) {
			var ppms=rows[0].ppms;
			ppms = ppms.replace("车签", "1");
			ppms = ppms.replace("人签", "2");
			ppms = ppms.replace("车牌", "3");
			var pp= ppms.split(",");
			rows[0].ppms = pp;
		}
		if ('内部' == rows[0].xjfx) {
			rows[0].xjfx = '1';
		} else if ('外部' == rows[0].xjfx) {
			rows[0].xjfx = '2';
		}
		if (false) {
			alert("同级别权限和自己,不可操作");
		} else {
			$("#ddwhDuiHuaKuang").dialog({
				title : "修改信息",
				buttons : [ {
					//id : "save",
					text : "确定",
					handler : function() {
						var id=rows[0].id;
						 $.ajax({
		                        url:'xtgl/updateDdwh?yhid='+encodeURI(encodeURIComponent(id))+'&time='+new Date().getTime(),
		                        data:$("#ddwh_form").serialize(),//序列化
		                        type:"POST",
		                        dataType:"json",
		                        success:function(msg){
		                            if(msg){
		                                alert("修改成功");
		                                $("#ddwh_list").datagrid("unselectAll");
		                                //刷新表格数据
		                                $("#ddwh_list").datagrid("reload");
		                                //关闭窗口
		                                $("#ddwhDuiHuaKuang").dialog("close");
		                            }else {
		                            	alert("修改失败");
									}
		                        }
		                    });
					}
				}, {
					text : "关闭",
					handler : function() {
						$("#ddwhDuiHuaKuang").dialog("close");
					}
				} ]
			});
			//显示修改前数据
			$("#ddwh_form").form("load", rows[0]);
			$("#ddwhDuiHuaKuang").dialog('open');
		}
	} else {
		//没有选中
		alert("请正确选择要修改项  并且只能修改一项");
	}
}
