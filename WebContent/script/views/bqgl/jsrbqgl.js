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
	$("#jsrbqgl_list").datagrid({
		url : 'bqgl/findJsrbqgl?time=' + new Date().getTime(),
		title : "车辆通行情况列表",
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
		singleSelect : true,
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
			},
			hidden : true
		}, {
			field : 'bqid',
			width : 200,
			title : '标签id',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'syr',
			width : 200,
			title : '姓名',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'sex',
			width : 100,
			title : '性别',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'sfzh',
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
			field : 'dqsj',
			width : 250,
			title : '身份证到期时间',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}else {
					var dqsj=new Date(value)
					dqsj.setDate(dqsj.getDate() + 7);
					value = formatterDate(dqsj);
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'fqsj',
			width : 250,
			title : '发签日期',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'yxjzrq',
			width : 250,
			title : '有效截止期',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'zjcx',
			width : 200,
			title : '准驾车型',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'ssdw',
			width : 200,
			title : '所属单位',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'ktxdd',
			width : 200,
			title : '可通行地点',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'cphm',
			width : 200,
			title : '车牌号码',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'je',
			width : 100,
			title : '金额',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'ld',
			width : 150,
			title : '所属领导',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'zt',
			width : 120,
			title : '标签状态',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		//,hidden:true
		} ] ],
		onClickRow : function(rowIndex, rowData) {
			var d = $('<div id="xqym_jsrbq"/>').dialog({
				title : '详细信息查询',
				//width : 1000,
				//height : 588,
				width : '94%',
				height : '94%',
				closed : false,
				modal : true,
				href : 'bqgl/findJsrbqglXx?id=' + rowData.id+'&sfzh='+encodeURI(encodeURIComponent(rowData.sfzh)),
				// 防止div添加
				onClose : function() {
					$(this).dialog('destroy');
				},
				onLoad : function() {
					$("#findBh").form('load', rowData);
				},
				buttons : [ {
					id : "gb",
					text : "关闭",
					handler : function() {
						$("#xqym_jsrbq").dialog("close");
					}
				}]
			});
		},
		toolbar : [ {
			text : '添加 ',
			iconCls : 'icon-add',
			handler : function() {
				document.getElementById("jsrjzts").innerHTML = "*请点击上传图片";
				//$('#sfzh').textbox('enableValidation')//设置启用验证
				//$('#sfzh').textbox('textbox').attr('readonly',false);  //设置输入框为启用
				var id = $("#isAdminQJ").val();
				if ("3" == id) {
					alert("没有权限操作");
					//}else if ("2"==id){
				} else {
					$('#yhm').numberbox('enable', true);
					//$('#findBh').form("reset");
					if ("1" != id) {
						$("#tj1").attr("disabled", "true");
					}
					$("#jsrbq_form").form("reset");
					$('#jgdd_jsr').combobox("clear");
					$("#jsrjz").empty();
					append();
				}
			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-cross',
			handler : function() {
				var id = $("#isAdminQJ").val();
				if ("3" == id) {
					alert("没有权限操作");
				} else {
					remove();
				}
			}
		}, '-', {
			text : '注销',
			iconCls : 'icon-remove',
			handler : function() {
				var id = $("#isAdminQJ").val();
				if ("3" == id) {
					alert("没有权限操作");
				} else {
					logout();
				}
			}
		}, '-', {
			text : '修改',
			iconCls : 'icon-search',
			handler : function() {
				//$('#sfzh').textbox('disableValidation');//设置禁用验证
				//$('#sfzh').textbox('textbox').attr('readonly',true);  //设置输入框为禁用
				//$('#sfzh').textbox('readonly', true);//设置输入框为禁用
				//$('#sfzh').attr('style','background-color:gray');
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
		}, '-', {
			text : '<span style="color: #151aee">单击数据行查看详细信息</span>'
		} ]
	});
});
/**
 * 查询
 */
function searchFun() {
	$("#jsrbqgl_list").datagrid("unselectAll");
	$("#jsrbqgl_list").datagrid('load', serializeObject($("#findBh")))

}

/**
 * 打开注册对话框
 */
function append() {
	var dqsj = new Date();
	dqsj.setDate(dqsj.getDate() + 7);
	dqsj.setFullYear(dqsj.getFullYear() + 1);
	$('#yxjzrq').datetimebox('setValue', formatterDate(dqsj));
	$('#sfzdqsj').datetimebox('setValue', formatterDate(dqsj));
	dqsj.setDate(dqsj.getDate() - 7);
	dqsj.setFullYear(dqsj.getFullYear() - 2);
	$('#sfzqssj').datetimebox('setValue', formatterDate(dqsj));
	$("#jsrbqDuiHuaKuang").dialog({
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
				var scdz=$('#jsrjztjdz').textbox('getValue');
				if (null!=scdz) {
					$.ajax({
						//url : 'bqgl/deleteTp?tpdz=' + encodeURI(encodeURIComponent(scdz)) + '&time=' + new Date().getTime(),
						url : 'bqgl/deleteTp?tpdz=' + encodeURI(encodeURIComponent(scdz))+'&wysb='+encodeURI(encodeURIComponent('00'))+'&i=3' + '&time=' + new Date().getTime(),
						type : "POST",
						dataType : "text",
						async : false,
						success : function(msg) {
							//ajaxdz=msg;
						}
					});
				}
				$("#jsrbqDuiHuaKuang").dialog("close");
				$("#jsrjz").empty();
				$('#sctpdz_jsr').replaceWith($('#sctpdz_jsr').clone(true));
			}
		} ]
	});
	$("#jsrbqDuiHuaKuang").dialog('open');
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
	var jiaoYan = $("#jsrbq_form").form('validate');
	if (jiaoYan) {
		//合法提交
		$.ajax({
			url : 'bqgl/addJsrbqgl?time=' + new Date().getTime(),
			data : $("#jsrbq_form").serialize(),//序列化表单数据,方便提交
			type : "POST",//提交方式
			dataType : "text", //服务器响应回来数据类型
			success : function(msg) {//接收服务器响应的处理函数
				//msg表示接收到的服务器响应内容
				if ("YES" === msg) {
					alert("添加成功");
					//刷新表格数据
					$("#jsrbqgl_list").datagrid("reload");
					//关闭窗口
					$("#jsrbqDuiHuaKuang").dialog("close");
					//window.location.reload();
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
 * 删除
 */
function remove() {
	var ids = [];
	var rows = $('#jsrbqgl_list').datagrid('getChecked');
	if (rows.length > 0) {
		var zhuxiao=rows[0].zt;
		if (zhuxiao=='注销') {
			alert("该条数据已经注销，不可删除");
		}else {
			if (false) {
				alert("同级别权限和自己,不可操作");
			} else {
				$.messager.confirm('提示框', "确定要删除数据吗？</br></br><span style='color: red;font-size: 18px;'>删除不可逆,请谨慎操作!</span>", function(r) {
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
							url : 'bqgl/deleteJsrbqgl?time=' + new Date().getTime(),
							success : function(date) {
								if (date) {
									$.messager.show({
										title : '提示',
										timeout : 2000,
										msg : '删除成功',
										showType : 'slide'
									});
									$("#jsrbqgl_list").datagrid('clearChecked');
									$("#jsrbqgl_list").datagrid('load');
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
 * 注销功能
 */
function logout() {
	var ids = [];
	var rows = $('#jsrbqgl_list').datagrid('getChecked');
	if (rows.length > 0) {
		var zhuxiao=rows[0].zt;
		if (zhuxiao=='注销') {
			alert("该条数据已经注销，不可重复操作");
		}else {
			if (false) {
				alert("同级别权限和自己,不可操作");
			} else {
				$.messager.confirm('提示框', "确定要注销数据吗？</br></br><span style='color: red;font-size: 18px;'>注销不可逆,请谨慎操作!</span>", function(r) {
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
							url : 'bqgl/zhuxiaoJsrbqgl?time=' + new Date().getTime(),
							success : function(date) {
								if (date) {
									$.messager.show({
										title : '提示',
										timeout : 2000,
										msg : '注销成功',
										showType : 'slide'
									});
									$("#jsrbqgl_list").datagrid('clearChecked');
									$("#jsrbqgl_list").datagrid('load');
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
	$('#jgdd_jsr').combobox("clear");
	var rows = $("#jsrbqgl_list").datagrid("getChecked");
	//如果有选中行则返回对象,执行if语句块
	if (rows.length == 1) {
		var zhuxiao=rows[0].zt;
		if (zhuxiao=='注销') {
			alert("不能修改注销数据");
		}else {
			var ajaxdz='';
			$.ajax({
				url : 'bqgl/tpfz?tpdz=' + encodeURI(encodeURIComponent(rows[0].sctpdz)) + '&time=' + new Date().getTime(),
				type : "POST",
				dataType : "text",
				async : false,
				success : function(msg) {
					ajaxdz=msg;
				}
			});
			$("#jsrjz").empty();
			//alert(rows[0].sctpdz);
			$("#jsrjz").append("<img src='upload/"+ajaxdz+"' width='300px' height='300px'/>");
			if ('男' == rows[0].sex) {
				rows[0].sex = '1';
			} else if ('女' == rows[0].sex) {
				rows[0].sex = '0';
			}
			if (null!=rows[0].ktxdd) {
				var ddmc=rows[0].ktxdd.split(',');
				var dd='';
				for ( var i = 0; i < ddmc.length; i++) {
					if ('地点已失效'!=ddmc[i]) {
						dd+=ddmc[i]+',';
					}
				}
				dd=dd.substring(0, dd.length-1);
				rows[0].ktxdd=dd;
			}
			if (null!=rows[0].dqsj) {
				var dqsj=new Date(rows[0].dqsj)
				dqsj.setDate(dqsj.getDate() + 7);
				rows[0].dqsj = formatterDate(dqsj);
			}
			if (null!=rows[0].fzrq) {
				var fqsj=new Date(rows[0].fzrq)
				fqsj.setDate(fqsj.getDate() + 7);
				rows[0].fzrq = formatterDate(fqsj);
			}
			if (false) {
				alert("同级别权限和自己,不可操作");
			} else {
				$("#jsrbqDuiHuaKuang").dialog({
					title : "修改信息",
					buttons : [ {
						//id : "save",
						text : "确定",
						handler : function() {
							var id = rows[0].id;
							var sfzh = rows[0].sfzh;
							$.ajax({
								url : 'bqgl/updateJsrbqgl?id=' + id + '&ytpdz='+encodeURI(encodeURIComponent(rows[0].sctpdz))+ '&time=' + new Date().getTime(),
								data : $("#jsrbq_form").serialize(),//序列化
								type : "POST",
								dataType : "json",
								success : function(msg) {
									if (msg) {
										alert("修改成功");
										$("#jsrbqgl_list").datagrid("unselectAll");
										//刷新表格数据
										$("#jsrbqgl_list").datagrid("reload");
										//关闭窗口
										$("#jsrbqDuiHuaKuang").dialog("close");
									} else {
										alert("修改失败");
									}
								}
							});
						}
					}, {
						text : "关闭",
						handler : function() {
							var scdz=$('#jsrjztjdz').textbox('getValue');
							var sfzh = rows[0].sfzh;
							if (null!=scdz) {
								$.ajax({
									url : 'bqgl/deleteTp?tpdz=' + encodeURI(encodeURIComponent(scdz))+'&wysb='+encodeURI(encodeURIComponent(sfzh))+'&i=1' + '&time=' + new Date().getTime(),
									type : "POST",
									dataType : "text",
									async : false,
									success : function(msg) {
										//ajaxdz=msg;
									}
								});
							}
							$("#jsrjz").empty();
							$("#jsrbqDuiHuaKuang").dialog("close");
						}
					} ]
				});
				//显示修改前数据
				$("#jsrbq_form").form("load", rows[0]);
				$("#jsrbqDuiHuaKuang").dialog('open');
			}
		}
	} else {
		//没有选中
		alert("请正确选择要修改项  并且只能修改一项");
	}
}

//照片提交
function sctp_jsrjz() {
	$('#jsrjz_form').form('submit', {
		url : 'bqgl/addJsrjz?time=' + new Date().getTime(),
		dataType : "text", //服务器响应回来数据类型
		//async : false,
		success : function(msg) {
			msg=decodeURIComponent(msg);
			msg=decodeURIComponent(msg);
			//alert(msg);
			var data=msg.split(',')
			var leng=data.length;
			//alert(data[1]);
			$('#sctpdz_jsr').replaceWith($('#sctpdz_jsr').clone(true));
			$("#jsrjztjdz").textbox("setValue", data[0]);
			document.getElementById("jsrjzts").innerHTML = "";
			$("#jsrjz").empty();
			$("#jsrjz").append("<img src='upload/" + data[leng-1] + "' width='300px' height='300px'/>");
		}
	});
}

//全选 全不选
window.onload = function() {
	//-----------------------------------------------------------
	var qx = document.getElementById('ddmcqx');
	var qbx = document.getElementById('ddmcqbx');
	var qxnr = document.getElementById('jgdd_jsr').options
	qx.onclick = function() {
		var shuzus = [];
		for (i = 0; i < qxnr.length; i++) {
			select = document.getElementById('jgdd_jsr').options[i].text
			shuzus.push(select);
		}
		$('#jgdd_jsr').combobox('setValue', shuzus);
	};
	// 全不选
	qbx.onclick = function() {
		$('#jgdd_jsr').combobox('clear');
	}
	//-----------------------------------------------------------
	var qxtj = document.getElementById('ddmcqxtj');
	var qbxtj = document.getElementById('ddmcqbxtj');
	var qxnrtj = document.getElementById('jgdd_jsrtj').options
	qxtj.onclick = function() {
		var shuzustj = [];
		for (i = 0; i < qxnrtj.length; i++) {
			selecttj = document.getElementById('jgdd_jsrtj').options[i].text
			shuzustj.push(selecttj);
		}
		$('#jgdd_jsrtj').combobox('setValue', shuzustj);
		// alert(shuzu)
	};
	// 全不选
	qbxtj.onclick = function() {
		$('#jgdd_jsrtj').combobox('clear');
	}
}
//单击复选框判断是否选中
$("#jsrfqtjxk").click(function() {
    if($('#jsrfqtjxk').is(':checked')) {
    	/*var dqsj = new Date();
    	dqsj.setDate(dqsj.getDate() + 7);
    	$('#dd').datetimebox('setValue', formatterDate(new Date()));
    	$('#jssjs').datetimebox('setValue', formatterDate(dqsj));*/
    	$("#dd").datetimebox({ disabled: false });
    	$('#dd').datetimebox('setValue', formatterDate(new Date()));
    	$("#jssjs").datetimebox({ disabled: false });
    }else {
    	/*$('#dd').datetimebox('setValue', null);
    	$('#jssjs').datetimebox('setValue', null);*/
    	$("#dd").datetimebox({ disabled: true });
    	$('#dd').datetimebox('setValue', formatterDate(new Date()));
    	$("#jssjs").datetimebox({ disabled: true });
	}
});
/*$.fn.datebox.defaults.formatter = function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+m+'-'+d;
}*/

