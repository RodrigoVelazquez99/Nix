

  // Dinamicamente crea una entrada para que el usuario pueda agregar otra dirección
  var counter = 1;
  addAddressButton = document.getElementById('addAddress');

  /**
  * Remueve el area de texto.
  **/
  function removeAddress () {
    id = event.srcElement.getAttribute('inputDiv');
    inputr = document.getElementById (id);
    inputr.parentNode.removeChild (inputr);
  }

  /**
  * Crea una nueva area de texto con un boton de eliminar y la agrega al formulario.
  **/
  function newAddress () {
    addAddress = document.getElementById('addresses');
    var str = "<div " + "id='"+ counter.toString() + "'" + " class='form-group pt-3'>" +
                " <textarea type='text' name='addresses' path='addresses' rows='3' cols='40' class='form-control'" +
                  " th:field='*{addresses}' placeholder='Ingresa una direccion' required></textarea>" +
                "<button class='btn btn-danger offset-xl-9' inputDiv='" + counter.toString() +
                  "' onclick='removeAddress()' >Descartar</button> " +
              "</div>";
    addAddress.insertAdjacentHTML('beforeend', str);
    counter++;
  }
  addAddressButton.addEventListener ('click', newAddress);
