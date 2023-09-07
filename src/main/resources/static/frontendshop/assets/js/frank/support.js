// ====================== 宣告變數 ====================== //
let faqList;
const div_faq = document.querySelector("#faq");
const req = 'http://localhost:8080/ezdom/frontend/faq/list';

// ====================== 網頁載入完成後執行 ====================== //
window.addEventListener("load", function() {
    getFaqList();

    // 獲取所有的折疊按鈕
    let accordionButtons = document.querySelectorAll(".accordion-button");

    // 註冊所有按鈕的click事件
    accordionButtons.forEach(function(button) {
        
        button.addEventListener("click", function() {
            // 獲取按鈕上的 data-bs-target 屬性值，即要展開/收合的面板的 ID
            let target = button.getAttribute("data-bs-target");

            // 使用 Bootstrap 提供的 collapse 方法展開/收合面板
            let collapse = new bootstrap.Collapse(target);
        });
    });

})

async function getFaqList() {
    let response = await fetch(req);
    faqList = await response.json();
    console.log(faqList);
    // 顯示faq列表
    showFaqList();
}

function showFaqList() {
    let btn = 1;

    for(let faq of faqList){
        let div = document.createElement("div");
        div.setAttribute("class", "accordion-item bg-light");
        div.innerHTML = `
            <h3 class="accordion-header" id="headingOne">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#btn${btn}" aria-expanded="fales" aria-controls="questionOne">${faq.faqName}</button>
            </h3>
            <div class="accordion-collapse collapse" id="btn${btn++}" aria-labelledby="headingOne" data-bs-parent="#faq">
                <div class="accordion-body fs-sm">${faq.faqAns}</div>
            </div>
        `;
    
        div_faq.appendChild(div);
    }

}



// ====================== 即時客服 ====================== //

// 註冊網頁所有元素的點擊事件
document.addEventListener('click', function(event) {
    // 問號按鈕 
    let support = document.getElementById("sup");
    let btn_sup = support.style.display;
    
    // 訊息視窗
    let supmes = document.getElementById("supm");
    let mes_sup = supmes.style.display;

    // 檢查點擊的元素是否位於即時客服的視窗範圍內，如果不是就隱藏視窗並顯示按鈕
    if (!supmes.contains(event.target)) {

        if(btn_sup === "none"){
            support.style.display = "block";
        }

        if(mes_sup === "block"){
            supmes.style.display = "none";
        }

    
    }

    // 檢查點擊的元素是否位於問號按鈕的範圍內，如果是就顯示視窗並隱藏按鈕
    if(support.contains(event.target)) {

        if(mes_sup === "none"){
            supmes.style.display = "block";
          }
        if(btn_sup === "block"){
            support.style.display = "none";
        }
    }
});

// 當即時客服的視窗被點擊時，隱藏視窗並顯示按鈕
$("#window").on("click", function() {
    // 問號按鈕 
    let support = document.getElementById("sup");
    let btn_sup = support.style.display;
    
    // 訊息視窗
    let supmes = document.getElementById("supm");
    let mes_sup = supmes.style.display;
  
    if(mes_sup === "block"){
      supmes.style.display = "none";
    }
    
    if(btn_sup === "none"){
        support.style.display = "block";
    }
})



const url = new URLSearchParams(window.location.search);
const url_memberId = url.get('memberId'); // 取得URL中查詢字串coachId的值
// 创建 WebSocket 连接，将 WebSocket 地址替换为你的服务器地址
const socket = new WebSocket("ws://localhost:8080/ezdom/frontend/SupportWS/" + url_memberId);

// 当连接打开时
socket.addEventListener("open", (event) => {
  console.log("WebSocket 连接已打开");
});

// 当接收到消息时
socket.addEventListener("message", (event) => {
  const message = JSON.parse(event.data);
  console.log("收到消息：", message);
});

// 当连接关闭时
socket.addEventListener("close", (event) => {
  if (event.wasClean) {
    console.log(`连接已关闭，状态码：${event.code}，原因：${event.reason}`);
  } else {
    console.error("连接意外关闭");
  }
});

// 当发生错误时
socket.addEventListener("error", (error) => {
  console.error("WebSocket 错误：", error);
});

// 向服务器发送消息
function sendMessage() {
    var message = {
        "type" : "chat",
        "sender" : "aaa",
        "receiver" : "bbb",
        "message" : "test",
        "messageTime" : "2023-09-05"
    };
    console.log(message);
  socket.send(JSON.stringify(message));
}

// 关闭连接
function closeWebSocket() {
  socket.close();
}


/*
// ====================== WebSocket ====================== //
// 模擬登入中的memberId
let memberId = 2;

var MyPoint = "/SupportWS/" + memberId;
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
// var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
var endPointURL = "ws://localhost:8080/ezdom" + MyPoint;
ws://localhost:8081/WebSocketChatWeb/FriendWS/gggggggggg

// var statusOutput = document.getElementById("statusOutput");
// var messagesBody = document.getElementById("messagesBody");
var messagesArea = document.getElementById("messagesArea");
var self = memberId;
var webSocket;

function connect() {
    // create a websocket
    console.log(endPointURL);
    webSocket = new WebSocket(endPointURL);

    webSocket.onopen = function(event) {
        console.log("Connect Success!");
        // document.getElementById('sendMessage').disabled = false;
        // document.getElementById('connect').disabled = true;
        // document.getElementById('disconnect').disabled = false;
    };

    webSocket.onmessage = function(event) {
        var jsonObj = JSON.parse(event.data);
        if ("open" === jsonObj.type) {
            // refreshFriendList(jsonObj);
            console.log("is open!!")
        } else if ("history" === jsonObj.type) {
            messagesArea.innerHTML = '';
            var ul = document.createElement('ul');
            ul.id = "area";
            messagesArea.appendChild(ul);
            // 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
            var messages = JSON.parse(jsonObj.message);
            for (var i = 0; i < messages.length; i++) {
                var historyData = JSON.parse(messages[i]);
                var showMsg = historyData.message;
                var li = document.createElement('li');
                // 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
                historyData.sender === self ? li.className += 'me' : li.className += 'friend';
                li.innerHTML = showMsg;
                ul.appendChild(li);
            }
            messagesArea.scrollTop = messagesArea.scrollHeight;
        } else if ("chat" === jsonObj.type) {
            var li = document.createElement('li');
            jsonObj.sender === self ? li.className += 'me' : li.className += 'friend';
            li.innerHTML = jsonObj.message;
            console.log(li);
            document.getElementById("area").appendChild(li);
            messagesArea.scrollTop = messagesArea.scrollHeight;
        } else if ("close" === jsonObj.type) {
            refreshFriendList(jsonObj);
        }
        
    };

    webSocket.onclose = function(event) {
        console.log("Disconnected!");
    };
}

function sendMessage() {
    var inputMessage = document.getElementById("message");
    // var friend = statusOutput.textContent;
    var system = "xxx";
    var message = inputMessage.value.trim();

    if (message === "") {
        alert("Input a message");
        inputMessage.focus();
    // } else if (friend === "") {
    //     alert("Choose a friend");
    } else {
        var jsonObj = {
            "type" : "chat",
            "sender" : memberId,
            "receiver" : 2,
            "message" : message
        };
        webSocket.send(JSON.stringify(jsonObj));
        inputMessage.value = "";
        inputMessage.focus();
    }
}

// 有好友上線或離線就更新列表
// function refreshFriendList(jsonObj) {
//     var friends = jsonObj.users;
//     var row = document.getElementById("row");
//     row.innerHTML = '';
//     for (var i = 0; i < friends.length; i++) {
//         if (friends[i] === self) { continue; }
//         row.innerHTML +='<div id=' + i + ' class="column" name="friendName" value=' + friends[i] + ' ><h2>' + friends[i] + '</h2></div>';
//     }
//     addListener();
// }
// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
// function addListener() {
//     var container = document.getElementById("row");
//     container.addEventListener("click", function(e) {
//         var friend = e.srcElement.textContent;
//         updateFriendName(friend);
//         var jsonObj = {
//                 "type" : "history",
//                 "sender" : self,
//                 "receiver" : friend,
//                 "message" : ""
//             };
//         webSocket.send(JSON.stringify(jsonObj));
//     });
// }

function disconnect() {
    webSocket.close();
    // document.getElementById('sendMessage').disabled = true;
    // document.getElementById('connect').disabled = false;
    // document.getElementById('disconnect').disabled = true;
}

// function updateFriendName(name) {
//     statusOutput.innerHTML = name;
// }
*/