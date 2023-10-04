package gersgarage2.GersGarage2.enumerates;

public enum Fuel {

    DIESEL("Diesel"),
    PETROL("Petrol"),
    HYBRID("Hybrid"),
    ELECTRIC("Electric");


    private String displayFuel;

    private Fuel(String displayFuel) {
        this.displayFuel = displayFuel;
    }

    public String getDisplayFuel() {
        return displayFuel;
    }

    public void setDisplayEngine(String displayFuel) {
        this.displayFuel = displayFuel;
    }
}
