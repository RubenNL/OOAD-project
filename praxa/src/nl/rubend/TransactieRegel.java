package nl.rubend;

import java.util.ArrayList;

public class TransactieRegel {
	private int aantal;
	private double regelTotaal;
	private Product product;

//	eventueel implementeren?
//	private Transactie transactie

	public TransactieRegel(int aantal, Product product) {
		this.product=product;
		this.aantal = aantal;
	}
	public double getRegelTotaal() {
		return product.getPrijs() * aantal;
	}

}
