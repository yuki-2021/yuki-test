$(document).ready(function(){
   //首次打开页面自动连接
   connect();
})

//执行连接
function connect() {

    //接入端点/backend
    var socket = new SockJS('/backend');
    window.stompClient = Stomp.over(socket);
    window.stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame)
        log('Connected: ' + frame);

        //订阅服务端输出的 Topic
        stompClient.subscribe('/topic/console', function (message) {
            log("[服务器说]：" + message.body);
        });
    });

}

//断开连接
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    log("Disconnected");
}

//重新连接
function reconnect(){
  clearConsole();
  disconnect();
  connect();
}

//发送消息
function sendMessage(){
    var content = $("#word").val();
    if(!content){
        alert("请输入消息!")
        return;
    }
    //向应用Topic发送消息
    stompClient.send("/app/message", {}, content);
    log("[你说]：" + content);
}

//记录控制台消息
function log(message){
    $("<p></p>").text(message).appendTo($("#console"));
}

//清空控制台
function clearConsole(){
    $("#console").empty();
}