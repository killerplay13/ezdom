// ====================== 宣告變數 ====================== //
const nickname = document.getElementById('name');
const gender = document.getElementById('gender');
const introduction = document.getElementById('introduction');
const checkboxes = document.querySelectorAll('.form-check-input[type="checkbox"]');
const picture = document.getElementById('picture');
const ok = document.getElementById('terms');
let reader = new FileReader();
let formData = [];
let memberId = 3; // 模擬登入中的會員

function check() {
    let text = "";
    
    if (formData.nickname === "") {
        text += "教練暱稱不能為空\n";
      }
      
      if (formData.gender != "男" && gender.value != "女") {
        text += "請選擇性別\n";
      }

      if (formData.skills.length === 0) {
        text += "請至少選擇一項專業項目\n";
      }
      
      if (formData.introduction === "") {
        text += "自我介紹不能為空\n";
      }
      
      if (picture.files.length === 0) {
        text += "請選擇教練圖片\n";
      }

      if(!ok.checked){
        text += "請先詳讀並同意教練條款";
      }

      if(text != ""){
        alert(text);
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
            const req = "http://localhost:8080/ezdom/coach/register";
            fetch(req, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            })
            .then(response => response.json())
            .then(body => {
                // const { successful } = body;
                if (body.successful) {
                    location.reload();
                    alert(body.message);
                } else {
                    alert(body.message);
                }
            });
        };
        reader.readAsBinaryString(picture.files[0]); // 讀取上傳的圖片
        
    }

}