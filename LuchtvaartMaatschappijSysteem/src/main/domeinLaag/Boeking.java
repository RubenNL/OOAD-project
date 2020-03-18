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
}
