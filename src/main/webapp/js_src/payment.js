// console.log("hij doet het");

// fetch('https://api-sandbox.abnamro.com/v2/tikkie/paymentrequests', {
//     method: 'POST',
//     headers: {
//         'nru7vQI56ZAoUCfeHVJINgVeUeDLqFsa': 'true',
//         'X-App-Token': 'a28b5137-b598-4a66-9b72-dc03d3ae7273',
//         'Content-Type': 'application/json',
//         'API-KEY': 'nru7vQI56ZAoUCfeHVJINgVeUeDLqFsa',
//         'Access-Control-Allow-Origin': 'localhost'
//     },
//     body: JSON.stringify({
//         "description": "test payment",
//         "expiryDate": "2022-06-05",
//         "referenceId": "test1"
//     })
// }).then(res => {
//     return res.json()
// }).then(data => console.log(data))

var myHeaders = new Headers();
myHeaders.append("API-KEY", "nru7vQI56ZAoUCfeHVJINgVeUeDLqFsa");
myHeaders.append("nru7vQI56ZAoUCfeHVJINgVeUeDLqFsa", "true");
myHeaders.append("Access-Control-Allow-Origin", "https://api-sandbox.abnamro.com/v2/tikkie")

var requestOptions = {
    method: 'POST',
    headers: myHeaders,
    redirect: 'follow'
};

fetch("https://api-sandbox.abnamro.com/v2/tikkie/sandboxapps", requestOptions)
    .then(response => response.text())
    .then(result => console.log(result))
    .catch(error => console.log('error', error));