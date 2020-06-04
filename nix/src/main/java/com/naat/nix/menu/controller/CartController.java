package com.naat.nix.menu.controller;

import java.util.ArrayList;

import com.naat.nix.menu.model.Cart;
import com.naat.nix.menu.model.CartID;
import com.naat.nix.menu.model.Food;
import com.naat.nix.menu.model.CartFood;
import com.naat.nix.order.controller.TakeoutService;
import com.naat.nix.order.model.Takeout;
import com.naat.nix.user.config.UserWrapper;
import com.naat.nix.user.model.User;
import com.naat.nix.user.model.Client;
import com.naat.nix.user.controller.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Controlador encargado de la lectura, manipulacion de platillos del carrito
 */
@Controller
@RequestMapping(value = "/cart")
public class CartController {

	/* El servicio para manejar las operaciones sobre el carrito */
	@Autowired
	CartService cartService;

	/* El servicio para manejar las operaciones sobre los platillos */
	@Autowired
	FoodService foodService;

	/* Servicio para operaciones sobre los pedidos de platillos */
	@Autowired
	CartFoodService cartFoodService;

	/* Servicio para operaciones sobre clientes */
	@Autowired
	ClientService clientService;

	/* Referencia al carrito actual */
	Cart carrito;


	/**
	 * Solicitud para ver el carrito.
	 * Obtiene el carrito de la base de datos, lo referencia
	 * a la variable 'carrito' y carga sus platillos.
	 * en la vista VerCarritoIH.html.
	 * @param user Usuario activo
	 * @return Vista con atributos
	 */
	@RequestMapping( value = "", method = RequestMethod.GET)
	public ModelAndView seeCart(@AuthenticationPrincipal UserWrapper user) {
		User current = user.getCustomUser();
		ArrayList<String> addresses = clientService.getAddresses(current.getClient());
		ModelAndView modelAndView = new ModelAndView("cart");
		check(current);
		ArrayList<CartFood> platillos = new ArrayList<CartFood>(carrito.getCartFoods());
		boolean hayElementos = (platillos.size() == 0)? false : true;
		double total = cartService.totalPrice(carrito);
		modelAndView.addObject("addresses", addresses);
		modelAndView.addObject("carrito", platillos);
		modelAndView.addObject("hayElementos", hayElementos);
		modelAndView.addObject("total", Double.toString(total));
		return modelAndView;
	}


	/**
	 * Solicitud para editar el carrito.
	 * Carga los platillos del carrito en la vista EliminarCarritoIH
	 * @return Vista con atributos
	 */
	@RequestMapping( value = "/edit", method = RequestMethod.GET)
	public ModelAndView editCart() {
		ModelAndView modelAndView = new ModelAndView("cart_delete");
		ArrayList<CartFood> platillos = new ArrayList<CartFood>(carrito.getCartFoods());
		modelAndView.addObject("carrito", platillos);
		boolean hayElementos = (platillos.size() == 0)? false : true;
		// Las url del popup
		modelAndView.addObject("aceptar", "/cart/delete");
		modelAndView.addObject("hayElementos", hayElementos);
		return modelAndView;
	}

	/**
	* Solicitud para descartar un platillo.
	* Elimina el platillo del carrito.
	* @param nombre el nombre del platillo a descartar
	*/
	@RequestMapping( value = "/edit/{idFood}")
	public String descartar(@PathVariable("idFood") int idFood) {
		Food d = foodService.getFoodById (idFood);
		carrito.removeFood (d);
		return "redirect:/cart/edit";
	}

	/**
	 * Solicitud para eliminar los platillos descartados del carrito.
	 * Actualiza en la base de datos el carrito actual.
	 * @return Nombre de plantilla a redireccionar
	 */
	@RequestMapping( value = "/delete")
	public String delete() {
		try {
			cartService.update(carrito);
		} catch ( Exception e) {}
		return "redirect:/cart";
	}

	/**
	* Solicitud para agregar un platillo al carrito.
	* Obten el platillo de la base de datos, guarda en el carrito
	* y actualiza el carrito en la base de datos.
	* Finalmente deririge a la vista del menu
	* @param f el id del platillo a agregar
	*/
	@RequestMapping( value = "/add/{idFood}/{cantidad}")
	public String agregar(@PathVariable("idFood") int idFood,
	 											@PathVariable("cantidad") int cantidad, @AuthenticationPrincipal UserWrapper user) {
		User current = user.getCustomUser();
		Food p = foodService.getFoodById(idFood);
		check(current);
		// Ya se agrego al menos un elemento de este platillo
		if (carrito.contains (p)) {
			// Agrega la cantidad de platillos de este alimento
			carrito.addFood (p, cantidad);
		} else {
			// Crea uno e insertalo en el carrito
			CartFood c = new CartFood ();
			c.setFood (p);
			c.setCart (carrito);
			c.setAmount (cantidad);
			carrito.add (c);
			cartFoodService.save (c);
		}
		try {
		cartService.update (carrito);
		} catch ( Exception e) {}
		// Simplemente mostramos el carrito sin cambios
		return "redirect:/cart";
	}

	/**
	 * Revisa si el carrito del usuario actual existe
	 * en la base de datos (porque puede que apenas se registro)
	 * y asigna a la variable carrito el valor
	 * de la base de datos
	 * @param user Usuario dueños de los carritos
	 */
	private void check (User user) {
		// Revisa si el carrito se encuentra en la base de datos
		carrito = cartService.getCartByEmail(user.getEmail());
		// El usuario apenas se registro
		if (carrito == null) {
			// Le asignamos un id
			ArrayList<Cart> c = (ArrayList<Cart>) cartService.getCarts();
			int id = (c.size() == 0) ? 1 : c.size() + 1;
			carrito = new Cart (new CartID (id, user.getEmail()));
			carrito = cartService.save(carrito);
		}
	}

	/**
	 * Toma los contenidos del carrito actual y crea una orden con ellos
	 * @param user Usuario actual
	 */
	@GetMapping(value = "/order/{address}")
	public String confirmOrder(@PathVariable("address") String address, @AuthenticationPrincipal UserWrapper user) {
		cartService.order(carrito, user.getCustomUser().getClient(), address);
		return "redirect:/menu";
	}
}
