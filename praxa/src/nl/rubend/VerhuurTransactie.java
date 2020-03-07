package nl.rubend;

public class VerhuurTransactie extends Transactie {
	private double borgTotaal;

//	een afgeleide attribuut
//	private double afrekenTotaal;

	public VerhuurTransactie(int trNr, double brgTt) {
		super(trNr);
		this.borgTotaal = brgTt;
	}


}
