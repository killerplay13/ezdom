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
// 設定資訊div
const bord = document.querySelector("#set_bord");
let reader = new FileReader();

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

// ====================== 網頁載入完成後執行 ====================== //
window.addEventListener("load", function() {
    getSession();
    (async () => {
        await getCoachDetails(); // 等待第一个函数完成
        skillsChecked(); // 在第一个函数完成后，调用第二个函数
    })();

})

// ====================== 側邊欄資訊 ====================== //
const url = new URLSearchParams(window.location.search);
const url_coachId = url.get('coachId'); // 取得URL中查詢字串coachId的值
const d_req = '/ezdom/frontend/browse/list/' + url_coachId;

async function getCoachDetails(){

    try {
        let response = await fetch(d_req);

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

//	let response = await fetch(d_req);
//    coachDetails = await response.json();
    console.log(coachDetails);
    // SHOW出側邊欄
    showCoachDetails();
}

let imgUrl = "data:image/jpeg;base64,";
function showCoachDetails(){
    imgUrl += coachDetails.picture;

    coachPicture.src = imgUrl;
    coachName.innerHTML = `${coachDetails.nickname}`;
    coachEmail.innerHTML = `${coachDetails.email}`;
    detailsURL.href = `coach-details.html?coachId=${coachDetails.coachId}`;
    itemURL.href = `../frontendreserve/reserve-appointmentArea.html?coachId=${coachDetails.coachId}`;
    timeURL.href = `../frontendreserve/reserve-memberDate.html?coachId=${coachDetails.coachId}`;
    messageURL.href = `coach-message.html?coachId=${coachDetails.coachId}`;
    settingURL.href = `coach-settings.html?coachId=${coachDetails.coachId}`;

    /*
        1. 判斷登入中的會員是否為教練
        2. 若是，判斷該會員的 coachId，是否與所點擊教練頁面的 coachID相同
        3. 若是，則讓設置的選項 show出
    */
    if(coachId === coachDetails.coachId){
        settingURL.style = "";
        reportURL.style = "display:none";
    }

    
    // ====================== 設定資訊 ====================== //
    bord.innerHTML = `
        <h1 class="h2 mb-4">設置</h1>
        <!-- Basic info-->
        <section class="card border-0 py-1 p-md-2 p-xl-3 p-xxl-4 mb-4">
        <div class="card-body">
            <div class="d-flex align-items-center mt-sm-n1 pb-4 mb-0 mb-lg-1 mb-xl-3"><i class="ai-user text-primary lead pe-1 me-2"></i>
            <h2 class="h4 mb-0">基本資訊</h2>
            </div>
            <div class="d-flex align-items-center">
            <div class="dropdown"><a id="img" class="d-flex flex-column justify-content-end position-relative overflow-hidden rounded-circle bg-size-cover bg-position-center flex-shrink-0" href="#" data-bs-toggle="dropdown" aria-expanded="false" style="width: 80px; height: 80px; background-image:url(${imgUrl});" data-value="${coachDetails.picture}"><span class="d-block text-light text-center lh-1 pb-1" style="background-color: rgba(0,0,0,.5)"><i class="ai-camera"></i></span></a>
                <div class="dropdown-menu my-1" id="load_btn"><a class="dropdown-item fw-normal" href="#"><i class="ai-camera fs-base opacity-70 me-2"></i>Upload new photo</a></div>
            </div>
            <div class="ps-3">
                <h3 class="h6 mb-1">大頭貼</h3><input class="form-control" type="file" id="load">
            </div>
            </div>
            <div class="row g-3 g-sm-4 mt-0 mt-lg-2">
            <div class="col-sm-9">
                <label class="form-label" for="ln">教練暱稱</label>
                <input class="form-control" type="text" value="${coachDetails.nickname}" id="nickname">
            </div>
            <div class="col-sm-9">
                <label class="form-label" for="email">電子郵件地址</label>
                <input disabled class="form-control" type="email" value="${coachDetails.email}" id="email">
            </div>
            <div class="col-sm-9">
                <label class="form-label" for="phone">手機</label>
                <input disabled class="form-control" type="tel" data-format="{&quot;numericOnly&quot;: true, &quot;delimiters&quot;: [&quot;+1 &quot;, &quot; &quot;, &quot; &quot;], &quot;blocks&quot;: [0, 3, 3, 2]}" value="${coachDetails.phone}" id="phone">
            </div>

            <div>
                <p>專業項目:</p>
                <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="ex-check-1">
                <label class="form-check-label" for="ex-check-1">籃球</label>
                </div>
                <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="ex-check-2">
                <label class="form-check-label" for="ex-check-2">羽球</label>
                </div>
                <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="ex-check-3">
                <label class="form-check-label" for="ex-check-3">網球</label>
                </div>
                <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="ex-check-4">
                <label class="form-check-label" for="ex-check-4">足球</label>
                </div>
                <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="ex-check-5">
                <label class="form-check-label" for="ex-check-5">桌球</label>
                </div>
                <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="ex-check-6">
                <label class="form-check-label" for="ex-check-6">跑步</label>
                </div>
                <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="ex-check-7">
                <label class="form-check-label" for="ex-check-7">游泳</label>
                </div>
            </div>
            <div>
                <div class="form-check form-check-inline">  
                <input class="form-check-input" type="checkbox" id="ex-check-8">
                <label class="form-check-label" for="ex-check-8">攀岩</label>
                </div>
                <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="ex-check-9">
                <label class="form-check-label" for="ex-check-9">健美</label>
                </div>
                <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="ex-check-10">
                <label class="form-check-label" for="ex-check-10">健力</label>
                </div>
                <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="ex-check-11">
                <label class="form-check-label" for="ex-check-11">格鬥</label>
                </div>
                <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="ex-check-12">
                <label class="form-check-label" for="ex-check-12">飛輪</label>
                </div>
                <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="ex-check-13">
                <label class="form-check-label" for="ex-check-13">有氧</label>
                </div>
                <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="ex-check-14">
                <label class="form-check-label" for="ex-check-14">瑜珈</label>
                </div>
            </div>
            <div class="col-9">
                <label class="form-label" for="bio">自我介紹</label>
                <textarea style="resize: none;" class="form-control" rows="5" placeholder="新增自我介紹" id="introduction">${coachDetails.introduction}</textarea>
            </div>
            <div class="col-9 d-flex justify-content-end pt-3">
                <button class="btn btn-secondary" type="button" id="cancel">Cancel</button>
                <button class="btn btn-primary ms-3" type="button" id="save">Save changes</button>
            </div>
            </div>
        </div>
        </section>
    `;

}

async function skillsChecked(){
    // 取得專項項目的字串
    const skillsString = coachDetails.skills;

    // 將字串依據"、"分開，並放入陣列
    const skillsArray = skillsString.split("、");

    // 註冊所有專業項目的checkboxes
    const checkboxes = document.querySelectorAll(".form-check-input");

    // 針對checkboxes跑迴圈
    checkboxes.forEach(function(checkbox) {
        // 取得check標籤下一個兄弟元素的文字內容
        const label = checkbox.nextElementSibling.textContent;

        // 檢查是否存在陣列中，若是則勾選
        if (skillsArray.includes(label)) {
            checkbox.checked = true;
        }
    });
}

// 按下Save changes的按鈕時
$(bord).on("click", "#save", function() {
    // collectFormData();

    Swal.fire({
        icon: 'warning',
        title: '編輯',
        text: `是否確定編輯?`,
        confirmButtonText: '確定',
        cancelButtonText: '取消',
        showCancelButton: true
    }).then((result) => {
        if (result.isConfirmed) {
            collectFormData();
        }
      })
})

function check() {
    let text = "";
    
    if (formData.nickname === "") {
        text += "教練暱稱不能為空<br>";
      }
      
      if (formData.skills.length === 0) {
        text += "請至少選擇一項專業項目<br>";
      }
      
      if (formData.introduction === "") {
        text += "自我介紹不能為空<br>";
      }
      
      if (formData.introduction.length > 200) {
        text += "自我介紹長度不得超過200字";
      }

      if(text != ""){
        Swal.fire({
            icon: 'error',
            title: '資料尚未完善',
            html: text,
            confirmButtonText: '確定'
          });
        return false;
      }

      return true;
}

function collectFormData() {
    const img = document.getElementById('img');
    const nickname = document.getElementById('nickname');
    const email = document.getElementById('email');
    const phone = document.getElementById('phone');
    const checkboxes = document.querySelectorAll('.form-check-input[type="checkbox"]');
    const introduction = document.getElementById('introduction');
    const req = "/ezdom/frontend/browse/list/update/" + url_coachId;

    formData = {
        picture: img.getAttribute("data-value"),
        nickname: nickname.value,
        skills: "",
        introduction: introduction.value,
        memberId: memberId,
        gender: coachDetails.gender
    };

    // 將所有專業項目的value打包
    let isFirstSelected = true;

    checkboxes.forEach(function(checkbox) {
        if (checkbox.checked) {
            if (!isFirstSelected) {
                formData.skills += "、";
            } else {
                isFirstSelected = false;
            }
            formData.skills += checkbox.nextElementSibling.textContent;
        }
    });

    console.log(formData);

    // 判斷項目是否為空
    if(check()){

        fetch(req, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(formData)
        })
        .then(response => {
          if (response.status === 401) {
            // 重定向到登录页面 登入失敗
            window.location.href = '/ezdom/frontendmember/account-signin.html';
          } else if (response.ok) {
            //登入成功
            return response.json();
          } else {
            alert("錯誤狀態" + response.status);
            return;
          }
        })
        .then(body => {
            if (body.successful) {
                Swal.fire({
                    icon: 'success',
                    title: '編輯成功',
                    text: body.message,
                    confirmButtonText: '確定'
                  }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.replace("coach-details.html?coachId=" + url_coachId);
                    }
                  })

                // alert(body.message);
                // window.location.replace("coach-details.html?coachId=" + url_coachId);
            } else {
                Swal.fire({
                    icon: 'error',
                    title: '編輯失敗',
                    text: body.message,
                    confirmButtonText: '確定'
                  })
            }
        });
    }
}

$(bord).on("click", "#load_btn", function() {
    $("#load").click();
})

var preview_img = function(file){

    reader.readAsDataURL(file); // 讀取檔案
    reader.addEventListener("load", function () {

      let img_str = reader.result;
    //   console.log(img_str);
      let img = document.querySelector("#img");
      img.style.backgroundImage = `url(${img_str})`;
      base64 = img_str.substring(img_str.indexOf(",") + 1);
    //   console.log(base64);
      img.setAttribute("data-value", base64);
    });
  };


$(bord).on("change", "#load" , function(e){
if(this.files.length > 0){
    preview_img(this.files[0]);
}else{
//   preview_el.innerHTML = '<span class="text">預覽圖</span>';
}
});

$(bord).on("click", "#cancel", function() {
    // console.log("ok");
    let href = `coach-details.html?coachId=${coachDetails.coachId}`;
    // if(confirm("要放棄修改嗎??")){
    //     window.location.replace(href);
    // }

    Swal.fire({
        icon: 'warning',
        title: '取消',
        text: `是否取消編輯?`,
        confirmButtonText: '確定',
        cancelButtonText: '取消',
        showCancelButton: true
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.replace(href);
        }
      })

})