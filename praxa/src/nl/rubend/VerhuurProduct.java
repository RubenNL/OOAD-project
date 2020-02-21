package nl.rubend;

import java.util.ArrayList;

public class VerhuurProduct extends Product {
	private static ArrayList<VerhuurProduct> alleVerhuurProducten;
	private double verhuurPrijs;
	private double borg;
	private ArrayList<Exemplaar> exemplaren=new ArrayList<Exemplaar>();
}
