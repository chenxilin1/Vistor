var g_iWndIndex = 0;
var param = {
	init : function() {
		// 检查插件是否已经安装过
		var oPlugin = {
			iWidth : '100%',
			iHeight : '100%'
		};
		if (0 != WebVideoCtrl.I_CheckPluginInstall()) {
			//alert("您还未安装插件");
			$.messager.confirm('提示框', "<span style='color: red;font-size: 18px;'>您还未安装视频插件,是否下载安装?</span>", function(r) {
				if (r) {
					var localObj=window.location+"";
					var basePath=localObj.split("/Visitor/")[0];
					//alert(basePath);
					location.href = basePath+"/Visitor/plugin/WebComponents.exe";
				}
			})
			return;
		}
		// 初始化插件参数及插入插件
		WebVideoCtrl.I_InitPlugin(oPlugin.iWidth, oPlugin.iHeight, {
			bWndFull : true,// 是否支持单窗口双击全屏，默认支持 true:支持 false:不支持
			iWndowType : 2,
			cbSelWnd : function(xmlDoc) {
				g_iWndIndex = $(xmlDoc).find("SelectWnd").eq(0).text();
				hqxx();
			}
		});
		WebVideoCtrl.I_InsertOBJECTPlugin("divPlugin");
	}
}
//获取相机信息
function hqxx(){
	var xj=WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);
	if (null==xj) {
		document .getElementById ("span_cx").innerHTML = "此窗格无视频信息";
		document .getElementById ("span_ysdsz").innerHTML = "此窗格无视频信息";
		document.getElementById('ysddxz').innerHTML="";
		var select = document.getElementById("ysddxz");
		var theOptions = document.createElement("option");
		theOptions.innerHTML = "请选择";
        theOptions.value = "999";
        select.appendChild(theOptions);
	}else {
		document .getElementById ("span_ysdsz").innerHTML = "";
		//alert(xj.szIP);
		$.ajax({ 
	        url :'sxgl/findAllXjmc2yzd', 
	        type : 'POST',                   
	        timeout : 60000, 
	        data:{"xjip":xj.szIP}, 
	        dataType:"json",
	        success : function(data) {
	        	document.getElementById('ysddxz').innerHTML="";
	        	var select = document.getElementById("ysddxz");
	        	if (null==data.obj) {
		        	var theOptions = document.createElement("option");
		        	theOptions.innerHTML = "请选择";
		            theOptions.value = "999";
		            select.appendChild(theOptions);
				}else {
					var length=data.obj.length;
		        	var theOptions = document.createElement("option");
		        	theOptions.innerHTML = "请选择";
		            theOptions.value = "999";
		            select.appendChild(theOptions);
		        	for (var i = 0; i < length; i++) {
		        		var aa=data.obj[i].split(",");
		                var theOption = document.createElement("option");
		                theOption.innerHTML = aa[0];
		                theOption.value = aa[1];
		                select.appendChild(theOption);
		            }
		        	$("#select_id option[value='999']").remove();
				}
	        }
		});
		
		
		
	}
}
// 预览
function clickStartRealPlay(ip, iport, username, password) {
	
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);
	if (oWndInfo != null) {// 已经在播放了，先停止
		WebVideoCtrl.I_Stop();
	}
	var rest = WebVideoCtrl.I_Login(ip, 1, 80, username, password, {
		success : function(xml) {
			document .getElementById ("span_ysdsz").innerHTML = "预览成功";
			WebVideoCtrl.I_StartRealPlay(ip, {
				iStreamType:2,
				iChannelID : iport
			});
		},
		error : function() {
			//document .getElementById ("span_ysdsz").innerHTML = "预览失败";
		}
	})
	// alert("rest外:"+rest);

	if (rest == -1) {
		WebVideoCtrl.I_StartRealPlay(ip, {
			iStreamType:2,
			iChannelID : iport

		});
	}else {
		//document .getElementById ("span_cx").innerHTML = "预览失败";
	}
	$("#hiddenIp").val(ip)
	var xjmc=$("#input_xjmc").val();
	$.ajax({ 
        url :'sxgl/findAllXjmc2yzd', 
        type : 'POST',                   
        timeout : 60000, 
        data:{"xjmc":xjmc}, 
        dataType:"json",
        success : function(data) {
        	document.getElementById('ysddxz').innerHTML="";
        	var select = document.getElementById("ysddxz");
        	if (null==data.obj) {
	        	var theOptions = document.createElement("option");
	        	theOptions.innerHTML = "请选择";
	            theOptions.value = "999";
	            select.appendChild(theOptions);
			}else {
				var length=data.obj.length;
	        	var theOptions = document.createElement("option");
	        	theOptions.innerHTML = "请选择";
	            theOptions.value = "999";
	            select.appendChild(theOptions);
	        	for (var i = 0; i < length; i++) {
	        		var aa=data.obj[i].split(",");
	                var theOption = document.createElement("option");
	                theOption.innerHTML = aa[0];
	                theOption.value = aa[1];
	                select.appendChild(theOption);
	            }
	        	$("#select_id option[value='999']").remove();
			}
        }
	})
}

// 停止预览
function clickStopRealPlay() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);
	//alert(oWndInfo);
	if (oWndInfo != null) {
		var iRet = WebVideoCtrl.I_Stop();
		if (0 == iRet) {
			szInfo = "停止预览成功！";
		} else {
			szInfo = "停止预览失败！";
		}
	}
}
// 全屏
function clickFullScreen() {
	WebVideoCtrl.I_FullScreen(true);
}

// 窗口分割数
function changeWndNum(iType) {
	iType = parseInt(iType);
	WebVideoCtrl.I_ChangeWndNum(iType);
}
// PTZ控制 9为自动，1,2,3,4,5,6,7,8为方向PTZ
var g_bPTZAuto = false;
function mouseDownPTZControl(iPTZIndex) {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), iPTZSpeed = $("#ptzspeed").val();
	if (oWndInfo != null) {
		if (9 == iPTZIndex && g_bPTZAuto) {
			iPTZSpeed = 0;// 自动开启后，速度置为0可以关闭自动
		} else {
			g_bPTZAuto = false;// 点击其他方向，自动肯定会被关闭
		}
		WebVideoCtrl.I_PTZControl(iPTZIndex, false, {
			iPTZSpeed : iPTZSpeed,
			success : function(xmlDoc) {
				if (9 == iPTZIndex) {
					g_bPTZAuto = !g_bPTZAuto;
				}
			},
			error : function() {
			}
		});
	}
}
// 方向PTZ停止
function mouseUpPTZControl() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);
	if (oWndInfo != null) {
		WebVideoCtrl.I_PTZControl(1, true, {
			success : function(xmlDoc) {
				document .getElementById ("span_ysdsz").innerHTML = "方向调节成功";
			},
			error : function() {
				document .getElementById ("span_ysdsz").innerHTML = "方向调节失败，请检查相机是否支持";
			}
		});
	}else {
		document .getElementById ("span_ysdsz").innerHTML = "方向调节失败，请选择相机后重试";
	}
}

// 设置预置点
function clickSetPreset() {
	var ysd=$("#preset").val();
	//var xjmc=$("#input_xjmc").val();
	
	var xj=WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);
	//alert(xj);
	
	if (null==xj) {
		document .getElementById ("span_ysdsz").innerHTML = "设置失败，请选择相机";
	}else {
		if (""==ysd) {
			document .getElementById ("span_ysdsz").innerHTML = "设置失败，请设置预置点名称";
		}else {
			$.ajax({ 
		        url :'sxgl/ajax_addYsd', 
		        type : 'POST',                   
		        timeout : 60000, 
		        data:{"xjyzdmc":ysd,"xjip":xj.szIP}, 
		        dataType:"json",
		        success : function(data) {
		        	//var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), iPresetID = parseInt($("#preset").val(), 10);
		        	//alert(data.msg);
		        	if ("NO"==data.msg) {
		        		document .getElementById ("span_ysdsz").innerHTML = "预置点名称已存在，请更改";
					}else {
						document.getElementById('ysddxz').innerHTML="";
			        	var select = document.getElementById("ysddxz");
			        	var length=data.obj.length;
			        	var theOptions = document.createElement("option");
			        	theOptions.innerHTML = "请选择";
			            theOptions.value = "";
			            select.appendChild(theOptions);
			        	for (var i = 0; i < length; i++) {
			        		//alert(data.obj[i]);
			        		var aa=data.obj[i].split(",");
			                var theOption = document.createElement("option");
			                theOption.innerHTML = aa[0];
			                theOption.value = aa[1];
			                select.appendChild(theOption);
			            }
			        	document.getElementById("ysddxz").value="";
						var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), iPresetID = parseInt(data.msg, 10);
			        	//alert(iPresetID);
			        	if (oWndInfo != null) {
			        		//alert(iPresetID);
			        		WebVideoCtrl.I_SetPreset(iPresetID, {
			        			success : function(xmlDoc) {
			        				document .getElementById ("span_ysdsz").innerHTML = "设置成功";
			        			},
			        			error : function() {
			        				document .getElementById ("span_ysdsz").innerHTML = "设置失败，此相机暂不支持设置预置点";
			        			}
			        		});
			        	}else {
			        		document .getElementById ("span_ysdsz").innerHTML = "设置失败，请选择相机";
			        	}
					}
		        },
		        error:function(){
		        	document .getElementById ("span_ysdsz").innerHTML = "设置失败";
		        }
			})
		}
	}
}
//修改预置点
function clickUpdPreset() {
	var ysdmc=$('#ysddxz option:selected').text();
	if ("请选择"==ysdmc) {
		document .getElementById ("span_ysdsz").innerHTML = "预置点修改失败，请选择预置点名称";
	}else {
		var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),iPresetID = parseInt($("#ysddxz").val(), 10);
		if (oWndInfo != null) {
			WebVideoCtrl.I_SetPreset(iPresetID, {
				success: function (xmlDoc) {
					document .getElementById ("span_ysdsz").innerHTML = "预置点修改成功";
				},
				error: function () {
					document .getElementById ("span_ysdsz").innerHTML = "预置点修改失败,请检查相机是否支持";
				}
			});
		}else {
			document .getElementById ("span_ysdsz").innerHTML = "预置点修改失败，请选择相机";
		}
	}
}
//删除预置点
function clickDelPreset() {
	var ysdmc=$('#ysddxz option:selected').text();
	//var xjmc=$("#input_xjmc").val();
	var xj=WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);
	if (""==xj) {
		document .getElementById ("span_ysdsz").innerHTML = "删除失败，请选择相机";
	}else {
		if ("请选择"==ysdmc) {
			document .getElementById ("span_ysdsz").innerHTML = "删除失败，请选择预置点名称";
		}else {
			//alert(ysdmc);
			//alert(xjmc);
			$.ajax({ 
		        url :'sxgl/ajax_delYsd', 
		        type : 'POST',                   
		        timeout : 60000, 
		        data:{"xjyzdmc":ysdmc,"xjip":xj.szIP}, 
		        dataType:"json",
		        success : function(data) {
		        	if ("NO"==data.msg) {
		        		document .getElementById ("span_ysdsz").innerHTML = "删除异常，请刷新页面重试";
		        	}else if("BCZ"==data.msg){
		        		document .getElementById ("span_ysdsz").innerHTML = "删除异常，不存在，请刷新页面重试";
					}else {
						document.getElementById('ysddxz').innerHTML="";
			        	var select = document.getElementById("ysddxz");
			        	if (null==data.obj) {
				        	var theOptions = document.createElement("option");
				        	theOptions.innerHTML = "请选择";
				            theOptions.value = "999";
				            select.appendChild(theOptions);
						}else {
							var length=data.obj.length;
				        	var theOptions = document.createElement("option");
				        	theOptions.innerHTML = "请选择";
				            theOptions.value = "";
				            select.appendChild(theOptions);
				        	for (var i = 0; i < length; i++) {
				        		//alert(data.obj[i]);
				        		var aa=data.obj[i].split(",");
				                var theOption = document.createElement("option");
				                theOption.innerHTML = aa[0];
				                theOption.value = aa[1];
				                select.appendChild(theOption);
				            }
				        	document.getElementById("ysddxz").value="";
						}
						document .getElementById ("span_ysdsz").innerHTML = "删除成功";
					}
		        },
		        error:function(){
		        	document .getElementById ("span_ysdsz").innerHTML = "删除失败";
		        }
			});
		}
	}
}

// 调用预置点
function clickGoPreset() {
	//var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), iPresetID = parseInt($("#preset").val(), 10);
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex), iPresetID = parseInt($("#ysddxz").val(), 10);
	if (oWndInfo != null) {
		WebVideoCtrl.I_GoPreset(iPresetID, {
			success : function(xmlDoc) {
				document .getElementById ("span_ysdsz").innerHTML = "调用成功";
			},
			error : function() {
				document .getElementById ("span_ysdsz").innerHTML = "预置点调用失败，请检查相机是否支持";
			}
		});
	}else {
		document .getElementById ("span_ysdsz").innerHTML = "预置点调用失败,请选择预设点";
	}
}
function PTZZoomIn() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

	if (oWndInfo != null) {
		WebVideoCtrl.I_PTZControl(10, false, {
			iWndIndex : g_iWndIndex,
			success : function(xmlDoc) {
				document .getElementById ("span_ysdsz").innerHTML = "变 倍+成功";
			},
			error : function() {
				document .getElementById ("span_ysdsz").innerHTML = "变 倍+失败";
			}
		});
	}else {
		document .getElementById ("span_ysdsz").innerHTML = "调节失败，请选择相机后重试";
	}
}

function PTZZoomout() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

	if (oWndInfo != null) {
		WebVideoCtrl.I_PTZControl(11, false, {
			iWndIndex : g_iWndIndex,
			success : function(xmlDoc) {
				document .getElementById ("span_ysdsz").innerHTML = "变 倍-成功";
			},
			error : function() {
				document .getElementById ("span_ysdsz").innerHTML = "变 倍-失败";
			}
		});
	}
}

function PTZZoomStop() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

	if (oWndInfo != null) {
		WebVideoCtrl.I_PTZControl(11, true, {
			iWndIndex : g_iWndIndex,
			success : function(xmlDoc) {
				document .getElementById ("span_ysdsz").innerHTML = "调 节 变 倍 成 功";
			},
			error : function() {
				document .getElementById ("span_ysdsz").innerHTML = "调 节 变 倍 失 败";
			}
		});
	}else {
		document .getElementById ("span_ysdsz").innerHTML = "变倍调节失败，请选择相机后重试";
	}
}

function PTZFocusIn() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

	if (oWndInfo != null) {
		WebVideoCtrl.I_PTZControl(12, false, {
			iWndIndex : g_iWndIndex,
			success : function(xmlDoc) {
				document .getElementById ("span_ysdsz").innerHTML = "变 焦+成功";
			},
			error : function() {
				document .getElementById ("span_ysdsz").innerHTML = "变 倍-失败";
			}
		});
	}
}

function PTZFoucusOut() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

	if (oWndInfo != null) {
		WebVideoCtrl.I_PTZControl(13, false, {
			iWndIndex : g_iWndIndex,
			success : function(xmlDoc) {
				document .getElementById ("span_ysdsz").innerHTML = "变 焦-成功";
			},
			error : function() {
				document .getElementById ("span_ysdsz").innerHTML = "变 焦-失败";
			}
		});
	}
}

function PTZFoucusStop() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

	if (oWndInfo != null) {
		WebVideoCtrl.I_PTZControl(12, true, {
			iWndIndex : g_iWndIndex,
			success : function(xmlDoc) {
				document .getElementById ("span_ysdsz").innerHTML = "调 节 变 焦 成 功";
			},
			error : function() {
				document .getElementById ("span_ysdsz").innerHTML = "调 节 变 焦 失 败";
			}
		});
	}else {
		document .getElementById ("span_ysdsz").innerHTML = "变焦调节失败，请选择相机后重试";
	}
}

function PTZIrisIn() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

	if (oWndInfo != null) {
		WebVideoCtrl.I_PTZControl(14, false, {
			iWndIndex : g_iWndIndex,
			success : function(xmlDoc) {
				document .getElementById ("span_ysdsz").innerHTML = "光 圈+成功";
			},
			error : function() {
				document .getElementById ("span_ysdsz").innerHTML = "光 圈+失败";
			}
		});
	}
}

function PTZIrisOut() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);

	if (oWndInfo != null) {
		WebVideoCtrl.I_PTZControl(15, false, {
			iWndIndex : g_iWndIndex,
			success : function(xmlDoc) {
				document .getElementById ("span_ysdsz").innerHTML = "光 圈-成功";
			},
			error : function() {
				document .getElementById ("span_ysdsz").innerHTML = "光 圈-失败";
			}
		});
	}
}
function PTZIrisStop() {
	var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex);
	if (oWndInfo != null) {
		WebVideoCtrl.I_PTZControl(14, true, {
			iWndIndex : g_iWndIndex,
			success : function(xmlDoc) {
				document .getElementById ("span_ysdsz").innerHTML = "调 节 光 圈 成 功";
			},
			error : function() {
				document .getElementById ("span_ysdsz").innerHTML = "调 节 光 圈 失 败";
			}
		});
	}else {
		document .getElementById ("span_ysdsz").innerHTML = "光圈调节失败，请选择相机后重试";
	}
}
//单击登录
$("#yldl").click(function() {
	var url = document.getElementById("sxsb").value;
	//alert(url);
	var a = url.split(",");
	clickStartRealPlay(a[0], a[1], a[2], a[3]);
})
//单击开始预览
$("#ksyl").click(function() {
	var url = document.getElementById("sxsb").value;
	var a = url.split(",");
	clickStartRealPlay(a[0], a[1], a[2], a[3]);
	
})
//单击停止预览
$("#tzyl").click(function() {
	clickStopRealPlay();
})
//单击停止全部预览
$("#qbtzyl").click(function() {
	for ( var i = 0; i < 16; i++) {
		//alert(i);
		g_iWndIndex =i;
		clickStopRealPlay();
	}
})
//添加前清空表单
$("#xjxx_txxx").click(function() {
	document .getElementById ("span_cx").innerHTML = "可以添加";
	$('#xjxx').get(0).reset();
	//修改编辑状态
	$('#input_ddmc').removeAttr('disabled');
	$('#input_xjmc').removeAttr('disabled');
	$('#input_xjip').removeAttr('disabled');
	$('#input_xjtd').removeAttr('disabled');
	$('#input_xjzh').removeAttr('disabled');
	$('#input_xjmm').removeAttr('disabled');
})
//单击相机名称填写详细信息到表单
$("#sxsb").change(function(){
	//alert($("#sxsb").val());
	var jgmc=$("#sxsb").val();
	var aa=jgmc.split(",");
	$.ajax({ 
        url :'sxgl/ajax_cxxjxx', 
        type : 'POST',                   
        timeout : 60000, 
        data:{"jgmc":aa[4]}, 
        //async: false,   
        dataType:"json",
        success : function(data) { 
        	//查询后的信息写入input
        	document.getElementsByName("ddmc")[0].value=data.obj[0].ddmc;
        	document.getElementsByName("xjmc")[0].value=data.obj[0].xjmc;
        	document.getElementsByName("xjip")[0].value=data.obj[0].xjip;
        	document.getElementsByName("xjtd")[0].value=data.obj[0].xjtd;
        	document.getElementsByName("xjzh")[0].value=data.obj[0].xjzh;
        	document.getElementsByName("xjmm")[0].value=data.obj[0].xjmm;
        	//修改编辑状态
        	document.getElementsByName("ddmc")[0].disabled='true';
        	document.getElementsByName("xjmc")[0].disabled='true';
        	document.getElementsByName("xjip")[0].disabled='true';
        	document.getElementsByName("xjtd")[0].disabled='true';
        	document.getElementsByName("xjzh")[0].disabled='true';
        	document.getElementsByName("xjmm")[0].disabled='true';
        	//选择相机后的提示信息
        	var xzxj=document.getElementById("sxsb").value;
        	//alert(xzxj);
        	if (""==xzxj) {
        		document .getElementById ("span_cx").innerHTML = "此相机不可预览,请选择其他相机";
        	}else {
        		document .getElementById ("span_cx").innerHTML = "可以预览";
        	}
        } 
    })
})
//相机信息的添加
$("#xjxx_add").click(function() {
	var s=$("#input_xjzh").attr("disabled")
	if ("disabled"==s) {
		document .getElementById ("span_cx").innerHTML = "请填写新数据后再执行添加";	
	}else {
		$.ajax({ 
	        url :'sxgl/ajax_xjxx_add', 
	        type : 'POST',                   
	        data:$("#xjxx").serialize(),//序列化表单数据,方便提交
	        dataType:"json",
	        success : function(data) { 
	        	//alert("现在是="+data.obj);
	        	var msg=data.msg;
	        	if ("WDDMC"==msg) {
	        		document .getElementById ("span_cx").innerHTML = "请填写：选择地点";
				}else if ("XJIPCZ"==msg) {
					document .getElementById ("span_cx").innerHTML = "添加失败,相机IP已存在";
				}else if ("WXJIP"==msg) {
					document .getElementById ("span_cx").innerHTML = "请填写：相机IP";
				}else if ("WXJMC"==msg) {
					document .getElementById ("span_cx").innerHTML = "请填写：相机名称";
				}else if ("WXJMM"==msg) {
					document .getElementById ("span_cx").innerHTML = "请填写：相机密码";
				}else if ("WXJTD"==msg) {
					document .getElementById ("span_cx").innerHTML = "请填写：相机通道";
				}else if ("XJTDCD"==msg) {
					document .getElementById ("span_cx").innerHTML = "相机通道暂支持一位数字";
				}else if ("WXJZH"==msg) {
					document .getElementById ("span_cx").innerHTML = "请填写：相机账号";
				}else if ("XJMCCF"==msg) {
					document .getElementById ("span_cx").innerHTML = "相机名称已存在。请修改";
				}else if ("OK"==msg) {
					document .getElementById ("span_cx").innerHTML = "添加成功";
					document.getElementById('sxsb').innerHTML="";
		        	var select = document.getElementById("sxsb");
		        	var length=data.obj.length;
		        	var theOptions = document.createElement("option");
		        	theOptions.innerHTML = "不选择";
		            theOptions.value = "";
		            select.appendChild(theOptions);
		        	for (var i = 0; i < length; i++) {
		        		var aa=data.obj[i].split(",")[4];
		                var theOption = document.createElement("option");
		                theOption.innerHTML = aa;
		                theOption.value = data.obj[i];
		                select.appendChild(theOption);
		            }
		        	document.getElementById("sxsb").value="";
				}else {
					document .getElementById ("span_cx").innerHTML = "添加失败，请检查表单";
				}
	        } 
	    })
	}
})
//相机信息删除
$("#xjxx_delete").click(function() {
	var jgmc=$("#sxsb").val().split(",");
	//alert(jgmc[4]);
	if (null!=jgmc[4]) {
		$.ajax({ 
	        url :'sxgl/ajax_xjxx_delete', 
	        type : 'POST',                   
	        data:{"jgmc":jgmc[4]}, 
	        dataType:"json",
	        success : function(data) { 
	        	//alert("现在是="+data.obj);
	        	document .getElementById ("span_cx").innerHTML = "删除成功";
	        	//alert("删除成功");
	        	document.getElementById('sxsb').innerHTML="";
	        	var select = document.getElementById("sxsb");
	        	var length=data.obj.length;
	        	for (var i = 0; i < length; i++) {
	        		var aa=data.obj[i].split(",")[4];
	                var theOption = document.createElement("option");
	                theOption.innerHTML = aa;
	                theOption.value = data.obj[i]; 	
	                select.appendChild(theOption);
	                if (i==0) {
	                	theOption.selected = true;
	        		}
	            }
	        } 
		})
	}else {
		document .getElementById ("span_cx").innerHTML = "删除失败";
	}
})
//相机信息修改前编辑状态
$("#xjxx_bj").click(function() {
	var xjzh=$("#input_xjzh").val();
	var jgmc=$("#sxsb").val();
	if (""==jgmc) {
		document .getElementById ("span_cx").innerHTML = "请更改其他相机修改";
	}else if (""==xjzh) {
		document .getElementById ("span_cx").innerHTML = "请先选择相机，再执行修改";
	}else {
		document .getElementById ("span_cx").innerHTML = "可以修改";
		//修改编辑状态
		$('#input_ddmc').removeAttr('disabled');
		//$('#input_xjmc').removeAttr('disabled');
		$('#input_xjip').removeAttr('disabled');
		$('#input_xjtd').removeAttr('disabled');
		$('#input_xjzh').removeAttr('disabled');
		$('#input_xjmm').removeAttr('disabled');
	}
})
//相机信息修改
$("#xjxx_update").click(function() {
	//alert($("#input_xjmc").val())
	//alert($("#input_xjmc").val().length)
	var s=$("#input_xjzh").attr("disabled")
	if ("disabled"==s) {
		document .getElementById ("span_cx").innerHTML = "请编辑内容后再修改";	
	}else if (""==$("#input_ddmc").val()) {
		document .getElementById ("span_cx").innerHTML = "请填写：选择地点";
	}else if (""==$("#input_xjip").val()) {
		document .getElementById ("span_cx").innerHTML = "请填写：相机IP";
	}else if (""==$("#input_xjmc").val()) {
		document .getElementById ("span_cx").innerHTML = "请填写：相机名称";
	}else if (""==$("#input_xjmm").val()) {
		document .getElementById ("span_cx").innerHTML = "请填写：相机密码";
	}else if (""==$("#input_xjtd").val()) {
		document .getElementById ("span_cx").innerHTML = "请填写：相机通道";
	}else if (1!=$("#input_xjtd").val().length) {
		document .getElementById ("span_cx").innerHTML = "相机通道暂支持一位数字";
	}else if (""==$("#input_xjzh").val()) {
		document .getElementById ("span_cx").innerHTML = "请填写：相机账号";
	}else {
		var jgmc=$("#sxsb").val().split(",")[4];
		$.ajax({ 
	        url :'sxgl/ajax_xjxx_update?jgmc='+encodeURI(encodeURI(jgmc)), 
	        type : 'POST',                   
	        data:$("#xjxx").serialize(),//序列化表单数据,方便提交
	        dataType:"json",
	        success : function(data) { 
	        	if ("YES"==data.msg) {
	        		document .getElementById ("span_cx").innerHTML = "修改成功";	
		        	//alert("修改成功");
		        	document.getElementById('sxsb').innerHTML="";
		        	var select = document.getElementById("sxsb");
		        	var length=data.obj.length;
		        	for (var i = 0; i < length; i++) {
		        		var aa=data.obj[i].split(",")[4];
		                var theOption = document.createElement("option");
		                theOption.innerHTML = aa;
		                theOption.value = data.obj[i];
		                select.appendChild(theOption);
		                if (i==0) {
		                	theOption.selected = true;
		        		}
		            }
				}else if ("NO"==data.msg) {
					document .getElementById ("span_cx").innerHTML = "修改失败";	
				}
	        	
	        } 
		})
	}
})
















