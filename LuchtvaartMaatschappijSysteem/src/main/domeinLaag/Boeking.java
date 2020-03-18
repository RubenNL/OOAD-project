package main.domeinLaag;

public class Boeking {
	private Vlucht vlucht;
	private Integer stoelen;
	public Boeking(Vlucht vlucht, Integer stoelen) {
		this.vlucht=vlucht;
		this.stoelen=stoelen;
	}
	public Boeking() {}
	public void setVlucht(Vlucht vlucht) {
		this.vlucht=vlucht;
	}
	public Vlucht getVlucht() {return this.vlucht;}
	public void bewaar() throws IllegalStateException {
		if (vlucht == null)
			throw new IllegalStateException("vlucht niet ingevuld!");
		else if (stoelen == null || stoelen == 0)
			throw new IllegalStateException("stoelen niet goed ingevuld!");
		else
			vlucht.addBoeking(this);
	}
	public void setStoelen(Integer stoelen) {
		this.stoelen=stoelen;
	}
	public Integer getStoelen() {return stoelen;}
}
