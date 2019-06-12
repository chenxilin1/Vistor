var LODOP;
//填写时间空间
var date = new Date();
var times = date.getTime();
var moths = 1000 * 60 * 60 * 24 * 7
var dates = new Date(times - moths);
var day = dates.getDate() > 9 ? dates.getDate() : "0" + dates.getDate();
var month = (dates.getMonth() + 1) > 9 ? (dates.getMonth() + 1) : "0" + (dates.getMonth() + 1);
var shi = dates.getHours() > 9 ? dates.getHours() : "0" + dates.getHours();
var fen = dates.getMinutes() > 9 ? dates.getMinutes() : "0" + dates.getMinutes();
var miao = dates.getSeconds() > 9 ? dates.getSeconds() : "0" + dates.getSeconds();
var ks = dates.getFullYear() + '-' + month + '-' + day + ' ' + shi + ':' + fen + ':' + miao;
$('#datemax').val(ks);

var date_k = new Date();
//var times = date.getTime();
//var moths = 1000 * 60 * 60 * 24 * 7
//var dates = new Date(times - moths);
var day_k = date_k.getDate() > 9 ? date_k.getDate() : "0" + date_k.getDate();
var month_k = (date_k.getMonth() + 1) > 9 ? (date_k.getMonth() + 1) : "0" + (date_k.getMonth() + 1);
var shi_k = date_k.getHours() > 9 ? date_k.getHours() : "0" + date_k.getHours();
var fen_k = date_k.getMinutes() > 9 ? date_k.getMinutes() : "0" + date_k.getMinutes();
var miao_k = date_k.getSeconds() > 9 ? date_k.getSeconds() : "0" + date_k.getSeconds();
var js = date_k.getFullYear() + '-' + month_k + '-' + day_k + ' ' + shi_k + ':' + fen_k + ':' + miao_k;
$('#datemin').val(js);
var btName = [];//饼图全局名字	
var btValue = [];//饼图全局数量
var xtext = []
//时间轴
var lukou1 = []
//
var lukou = ''//路口 
//var tiaoshu = [];//数据
tiaoshu = [];//数据
var tjlx = '';//名称
var nhphm = '';//临时存放排除的号牌号码
var djr = '';//临时存放登记人
var bt = '';//
//指定图表的配置项和数据-第一个图标
var myChart = echarts.init(document.getElementById('main'));
var optiony = {
		title : {
			text : '车辆记录-统计图'
		},
	    tooltip : {
	        trigger: 'axis',
	        //position:['150','center'],
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    legend: {
	        //x:'center',
	    	//top:'0',
	        left:'150',
	        width:'80%',
	        data:[]
	    },
	    grid: {
	    	top : '25%',
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
		toolbox : {
			show : true,
			feature : {
				/*myTool1: {
	                show: true,
	                title: '打印数据表格视图',
	                icon: 'path://M0,10.014c0,1.985,1.486,3.632,3.438,3.95l0.688,0.055h3.832v-0.993H4.173L3.717,12.99c-1.318-0.21-2.774-1.773-2.774-3.051c0-0.998,0.851-2.598,2.495-3.166L3.107,5.909C3.03,5.743,2.991,5.566,2.991,5.382c0-0.718,0.726-1.785,1.865-1.785c0.391,0,0.849,0.195,1.111,0.472l1.075,0.747l0.914-1.219c1.035-1.543,2.781-2.604,4.75-2.604c2.777,0,5.518,2.647,5.562,5.293v0.938l0.967,0.259c1.09,0.367,1.821,1.354,1.821,2.456c0,1.277-1.308,2.877-2.626,3.087h-0.605h-3.758v0.993h3.806l0.688-0.055C20.514,13.646,22,11.999,22,10.014c0-1.744-1.148-3.229-2.751-3.778C19.191,2.782,16.289,0,12.719,0c-2.575,0-4.802,1.447-5.866,3.55C6.35,3.01,5.622,2.67,4.812,2.67c-1.519,0-2.75,1.196-2.75,2.67c0,0.385,0.084,0.751,0.234,1.081C0.936,7.076,0,8.439,0,10.014z',
	                onclick: function (){
	                	//数据
	                	LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));     
	                	
	                }
	            },*/
				mark : {
					show : true
				},
				dataView : {
					show : true,	//显示表格数据
					readOnly : true,//只读属性
					textareaColor:'#addcb2',
	                optionToContent: function(opt) {
	                    var axisData = opt.xAxis[0].data;
	                    var series = opt.series;
	                    var topmenus='';
	                    for ( var i = 0; i < series.length; i++) {
	                    	topmenus+='<td>' + series[i].name + '</td>'
						}
	                    var table = '<table style="width:100%;text-align:center"><tbody><tr>'
	                                 + '<td>时间</td>'
	                                 + topmenus
	                                 + '</tr>';
	                    for (var i = 0, l = axisData.length; i < l; i++) {
	                    	 var datas='';
	                    	for ( var j = 0; j < series.length; j++) {
		                    	datas+='<td>' + series[j].data[i] + '</td>'
							}
	                        table += '<tr>'
	                                 + '<td>' + axisData[i] + '</td>'
	                                 + datas
	                                 + '</tr>';
	                    }
	                    table += '</tbody></table>';
	                    return table;
	                }
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]	//可以调节柱状图或曲线图
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true	//可以下载成图片 不支持ie8
				}
			}
		},
		calculable : true,
	    xAxis : [{
	            type : 'category',
	            data : []
	        }
	    ],
	    yAxis : [{type : 'value'}],
	    series : []
	};
myChart.setOption(optiony);
//指定图表的配置项和数据-第二个饼图
var myChart1 = echarts.init(document.getElementById('main1'));
option1 = {
	    title : {
	        text: '数据分布图',
	        x:'56%'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient : 'vertical',
	        x : 'left',
	        data:[]
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {
	                show: true, 
	                type: ['pie','funnel'],
	                option: {
	                    funnel: {
	                        x: '25%',
	                        width: '50%',
	                        funnelAlign: 'left',
	                        max: 1548
	                    }
	                }
	            },
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    series : [
	        {
	            name:'数据',
	            type:'pie',
	            radius : '55%',
	            center: ['65%', '60%'],
	            data:[]
	        }
	    ]
	};
myChart1.setOption(option1);
$.ajax({
	url : 'sjtj/CxTJt_Cljl?time=' + new Date().getTime() + '&kssj=' + ks + '&jssj=' + js + '&tjfs=3' + '&txqk=1',
	async : false, // 同步执行
	cache:false,
	dataType : "json", // 服务器响应回来数据类型
	success : function(date) {
		tjlx = date.titlew
		lukou = date.legendw
		tiaoshu = date.map
		xtext = date.beiyong
		lukou1 = date.legends
		
		optiony.legend.data=lukou1;
		optiony.xAxis[0].data=xtext;
		optiony.series=tiaoshu;
		myChart.setOption(optiony,true);
		
		if ("此时间段无数据"!=tiaoshu[0].name) {
			bt=xtext[0];
			var btsj=date.object;
          	btName=date.cp;
          	//btValue=btsj;
          	var mz=btsj.name
          	var zhi=btsj.value
          	var t_data = [];
				for(var i=0; i<mz.length; i++){
					t_data.push({
						"name":mz[i],
						"value":zhi[i]
					});
				}
			btValue=t_data;	
			/*myChart1.setOption({
	   		    legend: {
	   		        data:btName
	   		    },
	   		    series : [{
   		            name:'数据',
   		            type:'pie',
   		            radius : '55%',
   		            center: ['65%', '60%'],
   		            data:btValue
   		        }]
        	})*/
		}
	},
	error : function(date) {
		// alert("1"+date)
		alert("系统发生错误.");
	}
})


//----------------------------------------------------------------------------------------------

    myChart.on('click', function (params) {
    	bt=params.name;
        var zt=$("input[name='txqk']:checked").val(); 
        //var tjfs=$("input[name='tjfs']:checked").val(); 
        //var txdd=$("input[name='tjfs']:checked").val();
        //var hphm=$("input[name='tjfs']:checked").val();
        
        if (zt==1) {
        	$.ajax({
            	url:'sjtj/clickcjjlTb?time='+new Date().getTime()+'&shijian='+encodeURI(encodeURIComponent(params.name))+
            	"&djry="+encodeURI(encodeURIComponent(zt)),
            	async: false, //同步执行
                type:"POST",//提交方式
                data : $("#findDate").serialize(),//序列化表单数据,方便提交
                dataType:"json",    //服务器响应回来数据类型
                success:function(date) {//接收服务器响应的处理函数
                  	nhphm=date.nhphm;
                	var cp=date.cp;
                	var btsj=date.object;
                	btName=cp;
                	btValue=btsj;
                	var mz=btsj.name
                	var zhi=btsj.value
                	var t_data = [];
    				for(var i=0; i<mz.length; i++){
    					t_data.push({
    						"name":mz[i],
    						"value":zhi[i]
    					});
    				}					
                	myChart1.hideLoading();
                	myChart1.setOption({
                		title : {
            		        text: bt+'-数据分布图'
            		    },
            		    tooltip : {
            		        trigger: 'item',
            		        formatter: "{a} <br/>{b} : {c} ({d}%)"
            		    },
            		    legend: {
            		        orient : 'vertical',
            		        x : 'left',
            		        data:cp
            		    },
            		    series : [{
        		            name:'数据',
        		            data:t_data
            		    }]
                    });
                },
                error: function (msg) {
                    alert("系统发生错误");
                }
            });
        	$("#main10").dialog({
            	title:"信息",
    			top:'80px',
            	height:"400px",
    			width:"700px",
                buttons:[{
                    text: "关闭",
                    handler: function () {
                        $("#main10").dialog("close");
                    }
                }]
            });
            $("#main10").dialog('open');
    	}
    });
  //单击 统计 按钮 ------------------------------------------------------------------------------------------
$("#sjtjs").click(function() {
	var zt=$("input[name='txqk']:checked").val(); 
	$.ajax({
		url : 'sjtj/CxTJt_Cljl?time=' + new Date().getTime(),
		async : false, // 同步执行
		cache:false,
		data : $("#findDate").serialize(),//序列化表单数据,方便提交
		type : "POST",//提交方式
		dataType : "json", // 服务器响应回来数据类型
		success : function(date) {
			tjlx = date.titlew;
			djr = date.djry;
			lukou = date.legendw;
			tiaoshu = date.map;
			xtext = date.beiyong;
			lukou1 = date.legends
			//alert(tiaoshu)
			//console.log(tiaoshu)
			//给折线图赋新值
			optiony.legend.data=lukou1;
			optiony.xAxis[0].data=xtext;
			optiony.series=tiaoshu;
			myChart.setOption(optiony,true);
			//给折线图赋新值 end
			if (10==zt) {
				$("#bt_xx").attr("style","width: 1050px;height:240px;display: block;");
				if ("此时间段无数据"!=tiaoshu[0].name && null!=tiaoshu[0].name) {
					bt=xtext[0];
					var btsj=date.object;
		          	btName=date.cp;
		          	//btValue=btsj;
		          	var mz=btsj.name
		          	var zhi=btsj.value
		          	var t_data = [];
					for(var i=0; i<mz.length; i++){
						t_data.push({
							"name":mz[i],
							"value":zhi[i]
						});
					}
					btValue=t_data;	
		        	/*myChart1.setOption({
						title : {
			   		        text: lukou+'-数据分布图',
			   		        x:'center'
			   		    },
			   		    legend: {
			   		        orient : 'vertical',
			   		        x : 'left',
			   		        data:btName
			   		    },
			   		    series : [
			   		        {
			   		            name:'数据',
			   		            type:'pie',
			   		            radius : '55%',
			   		            center: ['65%', '60%'],
			   		            data:btValue
			   		        }
			   		    ]
		        	})*/
		        	
				}
			}else {
				$("#bt_xx").attr("style","display: none;");
				myChart1.setOption({
					title : {
		   		        text: lukou+'-数据分布图',
		   		        x:'56%'
		   		    },
		   		    legend: {
		   		        orient : 'vertical',
		   		        x : 'left',
		   		        data:''
		   		    },
		   		    series : [
		   		        {
		   		            name:'数据',
		   		            type:'pie',
		   		            radius : '55%',
		   		            center: ['65%', '60%'],
		   		            data:[]
		   		        }
		   		    ]
	        	})
			}
		},
		error : function(date) {
			alert("系统发生错误");
		}
	})
})
//单击统计按钮 end---------------------------------------------------------------------------------------    
//单击饼图----------------------------------------------------------------------------------------------
    myChart1.on('click', function (params) {
    	var txdd=$("#xzdd").combobox("getValues");
    	var hp=$("input[name='hp']").val();
    	var hm=$("input[name='hm']").val();
    	var zhi=$("input[name='zhi']").val();
    	/*var hphm='';
    	if (hp!='全部') {
			if (hm!='全部') {
				hphm=zhi;
			}else {
				hphm=hm+zhi;
			}
		}else {
			
		}*/
    	//$("#modal-demo").modal("show");
    	//alert(txdd);
    	var zt=$("input[name='txqk']:checked").val(); 
        var tjfs=$("input[name='tjfs']:checked").val(); 
        $("#main2").dialog({
        	title:"详细信息",
			top:'120px',
        	height:"300px",
			width:"700px",
            buttons:[{
                text: "关闭",
                handler: function () {
                    $("#main2").dialog("close");
                }
            }]
        });
        $("#main2").dialog('open');
		$("#main3").datagrid({
			url:'sjtj/clickcljlBt?time='+new Date().getTime()+'&tbmc='+encodeURI(encodeURIComponent(params.name))+
			'&shijian='+encodeURI(encodeURIComponent(bt))+'&tjfs='+tjfs+"&txzt="+zt+"&txdd="+encodeURI(encodeURIComponent(txdd))+
			"&hp="+encodeURI(encodeURIComponent(hp))+"&hm="+encodeURI(encodeURIComponent(hm))+"&zhi="+encodeURI(encodeURIComponent(zhi)),
			fit : true,
			fitColumns : true,
			pagination : true,
			border : false,
			rownumbers:true ,
			striped:true,//隔行变色
			height:"240px",
			width:"1000px",
			loadMsg:'正在玩命加载，请耐心等待。。。。。。',
			//条的上下
			pagination:true,
			pagePosition:'bottom',
			pageSize : 5,
			pageList : [ 5,10, 20, 30, 40],
			//排序
			checkOnSelect : false,
			selectOnCheck : false,
			columns:[[{
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
				width : 200,
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
				field : 'hpys',
				width : 170,
				title : '号牌颜色',
				sortable : true,
				formatter : function(value) {
					if (null == value) {
						value = '';
					}
					return "<span title='" + value + "'>" + value + "</span>";
				}
			}, {
				field : 'fx',
				width : 150,
				title : '进出口',
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
			}]]
		});
         
     });
//单击饼图  end------------------------------------------------------------------------------------------
    //通行
$("#tj4").click(function() {
	//alert("004");
	//$("#main").attr("class","y1");
	$("#s1").attr("style","border: solid 0px;width: 100%;height:220px");
	//$("#main").addClass('y1')
})
//未通行
$("#tj3").click(function() {
	$("#s1").attr("style","border: solid 0px;width: 100%;height:420px");
	//$("#main").attr("height","420px");
	//$("#main").addClass('y0')
	$("#main").attr("class","y0");
})
//地点 全选 全部选
window.onload = function() {
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
/**
 * 打印查询数据
 */
function doPrint() { 
	LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
	var kd='';
	if (lukou1.length<=7) {
		kd=120;
	}else if(lukou1.length >7 && lukou1.length<=9){
		kd=100;
	}else if(lukou1.length >9 && lukou1.length<=13){
		kd=70;
	}else if(lukou1.length>13 && lukou1.length <= 16){
		kd=40;
	}else if(lukou1.length>16){
		kd=30;
	}
	var table_head=lukou1;
	var table_thead="";
	table_thead="<thead><tr><th style='width:100px;height:35px;'>时间</th>";
	for ( var a = 0; a < table_head.length; a++) {
		table_thead+="<th style='width:"+kd+"px;height:35px;'>"+table_head[a]+"</th>";
	}
	table_thead+="<thead><tr>";
	LODOP.ADD_PRINT_TABLE(5,8,1500,25,"" +"<table border='1' cellpadding='0' style='font-size: 10;'>" +table_thead+"</table>");
	//------------------
	for ( var i = 0; i <= xtext.length-1; i++) {
		var table_head_stuff=[xtext[i]];
		for ( var j = 0; j < tiaoshu.length; j++) {
			table_head_stuff.push(tiaoshu[j].data[i]);
		}
		var table_tbody="";
		table_tbody="<thead><tr>";
		for ( var a = 0; a < table_head_stuff.length; a++) {
			if (a==0) {
				table_tbody+="<td style='width:100px;height:40px;'>"+table_head_stuff[a]+"</td>";
			}else {
				table_tbody+="<td style='width:"+kd+"px;height:40px;'>"+table_head_stuff[a]+"</td>";
			}
		}
		table_tbody+="<thead><tr>";
		
		LODOP.ADD_PRINT_TABLE(40*(i+1),8,1500,25,"" +
				"<table border='1'  cellpadding='0' style='font-size: 10;'>" +table_tbody+
				"</table>");
	}
	if (table_head.length>5) {
		LODOP.SET_PRINT_PAGESIZE(2,0,0,"");
    	LODOP.SET_PREVIEW_WINDOW(0,0,0,0,0,"");	
    	LODOP.SET_SHOW_MODE("LANDSCAPE_DEFROTATED",1);//横向时的正向显示
	}
	LODOP.PREVIEW();
}















