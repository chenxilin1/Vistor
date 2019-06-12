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
	$("#rzgl_list").datagrid({
		url : 'xtgl/findRzgl?time=' + new Date().getTime(),
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
			field : 'rzid',
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
			field : 'yhm',
			width : 200,
			title : '操作用户',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'cznr',
			width : 200,
			title : '操作内容',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		}, {
			field : 'czip',
			width : 200,
			title : '操作IP',
			sortable : true,
			formatter : function(value) {
				if (null == value) {
					value = '';
				}
				return "<span title='" + value + "'>" + value + "</span>";
			}
		//,hidden:true
		}, {
			field : 'czsj',
			title : '操作时间',
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
			field : 'czbz',
			width : 200,
			title : '操作备注',
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
		} ],
		rowStyler : function(index, row) {
			if (index==0) {
				//return 'color:red;font-weight:bold;';
			}
		}
	});
});
/**
 * 查询
 */
function tjcx() {
	$("#rzgl_list").datagrid('load', serializeObject($("#findBh")))

}

$(function(){
	formatterDate = function (date) {
		var times=date.getTime();
		var moths=1000*60*60*24*7
		var dates=new Date(times-moths);
		var day = dates.getDate() > 9 ? dates.getDate() : "0" + dates.getDate();
		var month = (dates.getMonth() + 1) > 9 ? (dates.getMonth() + 1) : "0" + (dates.getMonth() + 1);
		var hor = dates.getHours();
		var min = dates.getMinutes();
		var sec = dates.getSeconds();
		return dates.getFullYear() + '-' + month + '-' + day+" "+hor+":"+min+":"+sec;
		};
		$('#dd').datetimebox('setValue', formatterDate(new Date()));
	$.extend($.fn.validatebox.defaults.rules, {  
	       equaldDate: {  
	           validator: function (value, param) {  
	               var start = $(param[0]).datetimebox('getValue');  //获取开始时间    
	               return value > start;                             //有效范围为当前时间大于开始时间    
	           },  
	           message: '结束日期应大于开始日期!'                     //匹配失败消息  
	       }  
	 });
});



















