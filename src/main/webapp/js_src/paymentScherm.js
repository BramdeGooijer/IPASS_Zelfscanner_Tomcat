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

function toggleOff() {
    paymentScherm.classList.toggle('schuif-in');
    witScherm.classList.toggle('schuif-in');
}