package Programm;

public class ArbeitsPosten extends Posten {

    private double stunden;
    private double stundenlohn;

    public ArbeitsPosten(String name, double stundenlohn, double stunden) {
        super(name, stundenlohn * stunden);
        this.stundenlohn = stundenlohn;
        this.stunden = stunden;
    }

    public double getStunden() {
        return stunden;
    }

    public void setStunden(double stunden) {
        this.stunden = stunden;
        this.setPreis(stunden * stundenlohn);
    }

    @Override
    public String toString() {
        return this.getName() + " " + stunden + " x " + stundenlohn + "€: " + this.getPreis() + "€";
    }
}
