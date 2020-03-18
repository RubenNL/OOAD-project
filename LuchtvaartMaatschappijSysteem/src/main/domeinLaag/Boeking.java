package main.domeinLaag;

public class Boeking {
	private Vlucht vlucht;
	private String klas;
	private Integer stoelen;
	public Boeking(Vlucht vlucht,String klas, Integer stoelen) {
		this.vlucht=vlucht;
		this.klas=klas;
		this.stoelen=stoelen;
	}
	public Boeking() {}
	public void setVlucht(Vlucht vlucht) {
		this.vlucht=vlucht;
	}
	public void setKlas(String klas) {
		this.klas=klas;
	}
	public void setStoelen(Integer stoelen) {
		this.stoelen=stoelen;
	}
	public void bewaar() throws IllegalStateException {
		if (vlucht == null)
			throw new IllegalStateException("vlucht niet ingevuld!");
		else if (klas == null || klas.isEmpty())
			throw new IllegalStateException("klas niet goed ingevuld!");
		else if (stoelen == null || stoelen==0)
			throw new IllegalStateException("stoelen niet goed ingevuld!");
		else
			vlucht.addBoeking(this);
	}
}
