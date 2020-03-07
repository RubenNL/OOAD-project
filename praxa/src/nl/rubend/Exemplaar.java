package nl.rubend;

import java.util.ArrayList;

public class Exemplaar {
	private String serieNr;
	private String aanschafDatum;
	private String status;
	private ArrayList<VerhuurRegel> regels=new ArrayList<VerhuurRegel>();


	public Exemplaar(String serieNr){
		this.serieNr = serieNr;
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
