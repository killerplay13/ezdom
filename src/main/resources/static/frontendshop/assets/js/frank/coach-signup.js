// ====================== 宣告變數 ====================== //
const nickname = document.getElementById('name');
const gender = document.getElementById('gender');
const introduction = document.getElementById('introduction');
const checkboxes = document.querySelectorAll('.form-check-input[type="checkbox"]');
const picture = document.getElementById('picture');
const ok = document.getElementById('terms');
let reader = new FileReader();
let formData = [];
let memberId = null; // 模擬登入中的會員

window.addEventListener("load", function() {
  getSession();
})

const s_req = '/ezdom/frontend/session';
let session;
async function getSession(){
	let response = await fetch(s_req);
    session = await response.json();
    console.log(session);
    memberId = session.memberId;
}

function check() {
    let text = "";
    
    if (formData.nickname === "") {
        text += "教練暱稱不能為空<br>";
      }
      
      if (formData.gender != "男" && gender.value != "女") {
        text += "請選擇性別<br>";
      }

      if (formData.skills.length === 0) {
        text += "請至少選擇一項專業項目<br>";
      }
      
      if (formData.introduction === "") {
        text += "自我介紹不能為空<br>";
      }
      
      if (picture.files.length === 0) {
        text += "請選擇教練圖片<br>";
      }

    //   if(!ok.checked){
    //     text += "請先詳讀並同意教練條款";
    //   }

      if(text != ""){
        Swal.fire({
            icon: 'error',
            title: '資料尚未完善',
            html: text,
            confirmButtonText: '確定'
          });
          console.log(text);
        return false;
      }

      return true;
}

function collectFormData() {

    formData = {
        nickname: nickname.value,
        gender: gender.value,
        skills: "",
        introduction: introduction.value,
        picture: null,
        memberId: memberId
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

    // 判斷項目是否為空
    if(check()){
        
        // 讀取完成後觸發
        reader.onload = function(event) {
            const imageBase64 = btoa(event.target.result);

            formData.picture = imageBase64;  // 將圖片的 base64數據存入 formData
            console.log(formData);
            const req = "/ezdom/frontend/coach/register";
            fetch(req, {
                method: "POST",
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
                // const { successful } = body;
                if (body.successful) {
                    Swal.fire({
                        icon: 'success',
                        title: '註冊成功',
                        text: body.message,
                        confirmButtonText: '確定'
                      }).then((result) => {
                        if (result.isConfirmed) {
                          window.location.replace(`../ezdomindex.html`);
                        }
                      })
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: '註冊失敗',
                        text: body.message,
                        confirmButtonText: '確定'
                      })
                }
            });
        }
        reader.readAsBinaryString(picture.files[0]); // 讀取上傳的圖片
    }

}