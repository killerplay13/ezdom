// ====================== 宣告變數 ====================== //
let coachDetails;
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
// 個人資訊div
const card_div = document.querySelector("#card");


// ====================== 瀏覽教練個人頁面 ====================== //
window.addEventListener("load", function() {
    getSession();
    getCoachDetails();
})

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

const url = new URLSearchParams(window.location.search);
const url_coachId = url.get('coachId'); // 取得URL中查詢字串coachId的值
const req = 'http://localhost:8080/ezdom/frontend/browse/list/' + url_coachId;

async function getCoachDetails(){
    try {
        let response = await fetch(req);

        if (response.status === 401) {
            // 重定向到登录页面 登入失敗
            window.location.href = '/ezdom/frontendmember/account-signin.html';
        } else if (response.ok) {
            // 登入成功
            coachDetails = await response.json();
        } else {
            alert("錯誤狀態 " + response.status);
        }
    } catch (error) {
        console.error("出现错误: " + error);
    }

//	let response = await fetch(req);
//    coachDetails = await response.json();
    showCoachDetails();
}

function showCoachDetails(){
    let imgUrl = "data:image/jpeg;base64," + coachDetails.picture;
    let update_btn = "none";

    /*
      1. 判斷登入中的會員是否為教練
      2. 若是，判斷該會員的 coachId，是否與所點擊教練頁面的 coachID相同
      3. 若是，則讓設置的選項 show出
    */
    if(coachId === coachDetails.coachId){
      update_btn = "";
      settingURL.style = "";
      reportURL.style = "display:none";
    }

    card_div.innerHTML = `
    <div class="d-flex align-items-center mt-sm-n1 pb-4 mb-0 mb-lg-1 mb-xl-3"><i class="ai-user text-primary lead pe-1 me-2"></i>
                  <h2 class="h4 mb-0">基本資料</h2>
                  <a class="btn btn-sm btn-secondary ms-auto" href="coach-settings.html?coachId=${coachDetails.coachId}" style="display: ${update_btn};"><i class="ai-edit ms-n1 me-2"></i>Edit info</a>
                </div>
                <div class="d-md-flex align-items-center">
                  <div class="d-sm-flex align-items-center">
                    <div class="rounded-circle bg-size-cover bg-position-center flex-shrink-0" style="width: 80px; height: 80px; background-image: url(${imgUrl});"></div>
                    <div class="pt-3 pt-sm-0 ps-sm-3">
                      <h3 class="h5 mb-2">${coachDetails.nickname}<i class="ai-circle-check-filled fs-base text-success ms-2"></i></h3>
                      <div class="text-muted fw-medium d-flex flex-wrap flex-sm-nowrap align-iteems-center">
                        <div class="d-flex align-items-center me-3"><i class="ai-mail me-1"></i>${coachDetails.email}</div>
                      </div>
                    </div>
                  </div>
                  <div class="w-100 pt-3 pt-md-0 ms-md-auto" style="max-width: 212px;">
                    <!-- <div class="d-flex justify-content-between fs-sm pb-1 mb-2">Profile completion<strong class="ms-2">62%</strong></div>
                    <div class="progress" style="height: 5px;">
                      <div class="progress-bar" role="progressbar" style="width: 62%" aria-valuenow="62" aria-valuemin="0" aria-valuemax="100"></div>
                    </div> -->
                  </div>
                </div>
                <div class="row py-4 mb-2 mb-sm-3">
                  <div class="col-md-9 mb-4 mb-md-0">
                    <table class="table mb-0">
                      <tr>
                        <td class="border-0 text-muted py-1 px-0" style="min-width:80px;">性別</td>
                        <td class="border-0 text-dark fw-medium py-1 ps-3">${coachDetails.gender}</td>
                      </tr>
                      <tr>
                        <td class="border-0 text-muted py-1 px-0" style="min-width:80px;">聯絡方式</td>
                        <td class="border-0 text-dark fw-medium py-1 ps-3">${coachDetails.phone}</td>
                      </tr>
                      <tr>
                        <td class="border-0 text-muted py-1 px-0" style="min-width:80px;">專業項目</td>
                        <td class="border-0 text-dark fw-medium py-1 ps-3">${coachDetails.skills}</td>
                      </tr>
                      <tr>
                        <td class="border-0 text-muted py-1 px-0" style="min-width:80px;">自我介紹</td>
                        <td class="border-0 text-dark fw-medium py-1 ps-3" style="max-width: 200px;">
                          <p style="min-width: 500px;">
                            ${coachDetails.introduction}
                          </p>
                        </td>
                      </tr>
                    </table>
                  </div>
                </div>
    `;

    coachPicture.src = imgUrl;
    coachName.innerHTML = `${coachDetails.nickname}`;
    coachEmail.innerHTML = `${coachDetails.email}`;
    detailsURL.href = `coach-details.html?coachId=${coachDetails.coachId}`;
    itemURL.href = `../frontendreserve/reserve-appointmentArea.html?coachId=${coachDetails.coachId}`;
    timeURL.href = `../frontendreserve/reserve-memberDate.html?coachId=${coachDetails.coachId}`;
    messageURL.href = `coach-message.html?coachId=${coachDetails.coachId}`;
    settingURL.href = `coach-settings.html?coachId=${coachDetails.coachId}`;
}

