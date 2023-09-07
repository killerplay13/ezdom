// ====================== 宣告變數 ====================== //
let faqList;
const url = new URLSearchParams(window.location.search);
const url_tag = url.get('faqTag'); // 取得URL中查詢字串status的值
let req = 'http://localhost:8080/ezdom/backend/faq/list'; // 預設為查詢所有常見問題

if(url_tag !== null){
    req += '?faqTag=' + url_tag;
    $("#classSelect").val(url_tag);
}

const faq_bord = document.querySelector("#bord");
const tbody_el = document.querySelector("#tbody");

// ====================== 瀏覽常見問題清單 ====================== //
window.addEventListener("load", function() {
    getFaqList();
    // console.log(req);


})

async function getFaqList(){
	let response = await fetch(req);
    faqList = await response.json();
    console.log(faqList);
    showFaqDetails();
}

function showFaqDetails(){

    for(let faq of faqList){
        let tr = document.createElement("tr");

        tr.innerHTML = `
            <td class="s">${faq.faqId}</td>
            <td class="s tag">${faq.faqTag}</td>
            <td class="big name">${faq.faqName}</td>
            <td class="big ans">${faq.faqAns}</td>
            <td class="s"><button class="btn btn-outline-info btn_ok" id="${faq.faqId}" value="${faq.faqTag}" >編輯</button></td>
            <td class="s"><button class="btn btn-outline-danger btn_no" id="${faq.faqId}">刪除</button></td>
        `; 

        tbody_el.appendChild(tr);
    }
}

// 按下刪除按鈕
$(tbody_el).on("click", ".btn_no", function() {
    const id = this.id;
    const req = "http://localhost:8080/ezdom/backend/faq/delete/" + id;

    Swal.fire({
        icon: 'warning',
        title: '編輯',
        text: "確定刪除此常見問題嗎?",
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
                fetch(req, {
                    method: 'delete',
                })
                location.reload();
            })
        }
    })
})

// 按下新增按鈕，顯示新增視窗
$('#add').on("click",function() {
    $('#addQuestionModal').modal('show');

    // 清空輸入框
    $('#category').val("會員");
    $('#question').val("");
    $('#solution').val("");
});

// 按下確定新增
$('#confirmAddBtn').on("click",function() {

    let text = "";
    if($('#category').val() === ""){
        text += "類別不得為空!!<br>";
    }
    if($('#question').val() === ""){
        text += "問題說明不得為空!!<br>";
    }
    if($('#solution').val() === ""){
        text += "解決方法不得為空!!";
    }

    if(text !== ""){
        Swal.fire({
            icon: 'error',
            title: '資料尚未完善',
            html: text,
            confirmButtonText: '確定'
          });
        return;
    }

    let data = {
        faqTag: $('#category').val(),
        faqName: $('#question').val(),
        faqAns: $('#solution').val()
    }
    console.log(data);

    let req = "http://localhost:8080/ezdom/backend/faq/add";

    Swal.fire({
        icon: 'warning',
        title: '編輯',
        text: "確定新增此常見問題嗎?",
        confirmButtonText: '確定',
        cancelButtonText: '取消',
        showCancelButton: true
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire({
                icon: 'success',
                title: '新增成功',
                confirmButtonText: '確定'
            }).then((result) => {
                fetch(req, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(data)
                })
                $('#addQuestionModal').modal('hide');
                location.reload();
            })
        }
    })
});


let faqId;
// 按下編輯按鈕，顯示編輯視窗
$(tbody_el).on("click", ".btn_ok", function() {
    $('#updateModal').modal('show');

    faqId = this.id;
    console.log(faqId);

    // 將原本的資料代入編輯視窗中
    let faqTag = $(this).closest("tr").find("td.tag").text();
    let faqName = $(this).closest("tr").find("td.name").text();
    let faqAns= $(this).closest("tr").find("td.ans").text();

    $('#u_category').val(faqTag);
    $('#u_question').val(faqName);
    $('#u_solution').val(faqAns);

});

// 按下確定編輯
$('#u_confirmAddBtn').on("click",function() {

    let text = "";
    if($('#u_category').val() === ""){
        text += "類別不得為空!!\n";
    }
    if($('#u_question').val() === ""){
        text += "問題說明不得為空!!\n";
    }
    if($('#u_solution').val() === ""){
        text += "解決方法不得為空!!\n";
    }
    
    if(text !== ""){
        Swal.fire({
            icon: 'error',
            title: '資料尚未完善',
            html: text,
            confirmButtonText: '確定'
          });
        return;
    }

    let data = {
        faqTag: $('#u_category').val(),
        faqName: $('#u_question').val(),
        faqAns: $('#u_solution').val()
    }
    // console.log(data);

    let req = "http://localhost:8080/ezdom/backend/faq/update/" + faqId;

    Swal.fire({
        icon: 'warning',
        title: '編輯',
        text: "確定編輯此常見問題嗎?",
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
                    body: JSON.stringify(data)
                })
                $('#updateModal').modal('hide');
                location.reload();
            })
        }
    })
});

// 點選問題類別
$("#classSelect").on("change", function() {
    let cla = this.value;
    let url = "faq.html"

    if(cla === "所有類別"){
        window.location.href = url;
        return;
    }
    url += "?faqTag=" + cla;
    window.location.href = url;
})