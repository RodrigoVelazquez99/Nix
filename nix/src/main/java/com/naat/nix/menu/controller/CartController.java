package com.naat.nix.menu.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import com.naat.nix.menu.model.Cart;
import com.naat.nix.menu.model.CartID;
import com.naat.nix.menu.model.Food;
import com.naat.nix.menu.model.CartFood;
import com.naat.nix.order.controller.TakeoutService;
import com.naat.nix.order.model.Takeout;
import com.naat.nix.user.config.UserWrapper;
import com.naat.nix.user.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
* Controlador encargado de la lectura, manipulacion de platillos del carrito
*/
@Controller
@RequestMapping(value = "/carrito")
public class CartController {

	/* El servicio para manejar las operaciones sobre el carrito */
	@Autowired
	CartService cartService;

	/* El servicio para manejar las operaciones sobre los platillos */
	@Autowired
	FoodService foodService;

	/* Manejo de órdenes */
	@Autowired
	TakeoutService takeoutService;

	/* Servicio para operaciones sobre los pedidos de platillos */
	@Autowired
	CartFoodService cartFoodService;

	/* Referencia al carrito actual */
	Cart carrito;


	/**
	* Solicitud para ver el carrito.
	* Obtiene el carrito de la base de datos, lo referencia
	* a la variable 'carrito' y carga sus platillos.
	* en la vista VerCarritoIH.html.
	**/
	@RequestMapping( value = "/ver", method = RequestMethod.GET)
	public ModelAndView verCarrito(@AuthenticationPrincipal UserWrapper user) {
		User current = user.getCustomUser();
		ModelAndView modelAndView = new ModelAndView("VerCarritoIH");
		check(current);
		ArrayList<CartFood> platillos = new ArrayList<CartFood>(carrito.getCartFoods());
		boolean hayElementos = (platillos.size() == 0)? false : true;
		double total = calculaPrecio();
		modelAndView.addObject("carrito", platillos);
		modelAndView.addObject("hayElementos", hayElementos);
		modelAndView.addObject("total", Double.toString(total));
		return modelAndView;
	}


	/**
	* Solicitud para editar el carrito.
	* Carga los platillos del carrito en la vista EliminarCarritoIH
	*/
	@RequestMapping( value = "/editar", method = RequestMethod.GET)
	public ModelAndView editarCarrito() {
		ModelAndView modelAndView = new ModelAndView("EliminarCarritoIH");
		ArrayList<CartFood> platillos = new ArrayList<CartFood>(carrito.getCartFoods());
		modelAndView.addObject("carrito", platillos);
		boolean hayElementos = (platillos.size() == 0)? false : true;
		// Las url del popup
		modelAndView.addObject("aceptar", "/carrito/eliminar");
		modelAndView.addObject("hayElementos", hayElementos);
		return modelAndView;
	}

	/**
	* Solicitud para descartar un platillo.
	* Elimina el platillo del carrito.
	* @param nombre el nombre del platillo a descartar
	*/
	@RequestMapping( value = "/editar/{idPlatillo}")
	public String descartar(@PathVariable("idPlatillo") int idPlatillo) {
		Food d = foodService.obtenerPlatilloPorId (idPlatillo);
		carrito.eliminar(d);
		return "redirect:/carrito/editar";
	}

	/**
	* Solicitud para eliminar los platillos descartados del carrito.
	* Actualiza en la base de datos el carrito actual.
	*/
	@RequestMapping( value = "/eliminar")
	public String eliminar() {
		try {
			cartService.actualizar(carrito);
		} catch ( Exception e) {}
		return "redirect:/carrito/ver";
	}

	/**
	* Solicitud para agregar un platillo al carrito.
	* Obten el platillo de la base de datos, guarda en el carrito
	* y actualiza el carrito en la base de datos.
	* Finalmente deririge a la vista del menu
	* @param f el id del platillo a agregar
	*/
	@RequestMapping( value = "/agregar/{id_platillo}/{cantidad}")
	public String agregar(@PathVariable("id_platillo") int id_platillo,
	 											@PathVariable("cantidad") int cantidad, @AuthenticationPrincipal UserWrapper user) {
		User current = user.getCustomUser();
		Food p = foodService.obtenerPlatilloPorId(id_platillo);
		check(current);
		// Ya se agrego al menos un elemento de este platillo
		if (carrito.contiene (p)) {
			// Agrega la cantidad de platillos de este alimento
			carrito.sumar(p, cantidad);
		} else {
			// Crea uno e insertalo en el carrito
			CartFood c = new CartFood ();
			c.setFood (p);
			c.setCart (carrito);
			c.setAmount (cantidad);
			carrito.agregar (c);
			cartFoodService.guardar (c);
		}
		try {
		cartService.actualizar(carrito);
		} catch ( Exception e) {}
		// Simplemente mostramos el carrito sin cambios
		return "redirect:/carrito/ver";
	}

	/**
	* Revisa si el carrito del usuario actual existe
	* en la base de datos (porque puede que apenas se registro)
	* y asigna a la variable carrito el valor
	* de la base de datos
	*/
	private void check (User user) {
		// Revisa si el carrito se encuentra en la base de datos
		carrito = cartService.obtenerCarritoCorreo(user.getEmail());
		// El usuario apenas se registro
		if (carrito == null) {
			// Le asignamos un id
			ArrayList<Cart> c = (ArrayList<Cart>) cartService.obtenerCarritos();
			int id = (c.size() == 0) ? 1 : c.size() + 1;
			carrito = new Cart (new CartID (id, user.getEmail()));
			carrito = cartService.guardar(carrito);
		}
	}

	@GetMapping(value = "/ordenar")
	public String confirmaOrden(Model model,
	@AuthenticationPrincipal UserWrapper user) {

		var platillos = carrito.getCartFoods();
		var cliente = user.getCustomUser().getClient();
		var precio = calculaPrecio();
		var orden = new Takeout();
		// Pendiente !!!!!!!!!!!!!!!!!!!!!!!1
		// Hacer un parser
		//orden.setFood_items(platillos);
		orden.setDeliveryDate(LocalDate.now());
		orden.setPrice(precio);
		orden.setClient(cliente);
		//orden.setRepartidor(repartidor);
		takeoutService.save(orden);
		carrito = new Cart();
		return "redirect:/menu";
	}


	private double calculaPrecio() {
			double total = 0;
			for (CartFood f : carrito.getCartFoods()) {
					total += f.getFood().getPrecio() * f.getAmount();
			}
			return total;
	}


}
