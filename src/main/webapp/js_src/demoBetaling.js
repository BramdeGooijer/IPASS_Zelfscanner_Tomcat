let button = document.querySelector("#paymentBtn");
let qrCodeSpace = document.querySelector("#qrCodeSpace");

function betaal() {
    fetch('http://localhost:8080/tikkieAPI/generateQR', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
                description: 'Testing through website',
                expiryDateTime: '2022-06-07T12:00:00.000Z',
                amountInCents: 1
        }
        )
    })
        .then(response => response.json())
        .then(data => {
            console.log(data.url);
            let img = document.createElement("img");
            img.src = data.url;
            img.width = 220;
            img.height = 220;
            qrCodeSpace.append(img);
        });
}

button.addEventListener('click', betaal);