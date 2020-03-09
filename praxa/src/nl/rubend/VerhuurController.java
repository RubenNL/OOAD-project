package nl.rubend;

import java.util.ArrayList;

public class VerhuurController {
	public ArrayList<Product> start() {
		return VerhuurProduct.geefAlle();
	}
	public Product product;
	public Exemplaar exemplaar;
}