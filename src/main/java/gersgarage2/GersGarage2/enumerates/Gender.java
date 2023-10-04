package gersgarage2.GersGarage2.enumerates;

public enum Gender {

    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private String displayGender;

    private Gender(String displayGender){
        this.displayGender = displayGender;
    }

    public String getDisplayGender(){
        return displayGender;
    }
    public void setDisplayGender(java.lang.String displayGender) {
        this.displayGender = displayGender;
    }
}
