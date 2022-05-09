console.log('de Stekker zit erin')

window.addEventListener('load', () => init());

function init() {
    let response_h3 = document.querySelector("#Smile");

    fetch("http://localhost:8080/getThatSmile")
        .then(response => response.text())
        .then(text => response_h3.innerHTML = text)
        .catch(error => response_h3.innerHTML = error);
}