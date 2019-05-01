package Programm;

public class Posten {
	
	private String name;
	private double preis;
	
	public Posten(String name, double preis) {
		this.name = name;
		this.preis = preis;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	protected void setPreis(double preis) {
		this.preis = preis;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getPreis() {
		return this.preis;
	}

	@Override
	public String toString() {
		return  name +"  " + preis + "€";
	}

}
