//Script para redireccionar cambio de estatus dinámicamente

  $('#changeOrderStatus').on('show.bs.modal', function (event) {
    let id = $(event.relatedTarget).data('id')
    document.getElementById("changeOrderBtn").href= "/orders/update/" + id.toString()
  })
