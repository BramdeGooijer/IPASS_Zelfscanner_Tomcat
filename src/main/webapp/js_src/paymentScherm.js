import {boodschappenlijst} from "./barcodeScanner.js";

let paymentBtn = document.getElementById('paymentBtn');
let paymentScherm = document.querySelector('#paymentContainer');
let witScherm = document.querySelector('#whiteBackground');
let paidBtn = document.querySelector('#betaaltBtn');

paymentBtn.addEventListener('click', toggle);
paidBtn.addEventListener('click', toggle);
paidBtn.addEventListener('click', clear);


function toggle() {
    if (boodschappenlijst.length === 0) {
        window.alert('scan eerst iets');
    } else {
        paymentScherm.classList.toggle('schuif-in');
        witScherm.classList.toggle('schuif-in');
    }
}

function clear() {
    let ul = document.querySelector('#ProductRecapList');

    ul.innerHTML = '';

    boodschappenlijst = [];
}