export let boodschappenlijst = [];

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
                //maak hier code voor product niet gevonden-9
                console.log('product niet gevonden');
                return null;
            }
        }).then(data => {
          if (data !== null) {
              let amount = 0;
              let toegevoegd = false;

              for (let i = 0; i < boodschappenlijst.length; i++) {
                  if (boodschappenlijst[i][0] === data.barcode) {
                      boodschappenlijst[i][1]++;
                      amount = boodschappenlijst[i][1];

                      let frontendAmount = document.querySelector(`#${data.barcode}Amount`);

                      frontendAmount.textContent = amount;

                      toegevoegd = true;
                  }
              }

              if (toegevoegd === false) {
                  let item = [data.barcode, 1];
                  amount = 1;
                  boodschappenlijst.push(item);

                  const ul = document.querySelector("#items");

                  const li = document.createElement("li");
                  li.classList.add("item");
                  li.setAttribute('id', data.naam)
                  li.appendChild(document.createTextNode(data.naam));

                  const itemAmountDiv = document.createElement("div");
                  itemAmountDiv.classList.add("itemAmount");
                  li.appendChild(itemAmountDiv);

                  const priceDiv = document.createElement("div");
                  priceDiv.appendChild(document.createTextNode("€" + data.prijs));
                  priceDiv.classList.add("price");
                  const decreaseDiv = document.createElement("div");
                  decreaseDiv.appendChild(document.createTextNode("-"));
                  decreaseDiv.setAttribute('id', `${data.barcode}DecreaseBtn`);
                  decreaseDiv.classList.add("decrease");
                  decreaseDiv.classList.add("itemBubble");
                  const amountDiv = document.createElement("div");
                  amountDiv.appendChild(document.createTextNode(amount));
                  amountDiv.setAttribute('id', `${data.barcode}Amount`);
                  amountDiv.classList.add("amount");
                  amountDiv.classList.add("itemBubble");
                  const increaseDiv = document.createElement("div");
                  increaseDiv.appendChild(document.createTextNode("+"));
                  increaseDiv.setAttribute('id', `${data.barcode}IncreaseBtn`);
                  increaseDiv.classList.add("increase");
                  increaseDiv.classList.add("itemBubble");

                  itemAmountDiv.appendChild(priceDiv);
                  itemAmountDiv.appendChild(decreaseDiv);
                  itemAmountDiv.appendChild(amountDiv);
                  itemAmountDiv.appendChild(increaseDiv);

                  ul.appendChild(li);

                  let barcode = data.barcode;
                  let minknop = document.querySelector(`#${barcode}DecreaseBtn`);
                  let plusknop = document.querySelector(`#${barcode}IncreaseBtn`);
                  let productAmount = document.querySelector(`#${barcode}Amount`);

                  minknop.addEventListener('click', () => {
                      for (let i = 0; i < boodschappenlijst.length; i++) {
                          if (boodschappenlijst[i][0] === barcode && boodschappenlijst[i][1] > 0) {
                              boodschappenlijst[i][1]--;
                              productAmount.textContent = boodschappenlijst[i][1];
                              if (boodschappenlijst[i][1] === 0) {
                                  let index = boodschappenlijst.indexOf(boodschappenlijst[i]);

                                  boodschappenlijst.splice(index, 1);

                                  let zeroItem = document.querySelector(`#${data.naam}`);
                                  ul.removeChild(zeroItem);
                              }
                          }
                      }
                      console.log(boodschappenlijst);
                  })

                  plusknop.addEventListener('click', () => {
                      for (let i = 0; i < boodschappenlijst.length; i++) {
                          if (boodschappenlijst[i][0] === barcode) {
                              boodschappenlijst[i][1]++;
                              productAmount.textContent = boodschappenlijst[i][1];
                          }
                      }
                      console.log(boodschappenlijst);
                  })

              }

              console.log(boodschappenlijst);
          }
    })

}

paymentBtn.addEventListener('click', procesData);

function procesData() {
    let productRecapList = document.querySelector('#ProductRecapList');
    for (let i = 0; i < boodschappenlijst.length; i++) {
        fetch(`/restapi/product/${boodschappenlijst[i][0]}`)
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    console.log('er is iets fout gegaan');
                    return null;
                }
            }).then(data => {
                if (data !== null) {
                    let li = document.createElement('li');
                    li.innerHTML = `<span class="recapListItemname">${data.naam}</span><span class="recapListItemAmount">${boodschappenlijst[i][1]}x</span><span class="recapListItemPrice">€${data.prijs}</span>`;

                    productRecapList.appendChild(li);
                }
        })
    }
}

let paidBtn = document.querySelector('#betaaltBtn');

paidBtn.addEventListener('click', clear);

function clear() {
    let ulRecap = document.querySelector('#ProductRecapList');
    let ulBoodschappenlijst = document.querySelector('#items');

    ulRecap.innerHTML = '';
    ulBoodschappenlijst.innerHTML = '';

    boodschappenlijst = [];
}