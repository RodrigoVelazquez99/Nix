// Obtiene el nombre de la categoria a eliminar
$('#eliminarCategoria').on('show.bs.modal', function (event) {
    var boton = $(event.relatedTarget);
    var nombre = boton.data('value');
    document.getElementById('aceptar_eliminar').href = "/categories/edit/delete/" + nombre.toString();
})

// Inserta el nombre de la categoria actual en las entradas del popup
$('#editarCategoria').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget);
  var nombre = button.data('value');
  if (typeof nombre != 'undefined') {
    document.getElementById("inputNewCategory").value = nombre.toString();
    document.getElementById("inputOldCategory").value = nombre.toString();
  }
})

// Cuando se cierra el popup tambien se debe cerrar el mensaje de error si existe
$('#editarCategoria').on('hidden.bs.modal', function (event) {
  var alert = document.getElementById('alert-error');
  if (typeof alert != 'undefined') {
    $('#alert-error').alert("close");
  }
})

// Si ocurrio un error, muestra el popup con la alerta del mensaje
var error = document.getElementById('error').innerHTML;
if (error != null) {
  $("#editarCategoria").modal();
}
