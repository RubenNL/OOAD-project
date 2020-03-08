package nl.rubend;

import java.util.ArrayList;

public class Product {
	private String productNr;
	private String omschrijving;
	private double prijs;

	ArrayList<TransactieRegel> transactieregels;


	public Product(String prNr) {
		this.productNr = prNr;
	}
	public Product(String prNr, String omschr, double prs) {
		this.productNr = prNr;
		this.omschrijving = omschr;
		this.prijs = prs;
	}

	public double getPrijs() {
		return this.prijs;
	}
	public boolean equals(Object other) {
		if (other instanceof  Product) {
			return productNr == ((Product) other).productNr;
		}
		return false;
	}

	public ArrayList<TransactieRegel> getTransactieregels() {
		return transactieregels;
	}

	public void addTransactieRegel(TransactieRegel transactieRegel) {
		this.transactieregels.add(transactieRegel);
	}

}
