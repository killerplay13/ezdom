// ====================== 宣告變數 ====================== //
let coachList;
let page;
const url = new URLSearchParams(window.location.search);
const url_status = url.get('status'); // 取得URL中查詢字串status的值
let url_page = url.get('page');
$("#statusSelect").val("1"); //教練狀態下拉選單。預設為未審核
let req = 'http://localhost:8080/ezdom/backend/verify/list'; // 如果查詢字串為空，預設為查詢未審核的教練
let page_req = 'http://localhost:8080/ezdom/backend/verify/list/page'; // 如果查詢字串為空，預設為查詢未審核的教練

if (url_status !== null) {
    req += '?status=' + url_status;
    $("#statusSelect").val(url_status);
    page_req += '?status=' + url_status;
}
if (url_page !== null) {
    // console.log(req);
    req += '&page=' + url_page;
}

const tbody_el = $("#tbody");
const page_el = $("#page");




// ====================== 瀏覽教練清單 ====================== //
window.addEventListener("load", function () {
    getCoachList();
    // console.log(req);
    getListPage();

})

async function getCoachList() {
    try {
        let response = await fetch(req);

        if (response.status === 401) {
            // 重定向到登录页面 登入失敗
            window.location.href = '/ezdom/backendemp/empSignin.html';
        } else if (response.ok) {
            // 登入成功
            coachList = await response.json();
        } else {
            alert("錯誤狀態 " + response.status);
        }
    } catch (error) {
        console.error("出现错误: " + error);
    }

    showCoachDetails();
}


async function getListPage() {
    try {
        const response = await fetch(page_req);

        if (response.status === 401) {
            // 重定向到登录页面 登入失敗
            window.location.href = '/ezdom/backendemp/empSignin.html';
        } else if (response.ok) {
            // 登入成功
            page = await response.json();
        } else {
            alert("錯誤狀態 " + response.status);
        }
    } catch (error) {
        console.error("出现错误: " + error);
    }


    for (let i = 0; i < page; i++) {
        let html = '';
        html += `
            <li class="page-item"><a class="page-link" href="javascript: void(0);"value=${i}>${i + 1}</a></li>
        `;
        console.log(html);
        page_el.append(html);
    }

    // 依照所選的分頁，將分頁按鈕加上顏色
    let pages = document.querySelectorAll("a.page-link");
    console.log(pages);

    if (url_page !== null) {
        let index = parseInt(url_page);
        pages[index].classList.add('active');
    } else {
        pages[0].classList.add('active');
    }
}

function showCoachDetails() {

    // console.log(page_el);
    // let pageNum = 1;
    // // page_el.innerHTML = `
    // //     <li class="page-item"><a class="page-link" href="javascript: void(0);" value="${pageNum}">${pageNum}</a></li>
    // // `;
    // if(coachList.length > 9){
    //     let li = document.createElement("li");
    //     li.setAttribute("class", "page-item");
    //     li.innerHTML = `
    //         <a class="page-link" href="javascript: void(0);" value="${pageNum}">${++pageNum}</a>
    //     `;
    //     page_el.append(li);
    //     console.log("ok");

    // }







    for (let coach of coachList) {
        let time = coach.createTime.substring(0, 16);
        let tr = document.createElement("tr");

        if (url_status === null || url_status === "1") {
            tr.innerHTML = `
                <td class="s">${coach.coachId}</td>
                <td class="s">${coach.nickname}</td>
                <td class="s">${coach.gender}</td>
                <td class="big">${coach.skills}</td>
                <td class="big">${coach.introduction}</td>
                <td class="s"><img src="data:image/jpeg;base64,${coach.picture}" class="product-image"></td>
                <td class="s">${time}</td>
                <td class="s"><button class="btn btn-outline-info btn_ok" id="${coach.coachId}" value="2">通過</button></td>
                <td class="s"><button class="btn btn-outline-danger btn_no" id="${coach.coachId}" value="3">撤回</button></td>
            `;
        } else if (url_status === "2") {
            tr.innerHTML = `
                <td class="s">${coach.coachId}</td>
                <td class="s">${coach.nickname}</td>
                <td class="s">${coach.gender}</td>
                <td class="big">${coach.skills}</td>
                <td class="big">${coach.introduction}</td>
                <td class="s"><img src="data:image/jpeg;base64,${coach.picture}" class="product-image"></td>
                <td class="s">${time}</td>
                <td class="s"><button class="btn btn-outline-info btn_ok" id="${coach.coachId}" value="0">停權</button></td>
            `;

            $("#delete").css("display", "none");
        } else if (url_status === "0") {
            tr.innerHTML = `
                <td class="s">${coach.coachId}</td>
                <td class="s">${coach.nickname}</td>
                <td class="s">${coach.gender}</td>
                <td class="big">${coach.skills}</td>
                <td class="big">${coach.introduction}</td>
                <td class="s"><img src="data:image/jpeg;base64,${coach.picture}" class="product-image"></td>
                <td class="s">${time}</td>
                <td class="s"><button class="btn btn-outline-info btn_ok" id="${coach.coachId}" value="2">復權</button></td>
            `;

            $("#delete").css("display", "none");
        }
        tbody_el.append(tr);
    }
}

// 按下通過
$(tbody_el).on("click", ".btn_ok", function () {
    const id = this.id;
    const status = this.value;
    const req = "http://localhost:8080/ezdom/backend/verify/list/update/" + id;
    let text;

    switch (url_status) {
        case "2":
            text = "確定停權該教練嗎?";
            break;
        case "0":
            text = "確定復權該教練嗎?";
            break;
        case null:
            text = "確定審核通過嗎?";
            break;
        case "1":
            text = "確定審核通過嗎?";
            break;
    }

    Swal.fire({
        icon: 'warning',
        title: '編輯',
        text: text,
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
                fetch(req, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: status
                }).then(response => {
                      if (response.status === 401) {
                          // 重定向到登录页面 登入失敗
                          window.location.href = '/ezdom/backendemp/empSignin.html';
                      }else if(response.ok){
                          //登入成功
                          location.reload();
                      }else{
                          alert("錯誤狀態" + response.status);
                          return;
                      }
                  })
            })
        }
    })
})

// 按下撤回
$(tbody_el).on("click", ".btn_no", function () {
    const id = this.id;
    const status = this.value;
    const req = "http://localhost:8080/ezdom/backend/verify/list/update/" + id;

    Swal.fire({
        icon: 'warning',
        title: '編輯',
        text: "確定撤回申請?",
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
                fetch(req, {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: status
                }).then(response => {
                      if (response.status === 401) {
                          // 重定向到登录页面 登入失敗
                          window.location.href = '/ezdom/backendemp/empSignin.html';
                      }else if(response.ok){
                          //登入成功
                          location.reload();
                      }else{
                          alert("錯誤狀態" + response.status);
                          return;
                      }
                  })
            })
        }
    })
})


// 教練狀態清單
$("#statusSelect").on("change", function () {

    const selectedValue = $(this).val();

    if (selectedValue === "1") {
        window.location.href = 'coach-verify.html';
    } else if (selectedValue === "2") {
        window.location.href = 'coach-verify.html?status=' + selectedValue;
    } else if (selectedValue === "0") {
        window.location.href = 'coach-verify.html?status=' + selectedValue;
    }
})

// 分頁
$(page_el).on("click", "a.page-link", function () {
    let page = this.getAttribute("value");
    console.log(page);

    window.location.href = 'coach-verify.html?status=' + $("#statusSelect").val() + '&page=' + page;
    // location.reload();
    // getCoachList();
})

