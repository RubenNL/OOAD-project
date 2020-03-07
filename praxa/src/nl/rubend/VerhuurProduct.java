package nl.rubend;
import java.util.ArrayList;

public class VerhuurProduct extends Product {
	private double verhuurPrijs;
	private double borg;

	private ArrayList<Exemplaar> exemplaren;
	private static ArrayList<VerhuurProduct> alleVerhuurProducten = new ArrayList<VerhuurProduct>();

	public VerhuurProduct(String prNr, String omschr, double prs, double verhuurPrijs, double borg){
		super(prNr, omschr, prs);

		this.verhuurPrijs =verhuurPrijs;
		this.borg = borg;

		if (!alleVerhuurProducten.contains(this)) {
			alleVerhuurProducten.add(this);
		}
	}

	public static ArrayList geefAlle(){
		for (VerhuurProduct VerhuurProduct : alleVerhuurProducten) {
			alleVerhuurProducten.add(VerhuurProduct);
		}
		return alleVerhuurProducten;
	}

	public ArrayList geefBeschikbareExemplaren(){
		for(Exemplaar Exemplaar: exemplaren) {
			if (Exemplaar.isVerhuurd() == false) {
				this.exemplaren.add(Exemplaar);
			}
		}

		return exemplaren;
	}

	// 	Moet getest worden
	public boolean equals(Object other) {
		boolean gelijkeObjecten = false;
		if (other instanceof VerhuurProduct) {
			return  super.equals(other)
					&& verhuurPrijs == ((VerhuurProduct) other).verhuurPrijs;
		}
		return false;
	}


	public ArrayList<Exemplaar> getExemplaren() {
		return exemplaren;
	}

	public void setExemplaren(ArrayList<Exemplaar> exmplRn) {
		this.exemplaren = exmplRn;
	}


}
