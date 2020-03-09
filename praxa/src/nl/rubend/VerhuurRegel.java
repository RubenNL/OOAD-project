package nl.rubend;


import java.util.Date;
import java.util.concurrent.TimeUnit;

public class VerhuurRegel extends TransactieRegel {
	private Date eindDatum;
	private Date startDatum;
	public VerhuurRegel(int aantal, double regTot,Date startDatum, Date eindDatum) {
		super(aantal, regTot);
		this.startDatum=startDatum;
		this.eindDatum=eindDatum;
	}
	public int getAantalDagen() {
		long diff = eindDatum.getTime() - startDatum.getTime();
		return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	public Date geefDatum() {
		return this.eindDatum;
	}

}
