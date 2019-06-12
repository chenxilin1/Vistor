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
	$("#ppms_list").datagrid({
		url : 'xtgl/findPpms?time=' + new Date().getTime(),
		title : "匹配模式列表",
		fit : true,
		fitColumns : true,
		pagination : true,
		border : false,
		idField : 'id',
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
			field : 'ckip',
			width : 200,
			title : '进出口',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'jgmc',
			width : 200,
			title : '地点名称',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'jkip',
			width : 200,
			title : 'IP地址',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		//,hidden:true
		}, {
			field : 'xjsbbh',
			title : '相机状态',
			width : 130,
			//align:'center',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'xjfx',
			width : 200,
			title : '内外部',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
			,hidden : true
		}, {
			field : 'ppms',
			width : 240,
			title : '匹配模式',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'ppmsgx',
			width : 100,
			title : '模式关系',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
			,hidden : true
		}, {
			field : 'dk',
			width : 200,
			title : '端口',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'dzip',
			width : 200,
			title : '道闸IP',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'dkqip',
			width : 200,
			title : '读卡器IP',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'rfidms',
			width : 200,
			title : 'RFID优先',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'ckclfs',
			width : 200,
			title : '出口处理方式',
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
	$("#ppms_list").datagrid('load', serializeObject($("#findBh")))

}

/**
 * 打开注册对话框
 */
function append() {
	$("#ppms_form").form("reset");
	$("#ppmsDuiHuaKuang").dialog({
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
				$("#ppmsDuiHuaKuang").dialog("close");
				$("#ppms_form").form("reset");
			}
		} ]
	});
	$("#ppmsDuiHuaKuang").dialog('open');
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
	var jiaoYan = $("#ppms_form").form('validate');
	if (jiaoYan) {
		//合法提交
		$.ajax({
			url : 'xtgl/addPpms?time=' + new Date().getTime(),
			data : $("#ppms_form").serialize(),//序列化表单数据,方便提交
			type : "POST",//提交方式
			dataType : "text", //服务器响应回来数据类型
			success : function(msg) {//接收服务器响应的处理函数
				//msg表示接收到的服务器响应内容
				if ("YES" === msg) {
					alert("添加成功");
					//刷新表格数据
					$("#ppms_list").datagrid("reload");
					//关闭窗口
					$("#ppmsDuiHuaKuang").dialog("close");
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
	var rows = $('#ppms_list').datagrid('getChecked');
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
						url : 'xtgl/deletePpms?time=' + new Date().getTime(),
						success : function(date) {
							//alert(date)
							if (date) {
								$.messager.show({
									title : '提示',
									timeout : 2000,
									msg : '删除成功',
									showType : 'slide'
								});
								$("#ppms_list").datagrid('clearChecked');
								$("#ppms_list").datagrid('load');
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
	var rows = $("#ppms_list").datagrid("getChecked");
	$("#ppms_form").form("reset");
	//如果有选中行则返回对象,执行if语句块
	if (rows.length == 1) {
		if ('出口' == rows[0].ckip) {
			rows[0].ckip = '1';
			$('#ckclfs').combobox('enable')
			$("#tr_show").show();   
		} else if ('进口' == rows[0].ckip) {
			rows[0].ckip = '0';
			$('#ckclfs').combobox('disable')
			$("#tr_show").hide();   
		}
		if ('启用' == rows[0].xjsbbh) {
			rows[0].xjsbbh = '1';
		} else if ('未启用' == rows[0].xjsbbh) {
			rows[0].xjsbbh = '0';
		}
		if ('是' == rows[0].rfidms) {
			rows[0].rfidms = '1';
		} else if ('否' == rows[0].rfidms) {
			rows[0].rfidms = '0';
		}
		if (null!=rows[0].xjsbbh) {
			if ("处理数据发命令"==rows[0].xjsbbh) {
				rows[0].xjsbbh='1';
			}else if ("处理数据不发命令"==rows[0].xjsbbh) {
				rows[0].xjsbbh='2';
			}else if ("走匹配模式"==rows[0].xjsbbh) {
				rows[0].xjsbbh='3';
			}
		}
		//alert(rows[0].ppms);
		/*if (rows[0].ppms.indexOf("-") > -1) {
			//rows[0].ppms=rows[0].ppms.replace('-', ',');
			var aa=rows[0].ppms.split("-");
			rows[0].ppms="";
			for(var i=0;i<aa.length;i++){
				rows[0].ppms+=aa[i]+',';
			}
			//alert(rows[0].ppms);
			rows[0].ppms=rows[0].ppms.substring(0,rows[0].ppms.length-1);
		}*/
		/*if (null!=rows[0].ppms) {
			var ppms=rows[0].ppms;
			ppms = ppms.replace("车签", "1");
			ppms = ppms.replace("人签", "2");
			ppms = ppms.replace("车牌", "3");
			var pp= ppms.split(",");
			rows[0].ppms = pp;
		}
		if (null!=rows[0].ppmsgx) {
			if ('并且'==rows[0].ppmsgx) {
				rows[0].ppmsgx='0';
			}else if ('或者'==rows[0].ppmsgx) {
				rows[0].ppmsgx='1';
				//rows[0].ppms=rows[0].ppms.replace(",", ",");
			}
		}*/
		if ('内部' == rows[0].xjfx) {
			rows[0].xjfx = '1';
		} else if ('外部' == rows[0].xjfx) {
			rows[0].xjfx = '2';
		}
		if (false) {
			alert("同级别权限和自己,不可操作");
		} else {
			$("#ppmsDuiHuaKuang").dialog({
				title : "修改信息",
				buttons : [ {
					//id : "save",
					text : "确定",
					handler : function() {
						var id=rows[0].id;
						 $.ajax({
		                        url:'xtgl/updatePpms?yhid='+encodeURI(encodeURIComponent(id))+'&time='+new Date().getTime(),
		                        data:$("#ppms_form").serialize(),//序列化
		                        type:"POST",
		                        dataType:"json",
		                        success:function(msg){
		                            if(msg){
		                                alert("修改成功");
		                                //$("#ppms_list").datagrid("unselectAll");
		                                //刷新表格数据
		                                $("#ppms_list").datagrid("reload");
		                                //关闭窗口
		                                $("#ppmsDuiHuaKuang").dialog("close");
		                            }else {
		                            	alert("修改失败");
									}
		                        }
		                    });
					}
				}, {
					text : "关闭",
					handler : function() {
						$("#ppmsDuiHuaKuang").dialog("close");
						$("#ppms_list").datagrid("reload");
					}
				} ]
			});
			//显示修改前数据
			$("#ppms_form").form("load", rows[0]);
			$("#ppmsDuiHuaKuang").dialog('open');
		}
	} else {
		//没有选中
		alert("请正确选择要修改项  并且只能修改一项");
	}
}
//单击进口
$("#tj10").click(function() {
	$('#ckclfs').combobox('clear')
	$("#tr_show").hide();   
})
//单击出口
$("#tj2").click(function() {
	$('#ckclfs').combobox('enable')
	$('#ckclfs').combobox('reset')
	$("#tr_show").show();
})

//全选 全不选
window.onload = function() {
	var qx = document.getElementById('ddmcqx');
	var qbx = document.getElementById('ddmcqbx');
	var qxnr = document.getElementById('ppmsdd').options
	qx.onclick = function() {
		var shuzus = [];
		for (i = 0; i < qxnr.length; i++) {
			select = document.getElementById('ppmsdd').options[i].text
			shuzus.push(select);
		}
		$('#ppmsdd').combobox('setValue', shuzus);
		// alert(shuzu)
	};
	// 全不选
	qbx.onclick = function() {
		$('#ppmsdd').combobox('clear');
	}
	//-----------------------------------------------------------

}



















