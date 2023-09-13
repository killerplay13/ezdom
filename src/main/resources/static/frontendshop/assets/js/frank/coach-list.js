// ====================== 宣告變數 ====================== //

let coachList;
const list_div = document.querySelector("#list_div");

// ====================== 取得登入的session資訊 ====================== //
const s_req = '/ezdom/frontend/session';
let session;
let memberId = null;
let coachId = null;
async function getSession(){
	let response = await fetch(s_req);
    session = await response.json();
    console.log(session);
    memberId = session.memberId;
    if(null !== session.coachId){
        coachId = session.coachId;
    }

    let link = document.querySelector("#link");
    if(null !== coachId){
      link.setAttribute("href", `/ezdom/frontendcoach/coach-details.html?coachId=${coachId}`);
    } else if (null === coachId){
      link.setAttribute("href", `/ezdom/frontendcoach/coach-signup.html`);
      link.textContent = "註冊教練";
    }
}

// ====================== 瀏覽教練列表 ====================== //
window.addEventListener("load", function() {
    getCoachList();
    getSession();

})

async function getCoachList(){
    try {
        let response = await fetch("/ezdom/frontend/browse/list");

        if (response.status === 401) {
            // 重定向到登录页面 登入失敗
            window.location.href = '/ezdom/frontendmember/account-signin.html';
        } else if (response.ok) {
            // 登入成功
            coachList = await response.json();
        }
    } catch (error) {
        console.error("出现错误: " + error);
    }

//	let response = await fetch("/ezdom/frontend/browse/list");
//    coachList = await response.json();
    showCoachList();
}

function showCoachList(){
    for(let i = 0 ; i < coachList.length; i++){
        let html = document.createElement("div");
        html.setAttribute("class", "col text-center pt-2");
        /*
            data:：表示数据URI方案的开头部分。
            image/jpeg：指定要嵌入的数据的MIME类型，表示这是JPEG格式的图像数据。
            base64：表示数据将使用Base64编码进行编码，以便在URL中进行传输。
            ,：逗号将MIME类型和实际数据部分分开。
        */
        let imgUrl = "data:image/jpeg;base64," + coachList[i].picture;

        html.innerHTML = `
        <div class="col text-center pt-2">
            <a class="card-hover card-lifted d-inline-block text-decoration-none" href="coach-details.html?coachId=${coachList[i].coachId}">
            <img class="card-lifted d-block rounded-5 mb-3" src= ${imgUrl} width="340" alt="Alisa Black" style="min-height:270px; max-height:270px">
            <h3 class="h5 mb-1">${coachList[i].nickname}</h3>
            <p class="mb-0 d-inline-block text-truncate" style="max-width: 150px;">${coachList[i].skills}</p>
            </a>
        </div>
        `;

        list_div.appendChild(html);
    }
}


