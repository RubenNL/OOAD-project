package nl.rubend;

import java.util.Date;

public class VerhuurRegel extends TransactieRegel {
	private Date eindDatum;
	private int aantalDagen;
	private Exemplaar exemplaar;

	public Date geefDatum() {
		return eindDatum;
	}

}
