import {boodschappenlijst} from "./barcodeScanner.js";

let paymentBtn = document.getElementById('paymentBtn');
let paymentScherm = document.querySelector('#paymentContainer');
let paidBtn = document.querySelector('#betaaltBtn');

paymentBtn.addEventListener('click', toggle);
paidBtn.addEventListener('click', toggle)


function toggle() {
    if (boodschappenlijst.length === 0) {
        window.alert('scan eerst iets');
    } else {
        paymentScherm.classList.toggle('schuif-in');
    }
}
