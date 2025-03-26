package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.Cart;
import com.main.model.CartItem;
import com.main.model.User;
import com.main.request.AddCartItemRequest;
import com.main.request.UpdateCartItemRequest;
import com.main.service.CartService;
import com.main.service.UserService;

@RestController
@RequestMapping("/api")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;

	@PutMapping("/cart/add")
	public ResponseEntity<CartItem> addItemTocCart(@RequestBody AddCartItemRequest req,
			@RequestHeader("Authorization") String jwt) throws Exception {

		CartItem cartItem = cartService.addItemtoCart(req, jwt);

		return new ResponseEntity<CartItem>(cartItem, HttpStatus.CREATED);
	}

	@PutMapping("/cart-item/update")
	public ResponseEntity<CartItem> updateCartItemQuantity(@RequestBody UpdateCartItemRequest req,
			@RequestHeader("Authorization") String jwt) throws Exception {

		CartItem cartItem = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());

		return new ResponseEntity<CartItem>(cartItem, HttpStatus.CREATED);
	}

	@DeleteMapping("/cart-item/{id}/remove")
	public ResponseEntity<Cart> removeCartItem(@PathVariable Long id, @RequestHeader("Authorization") String jwt)
			throws Exception {

		Cart cart = cartService.removeItemFromCart(id, jwt);

		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}

	@PutMapping("/cart/clear")
	public ResponseEntity<Cart> clearcart(@RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserByJwtToken(jwt);

		Cart cart = cartService.clearCart(user.getId());

		return new ResponseEntity<>(cart, HttpStatus.CREATED);
	}

	@GetMapping("/cart")
	public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws Exception {

		User user = userService.findUserByJwtToken(jwt);

		Cart cart = cartService.findCartByUserId(user.getId());

		return new ResponseEntity<>(cart, HttpStatus.OK);
	}

}
