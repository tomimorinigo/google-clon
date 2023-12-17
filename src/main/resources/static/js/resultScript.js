const textSearch = document.getElementById("text-search");

let aux = document.location.href.split("?query=");
let query = aux[1];

textSearch.value = query;

document.getElementById("web-title").innerHTML = query + " - Buscar con Google";

fetch("http://localhost:8080/api/search?query=" + query)
    .then(response => response.json())
    .then(json =>{
        json.forEach(result => {
            let html = `<div class="result">
                            <h3><a href="${result.url}" target="_blank">${result.title}</a></h3>
                            <span>${result.description}</span>
                        </div>`;
            document.getElementById("results").innerHTML += html;
        });
    });

textSearch.addEventListener('keyup', (event) => {
    if (event.key == 'Enter') {
        const text = textSearch.value;
        document.location.href = `http://localhost:8080/results.html?query=${text}`;
    }
});