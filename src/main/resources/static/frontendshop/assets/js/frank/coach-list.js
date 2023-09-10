// ====================== 宣告變數 ====================== //

let coachList;
const list_div = document.querySelector("#list_div");

// ====================== 瀏覽教練列表 ====================== //
window.addEventListener("load", function() {
    getCoachList();
})

async function getCoachList(){
    try {
        let response = await fetch("http://localhost:8080/ezdom/frontend/browse/list");

        if (response.status === 401) {
            // 重定向到登录页面 登入失敗
            window.location.href = '/ezdom/frontendmember/account-signin.html';
        } else if (response.ok) {
            // 登入成功
            coachList = await response.json();
        } else {
            alert("錯誤狀態 " + response.status);
        }
    } catch (error) {
        console.error("出现错误: " + error);
    }

//	let response = await fetch("http://localhost:8080/ezdom/frontend/browse/list");
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


