package nl.rubend;


public class VerhuurRegel extends TransactieRegel {
	private String eindDatum;
	private int aantalDagen;

	public VerhuurRegel(int aantal, double regTot) {
		super(aantal, regTot);
	}

	public String geefDatum() {
		return this.eindDatum;
	}

	public int getAantalDagen() {
		return this.aantalDagen;
	}


	public void setEindDatum(String edt) {
		this.eindDatum = edt;
	}

	public void setAantalDagen(int ad) {
		this.aantalDagen = ad;
	}
}
