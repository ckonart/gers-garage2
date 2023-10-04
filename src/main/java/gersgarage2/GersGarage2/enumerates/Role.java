package gersgarage2.GersGarage2.enumerates;

public enum Role {

    ADMIN("Admin"),
    STAFF("Staff"),
    CLIENT("Client");

    private String displayRole;

    private Role(String displayRole){
        this.displayRole = displayRole;
    }
    public String getDisplayRole(){
        return displayRole;
    }
    public void setDisplayRole(String role){
        this.displayRole = displayRole;
    }
}
