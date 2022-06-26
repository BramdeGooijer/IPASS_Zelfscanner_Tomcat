const labels = document.querySelectorAll(".form-control label");
let submitBtn = document.querySelector("#BtnResp");

submitBtn.addEventListener('click', login);

labels.forEach((label) => {
    label.innerHTML = label.innerText.split("")
        .map(
            (letter, idx) => `<span style="transition-delay:${idx * 50}ms">${letter}</span>`
        )
        .join("");
});

function login() {
    let username = document.querySelector("#usernameInput").value;
    let password = document.querySelector("#passwordInput").value;
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
            window.alert('Username or password incorrect!')
            return null;
        }
        return response.json();
    }).then(data => {
        if (data !== null) {
            window.sessionStorage.setItem('BearerToken', 'Bearer ' + data.token);
            window.location.replace('./zelfscanner.html');
        }
    });
}
