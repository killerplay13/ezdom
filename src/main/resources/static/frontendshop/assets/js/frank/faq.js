// ====================== 宣告變數 ====================== //
let faqList;
const div_faq = document.querySelector("#faq");
const req = 'http://localhost:8080/ezdom/faq/list';

// ====================== 網頁載入完成後執行 ====================== //
window.addEventListener("load", function() {
    getFaqList();

    // 获取所有的折叠按钮
    var accordionButtons = document.querySelectorAll(".accordion-button");

    // 遍历每个按钮
    accordionButtons.forEach(function(button) {
        // 监听按钮的点击事件
        button.addEventListener("click", function() {
            // 获取按钮上的 data-bs-target 属性值，即要展开/收合的面板的 ID
            var target = button.getAttribute("data-bs-target");

            // 使用 Bootstrap 提供的 collapse 方法来展开/收合面板
            var collapse = new bootstrap.Collapse(target);
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