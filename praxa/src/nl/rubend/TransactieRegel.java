package nl.rubend;

public class TransactieRegel {
	private int aantal;
	private double regelTotaal;
	private Product product;
	private Transactie transactie;

	TransactieRegel(Transactie tr) {
	    this.transactie = tr;
    }


}
