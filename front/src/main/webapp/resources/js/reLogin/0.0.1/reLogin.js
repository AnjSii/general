var polling;


//生成二维码的方法，getRQCodeUrl 获取二维码的url地址，LongConnectionCheckUrl ajax轮询调用的url地址
function getRQCode(getRQCodeUrl, LongConnectionCheckUrl) {
	console.log(window);
	jQuery.ajax({
		type: 'POST',
		url: getRQCodeUrl,
		async: false,
		success: function(data) {
			//返回的是json
			var obj = JSON.parse(data);
			//取到imgBase64改变id为rqImg的<img>标签
			jQuery("#rqImg").attr("src", "data:image/jpeg;base64," + obj.imgBase64);
			//开始轮询
			timerPolling(getRQCodeUrl, LongConnectionCheckUrl, obj.sid);
		}
	});
}

//轮询方法
function timerPolling(getRQCodeUrl, LongConnectionCheckUrl, sid) {
	//使用定时器轮询，每秒轮询一次
	polling = setInterval(function(){LongConnectionCheck(getRQCodeUrl, LongConnectionCheckUrl, sid)}, 1000);
}

//轮询检查的方法，做长连接用
function LongConnectionCheck(getRQCodeUrl, LongConnectionCheckUrl, sid) {
	jQuery.ajax({
		type: 'GET',
		url: LongConnectionCheckUrl,
		async: false,
		data: { "sid": sid },
		success: function(data) {
			var obj = JSON.parse(data);
			//判断返回的json里面是否有username，有就登录成功，反之
			if (obj.userName != "" && obj.userName != undefined) {
				alert("登录成功，当前用户名为" + obj.userName);
				clearInterval(polling);
			}
			//二维码失效的时候可取得Invalid字段，有进入if语句，点击确认刷新二维码，重新轮询
			if (obj.Invalid != "" && obj.Invalid != undefined) {
				if (confirm(obj.Invalid + ",点击确认刷新二维码")) {
					clearInterval(polling);
					getRQCode(getRQCodeUrl, LongConnectionCheckUrl);
				}
			}
		}
	})
}