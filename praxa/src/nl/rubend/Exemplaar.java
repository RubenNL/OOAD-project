package nl.rubend;

import java.util.ArrayList;
import java.util.Date;

public class Exemplaar {
	private String serieNr;
	private Date aanschafDatum;
	private boolean verhuurd;
	private boolean verhuurbaar;
	private ArrayList<VerhuurRegel> regels=new ArrayList<VerhuurRegel>();


	public Exemplaar(String serieNr, Date aanschafDatum){
		this.serieNr = serieNr;
		this.aanschafDatum = aanschafDatum;
	}

	public Boolean beschikbaar() {
		return !verhuurd && verhuurbaar;
	}
	public Boolean isVerhuurd() {
		return verhuurd;
	}
}
