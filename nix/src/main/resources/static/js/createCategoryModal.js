// Cuando se cierra el popup tambien se debe cerrar el mensaje de error si existe
$('#crearCategoria').on('hidden.bs.modal', function (event) {
  var alert = document.getElementById('alert-error');
  if (typeof alert != 'undefined') {
    $('#alert-error').alert("close");
  }
})

// Si ocurrio un error, muestra el popup con la alerta del mensaje

if (document.getElementById('error').innerHTML != null) {
  $("#crearCategoria").modal();
}
