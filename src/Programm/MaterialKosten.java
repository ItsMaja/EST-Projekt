package Programm;

public class MaterialKosten extends AngebotsPosten {

	public MaterialKosten(String angebotsName, double angebotsPreis) {
		super(angebotsName,angebotsPreis, Art.Materialkosten);
	}
	
	/**
	 * @ Maja Wandura
	 * @return A string representation of material cost
	 */
	@Override
	public String toString() {
		return super.angebotsPostenName + " für " + super.angebotsPostenPreis + "€";
	}

}
