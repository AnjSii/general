// 域名
var DOMAIN = "localhost";

// XMPP服务器BOSH地址
var BOSH_SERVICE = 'http://' + DOMAIN + ':5280';

var HOST = window.location.host;

var URI = HOST + "/" + window.location.pathname.split("/")[1];

// XMPP连接
var connection = null;

// 当前状态是否连接
var connected = false;

// 当前登录的JID
var jid = "";

//发送者
var senderUserName = "";

//接收者
var receiverUserName = "";

//总消息数
var totalCount;

//分页索引
var index;

//查看更多索引
var checkMoreIndex;

//查看更多点击次数
var checkMoreCount = 0;

// 连接状态改变的事件
function onConnect(status) {
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
		msgCount();
		messageUser();
	}
}

/*获取当前用户与谁聊过天*/
function messageUser() {
	if (connected) {
		var iq = $iq({
			type: 'get',
			id: 'query1'
		}).c('list', {xmlns: 'urn:xmpp:archive'});
		connection.sendIQ(iq, getMessageUser);
	} else {
		alert("请先登录");
	}
}

/*获取与谁聊过天回调函数*/
function getMessageUser(iq){
	var xmlxx = new XMLSerializer();
	var users = xmlxx.serializeToString(iq);
	jQuery.get(
			"http://" + URI + "/chatting_users_ajax.htm", {
				"users":users,
				"currentreceiver":receiverUserName.split("@")[0]
			}, function(data) {
				jQuery("#chat_left_content").append(data);
			},"text");
}

/*获取聊天记录分页*/
function historyMsg(count) {
	if (connected) {
		switch (count) {
			default:
                showOrHiden();
				index = totalCount;
				break;
			case "front":
				index = 10;
				break;
			case "Previous":
				if (index - 10 >= 0) {
					index = index - 10;
				} else {
					return false;
				}
				break;
			case "next":
				index = index + 10;
				if (index > totalCount) {
					index = totalCount;
				}
				break;
			case "back":
				index = totalCount;
				break;
		}
		var iq = $iq({
			type: 'get',
			id: generateMixed(6)
		}).c('retrieve', {xmlns: 'urn:xmpp:archive', with: receiverUserName})
				.c("set", {xmlns: 'http://jabber.org/protocol/rsm'})
				.c("before", null, index)
				.c("max", null, 10)
				.up().up().c("max", null, 10);
		connection.sendIQ(iq, getHistoryMsg);
	} else {
		alert("请先登录");
	}
}

/*聊天记录分页回调函数*/
function getHistoryMsg(iq) {
	var startTime =  Date.parse(new Date(iq.childNodes[0].getAttribute("start")));
	var msg = iq.childNodes[0].childNodes;
	jQuery("#historyMessage_text").children().remove();
	jQuery(msg).each(function() {
		var className;
		var userName;
		var msgText;
		if (this.nodeName === "from") {
			className = "receiverHistory_name";
			userName = receiverUserName.split("@")[0];
		} else if (this.nodeName === "to") {
			className = "senderHistory_name";
			userName = senderUserName.split("@")[0];
		}
		if (this.nodeName !== "set") {
			var time = startTime + parseInt(this.getAttribute("secs")) * 1000;
			var newDate = new Date();
			newDate.setTime(time);
			jQuery(this.childNodes).each(function() {
				if (this.nodeName === "body") {
					msgText = this.innerHTML;
				}
			});
			var text = '<span class="' + className + '"><span class="history_name">' + userName + '</span><span class="history_time">' + newDate.toLocaleString()
					+ '</span></span><div class="history_msg">' + msgText + '</div>';
			jQuery("#historyMessage_text").append(text);
		}
		jQuery("#historyMessage_text")[0].scrollTop = jQuery("#historyMessage_text")[0].scrollHeight;
	});
}

function showOrHiden() {
    if($('#historyMessage').is(':hidden')){
        jQuery(".chat_body").css({"width":"1232px"});
        jQuery(".chat_top").css({"width":"983px"});
        $('#historyMessage').show();
    }else{
        jQuery(".chat_body").css({"width":"1094px"});
        jQuery(".chat_top").css({"width":"844px"});
        $('#historyMessage').hide();
    }
}

/*点击查看更多*/
function checkMore() {
    if (connected) {
		checkMoreCount++;
		if (checkMoreIndex - 10 >= 0 && checkMoreCount <= 5) {
			checkMoreIndex = checkMoreIndex - 10;
		} else {
			return false;
		}
		var iq = $iq({
			type: 'get',
			id: generateMixed(6)
		}).c('retrieve', {xmlns: 'urn:xmpp:archive', with: receiverUserName})
			.c("set", {xmlns: 'http://jabber.org/protocol/rsm'})
			.c("before", null, checkMoreIndex)
			.c("max", null, 10)
			.up().up().c("max", null, 10);
		connection.sendIQ(iq, getCheckMore);
	} else {
		alert("请先登录");
	}
}

/*点击查看更多回调函数*/
function getCheckMore(iq) {
	var xmlxx = new XMLSerializer();
	var chatRecordXml  = xmlxx.serializeToString(iq);
	jQuery.ajax({type:'POST',
		url: "http://" + URI + "/chatting_content_ajax.htm",
		data: {"chatRecordXml": chatRecordXml,
			"chatCurrentName": senderUserName.split("@")[0],
			"chatWithName": receiverUserName.split("@")[0]
		},
		success:function(html) {
			var height = jQuery("#msg")[0].scrollHeight;
			jQuery("#msg").prepend(html);
			if (checkMoreCount === 5 && totalCount > 50) {
				jQuery("#checkMore").html("点击打开聊天记录，查看更多消息");
				jQuery("#checkMore").attr("onclick", "historyMsg()");
			} else if (checkMoreCount !== 5 && checkMoreIndex <= 10 && totalCount <= 50) {
				jQuery("#checkMore").html("");
			}
			jQuery("#checkMore").insertBefore(jQuery(jQuery(".m1")[0]));
			jQuery("#msg")[0].scrollTop = jQuery("#msg")[0].scrollHeight - height - 200;
		}
	});
}

/*获取聊天记录的条数*/
function msgCount() {
	if (connected) {
		var iq = $iq({
			type: 'get',
			id: 'query12'
		}).c('retrieve', {xmlns: 'urn:xmpp:archive', with: receiverUserName})
				.c("set", {xmlns: 'http://jabber.org/protocol/rsm'}).c("max", null, 1);
		connection.sendIQ(iq, getMsgCount);
	}
}

/*获取聊天记录的条数回调函数*/
function getMsgCount(iq) {
	totalCount = iq.children[0].lastChild.children[2].innerHTML;
    checkMoreIndex = totalCount;
	messageRecord(totalCount);
}

/*获取指定用户（chatWith）的历史聊天记录*/
function messageRecord() {
	if (connected) {
		var iq = $iq({
			type: 'get',
			id: 'query'
		}).c('retrieve', {xmlns: 'urn:xmpp:archive', with: receiverUserName})
				.c("set", {xmlns: 'http://jabber.org/protocol/rsm'})
				.c("before", null, totalCount)
				.c("max", null, 10);
		connection.sendIQ(iq, getMessageRecord);
	} else {
		alert("请先登录");
	}
}

/*获取历史聊天记录回调函数*/
function getMessageRecord(iq) {
	var xmlxx = new XMLSerializer();
	var chatRecordXml  = xmlxx.serializeToString(iq);
	jQuery.ajax({type:'POST',
		url: "http://" + URI + "/chatting_content_ajax.htm",
		data: {"chatRecordXml": chatRecordXml,
			"chatCurrentName": senderUserName.split("@")[0],
			"chatWithName": receiverUserName.split("@")[0]
		},
		success:function(html) {
			jQuery("#msg").children().remove();
			jQuery("#msg").append(html);
			jQuery("#msg")[0].scrollTop = jQuery("#msg")[0].scrollHeight;
			//显示查看更多
			if (totalCount > 10) {
				var checkMore = '<div style="color: blue;cursor: pointer;text-align: center;margin-bottom: 8px;" id="checkMore" onclick="checkMore()">点击查看更多</div>';
				jQuery("#msg").prepend(checkMore);
			}
		}
	});
}

var chars = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];

function generateMixed(n) {
	var res = "";
	for(var i = 0; i < n ; i ++) {
		var id = Math.ceil(Math.random()*35);
		res += chars[id];
	}
	return res;
}

function inMessage() {
	if(connected) {
		// 创建一个<message>元素并发送
		var text = $("#area1").val();
		var msg = $msg({
			to: receiverUserName,
			from: senderUserName,
			type: 'chat'
		}).c("received", {xmlns:'urn:xmpp:receipts'}).up().c("body", null, text);
		connection.send(msg.tree());

		var date = new Date();

		var chatDate = '<p style="text-align: center;" id="currentTime">' + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + '</p>';

		//聊天框显示
		var message = '<dl class="me m1"><dt>'
				+ '<a href="#"><img src="http://localhost:8088/general-front/resources/images/chat7.png" alt=""/></a>'
				+ '<ul><li>' + senderUserName.split("@")[0]+ '<span>'
				+ date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + '</span></li></ul></dt><dd style="right: 39px;">'
				+ escape(text) +'</dd></dl>';
		if ($("#currentTime")[0] === undefined) {
			$("#msg").append(chatDate);
		}
		$("#msg").append(message);
		$("#input-msg").val('');
	} else {
		alert("请先登录！");
	}
}

// 接收到<message>
function onMessage(msg) {

	// 解析出<message>的from、type属性，以及body子元素
	var type = msg.getAttribute('type');
	var from = msg.getAttribute('from');
	var elems = msg.getElementsByTagName('body');
	var date = new Date();

	var user = from.split("/")[0];
	var user1 = from.split('@')[0];

	if (type === "chat" && elems.length > 0 && user === receiverUserName) {
		var body = elems[0];

		var chatDate = '<p style="text-align: center;" id="currentTime">' + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + '</p>';

		var message_seller='<dl class="vhe m1"><dt>'
				+ '<a href="#"><img src="http://localhost:8088/general-front/resources/images/chat4.png" alt=""/></a>'
				+ '<ul><li>'+ receiverUserName.split("@")[0] + '<span>'
				+  date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + '</span></li></ul></dt><dd>'
				+ escape(Strophe.getText(body)) +'</dd></dl>';

		if ($("#currentTime")[0] === undefined) {
			$("#msg").append(chatDate);
		}
		$("#msg").append(message_seller);
		$("#msg")[0].scrollTop = $("#msg")[0].scrollHeight;
	} else if (elems.length > 0 && user !== receiverUserName) {
		var count = jQuery(jQuery('dl[chat_id]')[jQuery('dl[chat_id]').length - 1]).attr("chat_id");
		var newUser = '<dl id="' + user1 + '" chat_id="tabcon' + count + '" class="" onclick="switchTab(this)">'
				+ '<dt><img src="http://localhost:8088/general-front/resources/images/upfile/chat_buyer.png" alt=""/></dt><dd><p>'
				+ '<strong>' + user1 + '</strong><span>15:20</span>'
				+ '<span style="display: none;" id="storeName">' + user + '</span></p></dd><p></p></dl>';

		jQuery("#chat_left_content").append(newUser);
		jQuery('#' + user1).addClass('tabmsg');
	} else if (elems.length > 0) {
		jQuery('#' + user1).addClass('tabmsg');
	}
	return true;
}

function switchTab(obj) {
	jQuery(".chat_body").css({"width":"1094px"});
	jQuery(".chat_top").css({"width":"844px"});
	$('#historyMessage').hide();
	jQuery(obj).siblings().removeClass("tabon");
	$(obj).removeClass("tabmsg");
	$(obj).addClass("tabon");
	receiverUserName = obj.id + "@" + DOMAIN;
	checkMoreCount = 0;
	msgCount();
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
	senderUserName = jQuery("#senderUserName").val() + "@" + DOMAIN;
	receiverUserName = jQuery("#receiverUserName").val() + "@" + DOMAIN;

	// 通过BOSH连接XMPP服务器
	if(!connected) {
		connection = new Strophe.Connection(BOSH_SERVICE);
		connection.connect(senderUserName, "sid=BE5F567C85216D7841B1CDF3C378833D", onConnect);
		jid = senderUserName;
	}

	jQuery("#page_Set button").click(function(){
		historyMsg(jQuery(this).attr("quantity"));
	});
});