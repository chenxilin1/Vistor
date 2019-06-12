$.extend($.fn.validatebox.defaults.rules, {
	yzyhmwy : {
		validator : function(value, param) {
			var bh = value;
			var a = false;
			$.ajax({
				url : 'xtgl/yzyhmwy',
				type : 'POST',
				timeout : 60000,
				data : {
					"bh" : encodeURI(encodeURIComponent(bh))
				},
				async : false,
				success : function(data) {
					if (data.flag) {
						a = true;
					}
				}
			});
			if (a) {
				return true;
			} else {
				return false;
			}
		},
		message : '用户名已存在，请更改！'
	},
	equals: {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '两次密码输入不一致'
	},
	ddmc_bsx: {
		validator: function(value,param){  
            if (value == "" || value.indexOf('请选择') >= 0) {   
               return false;  
            }else {  
               return true;  
            }    
       },   
       message: '请选择'
	},
	yzip: {
		validator: function(value,param){ 
			var exp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
			var reg = value.match(exp);
			if (reg == "" || reg==null) {   
				return false;  
			}else {  
				return true;  
			}    
		},   
		message: 'IP格式错误'
	},
	jdcbq_yzhphmgs:{
		validator: function(value,param){ 
			var hp=$('#hp').combobox('getValue');
		    var hm=$('#hm').combobox('getValue');
			var hphm=hp+hm+value;
			var cd=hphm.length
			if (cd!=7) {   
				return false;  
			}else {  
				return true;  
			}    
		},   
		message: '号牌号码格式错误'
	},
	jdcbq_yzhphmwy:{
		validator: function(value,param){ 
			var hp=$('#hp').combobox('getValue');
		    var hm=$('#hm').combobox('getValue');
		    var jdcbqid=$(param[0]).val();
			var hphm=hp+hm+value;
			var a = false;
			$.ajax({
				url : 'xtgl/yzhphmwy',
				type : 'POST',
				timeout : 60000,
				data : {
					"hphm" : encodeURI(encodeURIComponent(hphm)),
					'id':jdcbqid
				},
				async : false,
				success : function(data) {
					if (data.flag) {
						a = true;
					}
				}
			});
			if (a) {
				return false;
			} else {
				return true;
			}    
		},   
		message: '号牌号码已存在'
	},
	jsrbq_yzsfzhwy:{
		validator: function(value,param){ 
			var sfzh=value;
			var jsrbqid=$(param[0]).val();
			var a = false;
			$.ajax({
				url : 'xtgl/yzsfzhwy',
				type : 'POST',
				timeout : 60000,
				data : {
					"sfzh" : encodeURI(encodeURIComponent(sfzh)),
					"id":jsrbqid
				},
				async : false,
				success : function(data) {
					if (data.flag) {
						a = true;
					}
				}
			});
			if (a) {
				return false;
			} else {
				return true;
			}    
		},   
		message: '身份证号码已存在'
	},
	ppmsip_wy:{
		validator: function(value,param){ 
			var ppmsid=$(param[0]).val();
			var a = false;
			$.ajax({
				url : 'xtgl/yzIpIsCz',//验证匹配模式里IP是否存在
				type : 'POST',
				timeout : 60000,
				data : {
					"ip" : encodeURI(encodeURIComponent(value)),
					"id" : ppmsid
				},
				async : false,
				success : function(data) {
					if (data.flag) {
						a = true;
					}
				}
			});
			if (a) {
				return false;
			} else {
				return true;
			}    
		},   
		message: '此IP已被使用，请更改'
	},
	yzddmc_wy_ppms:{
		validator: function(value,param){ 
			var ppmsid=$(param[0]).val();
			//alert(value);
			var a = false;
			$.ajax({
				url : 'xtgl/yzddIspz',//验证匹配模式里的地点是否配置
				type : 'POST',
				timeout : 60000,
				data : {
					"ddmc" : encodeURI(encodeURIComponent(value)),
					"id" : ppmsid
				},
				async : false,
				success : function(data) {
					if (data.flag) {
						a = true;
					}
				}
			});
			if (a) {
				return false;
			} else {
				return true;
			}    
		},   
		message: '此地点已配置匹配模式，不可重复配置'
	}
	

});















