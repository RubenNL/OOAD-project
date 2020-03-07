package nl.rubend;

import java.time.LocalDate;
import java.util.ArrayList;

public class Transactie {
	private int transactienr;
	private String datum;
	private String tijd;
	private String plaats;
//	private double totaal;                     // een afgeleide attribuut
	private double btwTotaal;
	private String transactieType;


	// een arraylist van alle betrokken regels
	private ArrayList<TransactieRegel> regels = new ArrayList<TransactieRegel>();

//	de constructor moet waarschijnlijk nog aangepast wordern
	public Transactie(int trNr) {
		this.transactienr = trNr;
	}

	// de realisatie van de compositie relatie
	public void addTransactieRegel(int aantal, double regTot) {
		regels.add( new TransactieRegel(aantal, regTot));
	}


	public void setBtwTotaal(double btwTotaal) {
		this.btwTotaal = btwTotaal;
	}

	public void setTijd(String td) {
		this.tijd = td;
	}

	public void setDatum(String dt) {
		this.datum = dt;
	}

	public void setPlaats(String plaats) {
		this.plaats = plaats;
	}



	public void setTransactieType(String transactieType) {
		this.transactieType = transactieType;
	}

	public String geefDatum() {
		return this.datum;
	}

	public String geefTijd() {
		return this.tijd;
	}

//	Dit is een echter een aggregatie relatie en geen compositie, check  link hieronder voor een gedetailleerde uitleg:
//	https://www.reddit.com/r/javahelp/comments/5vrc69/need_help_understanding_aggregation_versus/
//	public void voegRegelToe(TransactieRegel regel) {
//		regels.add(regel);
//	}


}
