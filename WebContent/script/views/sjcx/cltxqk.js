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
	$("#cltxqk_list").datagrid({
		url : 'sjcx/findCltxqk?time=' + new Date().getTime(),
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
		pagination : true,
		pagePosition : 'bottom',
		remoteSort : false,//是否向服务器端请求排序
		pageSize : 20,
		pageList : [ 5, 10, 20, 30, 40, 50 ],
		checkOnSelect : true,//如果为true，当用户点击行的时候该复选框就会被选中或取消选中。
		selectOnCheck : true,//如果为true，单击复选框将永远选择行。
		singleSelect:true, //如果为true，则只允许选择一行。
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
			field : 'jgMc',
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
		//,hidden:true
		}, {
			field : 'hpzl',
			title : '号牌种类',
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
			field : 'cllx',
			width : 200,
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
			field : 'jgsj',
			width : 200,
			title : '经过时间',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
			//,hidden : true
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
			field : 'fx',
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
			field : 'rsfzh',
			width : 200,
			title : '驾驶人身份证号',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'txzt',
			width : 200,
			title : '通行状态',
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
			var d = $('<div id="xqym_cltxqk"/>').dialog({
				title : '详细信息查询',
				//width : 1100,
				//height : 600,
				width : '94%',
				height : '94%',
				closed : false,
				modal : true,
				href : 'sjcx/findCltxqkXx?id=' + rowData.id+'&hphm='+encodeURI(encodeURIComponent(rowData.hphm))+'&sfzh='+rowData.rsfzh,
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
						$("#xqym_cltxqk").dialog("close");
					}
				}]
			});
		},
		toolbar : [ {
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
	$("#cltxqk_list").datagrid("unselectAll");
	$("#cltxqk_list").datagrid('load', serializeObject($("#findBh")))

}

//
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
		// alert(shuzu)
	};
	// 全不选
	qbxwf.onclick = function() {
		$('#myselect').combobox('clear');
	}
	//-----------------------------------------------------------
	var ddqx = document.getElementById('ddqx');
	var ddqbx = document.getElementById('ddqbx');
	var selects = document.getElementById('xzdd').options
	ddqx.onclick = function() {
		var shuzu = [];
		for (i = 0; i < selects.length; i++) {
			select = document.getElementById('xzdd').options[i].text
			shuzu.push(select);
		}
		$('#xzdd').combobox('setValue', shuzu);
		// alert(shuzu)
	};
	// 全不选
	ddqbx.onclick = function() {
		$('#xzdd').combobox('clear');
		
	}
}

// 自动查询
var zdsx;
function zdcx() {
	zdsx = setInterval(a, 5000);
}
function a() {
	var sj = new Date();
	sj.setDate(sj.getDate() + 7);
	$('#jssjs').datetimebox('setValue', formatterDate(sj));
	$("#cltxqk_list").datagrid("unselectAll");
	$("#cltxqk_list").datagrid('load', serializeObject($("#findBh")))
}
// 取消自动查询
function qxzdcx() {
	clearInterval(zdsx);
}

