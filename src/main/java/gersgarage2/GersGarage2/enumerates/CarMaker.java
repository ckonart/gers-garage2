package gersgarage2.GersGarage2.enumerates;

public enum CarMaker {

    AUDI("Audi"),
    BMW("BMW"),
    CUPRA("Cupra"),
    FORD("Ford"),
    HONDA("Honda"),
    HYUNDAI("Hyundai"),
    INFINITI("Infiniti"),
    ISUZU("Isuzu"),
    JAGUAR("Jaguar"),
    JEEP("Jeep"),
    KAWASAKI("Kawasaki"),
    LAND_ROVER("Land Rover "),
    LEXUS("Lexus"),
    MAZDA("Mazda"),
    MERCEDES("Mercedes"),
    MINI("Mini"),
    MITSUBISHI("Mitsubishi"),
    NISSAN("Nissan"),
    OPEL("Opel"),
    PORSCHE("Porsche"),
    SCANIA("Scania"),
    SEAT("SEAT"),
    SMART("Smart"),
    SUBARU("Subaru"),
    SUZUKI("Suzuki"),
    TOYOTA("Toyota"),
    VOLKSWAGEN("Volkswagen"),
    VOLVO("volvo"),
    YAMAHA("Yamaha"),
    OTHER("Other");

    private String displayBrand;

    CarMaker(String displayBrand) {
        this.displayBrand = displayBrand;
    }

    public String getDisplayBrand() {
        return displayBrand;
    }

    public void setDisplayBrand(String displayBrand) {
        this.displayBrand = displayBrand;
    }
}
