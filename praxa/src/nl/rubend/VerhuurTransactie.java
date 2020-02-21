package nl.rubend;

public class VerhuurTransactie extends Transactie {
	private double borgTotaal;
	private double afrekenTotaal;


	VerhuurTransactie(EngineSpecs specs) {
		engine = new Engine(specs);
	}
}
