package nl.rubend;

import java.util.Date;

public class VerhuurTransactie extends Transactie {
	private double borgTotaal;

//	een afgeleide attribuut
//	private double afrekenTotaal;

	public VerhuurTransactie(int trNr, double brgTt, Date datum, String tijd, String plaats) {
		super("verhuur", datum, tijd, plaats);
		this.borgTotaal = brgTt;
	}
	public VerhuurTransactie(Date datum, String tijd, String plaats) {
		super("verhuur", datum, tijd, plaats);
		this.borgTotaal=0;
	}
}
