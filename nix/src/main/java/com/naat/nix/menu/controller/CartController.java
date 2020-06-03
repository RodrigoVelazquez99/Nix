package com.naat.nix.menu.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import com.naat.nix.menu.model.Cart;
import com.naat.nix.menu.model.CartID;
import com.naat.nix.menu.model.Food;
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
@RequestMapping(value = "/cart")
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

	/* Referencia al carrito actual */
	Cart carrito;


	/**
	* Solicitud para ver el carrito.
	* Obtiene el carrito de la base de datos, lo referencia
	* a la variable 'carrito' y carga sus platillos.
	* en la vista VerCarritoIH.html.
	**/
	@RequestMapping( value = "", method = RequestMethod.GET)
	public ModelAndView verCarrito(@AuthenticationPrincipal UserWrapper user) {
		User current = user.getCustomUser();
		ModelAndView modelAndView = new ModelAndView("cart");
		check(current);
		ArrayList<Food> platillos = new ArrayList<Food>(carrito.getFoods());
		modelAndView.addObject("carrito", platillos);
		return modelAndView;
	}


	/**
	* Solicitud para editar el carrito.
	* Carga los platillos del carrito en la vista EliminarCarritoIH
	*/
	@RequestMapping( value = "/edit", method = RequestMethod.GET)
	public ModelAndView editarCarrito() {
		ModelAndView modelAndView = new ModelAndView("cart_delete");
		ArrayList<Food> platillos = new ArrayList<Food>(carrito.getFoods());
		modelAndView.addObject("carrito", platillos);
		// Las url del popup
		modelAndView.addObject("aceptar", "/cart/delete");
		return modelAndView;
	}

	/**
	* Solicitud para descartar un platillo.
	* Elimina el platillo del carrito.
	* @param nombre el nombre del platillo a descartar
	*/
	@RequestMapping( value = "/edit/{name}")
	public String descartar(@PathVariable("name") String nombre) {
		carrito.deleteByName(nombre);
		return "redirect:/cart/edit";
	}

	/**
	* Solicitud para eliminar los platillos descartados del carrito.
	* Actualiza en la base de datos el carrito actual.
	*/
	@RequestMapping( value = "/delete")
	public String eliminar() {
		try {
			cartService.update(carrito);
		} catch ( Exception e) {
			/* Llamada a la pagina de error del sistema */
			return "redirect:/error";
		}
		return "redirect:/cart";
	}

	/**
	* Solicitud para agregar un platillo al carrito.
	* Obten el platillo de la base de datos, guarda en el carrito
	* y actualiza el carrito en la base de datos.
	* Finalmente deririge a la vista del menu
	* @param f el id del platillo a agregar
	*/
	@RequestMapping( value = "/add/{id}")
	public String agregar(@PathVariable("id") int id,
	@AuthenticationPrincipal UserWrapper user) {
		User current = user.getCustomUser();
		Food p = foodService.getFoodById(id);
		check(current);
		carrito.add(p);
		// Puede que ya se haya agregado el platillo
		try {
			carrito = cartService.update(carrito);
		} catch ( Exception e) { }
		// Simplemente mostramos el carrito sin cambios
		return "redirect:/cart";
	}

	/**
	* Revisa si el carrito del usuario actual existe
	* en la base de datos (porque puede que apenas se registro)
	* y asigna a la variable carrito el valor
	* de la base de datos
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

	@GetMapping(value = "/order")
	public String confirmaOrden(Model model,
	@AuthenticationPrincipal UserWrapper user) {
		
		var platillos = carrito.getFoods();
		var cliente = user.getCustomUser().getClient();
		var precio = calculaPrecio();
		var orden = new Takeout();
		orden.setFoodItems(platillos);
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
			for (Food f:carrito.getFoods()) {
					total+=f.getPrice();
			}
			return total;
	}
	

}
