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
	$("#user_list").datagrid({
		url : 'xtgl/findUser?time=' + new Date().getTime(),
		title : "用户列表",
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
		//    hidden:"false"
		}, {
			field : 'yhm',
			title : '用户名',
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
			field : 'yhmm',
			width : 200,
			title : '密码(加密后)',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		//,hidden:true
		}, {
			field : 'sex',
			title : '性别',
			width : 50,
			//align:'center',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'yhqx',
			width : 200,
			title : '用户权限',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			},
			hidden : true
		}, {
			field : 'yhid',
			width : 200,
			title : '用户id',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
			,hidden : true
		}, {
			field : 'ktxdd',
			width : 300,
			title : '可通行地点',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}else if ('null'==value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
			//,hidden : true
		}, {
			field : 'isAdmin',
			width : 100,
			title : '是否是管理员',
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
				$('#yhmm').textbox('resize','260');
				$('#yhqrmm').textbox('resize','260');
				$("#mmtr").show();   
				$("#qrmmtr").show();  
				var id = $("#isAdminQJ").val();
				if ("3" == id) {
					alert("没有权限操作");
					//}else if ("2"==id){

				} else {
					$('#yhm').numberbox('enable', true);
					$("#user_form").form("reset");
					$('#user_ktxdd').combobox("clear");
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
				$('#yhm').numberbox('disable', true);
				$('#yhmm').textbox('resize','0');
				$('#yhqrmm').textbox('resize','0');
				$("#mmtr").hide();   
				$("#qrmmtr").hide();   
				if ("3" == id) {
					alert("没有权限操作");
				} else {
					editFun();
				}
			}
		} ]
	});
});
/**
 * 查询
 */
function tjcx() {
	$("#user_list").datagrid('load', serializeObject($("#findBh")))

}

/**
 * 打开注册对话框
 */
function append() {
	$("#userDuiHuaKuang").dialog({
		title : "添加信息",
		buttons : [ {
			id : "save",
			text : "保存",
			handler : function() {
				addUser();
			}
		}, {
			text : "关闭",
			handler : function() {
				$("#userDuiHuaKuang").dialog("close");
				$("#user_form").form("reset");
			}
		} ]
	});
	$("#userDuiHuaKuang").dialog('open');
	$("#save").css({
		"text-algin" : "center"
	});
}
/**
 * 添加信息提交
 */
function addUser() {
	//validate该方法可以校验表单的输入是否合法
	var jiaoYan = $("#user_form").form('validate');
	if (jiaoYan) {
		//合法提交
		$.ajax({
			url : 'xtgl/addUser?time=' + new Date().getTime(),
			data : $("#user_form").serialize(),//序列化表单数据,方便提交
			type : "POST",//提交方式
			dataType : "text", //服务器响应回来数据类型
			success : function(msg) {//接收服务器响应的处理函数
				//msg表示接收到的服务器响应内容
				if ("YES" === msg) {
					alert("添加成功");
					//刷新表格数据
					$("#user_list").datagrid("reload");
					//关闭窗口
					$("#userDuiHuaKuang").dialog("close");
					//window.location.reload();
				} else if ("NO" === msg) {
					alert("请填写必选项,正确添加");
				} else if ("DDCZ" === msg) {
					alert("名称已存在在,请更改");
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
	var dlqx = $("#isAdminQJ").val();
	var ids = [];
	var rows = $('#user_list').datagrid('getChecked');
	if (rows.length > 0) {
		var djqx = rows[0].isAdmin;
		if ('超级管理员' == djqx) {
			djqx = '1';
		} else if ('高级管理员' == djqx) {
			djqx = '2';
		} else if ('普通管理员' == djqx) {
			djqx = '3';
		}
		//alert(djqx+"===="+dlqx);
		//if (djqx == dlqx) {
		if (false) {
			alert("同级别权限和自己,不可操作");
		} else {
			$.messager.confirm('提示框', "确定要删除数据吗？</br></br><span style='color: red;font-size: 18px;'>删除不可逆,请谨慎操作!</span>", function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].yhid);
					}
					//alert(ids[0]);
					$.ajax({
						type : 'post',
						data : {
							ids : ids.join(",")
						},
						dataType : 'json',
						url : 'xtgl/deleteUser?time=' + new Date().getTime(),
						success : function(date) {
							//alert(date)
							if (date) {
								$.messager.show({
									title : '提示',
									timeout : 2000,
									msg : '删除成功',
									showType : 'slide'
								});
								$("#user_list").datagrid('clearChecked');
								$("#user_list").datagrid('load');
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
	var dlqx = $("#isAdminQJ").val();
	var rows = $("#user_list").datagrid("getChecked");
	//如果有选中行则返回对象,执行if语句块
	if (rows.length == 1) {
		$.ajax({
            url:'xtgl/jmmd5?time='+new Date().getTime(),
            data:{'yhmm':encodeURI(encodeURIComponent(rows[0].yhmm))},//序列化
            type:"POST",
            dataType:"json",
            success:function(data){
                if(data){
                	rows[0].yhmm=data.msg
                }else {
                	alert("解密失败");
				}
            }
        });
		//alert(rows[0].ktxdd);
		if ('用户无可通行地点'==rows[0].ktxdd) {
			rows[0].ktxdd='';
		}else if ('用户可通行所有地点'==rows[0].ktxdd) {
			var qxnr = document.getElementById('user_ktxdd').options
			var xsnr='';
			for (i = 0; i < qxnr.length; i++) {
				select = document.getElementById('user_ktxdd').options[i].text
				xsnr+=select+",";
			}
			xsnr=xsnr.substring(0, xsnr.length-1);
			rows[0].ktxdd=xsnr;
		}
		if ('超级管理员' == rows[0].isAdmin) {
			rows[0].isAdmin = '1';
		} else if ('高级管理员' == rows[0].isAdmin) {
			rows[0].isAdmin = '2';
		} else if ('普通管理员' == rows[0].isAdmin) {
			rows[0].isAdmin = '3';
		}
		if ('男' == rows[0].sex) {
			rows[0].sex = '1';
		} else if ('女' == rows[0].sex) {
			rows[0].sex = '0';
		}
		//alert(rows[0].isAdmin+"===="+dlqx);
		//if (dlqx == rows[0].isAdmin) {
		if (false) {
			alert("同级别权限和自己,不可操作");
		} else {
			$("#userDuiHuaKuang").dialog({
				title : "修改用户资料",
				buttons : [ {
					id : "save",
					text : "确定",
					handler : function() {
						var id=rows[0].yhid;
						//alert(id);
						//alert(encodeURI(encodeURIComponent(id)))
						 $.ajax({
		                        url:'xtgl/updateUser?yhid='+encodeURI(encodeURIComponent(id))+'&time='+new Date().getTime(),
		                        data:$("#user_form").serialize(),//序列化
		                        type:"POST",
		                        dataType:"json",
		                        success:function(msg){
		                            if(msg){
		                                alert("修改成功");
		                                $('#user_form').form("clear");
		                                //刷新表格数据
		                                $("#user_list").datagrid("reload");
		                                //关闭窗口
		                                $("#userDuiHuaKuang").dialog("close");
		                            }else {
		                            	alert("修改失败");
									}
		                        }
		                    });
					}
				}, {
					text : "关闭",
					handler : function() {
						$("#userDuiHuaKuang").dialog("close");
						$("#user_form").form("reset");
					}
				} ]
			});
			//显示修改前数据
			$("#user_form").form("load", rows[0]);
			$("#userDuiHuaKuang").dialog('open');
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
	var qxnr = document.getElementById('user_ktxdd').options
	qx.onclick = function() {
		var shuzus = [];
		for (i = 0; i < qxnr.length; i++) {
			select = document.getElementById('user_ktxdd').options[i].text
			shuzus.push(select);
		}
		$('#user_ktxdd').combobox('setValue', shuzus);
		// alert(shuzu)
	};
	// 全不选
	qbx.onclick = function() {
		$('#user_ktxdd').combobox('clear');
	}
	//-----------------------------------------------------------

}
























