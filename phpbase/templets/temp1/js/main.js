/**
 * 系统配置
 */
var sysconfig = {
	debug:true,
};
/**
 * 公共常量
 */
var CONSTANTS = {
		/**
		 * 路径
		 */
		PATH:{
			BASE_URL:"",
		},
		/**
		 * 颜色
		 */
		COLOR:{
			RED:"#FF0000",
		}
}
/**
 * 公共方法对象
 */
var sys = {
	/**
	 * 弹出的iframe框layer关闭
	 */
	frameDialogClose:function(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭   
	},
	/**
	 * 提示消息
	 * @param d 例如{code:0,msg:"成功",url:"跳转连接",func:成功后执行方法}
	 */
	msg:function(d){
		if(d.code == 0){
			layer.msg(d.msg,{
			    time: 500
			}, function(){
				var url = d.url;
				var func = d.func;
				if(url!=null){
					loadHtml(url);
				}
				if(func!=null){
					func(d);
				}
			});
		}else if(d.code!=0){
			layer.msg(d.msg,{
			    time: 1000
			});
		}
	},
	/**
	 * 顶部弹出错误提示
	 * @param msg
	 */
	topmsg:function(msg){
		layer.msg(msg,{offset: 't',time:1000,anim:6});
	},
	/**
	 * 确认窗口
	 * @param msg 提示内容
	 * @param func 确认后执行的方法
	 * @param param 需要的参数
	 */
	confirm:function(msg,func,param){
		layer.confirm(msg, {icon: 3, title:'提示'}, function(index){
			layer.close(index);
			func(param);
		});
	},
	/**
	 * 弹出窗口
	 * @param icon 图标图片路径
	 * @param title 标题
	 * @param url 弹出文件地址
	 * @param width 宽度
	 * @param heigth 高度
	 */
	window:function(icon,title,url,width,heigth){
		var t = title;
		if(icon!=null){
			t = '<img src="'+icon+'" width="20"/>'+title;
		}
		layer.open({
			  type: 2,
			  title: t,
			  maxmin: true,
			  shadeClose: true, //点击遮罩关闭层
			  area : [width+"" , heigth+""],
			  content: url
		});
	},
	/**
	 * 跳转到页面
	 * @param url 目标地址
	 */
	go2page:function(url){
			/*if(url.indexOf("http")>=0){
				location.href=url;
			}else{
				location.href=$("base").attr("href")+url;
			}*/
			location.href=url;
	},
	/**
	 * 获取数据
	 * @param url 连接
	 * @param param 参数
	 * @param callback 回调方法
	 */
	getApiData:function (url,param,callback){
		var index = layer.load();
		$.ajax({
			type: "GET",
	        url:url,
	        data:param,
	        async: true,
	        error: function(request) {
	        	layer.close(index);
	        },
	        success: function(data) {
	        	layer.close(index);
	        	if(sysconfig.debug){
	        		console.log(data);
	        	}
	            callback(data);
	        }
		});
	},
	/**
	 * 获取浏览器地址栏参数
	 * @param name
	 * @returns
	 */
	GetQueryString:function (name)
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var search=decodeURI(window.location.search); //编码传输
	     var r = search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	},
	/**
	 * 获取 比特格式化后的大小数据
	 * @param bt
	 * @returns {String}
	 */
	filesize:function (bt){
		if(bt/1024/1024/1024>1){
			return (bt/1024/1024/1024).toFixed(2)+"GB";
		}else if(bt/1024/1024>1){
			return (bt/1024/1024).toFixed(2)+"MB";
		}else if(bt/1024>1){
			return (bt/1024).toFixed(2)+"KB";
		}else {
			return bt+"B";
		}
	},
	
}
	