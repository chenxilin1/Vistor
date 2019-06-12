    //给Document区域添加一个键盘监听 
document.onkeydown=function(event){
 	var e = event || window.event || arguments.callee.caller.arguments[0];           
	if(e && e.keyCode==13){ // enter 键
		danjidl();
	}
};  
$("#login").click(function(){
	danjidl();
})
$(function(){
    //登录对话框
    $("#loginDialog").dialog({
        title:"用户登录",
        width:"350",
        height:"250",
        iconCls:"icon-bomb"
    });
    /*登录按钮*/
    $("#login").linkbutton({

    });
//添加验证
$("input[name='username']").validatebox({
    required: true,
   // validType: 'inputLength[3,9]',
    missingMessage:"请输入账号"
});
$("input[name='password']").validatebox({
    required: true,
   // validType: 'inputLength[3,6]',
    missingMessage:"请输入密码"
});
//自定义校验规则
$.extend($.fn.validatebox.defaults.rules,{
    inputLength:{
        validator:function(value,param){
            return value.match("[a-z]{"+param[0]+","+param[1]+"}");
        },
        message:'长度只能是{0}到{1}之间的字母'
    }
});
  
    
});
//校验账号密码及验证码的正确性、页面跳转
function danjidls(){
    $.messager.progress();
    var isEnabled=$("#loginForm").form('validate');
    //console.log(isEnabled);
    if(isEnabled){
        $.ajax({
            url:"login?time="+new Date().getTime(),
            data:$("#loginForm").serialize(),
            type:"POST",
            dataType:"text",
            success:function(msg){
                if('YES'===msg){
                	$.messager.progress('close');
                    location.href="toIndex";
                	//window.location.href = root+'/index.jsp';
                }else if('wuzh'===msg){
                	$.messager.progress('close');
                    alert("账号输入错误");
                }else if('wumm'===msg){
                	$.messager.progress('close');
                    alert("密码输入错误");
                }
            },
            error:function(){
            	$.messager.progress('close');
            	alert("网络异常，请重试。。。");
            }
        });       
    }else{
    	$.messager.progress('close');
        alert( "账号或者密码无输入");
    }
}
//密碼框显示提示语
window.onload=function(){
	var tx=document.getElementById("tx");
	var qingkongpwd=document.getElementById("qingkongpwd");
	var qingkonguser=document.getElementById("qingkonguser");
	var zh=document.getElementById("zh");
	tx.onfocus=function(){
		if(this.value!="请输入密码")
		return;
		this.style.display="none";
		qingkongpwd.style.display="block";
		qingkongpwd.value="";
		qingkongpwd.focus();
	}
	qingkongpwd.onblur=function(){
		if(this.value!=""){
			return;
		}
		this.style.display="none";
		tx.style.display="";
		tx.value="请输入密码";
	}
	zh.onfocus=function(){
		if(this.value!="请输入账号")
			return;
		this.style.display="none";
		qingkonguser.style.display="block";
		qingkonguser.value="";
		qingkonguser.focus();
	}
	qingkonguser.onblur=function(){
		if(this.value!=""){
			return;
		}
		this.style.display="none";
		zh.style.display="";
		zh.value="请输入账号";
	}
}
//校验验证码是否正确
function danjidl(){
    $.ajax({
        url:"sxyzm?time="+new Date().getTime(),
        type:"POST",
        data:$(".code").val(),
        dataType:"text",
        success:function(msg){
            //获取当前页面验证码输入框的内容
            var input_yzm=$(".code").val();
            //获取服务器响应的内容
            //alert(input_yzm)
            //alert(msg)
            if(!(msg==input_yzm)){
                //两者对比,正确跳转页面,错误给出提示
                alert("验证码错误，请重新输入");
                //输入错误清空输入框
                $("input[name='code']").val("").focus();
            }else{
               /* $.messager.show({
                    title:'系统温馨提示',
                    msg:'验证码正确,准备跳转',
                    timeout:5000,
                    showType:'slide'

                })*/
                //验证账号密码
            	danjidls();
            }

        }
    })
}

/*点击刷新验证*/
$("#dianji").click(function(){
	//document.getElementById("dianji").src = 'yzmyz?time='+new Date().getTime();
	$("#dianji").attr("src","yzmyz?time="+new Date().getTime());
    $("#shuxin").attr("value","");
})





































