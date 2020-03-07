package nl.rubend;

import java.util.ArrayList;

public class Klant {
	private String naam;
	private String adres;
	private String telefoonNr;

	private ArrayList<Transactie> transacties;

	public Klant(String nm, String adr, String tel) {
		this.naam = nm;
		this.adres = adr;
		this.telefoonNr = tel;
	}


	public ArrayList<Transactie> getTransacties() {
		return transacties;
	}

	public void setTransacties(ArrayList<Transactie> trs) {
		this.transacties = trs;
	}

}
