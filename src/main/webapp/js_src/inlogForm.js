const labels = document.querySelectorAll(".form-control label");
let username = document.querySelector("#usernameInput");
let password = document.querySelector("#passwordInput");
let submitBtn = document.querySelector("#BtnResp");

submitBtn.addEventListener('click', login);

labels.forEach((label) => {
    label.innerHTML = label.innerText.split("")
        .map(
            (letter, idx) => `<span style="transition-delay:${idx * 50}ms">${letter}</span>`
        )
        .join("");
});

function login(username, password) {
    fetch('/restapi/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: username,
            password: password
        })
    }).then(response => {
        if (response.status === 401) {
        //    handle foutieve username of password
            console.log("foutieve dingen");
            return;
        }
        return response.json();
    }).then(data => {
        window.sessionStorage.setItem('BearerToken', 'Bearer ' + data.token);
        window.location.replace('./zelfscanner.html');
    });
}
