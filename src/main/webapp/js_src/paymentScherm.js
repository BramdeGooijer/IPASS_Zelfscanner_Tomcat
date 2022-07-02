import {boodschappenlijst} from "./barcodeScanner.js";

let paymentBtn = document.getElementById('paymentBtn');
let paymentScherm = document.querySelector('#paymentContainer');
let witScherm = document.querySelector('#whiteBackground');
let paidBtn = document.querySelector('#betaaltBtn');

paymentBtn.addEventListener('click', toggleOn);
paidBtn.addEventListener('click', toggleOff);


function toggleOn() {
    if (boodschappenlijst.length === 0) {
        window.alert('Scan an item before paying!');
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
        window.alert('Enter a valid barcode and price!');
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
                window.alert('Product not found / product does not exist');
            } else if (response.status === 401) {
                window.alert('Something went wrong!');
            } else if (response.status === 200) {
                window.alert('The price has been changed');
            }
        })
    }
})

let productToevoegenBtn = document.querySelector('#productToevoegenBtn');

productToevoegenBtn.addEventListener('click', () => {
    let barcode = document.querySelector('#barcodeInput').value;
    console.log(barcode);
    let prijs = document.querySelector('#prijsInput').value;
    console.log(prijs);
    let naam = document.querySelector('#naamInput').value;
    console.log(naam);
    let beschrijving = document.querySelector('#beschrijvingInput').value;
    console.log(beschrijving)

    if (barcode === '' || prijs === '' || naam === '' || beschrijving === '') {
        window.alert('Please enter all necessary information: name, price, barcode and description!');
    } else {
        fetch('/restapi/product', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': window.sessionStorage.getItem('AdminToken')
            },
            body: JSON.stringify({
                naam: naam,
                prijs: prijs,
                beschrijving: beschrijving,
                barcode: barcode
            })
        }).then(response => {
            if (response.status === 401) {
                window.alert('The product with this barcode already exists');
            } else if (response.status === 400) {
                window.alert('Something went wrong!');
            } else if (response.status === 200) {
                window.alert('The product has been added!');
            }
        })
    }

})

let productVerwijderenBtn = document.querySelector('#productVerwijderenBtn');

productVerwijderenBtn.addEventListener('click', () => {
    let barcode = document.querySelector('#barcodeInput').value;
    console.log(barcode);

    if (barcode === '') {
        window.alert('Please enter a barcode!')
    } else {
        fetch(`/restapi/product/${barcode}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': window.sessionStorage.getItem('AdminToken')
            }
        }).then(response => {
            if (response.status === 200) {
                window.alert('The product has been removed!');
            } else if (response.status === 404) {
                window.alert('Product not found / barcode incorrect!');
            } else if (response.status === 400) {
                window.alert('Something went wrong!');
            }
        });
    }
})