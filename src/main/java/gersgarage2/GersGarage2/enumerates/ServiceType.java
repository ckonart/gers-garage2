package gersgarage2.GersGarage2.enumerates;

public enum ServiceType {

    ANNUAL_SERVICE("Annual Service", 150.00),
    MAJOR_SERVICE("Major Service", 300.00),
    REPAIR("Repair", 250.00),
    MAJOR_REPAIR("Major Repair", 500.00),
    DETAILING("Major Repair", 130.00);

    private String type;
    private double price;

    private ServiceType(){
    }
    private ServiceType(String type, double price){
        this.type = type;
        this.price = price;
    }

    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
