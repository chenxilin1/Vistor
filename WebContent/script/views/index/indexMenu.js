var _menus = null;
//获取值
function tree() {
	//rzsx();
	(function() {
		function someAnimation(args) {
			document.getElementById("animation").style.opacity = args;
		}
		setInterval(function() {
			for ( var i = 0; i < 10; i++) {
				setTimeout((function(pos) {
					return function() {
						someAnimation(pos);
					}
				})(i / 10), i * 100)
			}
		}, 1000);
	})()
	var u = "queryAllMenu?time=" + new Date().getTime();
	$.ajax({
		dataType : 'json',
		url : u,
		success : function(date) {
			_menus = date;
			if (null == _menus) {
				$.ajax({
					url : "loginout?time=" + new Date().getTime(),
					// data:$("#loginForm").serialize(),
					type : "POST",
					dataType : "text",
					success : function(msg) {
						if ("YES" == msg) {
							alert("已掉线，请重新登录")
							location.href = "login.jsp";
						} else {
							alert("账户已掉线，请刷新页面");
						}
					}
				})
			} else {
				$("#nav").accordion({
					animate : false
				});
				$.each(date, function(i, n) {
					var menulist = '';
					menulist += '<ul>';
					$.each(n.menus, function(j, o) {
						menulist += '<li><div><a ref="' + o.menuid + '" href="javascript:void(0)" rel="' + o.url + '" ><span class="icon ' + o.icon + '" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div></li> ';
					});
					menulist += '</ul>';
					$('#nav').accordion('add', {
						title : n.menuname,
						content : menulist,
						iconCls : 'icon ' + n.icon
					});
				});
				$('.easyui-accordion li a').click(function() {
					var tabTitle = $(this).children('.nav').text();
					var url = $(this).attr("rel");
					var menuid = $(this).attr("ref");
					var icon = getIcon(menuid);
					addTab(tabTitle, url, icon);
					$('.easyui-accordion li div').removeClass("selected");
					$(this).parent().addClass("selected");
				}).hover(function() {
					$(this).parent().addClass("hover");
				}, function() {
					$(this).parent().removeClass("hover");
				});

				//选中第一个
				var panels = $('#nav').accordion('panels');
				var t = panels[0].panel('options').title;
				//alert(panels[0].panel('options').title);
				$('#nav').accordion('select', t);
			}
		}
	});
	/**获得当前动态日期**/
	document.getElementById("yue_fen").innerHTML = $(function() {
		setInterval(getTime, 1000);
	});
	function getTime() {
		var dateTime = new Date();
		var myYear = dateTime.getFullYear();
		var myMonth = dateTime.getMonth() + 1;
		var myDay = dateTime.getDate();
		var hours = dateTime.getHours();
		if (parseInt(hours) < 10) {
			hours = '0' + hours;
		}
		var timeValue = "" + ((hours >= 12) ? "下午 " : "上午 ");
		var minutes = dateTime.getMinutes();
		if (parseInt(minutes) < 10) {
			minutes = '0' + minutes;
		}
		var seconds = dateTime.getSeconds();
		if (parseInt(seconds) < 10) {
			seconds = '0' + seconds;
		}
		$("#yue_fen").text(myYear + "-" + myMonth + "-" + myDay + " " + timeValue + hours + ":" + minutes + ":" + seconds);
	}
}

// alert(_menus);
//设置登录窗口
function openPwd() {
	$('#w').window({
		title : '修改密码',
		width : 300,
		modal : true,
		shadow : true,
		closed : true,
		height : 190,
		resizable : false
	});
}
//关闭修改密码窗口
function closePwd() {
	$('#w').window('close');
}
//修改密码
function serverLogin() {
	var $newpass = $('#txtNewPass');
	var $rePass = $('#txtRePass');
	var $ysPwd = $('#ysmm');
	if ('' == $ysPwd.val()) {
		msgShow('系统提示', '请输入原始密码！', 'warning');
		return false;
	}
	if ('' == $newpass.val()) {
		msgShow('系统提示', '请输入密码！', 'warning');
		return false;
	}
	if ($rePass.val() == '') {
		msgShow('系统提示', '请输入确认密码！', 'warning');
		return false;
	}
	if ($newpass.val() != $rePass.val()) {
		msgShow('系统提示', '两次新密码不一致！请重新输入', 'warning');
		return false;
	}
	var username = document.getElementById("hqyhm").innerText;
	$.post('updatePwd_yh?newpass=' + $newpass.val() + '&user=' + username + '&password=' + $ysPwd.val(), function(msg) {
		if (msg == "NO") {
			msgShow('系统提示', '原始密码错误');
		} else if (msg == "NON") {
			msgShow('系统提示', '超级管理员不能修改密码');
		} else if (msg == "YES") {
			msgShow('系统提示', '恭喜，密码修改成功!<br>您的新密码为：' + $newpass.val());
			//msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
			$ysPwd.val('');
			$newpass.val('');
			$rePass.val('');
			//close();
			closePwd();
		}
	});
}
//退出操作
$(function() {
	openPwd();
	$('#editpass').click(function() {
		$('#w').window('open');
	});
	$('#btnEp').click(function() {
		serverLogin();
	});
	$('#btnCancel').click(function() {
		closePwd();
	});
	$('#loginOut').click(function() {
		var usernames = document.getElementById("hqyhm").innerText;
		//alert("00");
		$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
			if (r) {
				$.ajax({
					url : "loginout?time=" + new Date().getTime() + "&username=" + usernames,
					// data:$("#loginForm").serialize(),
					type : "POST",
					dataType : "text",
					success : function(msg) {
						if ("YES" == msg) {
							location.href = "login.jsp";
						} else {
							alert("退出失败");
						}
					}
				})
			}
		});
	});
});
//定时器
/*setInterval(function() {
	rzsx();
}, 1000000);*/
//日志刷新
function rzsx() {
	$.ajax({
		url : 'bqgl/sxrzxq?time=' + new Date().getTime(),
		//data : $("#fkxx_form").serialize(),//序列化表单数据,方便提交
		type : "POST",//提交方式
		dataType : "json", //服务器响应回来数据类型
		success : function(data) {//接收服务器响应的处理函数
			//alert(data.rows[0].czsj);
			var table="";
			var ztys="";
			for ( var i = data.rows.length-1; i >=0 ; i--) {
				if (i==0) {
					ztys="red";
				}else {
					ztys="black";
				}
				table+="<table style='border-bottom: dotted 1px #000;color: "+ztys+";'>" +
				"<tr><td>用户:"+data.rows[i].yhm+"</td></tr>" +
				"<tr><td>在"+data.rows[i].czsj+"</td></tr>" +
				"<tr><td>操作"+data.rows[i].cznr+"</td></tr>" +
				"</table>";
				
			}
			//divrztc:日志填充的div的id
			 var div1 = document.getElementById('divrztc');
			 div1.innerHTML=table;
			//alert(table);
		}
	});
}
//浏览器大小改变后执行
/*$(window).resize(function() { 
	if ($(window).width()<1077) {
		$(".easyui-layout").layout('collapse','east');
	}else {
		$(".easyui-layout").layout('expand','east');
	}
})*/















