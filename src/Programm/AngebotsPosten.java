package Programm;

public abstract class AngebotsPosten {

	protected enum Art {
		Arbeitskosten, Materialkosten
	}
	
	protected String angebotsPostenName;
	protected double angebotsPostenPreis;  // in €
	protected Art art;
	
	public AngebotsPosten(String angebotsPostenName, double angebotsPostenPreis, final Art art) {
		this.angebotsPostenName = angebotsPostenName;
		this.angebotsPostenPreis = angebotsPostenPreis;
		this.art = art;
	}
	
	public void setAngebotsPostenName(String angebotsName) {
		this.angebotsPostenName = angebotsName;
	}
	
	protected void setAngebotsPostenPreis(double angebotsPreis) {
		this.angebotsPostenPreis = angebotsPreis;
	}
	
	public String getAngebotsPostenName() {
		return this.angebotsPostenName;
	}
	
	public double getAngebotsPostenPreis() {
		return this.angebotsPostenPreis;
	}
	
	public Art getArt() {
		return art;
	}

}
