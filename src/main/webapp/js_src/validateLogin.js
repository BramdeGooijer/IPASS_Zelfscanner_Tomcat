fetch('/restapi/login/validate', {
    method: 'POST',
    headers: {
        'Authorization': window.sessionStorage.getItem('BearerToken')
    }
})
    .then(response => {
    if (response.status === 403) {
        console.log('status 403');
        // window.location.replace("./index.html");
    } else if (response.status === 200) {
        console.log("je bent ingelogd");
    }
})