package gersgarage2.GersGarage2.dto;

import gersgarage2.GersGarage2.enumerates.Gender;
import gersgarage2.GersGarage2.enumerates.Role;
import jakarta.validation.constraints.*;


public class ClientDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
            message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character")
    private String password;
    @NotBlank
    private String confirmPassword;
    @NotBlank
    private String phoneNumber;
    @NotNull
    private Gender gender;
    @NotBlank
    private String dob;
    @NotNull
    private Role role;
    private String img;

    public ClientDto(String firstName, String lastName, String email, String password, String confirmPassword, String phoneNumber, Gender gender,String dob, Role role, String img) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dob = dob;
        this.role = role;
        this.img = img;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public String getImg(){ return img;}
    public void setImg(String img) {this.img = img;}
}
