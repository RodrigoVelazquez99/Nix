
$('#agregarCarrito').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget);
  var nombre = button.data('nombre');
  var id = button.data('id');
  let categoria = button.data('categoria');
  var descripcion = button.data('descripcion');
  var precio = button.data('precio');
	var modalImg = document.getElementById ('modal-image');
	var foto = button.data('foto');
	modalImg.src = "http://localhost:8080/img/" + foto;
  document.getElementById("verPlatillo").href= "/cart/add/" + id.toString();
  var modal = $(this);
  modal.find('.modal-title').text(nombre);
  modal.find('.modal-descripcion').text(descripcion);
  modal.find('.modal-precio').text("Precio: $" + precio);
  modal.find('.modal-categoria').text(categoria);
})

$('a#verPlatillo').click(function(){
  var amount = document.getElementById("amount").value;
  document.getElementById("verPlatillo").href += "/" + amount.toString();
})
