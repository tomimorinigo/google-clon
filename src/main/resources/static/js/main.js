const textSearch = document.getElementById('text-search');
const buttonSearch = document.getElementsByClassName('button')[0];
const buttonLucky = document.getElementsByClassName('button')[1];

buttonSearch.addEventListener('click', () => {
    const text = textSearch.value;
    document.location.href = `http://localhost:8080/results.html?query=${text}`;
});

textSearch.addEventListener('keyup', (event) => {
    if (event.key == 'Enter') {
        event.preventDefault();
        buttonSearch.click();
    }
});

buttonLucky.addEventListener('click', () => {
    const text = textSearch.value;

    fetch("http://localhost:8080/api/search?query=" + text)
    .then(response => response.json())
    .then(json => {
        console.log(json);
        let firstUrl = json[0].url
        document.location.href = firstUrl;
    });
});