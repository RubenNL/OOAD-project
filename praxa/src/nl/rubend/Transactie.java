package nl.rubend;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Transactie {
	private int transactienr;
	private Date datum;
	private LocalTime tijd;
	private String plaats;
	private double totaal;
	private double btwTotaal;
	private String transactieType;
	private ArrayList<TransactieRegel> regels = new ArrayList<TransactieRegel>();
}
