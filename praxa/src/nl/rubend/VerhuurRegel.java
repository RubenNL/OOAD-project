package nl.rubend;

import java.util.Date;

public class VerhuurRegel extends TransactieRegel {
	private Date eindDatum;
	private int aantalDagen;
	private Exemplaar exemplaar;


	//moet nog gecheckt worden
	public Date geefDatum() {
		return eindDatum;
	}

}
