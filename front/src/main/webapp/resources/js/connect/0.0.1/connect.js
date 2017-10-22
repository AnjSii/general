// XMPP服务器BOSH地址
var BOSH_SERVICE = 'http://192.168.0.187:5280';

// XMPP连接
var connection = null;

// 当前状态是否连接
var connected = false;

// 当前登录的JID
var jid = "";

var senderUserName = "";

var receiverUserNname = "";

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
        alert("连接成功，可以开始聊天了！");
        connected = true;

        // 当接收到<message>节，调用onMessage回调函数
        connection.addHandler(onMessage, null, 'message', null, null, null);

        // 首先要发送一个<presence>给服务器（initial presence）
        connection.send($pres().tree());
    }
}

function inMessage() {
    if(connected) {
        // 创建一个<message>元素并发送
        var text = $("#area1").val();
        var msg = $msg({
            to: senderUserName,
            from: senderUserName,
            type: 'chat'
        }).c("body", null, text);
        connection.send(msg.tree());

        var date = new Date();

        //聊天框显示
        var message = '<dl class="me m1"><dt>'
            + '<a href="#"><img src="$!web.contextPath/resources/images/chat7.png" alt=""/></a>'
            + '<ul><li>' + jQuery("#senderUserName").val() + '<span>'
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
    var from = msg.getAttribute('from');
    var type = msg.getAttribute('type');
    var elems = msg.getElementsByTagName('body');

    if (type == "chat" && elems.length > 0) {
        var body = elems[0];
        $("#msg").append(from + ":<br>" + Strophe.getText(body) + "<br>")
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

    senderUserName = jQuery("#senderUserName").val() + "@192.168.0.187";
    receiverUserNname = jQuery("#receiverUserNname").val() + "@192.168.0.187";

    // 通过BOSH连接XMPP服务器
    if(!connected) {
        connection = new Strophe.Connection(BOSH_SERVICE);
        connection.connect(senderUserName , "", onConnect);
        jid = senderUserName;
    }

    // 发送消息
    /*$("#btn-send").click(function() {
        if(connected) {
            // 创建一个<message>元素并发送
            var msg = $msg({
                to: receiverUserNname,
                from: senderUserName,
                type: 'chat'
            }).c("body", null, $("#area1").val());
            connection.send(msg.tree());

            var date = new Date();

            //聊天框显示
            var message = '<dl class="me m1"><dt>'
                + '<a href="#"><img src="$!web.contextPath/resources/images/chat7.png" alt=""/></a>'
                + '<ul><li>$!senderUserNname<span>'
                +  date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + '</span></li></ul></dt><dd>'
                + escape(text) +'</dd></dl>';

            $("#msg").append(message);
            $("#input-msg").val('');
        } else {
            alert("请先登录！");
        }
    });*/
});