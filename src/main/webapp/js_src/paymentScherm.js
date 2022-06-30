import {boodschappenlijst} from "./barcodeScanner.js";

let paymentBtn = document.getElementById('paymentBtn');
let paymentScherm = document.querySelector('#paymentContainer');
let witScherm = document.querySelector('#whiteBackground');
let paidBtn = document.querySelector('#betaaltBtn');

paymentBtn.addEventListener('click', toggleOn);
paidBtn.addEventListener('click', toggleOff);


function toggleOn() {
    if (boodschappenlijst.length === 0) {
        window.alert('scan eerst iets');
    } else {
        paymentScherm.classList.toggle('schuif-in');
        witScherm.classList.toggle('schuif-in');
    }
}

let aantal = 0;
let prijs = 0;

for (let i = 0; i < boodschappenlijst.length; i++) {
    prijs += boodschappenlijst[i][1] * boodschappenlijst[i][2];
    aantal += boodschappenlijst[i][1];
}

function toggleOff() {
    paymentScherm.classList.toggle('schuif-in');
    witScherm.classList.toggle('schuif-in');
    fetch('/restapi/transactie', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            totaalAantal: aantal,
            totaalPrijs: prijs
        })
    }).then(response => {
        if(response.status === 200) {
            console.log('ok');
        } else {
            console.log('iets anders');
        }
    })
}

let prijsWijzigenBtn = document.querySelector('#prijsWijzigenBtn');

prijsWijzigenBtn.addEventListener('click', () => {
    let barcode = document.querySelector('#barcodeInput').value;
    console.log(barcode);
    let prijs = document.querySelector('#prijsInput').value;
    console.log(prijs);

    if (barcode === '' || prijs === '') {
        window.alert('vul een geldige barcode en prijs in');
    } else {
        fetch('/restapi/product/prijswijzigen', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': window.sessionStorage.getItem('AdminToken')
            },
            body: JSON.stringify({
                barcode: barcode,
                prijs: prijs
            })
        }).then(response => {
            if (response.status === 404) {
                window.alert('product is niet gevonden / product bestaat niet!');
            } else if (response.status === 401) {
                window.alert('er is iets mis gegaan!');
            } else if (response.status === 200) {
                window.alert('De prijs is gewijzigd');
            }
        })
    }
})