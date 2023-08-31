// ====================== 宣告變數 ====================== //

let coachList;
const list_div = document.querySelector("#list_div");

// ====================== 瀏覽教練列表 ====================== //
window.addEventListener("load", function() {
    getCoachList();
})

async function getCoachList(){
	let response = await fetch("http://localhost:8080/ezdom/browse/list");
    coachList = await response.json();
    console.log(coachList);
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
            <p class="mb-0 d-inline-block text-truncate" style="max-width: 150px;">${coachList[i].introduction}</p>
            </a>
        </div>
        `;

        list_div.appendChild(html);
    }
}

