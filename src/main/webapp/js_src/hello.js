let video = document.querySelector("#videoElement");

if (navigator.mediaDevices.getUserMedia) {
    navigator.mediaDevices.getUserMedia( { video: true })
        .then(function (stream) {
            video.srcObject = stream;
        })
        .catch (function (error) {
            console.log('something went wrong!')
        })
} else {
    console.log('getUserMedia not supported!');

}

window.addEventListener('load', () => init());

function init() {
    let form = document.querySelector("#btnID2")
    let handler = (event) => form.action = event.target.value

    document.querySelector("#btnID").addEventListener("click", handler);
    document.querySelector("#btnID2").addEventListener("click", handler);

    form.addEventListener("submit", (event) => submitForm(event));
}

function submitForm(event) {
    let form = document.querySelector("#btnID2");
    let data = new URLSearchParams(new FormData(form));

    fetch(form.action + "?" + data.toString(), {method: form.method})
        .then(response => response.text())
        .then(text => console.log(text))
        .catch(error => console.log(error));

    event.preventDefault();
}