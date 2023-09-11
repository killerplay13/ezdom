// ====================== WebSocket ====================== //
window.addEventListener("load", function() {
    connect();
    // getMember();
})



var messagesArea = document.getElementById("messagesArea");
var webSocket;
//const url = new URLSearchParams(window.location.search);
const url_memberId = 99; // 取得URL中查詢字串coachId的值
var self = url_memberId;
// let receiver = 1;
// if(url_memberId === "2"){receiver = 1;}

let memberDetails;
async function getMember(memId){

    try {
        let response = await fetch("http://localhost:8080/ezdom/faq/member?memberId=" + memId);

        if (response.status === 401) {
            // 重定向到登录页面 登入失敗
            window.location.href = '/ezdom/backendemp/empSignin.html';
        } else if (response.ok) {
            // 登入成功
            memberDetails = await response.json();
        } else {
            alert("錯誤狀態 " + response.status);
        }
    } catch (error) {
        console.error("出现错误: " + error);
    }

//	let response = await fetch("http://localhost:8080/ezdom/frontend/faq/member?memberId=" + memId);
//    memberDetails = await response.json();
}

function connect() {

    // create a websocket
    webSocket = new WebSocket("ws://localhost:8080/ezdom/SupportWS/" + url_memberId);

    webSocket.onopen = function(event) {
        console.log("Connect Success!");
    };

    webSocket.onmessage = function(event) {
        var jsonObj = JSON.parse(event.data);
        if ("open" === jsonObj.type) {
            // console.log(jsonObj);
            refreshFriendList(jsonObj);
            console.log("is open!!")

            setTimeout(function() {
                let onlines = jsonObj.memberIds;
                for(let onlin of onlines){
                    console.log(onlin);
                    refresh(onlin);
                }
            }, 200);

        } else if ("history" === jsonObj.type) {
            let data = JSON.parse(event.data);
            messagesArea.innerHTML = '';

            fetch('http://localhost:8080/ezdom/faq/member?memberId=' + data.receiver)
//            .then(resp => resp.json())
            .then(response => {
                if (response.status === 401) {
                    // 重定向到登录页面 登入失敗
                    window.location.href = '/ezdom/backendemp/empSignin.html';
                }else if(response.ok){
                    //登入成功
                    return response.json();
                }else{
                    alert("錯誤狀態" + response.status);
                    return;
                }
            })
            .then(body => {
                
                // 註冊所有列表中的所有a標籤，並移除點選狀態
                let s = $('a.x');
                for(let x of s){
                    s.removeClass("pe-none bg-gray");
                }
                let link = $(`a[value=${body.memberId}]`);
                link.addClass("pe-none bg-gray");


                // 依照點選的會員，顯示於對話視窗上
                let r_img = document.querySelector("#r_img");
                let r_name = document.querySelector("#r_name");

                r_img.innerHTML = `
                    <img id="img" value="${body.memberId}" class="rounded-circle" src="data:image/jpeg;base64,${body.memberPhoto}" width="48" alt="Avatar"><span class="position-absolute bottom-0 end-0 bg-success border border-white rounded-circle me-1" style="width: .625rem; height: .625rem;"></span>
                `;
                r_name.textContent = body.memberName;
                r_name.setAttribute("value", body.memberId);

                // 依照點選的會員，綁定memberId到送出按鈕上
                let btn = document.querySelector("#sendMessage");
                btn.setAttribute("value", body.memberId);
                let text = document.querySelector("#message");
                text.setAttribute("value", body.memberId);


                // 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
                var messages = JSON.parse(jsonObj.message);
                for (let i = 0; i < messages.length; i++) {
                    let historyData = JSON.parse(messages[i]);
                    let div = document.createElement('div');
                    div.setAttribute("style", "max-width: 392px;");
                    let showMsg = historyData.message;
                    let time = historyData.messageTime;
                    
                    // 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
                    historyData.sender === self ? div.className += 'ms-auto mb-3' : div.className += 'mb-3';
                    if(historyData.sender === url_memberId){
                        div.innerHTML = `
                            <div class="d-flex align-items-end mb-2">
                            <div class="message-box-end text-white"  style="background-color: #1977d6;">${showMsg}</div>
                            <div class="flex-shrink-0 ps-2 ms-1"><img class="rounded-circle" src="../frontendshop/image/FBI.png" width="48" alt="Avatar"></div>
                            </div>
                            <div class="fs-xs text-muted" >${time}</div>
                        `;
                        messagesArea.appendChild(div);
                    }else{
                        div.innerHTML = `
                            <div class="d-flex align-items-end mb-2">
                                <div class="flex-shrink-0 pe-2 me-1"><img class="rounded-circle" src="data:image/jpeg;base64,${body.memberPhoto}" width="48" alt="Avatar"></div>
                                <div class="message-box-start text-dark">${showMsg}</div>
                            </div>
                            <div class="fs-xs text-muted text-end">${time}</div>
                        `;
                        messagesArea.appendChild(div);
                    }
                    // test
                    let p_ms = document.querySelector(`#p_${body.memberId}`);
                    p_ms.textContent = showMsg;
                    let s_time = document.querySelector(`#s_${body.memberId}`);
                    s_time.textContent = time;
                }

                messagesArea.scrollTop = messagesArea.scrollHeight;
            })

        } else if ("chat" === jsonObj.type) {

            console.log(jsonObj);
            // 如果對話的成員中，不是當下訊息視窗的成員，則不顯示
            let r_name = document.querySelector("#r_name");
            let id = r_name.getAttribute("value");

            if(jsonObj.sender !== id && jsonObj.receiver !== id){
                if(jsonObj.sender === url_memberId){
                    refresh(jsonObj.receiver);
                }else{
                    refresh(jsonObj.sender);
                }
                return;
            }

            let div = document.createElement('div');
            jsonObj.sender === self ? div.className += 'ms-auto mb-3' : div.className += 'mb-3';
            div.setAttribute("style", "max-width: 392px;");

            let img = document.querySelector("#img");
            let src = img.getAttribute("src");
            if(jsonObj.sender === url_memberId){
                div.innerHTML = `
                    <div class="d-flex align-items-end mb-2">
                    <div class="message-box-end text-white" style="background-color: #1977d6;">${jsonObj.message}</div>
                    <div class="flex-shrink-0 ps-2 ms-1"><img class="rounded-circle" src="../frontendshop/image/FBI.png" width="48" alt="Avatar"></div>
                    </div>
                    <div class="fs-xs text-muted">${jsonObj.messageTime}</div>
                `;
            }else{
                div.innerHTML = `
                    <div class="d-flex align-items-end mb-2">
                        <div class="flex-shrink-0 pe-2 me-1"><img class="rounded-circle" src="${src}" width="48" alt="Avatar"></div>
                        <div class="message-box-start text-dark">${jsonObj.message}</div>
                    </div>
                    <div class="fs-xs text-muted text-end">${jsonObj.messageTime}</div>
                `;
            }
            messagesArea.appendChild(div);
            messagesArea.scrollTop = messagesArea.scrollHeight;

            // test
            addListener(id);


        } else if ("close" === jsonObj.type) {
            refreshFriendList(jsonObj);
            let onlines = jsonObj.memberIds;

            setTimeout(function() {
                for(let onlin of onlines){
                    console.log(onlin);
                    refresh(onlin);
                }
            }, 50);
        } else if ("refresh" === jsonObj.type){
            if(jsonObj.receiver === url_memberId){return;}
            var messages = JSON.parse(jsonObj.message);
            let p_ms = document.querySelector(`#p_${jsonObj.receiver}`);
            let s_time = document.querySelector(`#s_${jsonObj.receiver}`);
            let mes = JSON.parse(messages[messages.length - 1]).message;
            let time = JSON.parse(messages[messages.length - 1]).messageTime;
            p_ms.textContent = mes;
            s_time.textContent = time;
        }
        
    };

    webSocket.onclose = function(event) {
        console.log("Disconnected!");
    };
}

function sendMessage(receiver) {
    // var moment = require('moment');
    let messageTime = moment(new Date()).format('YYYY-MM-DD HH:mm');
    var inputMessage = document.getElementById("message");
    // var friend = statusOutput.textContent;
    var message = inputMessage.value.trim();

    if (message === "") {
        // alert("Input a message");
        inputMessage.focus();
    // } else if (friend === "") {
    //     alert("Choose a friend");
    } else {
        var jsonObj = {
            "type" : "chat",
            "sender" : url_memberId,
            "receiver" : receiver,
            "message" : message,
            "messageTime" : messageTime
        };
        webSocket.send(JSON.stringify(jsonObj));
        inputMessage.value = "";
        inputMessage.focus();
        console.log(JSON.stringify(jsonObj));
    }
}

// 有好友上線或離線就更新列表
function refreshFriendList(jsonObj) {
    console.log(jsonObj);
    var friends = jsonObj.memberIds;
    var list = document.getElementById("list");
    list.innerHTML = '';
    for (var i = 0; i < friends.length; i++) {
        // console.log(friends[i]);
        let num = friends[i];
        // console.log(num);
        if (friends[i] === self) { continue; }

        fetch('http://localhost:8080/ezdom/faq/member?memberId=' + num)
//            .then(resp => resp.json())
            .then(response => {
                if (response.status === 401) {
                    // 重定向到登录页面 登入失敗
                    window.location.href = '/ezdom/backendemp/empSignin.html';
                }else if(response.ok){
                    //登入成功
                    return response.json();
                }else{
                    alert("錯誤狀態" + response.status);
                    return;
                }
            })
            .then(body => {
                console.log(body);
                let a = document.createElement("a");
                a.setAttribute("value", num);
                a.setAttribute("class", "d-flex align-items-center text-decoration-none px-4 py-3 x");
                a.setAttribute("style", "cursor: pointer");
                a.addEventListener("click", function() {
                    addListener(num);
                });
                a.innerHTML = `
                    <div class="position-relative flex-shrink-0 my-1"><img class="rounded-circle" src="data:image/jpeg;base64,${body.memberPhoto}" width="48" alt="Avatar"><span class="position-absolute bottom-0 end-0 bg-success border border-white rounded-circle me-1" style="width: .625rem; height: .625rem;"></span></div>
                    <div class="d-flex justify-content-between w-100 ps-2 ms-1 my-1">
                    <div class="me-3">
                        <div class="h6 mb-1">${body.memberName}</div>
                        <p id="p_${num}" style="margin:0;"></p>
                        <div class="text-end"><span id="s_${num}" class="d-block fs-xs text-muted"></span></div>
                    </div>
                    </div>
                `;
                list.appendChild(a);
            })        
    }
}

// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
function addListener(receiver) {
    // console.log(this.getAttribute('value'));
    var jsonObj = {
            "type" : "history",
            "sender" : url_memberId,
            "receiver" : receiver,
            "message" : ""
        };
    
    webSocket.send(JSON.stringify(jsonObj));
    // console.log(JSON.stringify(jsonObj));

}

// 刷新列表的訊息與時間
function refresh(receiver) {
    // console.log(this.getAttribute('value'));
    var jsonObj = {
            "type" : "refresh",
            "sender" : url_memberId,
            "receiver" : receiver,
            "message" : ""
        };
    
    webSocket.send(JSON.stringify(jsonObj));
    console.log(JSON.stringify(jsonObj));

}

function disconnect() {
    webSocket.close();
    // document.getElementById('sendMessage').disabled = true;
    // document.getElementById('connect').disabled = false;
    // document.getElementById('disconnect').disabled = true;
}


$("#sendMessage").on("click", function() {
    let who = this.getAttribute("value");
    sendMessage(who);
});

$("#message").on("keydown", function(event) {

    let who = this.getAttribute("value");
    if (event.keyCode == 13){
        event.preventDefault();
        sendMessage(who);
    }
})