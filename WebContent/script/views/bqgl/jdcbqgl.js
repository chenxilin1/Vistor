var LODOP;
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
	$("#jdcbqgl_list").datagrid({
		url : 'bqgl/findJdcbqgl?time=' + new Date().getTime(),
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
			title : '所有人',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'lxdh',
			width : 200,
			title : '联系电话',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'hphm',
			width : 200,
			title : '号牌号码',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		//,hidden : true
		}, {
			field : 'hp',
			width : 50,
			title : '号牌',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
			,hidden : true
		}, {
			field : 'hm',
			width : 50,
			title : '号码',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
			,hidden : true
		}, {
			field : 'zhi',
			width : 50,
			title : '值',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
			,hidden:true
		}, {
			field : 'hpzl',
			title : '号牌种类',
			width : 180,
			//align:'center',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'cllx',
			width : 180,
			title : '车辆类型',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
			//,hidden : true
		}, {
			field : 'csys',
			width : 180,
			title : '车身颜色',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
			//,hidden : true
		}, {
			field : 'fdjh',
			width : 200,
			title : '发动机号',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'clsbdh',
			width : 200,
			title : '车辆识别代码',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'clpp',
			width : 200,
			title : '车辆品牌',
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
			field : 'ssks',
			width : 200,
			title : '所属科室',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'fqrq',
			width : 200,
			title : '发签日期',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'je',
			width : 200,
			title : '金额',
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
			field : 'bqzt',
			width : 200,
			title : '标签状态',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'ld',
			width : 200,
			title : '所属领导',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'sctpdz',
			width : 200,
			title : '图片名称',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		,hidden:true
		} ] ],
		onClickRow : function(rowIndex, rowData) {
			var d = $('<div id="xqym_jdcbq" />').dialog({
				title : '详细信息查询',
				//width : 1040,
				//height : 588,
				width : '94%',
				height : '94%',
				closed : false,
				resizable:true,
				modal : true,
				fitColumns:false,
				href : 'bqgl/findJdcbqglXx?id=' + rowData.id+'&hphm='+encodeURI(encodeURIComponent(rowData.hphm)),
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
						$("#xqym_jdcbq").dialog("close");
					}
				}]
			});
		},
		toolbar : [ {
			text : '添加 ',
			iconCls : 'icon-add',
			handler : function() {
				document.getElementById("jdcjzts").innerHTML = "*请点击上传图片";
				var id = $("#isAdminQJ").val();
				if ("3" == id) {
					alert("没有权限操作");
					//}else if ("2"==id){
				} else {
					$("#jdcjz").empty();
					//$('#zhi').textbox('enableValidation')//设置启用验证
					//$('#hp').combo('readonly', false);//设置输入框为启用
					//$('#hm').combo('readonly', false);  //设置输入框为启用
					//$('#zhi').textbox('textbox').attr('readonly',false);  //设置输入框为启用
					//$('#findBh').form("clear");
					if ("1" != id) {
						$("#tj1").attr("disabled", "true");
					}
					$('#zhi').textbox('resize','112px');
					$("#jdcbq_form").form("reset");
					$('#jgdd_jdc').combobox("clear");
					$('#bqgl_jdcbq_dhk_hp').show();
					$('#bqgl_jdcbq_dhk_hm').show();
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
				//$('#zhi').textbox('disableValidation');//设置禁用验证
				//$('#hp').combo('readonly', true);//设置输入框为禁用
				//$('#hm').combo('readonly', true);  //设置输入框为禁用
				//$('#zhi').textbox('textbox').attr('readonly',true);  //设置输入框为禁用
				$("#jdcbq_form").form("reset");
				$('#bqgl_jdcbq_dhk_hp').hide();
				$('#bqgl_jdcbq_dhk_hm').hide();
				$('#zhi').textbox('resize','260px');
				//$('#hm').combobox('resize','0');
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
		/*}, '-',{
			text : '本页导出到Excel',
			iconCls : 'icon-microsoft_excel',
			handler : function() {
				outExcel();
			}
		}, '-',{
			text : '本页打印',
			iconCls : 'icon-print',
			handler : function() {
				printdata();
			}*/
		}, '-' ]
	});
});
/**
 * 查询
 */
function searchFun() {
	$("#jdcbqgl_list").datagrid("unselectAll");
	$("#jdcbqgl_list").datagrid('load', serializeObject($("#findBh")))

}
/**
 * 导出数据到Excel
 */
function outExcel() {
	/*$.ajax({
		url : 'bqgl/outExcel?time=' + new Date().getTime(),
		type : "POST",
		data : $("#findBh").serialize(),//序列化表单数据,方便提交
		dataType : "json",
		async : false,
		success : function(msg) {
			//ajaxdz=msg;
		}
	});*/
	$('#findBh').form('submit', {
		url : 'bqgl/outExcel?time='+new Date().getTime(),
		dataType:"text", 
		success : function(data) {
			
		}
	});
}

/**
 * 打开注册对话框
 */
function append() {
	var dqsj = new Date();
	dqsj.setDate(dqsj.getDate() + 14);
	$('#yxjzrq_jdc').datetimebox('setValue', formatterDate(dqsj));
	$("#jdcbqDuiHuaKuang").dialog({
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
				var scdz=$('#jdcjztjdz').textbox('getValue');
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
				$("#jdcbqDuiHuaKuang").dialog("close");
				$("#jdcjz").empty();
				$('#sctpdz').replaceWith($('#sctpdz').clone(true));
				//$('#jdcjzts').outerHTML=$('#jdcjzts').outerHTML;
			}
		} ]
	});
	$("#jdcbqDuiHuaKuang").dialog('open');
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
	var jiaoYan = $("#jdcbq_form").form('validate');
	if (jiaoYan) {
		//合法提交
		$.ajax({
			url : 'bqgl/addJdcbqgl?time=' + new Date().getTime(),
			data : $("#jdcbq_form").serialize(),//序列化表单数据,方便提交
			type : "POST",//提交方式
			dataType : "text", //服务器响应回来数据类型
			success : function(msg) {//接收服务器响应的处理函数
				//msg表示接收到的服务器响应内容
				if ("YES" === msg) {
					alert("添加成功");
					//刷新表格数据
					$("#jdcbqgl_list").datagrid("reload");
					//关闭窗口
					$("#jdcbqDuiHuaKuang").dialog("close");
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
	var rows = $('#jdcbqgl_list').datagrid('getChecked');
	if (rows.length > 0) {
		var zhuxiao=rows[0].bqzt;
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
							url : 'bqgl/deleteJdcbqgl?time=' + new Date().getTime(),
							success : function(date) {
								if (date) {
									$.messager.show({
										title : '提示',
										timeout : 2000,
										msg : '删除成功',
										showType : 'slide'
									});
									$("#jdcbqgl_list").datagrid('clearChecked');
									$("#jdcbqgl_list").datagrid('load');
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
	var ids = [];//id
	var hphm = [];//hphm
	var rows = $('#jdcbqgl_list').datagrid('getChecked');
	if (rows.length > 0) {
		var zhuxiao=rows[0].bqzt;
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
							hphm.push(rows[i].hphm);
						}
						$.ajax({
							type : 'post',
							data : {
								ids : ids.join(",")
								//hphm : hphm.join(",")
							},
							dataType : 'json',
							url : 'bqgl/zhuxiaoJdcbqgl?time=' + new Date().getTime(),
							success : function(date) {
								if (date) {
									$.messager.show({
										title : '提示',
										timeout : 2000,
										msg : '注销成功',
										showType : 'slide'
									});
									$("#jdcbqgl_list").datagrid('clearChecked');
									$("#jdcbqgl_list").datagrid('load');
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
	$('#jgdd_jdc').combobox("clear");
	var rows = $("#jdcbqgl_list").datagrid("getChecked");
	//如果有选中行则返回对象,执行if语句块
	if (rows.length == 1) {
		var zhuxiao=rows[0].bqzt;
		//alert(zhuxiao);
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
			$("#jdcjz").empty();
			$("#jdcjz").append("<img src='upload/"+ajaxdz+"' width='300px' height='300px'/>");
	        var obj=new Image();
	        obj.src='upload/'+rows[0].sctpdz;
	        if (obj.width!=1277) {
	        	//alert("失败");
	        	$("#jdcjztjdz").textbox("setValue",'');
			}
	        rows[0].zhi=rows[0].hphm;
	        //alert(rows[0].zhi);
			//alert(rows[0].ktxdd);
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
			if (false) {
				alert("同级别权限和自己,不可操作");
			} else {
				$("#jdcbqDuiHuaKuang").dialog({
					title : "修改信息",
					buttons : [ {
						//id : "save",
						text : "确定",
						handler : function() {
							var xhphm=$("#zhi").textbox("getValue");
							var yhphm=rows[0].hphm;
							 $.ajax({
			                        url:'bqgl/updateJdcbqgl?yhphm='+encodeURI(encodeURIComponent(yhphm))+'&xhphm='+encodeURI(encodeURIComponent(xhphm))+ '&ytpdz='+encodeURI(encodeURIComponent(rows[0].sctpdz))+'&time='+new Date().getTime(),
			                        data:$("#jdcbq_form").serialize(),//序列化
			                        type:"POST",
			                        dataType:"json",
			                        success:function(msg){
			                            if(msg){
			                                alert("修改成功");
			                                $("#jdcbqgl_list").datagrid("unselectAll");
			                                //刷新表格数据
			                                $("#jdcbqgl_list").datagrid("reload");
			                                //关闭窗口
			                                $("#jdcbqDuiHuaKuang").dialog("close");
			                            }else {
			                            	alert("修改失败");
										}
			                        }
			                    });
						}
					}, {
						text : "关闭",
						handler : function() {
							//关闭修改 删除图片无用图片
							var scdz=$('#jdcjztjdz').textbox('getValue');
							var hphm=rows[0].hphm;
							if (null!=scdz) {
								$.ajax({
									url : 'bqgl/deleteTp?tpdz=' + encodeURI(encodeURIComponent(scdz))+'&wysb='+encodeURI(encodeURIComponent(hphm))+'&i=0' + '&time=' + new Date().getTime(),
									type : "POST",
									dataType : "text",
									async : false,
									success : function(msg) {
										//ajaxdz=msg;
									}
								});
							}
							$("#jdcbqDuiHuaKuang").dialog("close");
							$("#jdcjz").empty();
						}
					} ]
				});
				//显示修改前数据
				$("#jdcbq_form").form("load", rows[0]);
				$("#jdcbqDuiHuaKuang").dialog('open');
			}
		}
	} else {
		//没有选中
		alert("请正确选择要修改项  并且只能修改一项");
	}
}

//照片提交
function sctp_jdcjz() {
	$('#jdcjz_form').form('submit', {
		url : 'bqgl/addJdcjz?time='+new Date().getTime(),
		dataType:"text",    //服务器响应回来数据类型
		success : function(msg) {
			msg=decodeURIComponent(msg);
			msg=decodeURIComponent(msg);
			var data=msg.split(',')
			var leng=data.length;
			document.getElementById("jdcjzts").innerHTML = "";
			$('#sctpdz').replaceWith($('#sctpdz').clone(true));
			$("#jdcjztjdz").textbox("setValue",data[0]);
			$("#jdcjz").empty();
			$("#jdcjz").append("<img src='upload/"+data[leng-1]+"' width='300px' height='300px'/>");
		}
	});
}




//全选 全不选
window.onload = function() {
	var Allwf = document.getElementById('dwAllwf');
	var qbxwf = document.getElementById('qbxwf');
	var selectss = document.getElementById('myselect').options
	Allwf.onclick = function() {
		var shuzus = [];
		for (i = 0; i < selectss.length; i++) {
			select1 = document.getElementById('myselect').options[i].text
			shuzus.push(select1);
		}
		$('#myselect').combobox('setValue', shuzus);
	};
	// 全不选
	qbxwf.onclick = function() {
		$('#myselect').combobox('setValue', "");
	}
	//-----------------------------------------------------------
	var qx = document.getElementById('ddmcqx');
	var qbx = document.getElementById('ddmcqbx');
	var qxnr = document.getElementById('jgdd_jdc').options
	qx.onclick = function() {
		var shuzus = [];
		for (i = 0; i < qxnr.length; i++) {
			select = document.getElementById('jgdd_jdc').options[i].text
			shuzus.push(select);
		}
		$('#jgdd_jdc').combobox('setValue', shuzus);
		// alert(shuzu)
	};
	// 全不选
	qbx.onclick = function() {
		$('#jgdd_jdc').combobox('clear');
	}
	//-----------------------------------------------------------
	var qxtj = document.getElementById('ddmcqxtj');
	var qbxtj = document.getElementById('ddmcqbxtj');
	var qxnrtj = document.getElementById('jgdd_jdctj').options
	qxtj.onclick = function() {
		var shuzustj = [];
		for (i = 0; i < qxnrtj.length; i++) {
			selecttj = document.getElementById('jgdd_jdctj').options[i].text
			shuzustj.push(selecttj);
		}
		$('#jgdd_jdctj').combobox('setValue', shuzustj);
		// alert(shuzu)
	};
	// 全不选
	qbxtj.onclick = function() {
		$('#jgdd_jdctj').combobox('clear');
	}
	//
	
}
//单击复选框判断是否选中
$("#jdcfqtjxk").click(function() {
    if($('#jdcfqtjxk').is(':checked')) {
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

/**
 * 打印查询数据
 */
function printdata() {
	//window.open('outLodop','','height=500,width=911,left=300,top=150,scrollbars=yes,status =yes,location=no')
	/*$('#findBh').form('submit', {
		url : 'bqgl/outLodop?time='+new Date().getTime(),
		dataType:"text", 
		success : function(data) {
			
		}
	});*/
	//横向显示
	CreatePrintPage();
		
	LODOP.SET_PRINT_PAGESIZE(2,0,0,"");
	LODOP.SET_PREVIEW_WINDOW(0,0,0,0,0,"");	
	LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",1);//横向时的正向显示
	LODOP.PREVIEW();
	
	
}
function CreatePrintPage() {     
	//var Fields = $("#jdcbqgl_list").datagrid("getColumnTitles");
	LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));         
	var rows = $("#jdcbqgl_list").datagrid("getRows");
	var table_head="标签id,所有人,联系电话,号牌号码,号牌种类,车辆类型,所属单位,发签日期,可通行地点,标签状态,领导".split(',');
	var table_thead="";
	table_thead="<thead><tr>";
	for ( var a = 0; a < table_head.length; a++) {
		if (a==0) {
			table_thead+="<th style='width:130px;height:30px;'>"+table_head[a]+"</th>";
		}else if (a==2 || a==6 || a==7) {
			table_thead+="<th style='width:90px;height:30px;'>"+table_head[a]+"</th>";
		}else if (a==1 || a==8) {
			table_thead+="<th style='width:160px;height:30px;'>"+table_head[a]+"</th>";
		}else {
			table_thead+="<th style='width:50px;height:30px;'>"+table_head[a]+"</th>";
		}
	}
	table_thead+="<thead><tr>";
	LODOP.ADD_PRINT_TABLE(5,8,1500,25,"" +
			"<table border='1' cellpadding='0' style='font-size: 10;'>" +table_thead+
			"</table>");
	for ( var i = 0; i <= rows.length-1; i++) {
		var table_head_stuff=[rows[i].bqid!=null?rows[i].bqid:"",rows[i].syr!=null?rows[i].syr:"",rows[i].lxdh!=null?rows[i].lxdh:"",rows[i].hphm!=null?rows[i].hphm:"",rows[i].hpzl!=null?rows[i].hpzl:"",rows[i].cllx!=null?rows[i].cllx:"",rows[i].ssdw!=null?rows[i].ssdw:"",rows[i].fqrq!=null?rows[i].fqrq:"",rows[i].ktxdd!=null?rows[i].ktxdd:"",rows[i].bqzt!=null?rows[i].bqzt:"",rows[i].ld!=null?rows[i].ld:""];
		var table_tbody="";
		table_tbody="<thead><tr>";
		for ( var a = 0; a < table_head_stuff.length; a++) {
			if (a==0) {
				table_tbody+="<td style='width:130px;height:40px;'>"+table_head_stuff[a]+"</td>";
			}else if (a==2 || a==6 || a==7) {
				table_tbody+="<td style='width:90px;height:40px;'>"+table_head_stuff[a]+"</td>";
			}else if (a==1 || a==8) {
				table_tbody+="<td style='width:160px;height:40px;'>"+table_head_stuff[a]+"</td>";
			}else {
				table_tbody+="<td style='width:50px;height:40px;'>"+table_head_stuff[a]+"</td>";
			}
		}
		table_tbody+="<thead><tr>";
		
		LODOP.ADD_PRINT_TABLE(40*(i+1),8,1500,25,"" +
				"<table border='1'  cellpadding='0' style='font-size: 10;'>" +table_tbody+
				"</table>");
		
		
	}
}; 

















//待写 不选功能
//$("input[type='checkbox']").is(':checked')

