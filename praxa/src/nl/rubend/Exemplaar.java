package nl.rubend;

import java.util.ArrayList;
import java.util.Date;

public class Exemplaar {
	private Date aanschafDatum;
	private int serieNr;
	private String status;
	private ArrayList<VerhuurRegel> regels=new ArrayList<VerhuurRegel>();
}
