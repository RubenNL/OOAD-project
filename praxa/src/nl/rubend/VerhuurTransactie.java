package nl.rubend;

import java.util.ArrayList;
import java.util.Date;

public class VerhuurTransactie extends Transactie {
	private double borgTotaal;
	private ArrayList<VerhuurRegel> verhuurRegels=new ArrayList<VerhuurRegel>();

	public VerhuurTransactie(int trNr, double brgTt, Date datum, String tijd, String plaats) {
		super("verhuur", datum, tijd, plaats);
		this.borgTotaal = brgTt;
	}
	public VerhuurTransactie(Date datum, String tijd, String plaats) {
		super("verhuur", datum, tijd, plaats);
		this.borgTotaal=0;
	}
	public void voegVerhuurRegelToe(Product product, int aantalDagen, Date eindDatum) {
		verhuurRegels.add(new VerhuurRegel(product, aantalDagen, eindDatum));
	}
}
