package Models;

import java.time.LocalDateTime;
import java.util.HashSet;

public class FinalOrder {

	private double totalAmount;
	private LocalDateTime checkoutDate;
	private HashSet<CartOrder> cartOrders;

	public FinalOrder() {
		
		this.checkoutDate = LocalDateTime.now();
		this.cartOrders = new HashSet<CartOrder>();
		
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDateTime checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public HashSet<CartOrder> getCartOrders() {
		return cartOrders;
	}

	public void setCartOrders(HashSet<CartOrder> cartOrders) {
		this.cartOrders = cartOrders;
	}

		
	
}
