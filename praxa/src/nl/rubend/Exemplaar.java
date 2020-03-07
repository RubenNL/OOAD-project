package nl.rubend;

import java.util.ArrayList;
import java.util.Date;

public class Exemplaar {
	private String serieNr;
	private Date aanschafDatum;
	private String status;
	private ArrayList<VerhuurRegel> regels=new ArrayList<VerhuurRegel>();


	public Exemplaar(String serieNr, Date aanschafDatum){
		this.serieNr = serieNr;
		this.aanschafDatum = aanschafDatum;
	}


	public Boolean isVerhuurd(){
		Boolean verhuurd = null;

		if (status.equals("Verhuurd")){
			verhuurd = true;
		} else if (status.equals("Beschikbaar")){
			verhuurd = false;
		} else if (status.equals(null) || status.equals("")) {
				System.out.println("Status niet beschikbaar");
		}

		return verhuurd;
	}
}
