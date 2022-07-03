fetch('/restapi/login/validateToken', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
        'Authorization': window.sessionStorage.getItem('token')
    }
})
    .then(response => {
    if (response.status === 403) {
        // console.log('status 403');
        window.location.replace("./index.html");
    } else if (response.status === 200) {
        // console.log("je bent ingelogd");
    }
})