//自定义验证方式
$.extend($.fn.validatebox.defaults.rules, {
    minLength: {
		validator: function(value, param){
			return $.trim(value).length >= param[0];
		},
		message: '不能为空格'
    }
});


