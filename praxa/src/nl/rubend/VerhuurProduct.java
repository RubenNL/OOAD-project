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

	public static ArrayList geefAlle() {
		ArrayList<String> omschrijvingen=new ArrayList<String>();
		for(Product product:alleVerhuurProducten) {
			omschrijvingen.add(product.getOmschrijving());
		}
		return omschrijvingen;
	}

	// Geeft een lijst met beschikbare exemplaren terug
	public ArrayList geefBeschikbareExemplaren(){
		ArrayList<Exemplaar> beschikbaar=new ArrayList<Exemplaar>();
		for(Exemplaar Exemplaar: exemplaren) {
			if (Exemplaar.beschikbaar()) beschikbaar.add(Exemplaar);
		}
		return beschikbaar;
	}

	// 	Moet getest worden
	//	Mist exemplaren vergelijken
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

	public void addExemplaar(Exemplaar exemplaar) {
		this.exemplaren.add(exemplaar);
	}
}
