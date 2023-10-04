package gersgarage2.GersGarage2.enumerates;

public enum TypeVehicle {

    SEDAN("Sedan"),
    HATCH("Hatch"),
    CONVERTIBLE("Convertible"),
    MINIVAN("Minivan"),
    MINIBUS ("Minibus"),
    MOTORBIKE ("Motorbike");

    private String displayType;

    private TypeVehicle(String displayType) {
        this.displayType = displayType;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }
}
