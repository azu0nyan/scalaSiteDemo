console.log("js loaded")
function setupListeners(){
    console.log("Setting up listeners...")
    var input = document.getElementById("searchWindow");
    var output = document.getElementById("searchResult")

    input.addEventListener("input", (event) => {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/searchReq?q=" + input.value)
        xhr.onload = function() {
            if (xhr.status == 200) {
                console.log("response :" + xhr.responseText)
                var resArr = JSON.parse(xhr.responseText)
                var html = ""
                for (var i = 0; i < resArr.length; i++) {
                    html += "<tr><td>" + resArr[i].id + "</td><td> " + resArr[i].name + "</td></tr>"
                }
                html = "<table><tr><th> Индекс </th><th> Имя</th><tr>" + html + "</table>"
                output.innerHTML = html
            }
       }
       console.log("Sending search request :" + input.value);
       xhr.send(); //Параметры запроса
    });
}
window.onload=setupListeners()