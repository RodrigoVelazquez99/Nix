
  // Obtiene el nombre del platillo a eliminar
  $('#eliminarPlatillo').on('show.bs.modal', function (event) {
      var boton = $(event.relatedTarget);
      var nombre = boton.data('name');
      document.getElementById('aceptar_eliminar').href = "/foods/edit/delete/" + nombre.toString();
  })

  var curr_img;

  // Inserta los datos del platillo actual en las entradas del popup
  $('#editarPlatillo').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var name = button.data('name');
    var price = button.data('price');
    var description = button.data('description');
    var category = button.data('category');
    var img = button.data('img');
    if (typeof img == undefined) {
      img = curr_img;
    } else {
      curr_img = img;
    }
    if (typeof name != 'undefined') {
      document.getElementById("inputNewName").value = name.toString();
      document.getElementById("inputOldName").value = name.toString();
      document.getElementById("inputNewPrice").value = price;
      document.getElementById("inputNewDescription").value = description.toString();
      document.getElementById("inputNewCategory").value = category;
      document.getElementById("imageFood").src = "http://localhost:8080/img/" + img;
    }
  })

  // Cuando se cierra el popup tambien se debe cerrar el mensaje de error si existe
  $('#editarPlatillo').on('hidden.bs.modal', function (event) {
    var alert = document.getElementById('alert-error');
    if (typeof alert != 'undefined') {
      $('#alert-error').alert("close");
    }
  })


  // Si ocurrio un error, muestra el popup con la alerta del mensaje
  var error = document.getElementById('error').innerHTML;
  if (error != null) {
    $("#editarPlatillo").modal();
    document.getElementById("imageFood").src = "http://localhost:8080/img/" + curr_img;
  }
