package nl.rubend;

import java.util.ArrayList;

public class VerhuurProduct extends Product {
	private static ArrayList<VerhuurProduct> alleVerhuurProducten = new ArrayList<VerhuurProduct>();
	private double verhuurPrijs;
	private double borg;
	private ArrayList<Exemplaar> exemplaren = new ArrayList<Exemplaar>();

	public VerhuurProduct(){

	}

	public static ArrayList geefAlle(){
		for (VerhuurProduct VerhuurProduct : alleVerhuurProducten) {
			alleVerhuurProducten.add(VerhuurProduct);
		}
		return alleVerhuurProducten;
	}

	public ArrayList geefBeschikbareExemplaren(){
		for(Exemplaar Exemplaar: exemplaren){
			if (Exemplaar.isVerhuurd() == false){
				exemplaren.add(Exemplaar);
			}
		}

		return exemplaren;
	}
}
