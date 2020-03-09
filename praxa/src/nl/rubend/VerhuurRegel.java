package nl.rubend;


import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class VerhuurRegel extends TransactieRegel {
	private Date eindDatum;
	private int aantalDagen;
	private Exemplaar exemplaar;
	public VerhuurRegel(Product product, int aantalDagen, Date eindDatum) {
		super(1, product);
		this.aantalDagen=aantalDagen;
		this.eindDatum=eindDatum;
	}
	public Date geefDatum() {
		return this.eindDatum;
	}

}
