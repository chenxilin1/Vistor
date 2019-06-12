function placeholderfun(){  
          
        if( !('placeholder' in document.createElement('input')) ){    
            function GetStringNumValue(pxstr){  
                return pxstr.substring(0,pxstr.length-2);  
            }  
              
            $('input[placeholder],textarea[placeholder]').each(function(){  
                var $element = $(this),   
                placeholder = $element.attr('placeholder');  
                if($element.attr('type') != 'password'){//非密码  
                    if($element.val()===""){  
                        $element.val(placeholder).addClass('placeholder');     
                        $element.css('color','#ccc');  
                    }     
                    $element.focus(function(){     
                        if($element.val()===placeholder){  
                          $element.val("").removeClass('placeholder');     
                          $element.css('color','#000');  
                        }     
                    }).blur(function(){     
                        if($element.val()===""){   //if($element.val()==="" &&  ($element.attr('type') != 'password')){    
                            $element.val(placeholder).addClass('placeholder');     
                            $element.css('color','#ccc');     
                        }else if($element.val() == placeholder){  
                            $element.css('color','#ccc');     
                        }else{  
                            $element.css('color','#000');     
                        }     
                      }).closest('form').submit(function(){     
                        if($element.val() === placeholder){     
                          $element.val('');     
                        }     
                      });     
                }else{//密码框  
                     if (placeholder){  
                         // 文本框ID  
                        var elementId = $element.attr('id');  
                         if (!elementId) {  
                            var now = new Date();  
                            elementId = 'lbl_placeholder' + now.getSeconds() + now.getMilliseconds();  
                            $element.attr('id', elementId);  
                         }  
                     }//end of if (placeholder)  
                      // 添加label标签，用于显示placeholder的值  
                    var $label = $('<label>', {  
                                    html: $element.val() ? '' : placeholder,  
                                    'for': elementId,  
                                    css:{  
                                        position: 'absolute',  
                                        cursor: 'text',  
                                        color: '#cccccc',  
                                        fontSize: $element.css('fontSize'),  
                                        fontFamily: $element.css('fontFamily')  
                                    }  
                                }).insertAfter($element);  
                     // 绑定事件  
                    var _setPosition = function () {  
                            $label.css({   
                                marginTop:'10px',  
                                marginLeft:'-250px'  
                            });  
                        };  
                    var _resetPlaceholder = function () {  
                        if ($element.val()) { $label.html(null); }  
                        else {  
                            _setPosition();  
                            $label.html(placeholder);  
                        }  
                    };  
                    _setPosition();  
                    $element.on('focus blur input keyup propertychange resetplaceholder', _resetPlaceholder);  
                }     
            });   
          }    
    }  
 /*点击刷新验证*/
 $("#dianji").click(function(){
 	//document.getElementById("dianji").src = 'yzmyz?time='+new Date().getTime();
 	$("#dianji").attr("src","hzyzm?time="+new Date().getTime());
     $("#shuxin").attr("value","");
 })
 //alert("0013")
//给Document区域添加一个键盘监听 
document.onkeydown=function(event){
 	var e = event || window.event || arguments.callee.caller.arguments[0];           
	if(e && e.keyCode==13){ // enter 键
		//danjidl();
		dlyz();
	}
};  
$("#login").click(function(){
	//alert("干嘛点我");
	//danjidl();
	dlyz();
})
//校验验证码是否正确
function danjidl(){
    $.ajax({
        url:"jyyzm?time="+new Date().getTime(),
        type:"POST",
        data:$(".code").val(),
        dataType:"text",
        success:function(msg){
            //获取当前页面验证码输入框的内容
            var input_yzm=$(".code").val();
            //获取服务器响应的内容
            //alert(input_yzm+'----'+msg)
            //alert(msg)
            if(!(msg==input_yzm)){
                //两者对比,正确跳转页面,错误给出提示
                alert("验证码错误，请重新输入");
                //输入错误清空输入框
                $("input[name='code']").val("").focus();
            }else{
                //验证账号密码
            	dlyz();
            }

        }
    })
}
//中间转接  在此可以添加进度条
function dlyz(){
	//alert("001");
	showMask();
	$("#jzz").attr("style","display:block");
	setTimeout(dlyzs,3000);
	//dlyzs();
} 
//显示遮罩层
function showMask(){     
    //$("#mask").css("height",$(document).height());     
    //$("#mask").css("width",$(document).width());     
    $("#mask").css("height","100%");     
    $("#mask").css("width","100%");     
    $("#mask").show();     
}  
//隐藏遮罩层  
function hideMask(){     
    $("#mask").hide();     
}
//测试登录
function dlyzss(){
	location.href="login/toIndex";
}
//校验账号密码及验证码的正确性、页面跳转
function dlyzs(){
	//$.messager.progress();
	var yhm = document.getElementById("username").value;
	var yhmm = document.getElementById("password").value;
	//alert(yhm);
	//alert(yhmm);
	if ("" == yhm || "请输入用户名"==yhm) {
		$("#mask").hide();
		$("#jzz").attr("style", "display:none");
		alert("请输入账号");
	} else {
		if ("" == yhmm ||"请输入密码"==yhmm) {
			$("#mask").hide();
			$("#jzz").attr("style", "display:none");
			alert("请输入密码");
		} else {
			$.ajax({
				url : "yzdl?time=" + new Date().getTime(),
				data : $("#InitInfoForm").serialize(),
				type : "POST",
				dataType : "text",
				success : function(msg) {
					if ('YES' === msg) {
						//$.messager.progress('close');
						$("#jzz").attr("style", "display:none");
						hideMask();
						window.location.href = "toIndex?time="+new Date().getTime()
						//window.location.href = root+'/index.jsp';
					} else if ('wuzh' === msg) {
						//$.messager.progress('close');
						$("#jzz").attr("style", "display:none");
						alert("账号输入错误");
						hideMask();
					} else if ('FFDL' === msg) {
						$("#jzz").attr("style", "display:none");
						hideMask();
						//alert("盗版登录，请通过正规渠道购买正版。。。");
						$("#div_sdk").dialog({
				        	title:"正版产品初次使用请输入Mac或SDK验证",
							// top:'80px',
				        	height:"200px",
							width:"400px",
				            buttons:[{
				                text: "保存",
				                handler: function () {
				                	//--
				                	$.ajax({
				                        url:'bqgl/yzMacOrSdk?time='+new Date().getTime(),
				                        data:$("#MacOrIpForm").serialize(),//序列化
				                        type:"POST",
				                        dataType:"text",
				                        success:function(msg){
				                            if(msg=='YES'){
				                            	alert("正版产品验证成功，请重启Tomcat服务并刷新网页登录，祝您使用愉快");
				                            	//location.reload();
				                            	$("#div_sdk").dialog("close");
				                            }else if (msg=='MACCW') {
				                            	alert("Mac地址错误，请确认购买产品为正版");
				                            }else if (msg=='SDKCW') {
				                            	alert("SDK错误，请确认购买产品为正版");
				                            }else {
				                            	alert("输入错误，请确认购买的为正版");
											}
				                        }
				                    });
				                	//--
				                }
			               	},{
					            text: "关闭",
					            handler: function () {
					            	$("#div_sdk").dialog("close");
					            }
			                }]
				        });
						$("#div_sdk").dialog('open');
					} else if ('wumm' === msg) {
						//$.messager.progress('close');
						$("#jzz").attr("style", "display:none");
						alert("密码输入错误");
						hideMask();
					}
				},
				error : function() {
					//$.messager.progress('close');
					$("#jzz").attr("style", "display:none");
					alert("网络异常，请重试或检查数据库IP是否正确");
					hideMask();
					/*$("#div_IP").dialog({
			        	title:"数据库IP更新",
						// top:'80px',
			        	height:"150px",
						width:"400px",
			            buttons:[{
			                text: "保存",
			                handler: function () {
			                	//alert("保存");
			                	$.ajax({
			                        url:'bqgl/yzSjkIp?time='+new Date().getTime(),
			                        data:$("#SjkIpForm").serialize(),//序列化
			                        type:"POST",
			                        dataType:"text",
			                        success:function(msg){
			                            if(msg=='YES'){
			                            	alert("IP更新成功，请重启Tomcat服务并刷新网页登录，祝您使用愉快");
			                            	$("#div_IP").dialog("close");
			                            }else {
			                            	alert("IP输入错误，请确认数据库IP是否正确");
										}
			                        }
			                    });
			                }
		               	},{
				            text: "关闭",
				            handler: function () {
				            	$("#div_IP").dialog("close");
				            }
		                }]
			        });
					$("#div_IP").dialog('open');*/
				}
			});
		}
	}
}
//SDK验证 TODO 暂时废弃
$("#SDKYZ").click(function(){
	$("#div_sdk").dialog({
		title: 'SDK或MAC验证',    
		//top:'80px',
    	height:"400",
		width:"400",
        buttons:[{
            text: "关闭",
            handler: function () {
                $("#div_sdk").dialog("close");
            }
        }]
    });
})
//单击Mac版
$("#bb1").click(function(){
	$("#macdztr").show();  
	$("#Sdkdztr").hide();  
})
//单击SDK版
$("#bb2").click(function(){
	$("#macdztr").hide();  
	$("#Sdkdztr").show();  
})


 
 
 
 