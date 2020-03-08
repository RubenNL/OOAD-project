package nl.rubend;


import java.util.Date;

public class VerhuurRegel extends TransactieRegel {
	private Date eindDatum;
	private int aantalDagen;

	public VerhuurRegel(int aantal, Product product,Date eindDatum) {
		super(aantal, product);
		this.eindDatum=eindDatum;
	}

	public Date geefDatum() {
		return this.eindDatum;
	}

	public int getAantalDagen() {
		return this.aantalDagen;
	}

}
