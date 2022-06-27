export function loadQuagga() {
    Quagga.init({
        inputStream : {
            name : "Live",
            type : "LiveStream",
            target: document.querySelector("#videoTag")
        },
        frequency: 1,
        decoder : {
            readers : ["code_128_reader"]
        }
    }, function(err) {
        if (err) {
            console.log(err);
            return
        }
        console.log("Initialization finished. Ready to start");
        Quagga.start();
    });
}

Quagga.onDetected(function(result) {
    let last_code = result.codeResult.code;
    console.log(last_code);
    handleBarcode(last_code);
});

function handleBarcode(barcode) {
    //fetch hier naar de resource die de info door geeft van het product

    fetch(`/restapi/product/${barcode}`)
        .then(response => {
            if (response.status === 200) {
                return response.json();
            } else if (response.status === 404) {
                console.log('product niet gevonden');
                return null;
            }
        }).then(data => {
          if (data !== null) {
              console.log(data);

              const ul = document.querySelector("#items");

              const li = document.createElement("li");
              li.classList.add("item");
              li.appendChild(document.createTextNode(data.naam));

              const itemAmountDiv = document.createElement("div");
              itemAmountDiv.classList.add("itemAmount");
              li.appendChild(itemAmountDiv);

              const priceDiv = document.createElement("div");
              priceDiv.appendChild(document.createTextNode("€" + data.prijs));
              priceDiv.classList.add("price");
              const decreaseDiv = document.createElement("div");
              decreaseDiv.appendChild(document.createTextNode("-"));
              decreaseDiv.classList.add("decrease");
              decreaseDiv.classList.add("itemBubble");
              const amountDiv = document.createElement("div");
              amountDiv.appendChild(document.createTextNode("0"));
              amountDiv.classList.add("amount");
              amountDiv.classList.add("itemBubble");
              const increaseDiv = document.createElement("div");
              increaseDiv.appendChild(document.createTextNode("+"));
              increaseDiv.classList.add("increase");
              increaseDiv.classList.add("itemBubble");

              itemAmountDiv.appendChild(priceDiv);
              itemAmountDiv.appendChild(decreaseDiv);
              itemAmountDiv.appendChild(amountDiv);
              itemAmountDiv.appendChild(increaseDiv);

              ul.appendChild(li);
          }
    })

}