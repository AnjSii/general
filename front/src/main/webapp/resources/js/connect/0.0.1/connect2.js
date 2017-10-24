// XMPP服务器BOSH地址
var BOSH_SERVICE = 'http://im.hicailiao.com:5280';

var HOST = window.location.host;

var URI = HOST + window.location.pathname;

// XMPP连接
var connection = null;

// 当前状态是否连接
var connected = false;

// 当前登录的JID
var jid = "";

var senderUserName = "";

var receiverUserName = "";

// 连接状态改变的事件
function onConnect(status) {
	console.log(status);
	if (status == Strophe.Status.CONNFAIL) {
		alert("连接失败！");
	} else if (status == Strophe.Status.AUTHFAIL) {
		alert("登录失败！");
	} else if (status == Strophe.Status.DISCONNECTED) {
		alert("连接断开！");
		connected = false;
	} else if (status == Strophe.Status.CONNECTED) {
		connected = true;

		// 当接收到<message>节，调用onMessage回调函数
		connection.addHandler(onMessage, null, 'message', null, null, null);

		// 首先要发送一个<presence>给服务器（initial presence）
		connection.send($pres().tree());
		messageRecord();
	}
}

/*获取指定用户（chatWith）的历史聊天记录*/
function messageRecord() {
	if (connected) {
		//alert("this is my chatting message record");
		var iq = $iq({
			type: 'get',
			id: 'query'
		}).c('retrieve', {xmlns: 'urn:xmpp:archive', with: receiverUserName});
		connection.sendIQ(iq, getMessageRecord);
	} else {
		alert("请先登录");
	}
}

/*获取历史聊天记录回调函数*/
function getMessageRecord(iq){
	var xmlxx = new XMLSerializer();
	var chatRecordXml  = xmlxx.serializeToString(iq);
	jQuery.get(
			"http://" + URI + "chatting_content_ajax.htm", {
				"chatRecordXml": chatRecordXml,
				"chatCurrentName": "wuxun",
				"chatWithName": "hicailiao"
			},
			function(data) {
				jQuery("#msg").children().remove();
				jQuery("#msg").append(data);
			},
			"text");
}

function inMessage() {
	if(connected) {
		// 创建一个<message>元素并发送
		var text = $("#area1").val();
		var msg = $msg({
			to: receiverUserName,
			from: senderUserName,
			type: 'chat'
		}).c("body", null, text);
		connection.send(msg.tree());

		var date = new Date();

		//聊天框显示
		var message = '<dl class="me m1"><dt>'
				+ '<a href="#"><img src="/resources/images/chat7.png" alt=""/></a>'
				+ '<ul><li>' + senderUserName.split("@")[0]+ '<span>'
				+  date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + '</span></li></ul></dt><dd>'
				+ escape(text) +'</dd></dl>';

		$("#msg").append(message);
		$("#input-msg").val('');
	} else {
		alert("请先登！");
	}
}

// 接收到<message>
function onMessage(msg) {

	// 解析出<message>的from、type属性，以及body子元素
	var type = msg.getAttribute('type');
	var elems = msg.getElementsByTagName('body');
	var date = new Date();

	if (type == "chat" && elems.length > 0) {
		var body = elems[0];
		var message_seller='<dl class="vhe m1"><dt>'
				+ '<a href="#"><img src="/resources/images/chat4.png" alt=""/></a>'
				+ '<ul><li>'+ receiverUserName.split("@")[0] + '<span>'
				+  date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + '</span></li></ul></dt><dd>'
				+ escape(Strophe.getText(body)) +'</dd></dl>';
		$("#msg").append(message_seller);
	}
	return true;
}

function escape(text) {
	text = text.replace(/&/g, '&amp;');
	text = text.replace(/</g, '&lt;');
	text = text.replace(/>/g, '&gt;');
	text = text.replace(/"/g, '&quot;');
	text = text.replace(/'/g, '&#039;');
	return text;
}

$(document).ready(function() {

	senderUserName = jQuery("#senderUserName").val() + "@im.hicailiao.com";
	receiverUserName = jQuery("#receiverUserName").val() + "@im.hicailiao.com";

	// 通过BOSH连接XMPP服务器
	if(!connected) {
		connection = new Strophe.Connection(BOSH_SERVICE);
		connection.connect(senderUserName , "", onConnect);
		jid = senderUserName;
	}
});