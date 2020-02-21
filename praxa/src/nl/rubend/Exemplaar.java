package nl.rubend;

import java.util.ArrayList;
import java.util.Date;

public class Exemplaar {
	private Date aanschafDatum;
	private int serieNr;
	private String status;
	private ArrayList<VerhuurRegel> regels=new ArrayList<VerhuurRegel>();

	public Exemplaar(){

	}

	public Boolean isVerhuurd(){
		Boolean verhuurd = null;

		if (status.equals("Verhuurd")){
			verhuurd = true;
		} else if (status.equals("Beschikbaar")){
			verhuurd = false;
		} else if (status.equals("") || status.equals(null)) {
				System.out.println("Status niet beschikbaar");
		}
		return verhuurd;
	}
}
