import { loadQuagga } from './barcodeScanner.js';

console.log('startup')

let video = document.querySelector("#videoTag");

if (navigator.mediaDevices.getUserMedia) {
    navigator.mediaDevices.getUserMedia( { video: true })
        .then(function (stream) {
            video.srcObject = stream;
        })
        .catch (function (error) {
            console.log('something went wrong!')
        })
} else {
    console.log('getUserMedia not supported!');
}
console.log('camera done!')

loadQuagga();

console.log('quagga done');