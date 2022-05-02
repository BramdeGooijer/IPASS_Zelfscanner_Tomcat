let video = document.querySelector("#videoElement");

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