// ====================== 宣告變數 ====================== //
let coachDetails;
let coachMessage;
// 側邊欄大頭貼
const coachPicture = document.querySelector("#coachPicture");
// 側邊欄姓名
const coachName = document.querySelector("#name");
// 側邊欄信箱
const coachEmail = document.querySelector("#email");
// 側邊欄個人資訊的連結
const detailsURL = document.querySelector("#details_href");
// 側邊欄預約項目的連結
const itemURL = document.querySelector("#item_href");
// 側邊欄預約時段的連結
const timeURL = document.querySelector("#time_href");
// 側邊欄留言板的連結
const messageURL = document.querySelector("#message_href");
// 側邊欄設置的連結
const settingURL = document.querySelector("#setting_href");
// 側邊欄檢舉教練的連結
const reportURL = document.querySelector("#report_href");
// 留言板div
const bord = document.querySelector("#message_bord");
// 模擬登入的會員ID
const memberId = 1;
// 模擬登入的 coachId
const coachId = 1;


// ====================== 網頁載入完成後執行 ====================== //
window.addEventListener("load", function () {
    getCoachDetails();

    getCoachMessage();

})

// ====================== 側邊欄資訊 ====================== //
const url = new URLSearchParams(window.location.search);
const url_coachId = url.get('coachId'); // 取得URL中查詢字串coachId的值。哪個教練的留言板
const d_req = 'http://localhost:8080/ezdom/frontend/browse/list/' + url_coachId;

async function getCoachDetails() {
    let response = await fetch(d_req);
    coachDetails = await response.json();
    console.log(coachDetails);
    // SHOW出側邊欄、留言板標題、文字輸入框
    showCoachDetails();
}

function showCoachDetails() {
    let imgUrl = "data:image/jpeg;base64," + coachDetails.picture;

    coachPicture.src = imgUrl;
    coachName.innerHTML = `${coachDetails.nickname}`;
    coachEmail.innerHTML = `${coachDetails.email}`;
    detailsURL.href = `coach-details.html?coachId=${coachDetails.coachId}`;
    itemURL.href = `../frontendreserve/reserve-appointmentArea.html?coachId=${coachDetails.coachId}`;
    timeURL.href = `../frontendreserve/reserve-memberDate.html?coachId=${coachDetails.coachId}`;
    messageURL.href = `coach-message.html?coachId=${coachDetails.coachId}`;
    settingURL.href = `coach-settings.html?coachId=${coachDetails.coachId}`;

    // 留言板標題
    let bord = document.querySelector("#message_bord");
    let h1 = document.createElement("h1");
    h1.setAttribute("class", "h2 mb-4 display-6");
    h1.innerHTML = `${coachDetails.nickname}的留言板`;
    bord.appendChild(h1);

    /*
        1. 判斷登入中的會員是否為教練
        2. 若是，判斷該會員的 coachId，是否與所點擊教練頁面的 coachID相同
        3. 若是，則讓設置的選項 show出
    */
    if (coachId === coachDetails.coachId) {
        settingURL.style = "";
        reportURL.style = "display:none";
    }

}

// ====================== 留言板資訊 ====================== //
const req = 'http://localhost:8080/ezdom/frontend/coach/message/' + url_coachId;

async function getCoachMessage() {
    let response = await fetch(req);
    coachMessage = await response.json();
    console.log(coachMessage);
    showCoachMessage();
}

function showCoachMessage() {
    let count = 1; // 留言的樓層。 ex.B1, B2

    for (let data of coachMessage) {

        // 截斷時間，只顯示 yyyy-mm-dd HH-MM
        let time = data.createTime.substring(0, 16);

        // 判斷留言者是否與該教練留言板同人，是的話就顯示教練圖片和教練暱稱和修改連結為"教練的個人頁面"
        let img = "data:image/jpeg;base64,";
        let name = data.memberName;
        let a_href = "會員的個人資訊網址" + data.memberId;  // 預設為發表留言人的個人資訊
        if (data.memberId == coachDetails.memberId) {
            img += data.coachPicture;
            name = data.nickname;
            a_href = "coach-details.html?coachId=" + data.coachId;
        } else {
            img += data.memberPicture;
        }

        // 判斷發表留言的 memberId是否與登入的 memberId相同
        let btn_display = "none";
        if (memberId == data.memberId) {
            btn_display = "";
        }

        let div = document.createElement("div");
        div.setAttribute("class", "border-bottom pb-4");
        div.setAttribute("id", "message");
        div.innerHTML = `
            <div class="d-flex align-items-center pb-1 mb-0">
            <a href="${a_href}"><img src="${img}" class="rounded-circle" width="48" alt="Comment author"></a>
            <div class="ps-3">
                <h4 class="mb-0">${name}</h4>
            </div>
            <div class="ps-3" style="display:${btn_display};">
                <button id="update${data.messageId}" value="${data.messageId}" type="button" class="nav-link fs-sm fw-semibold px-0 py-2 btn_update" style="position: absolute; right: 100px;">
                <i class="ai-edit fs-xl ms-2"></i>
                </button>
                <button value="${data.messageId}" type="button" class="nav-link fs-sm fw-semibold px-0 py-2 btn_delete" style="position: absolute; right: 50px;">
                <i class="ai-trash fs-xl ms-2"></i>
                </button>
            </div>
            </div>
            <p class="pb-2 mb-0 para fs-6" style="margin-left:65px;">${data.content}</p>
            <textarea id="updateText" value='update${data.messageId}' style="margin-left:65px; resize:none; width:300px; height:60px;" type="text" class="task_name_update -none" placeholder="請輸入內容…">${data.content}</textarea>
            <div style="margin-left:65px;">
                <span class="fs-sm text-muted">B${count++}, </span>
                <span class="fs-sm text-muted">${time}</span>
            </div>
        `;
        bord.appendChild(div);
    }

    // 等待300毫秒在顯示出輸入框，避免輸入出現在留言板標題上方
    setTimeout(function () {
        let input = document.createElement("div");
        input.setAttribute("class", "input-group");
        input.innerHTML = `
            <span class="input-group-text align-self-start mt-1">
            <i class="ai-message"></i>
            </span>
            <textarea id="text" class="form-control textarea" placeholder="請輸入訊息..." rows="6"></textarea>
            <span class="input-group-text align-self-start mt-1">
            <button type="button" class="nav-link fs-sm fw-semibold px-0" id="enter">  
            <i class="ai-send"></i>
            </button>
            </span>
        `;
        bord.appendChild(input);
    }, 300);


    // <button type="button" class="nav-link fs-sm fw-semibold px-0 py-2">
    // <i class="ai-redo fs-xl ms-2"></i>
    // </button>
}

// 按下 Enter 鍵直接送出留言
$(bord).on("keydown", "#text", function (e) {

    if (e.which == 13) {
        e.preventDefault();
        $(bord).find("#enter").trigger("click");
    }
})

// 按下送出按鈕後，送出留言
$(bord).on("click", "#enter", function () {
    Swal.fire({
        icon: 'warning',
        title: '新增',
        text: `是否確定新增?`,
        confirmButtonText: '確定',
        cancelButtonText: '取消',
        showCancelButton: true
    }).then((result) => {
        if (result.isConfirmed) {
            add();
        }
    })
    
})

function add() {
    const content = $("#text").val().trim();// 取得訊息框內的文字
    const req = "http://localhost:8080/ezdom/frontend/coach/message/add";

    if (content === "") {
        Swal.fire({
            icon: 'error',
            title: '新增失敗',
            text: "留言內容不得為空，請確認!!",
            confirmButtonText: '確定'
        })
        return;
    } else if (content.length > 300) {
        Swal.fire({
            icon: 'error',
            title: '新增失敗',
            text: "留言內容不得超過300字!!",
            confirmButtonText: '確定'
        })
        return;
    } else {
        Swal.fire({
            icon: 'success',
            title: '新增成功',
            confirmButtonText: '確定'
        }).then((result) => {
            if (result.isConfirmed) {
                fetch(req, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({
                        coachId: url_coachId, // 此資料為哪個教練的留言板
                        memberId: memberId,
                        content: content
                    })
                })
            
                location.reload();
            }
        })
    }
}

// 在修改的textare內，按下enter鍵
$(bord).on("keydown", "#updateText", function (e) {

    if (e.which == 13) {
        e.preventDefault();
        let btn = "#" + this.getAttribute("value");
        $(bord).find(btn).trigger("click");
    }
})

let updateMessageId;
let updateMessage = "";
// 按下修改按鈕
$(bord).on("click", "button.btn_update", function () {

    updateMessageId = this.value;
    console.log(updateMessageId);

    if ($(this).attr("data-edit") == undefined) { // 進入編輯狀態

        $(this).attr("data-edit", true);
        $(this).closest("#message").find("p.para").toggleClass("-none");
        $(this).closest("#message").find("textarea.task_name_update").toggleClass("-none");

    } else {
        updateMessage = ($(this).closest("#message").find("textarea.task_name_update").val()).trim();
        if (updateMessage == "") {
            Swal.fire({
                icon: 'error',
                title: '編輯失敗',
                text: "留言內容不得為空!!",
                confirmButtonText: '確定'
            })
        } else if (updateMessage.length > 300) {
            Swal.fire({
                icon: 'error',
                title: '編輯失敗',
                text: "留言內容不得超過300字!!",
                confirmButtonText: '確定'
            })
        } else {
            Swal.fire({
                icon: 'warning',
                title: '編輯',
                text: `是否確定編輯?`,
                confirmButtonText: '確定',
                cancelButtonText: '取消',
                showCancelButton: true
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire({
                        icon: 'success',
                        title: '編輯成功',
                        confirmButtonText: '確定'
                    }).then((result) => {
                        $(this).closest("#message").find("p.para").html(updateMessage).toggleClass("-none");
                        $(this).closest("#message").find("textarea.task_name_update").val(updateMessage).toggleClass("-none");
                        $(this).removeAttr("data-edit");
                        update();
                        location.reload();
                    })
                }else{
                    $(this).removeAttr("data-edit");
                    $(this).closest("#message").find("p.para").toggleClass("-none");
                    $(this).closest("#message").find("textarea.task_name_update").toggleClass("-none");
                    location.reload();
                }
            })
        }
    }
})

function update() {
    const req = "http://localhost:8080/ezdom/frontend/coach/message/update/" + updateMessageId;
    fetch(req, {
        method: 'put',
        body: updateMessage
    })
}

let deleteMessageId;
// 按下刪除按鈕
$(bord).on("click", "button.btn_delete", function () {

    deleteMessageId = this.value;
    Swal.fire({
        icon: 'warning',
        title: '刪除',
        text: `是否確定刪除?`,
        confirmButtonText: '確定',
        cancelButtonText: '取消',
        showCancelButton: true
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire({
                icon: 'success',
                title: '刪除成功',
                confirmButtonText: '確定'
            }).then((result) => {
                deleteMessage();
                location.reload();
            })
        }
    })
})

function deleteMessage() {
    const req = "http://localhost:8080/ezdom/frontend/coach/message/delete/" + deleteMessageId;
    fetch(req, {
        method: 'delete',
        headers: { 'Content-Type': 'application/json' }
    })
}