package nl.rubend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Transactie {
	private int transactienr;
	private Date datum;
	private String tijd;
	private String plaats;
	private double btwTotaal;
	private String transactieType;


	// een arraylist van alle betrokken regels
	private ArrayList<TransactieRegel> regels = new ArrayList<TransactieRegel>();
	public Transactie(int trNr, String transactieType, Date datum, String tijd, String plaats) {
		this.transactienr = trNr;
		this.tijd=tijd;
		this.datum=datum;
		this.plaats=plaats;
	}
	public Transactie(String transactieType, Date datum, String tijd, String plaats) {
		this.tijd=tijd;
		this.datum=datum;
		this.plaats=plaats;
	}
	public void addTransactieRegel(int aantal, Product product) {
		regels.add( new TransactieRegel(aantal, product));
	}


	public void setBtwTotaal(double btwTotaal) {
		this.btwTotaal = btwTotaal;
	}

	public void setTransactieType(String transactieType) {
		this.transactieType = transactieType;
	}

	public Date geefDatum() {
		return this.datum;
	}

	public String geefTijd() {
		return this.tijd;
	}

}
