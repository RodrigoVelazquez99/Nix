package com.naat.nix.menu.controller;

import java.util.ArrayList;

import com.naat.nix.menu.model.Cart;
import com.naat.nix.menu.model.CartID;
import com.naat.nix.menu.model.Food;
import com.naat.nix.user.config.UserWrapper;
import com.naat.nix.user.model.User;

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

	/* Manejo de órdenes */
	//@Autowired
	//TakeoutService takeoutService;

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
		ModelAndView modelAndView = new ModelAndView("cart");
		check(current);
		ArrayList<Food> platillos = new ArrayList<Food>(carrito.getFoods());
		modelAndView.addObject("carrito", platillos);
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
		ArrayList<Food> platillos = new ArrayList<Food>(carrito.getFoods());
		modelAndView.addObject("carrito", platillos);
		// Las url del popup
		modelAndView.addObject("aceptar", "/cart/delete");
		return modelAndView;
	}

	/**
	 * Solicitud para descartar un platillo.
	 * Elimina el platillo del carrito.
	 * @param nombre El nombre del platillo a descartar
	 * @return Nombre de plantilla a redireccionar
	 */
	@RequestMapping( value = "/edit/{name}")
	public String discard(@PathVariable("name") String nombre) {
		carrito.deleteByName(nombre);
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
	 * @param id El identificador del platillo a agregar
	 * @param user Usuario actual
	 * @return Nombre de plantilla a redireccionar
	 */
	@RequestMapping( value = "/add/{id}")
	public String add(@PathVariable("id") int id,
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
	@GetMapping(value = "/order")
	public String confirmOrder(@AuthenticationPrincipal UserWrapper user) {
		
		cartService.order(carrito, user.getCustomUser().getClient());
		carrito = new Cart();
		return "redirect:/menu";
	}
}
